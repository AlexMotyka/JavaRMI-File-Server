import java.io.*;
import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

   private String name;

   public ServerImpl(String s) throws RemoteException{
      super();
      name = s;
   }
   public boolean authenticate(String password){
	   if(password.equals("rmiisawesome")){
		   return true;
	   } else {
		   return false;
	   }
   }
   public byte[] downloadFile(String filename){
	  
      try {
		 // files directory
		 File directory = new File("files");
		 
		 // requested file
         File file = new File(directory, filename);
		 
		 if (file.exists()){
			 // read from the file
			 byte buffer[] = new byte[(int)file.length()];
			 BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
			 input.read(buffer,0,buffer.length);
			 input.close();
			 return(buffer);
		 } else {
			 byte[] buffer = new byte[0];
			 return(buffer);
		 }
         // return(buffer);
      } catch(Exception e){
         System.out.println("ServerImpl: "+e.getMessage());
         e.printStackTrace();
         return(null);
      }
   }
   
   public void uploadFile(String filename, byte[] upFile){
      try {
         
		 // make sure files directory exists
		 File directory = new File("files");
		 directory.mkdirs();
					  
		 // create the file
		 File file = new File(directory, filename);
		 file.createNewFile();
		 
		 // write to the file
		 BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
		 output.write(upFile,0,upFile.length);
		 output.flush();
		 output.close();
		 System.out.println("File uploaded to server succesfully");
      } catch(Exception e){
         System.out.println("ServerImpl: "+e.getMessage());
         e.printStackTrace();
      }
   }
   
   public boolean deleteFile(String filename){
	   // make sure files directory exists
	   File directory = new File("files");
	   directory.mkdirs();
					  
	   // file to be deleted
	   File file = new File(directory, filename);
	   
	   if(file.delete()){
		   System.out.println("File deleted");
		   return true;
	   } else {
		   System.out.println("File did not exist");
		   return false;
	   }
   }
   
   public void writeFile(String filename, String line){
	   try{
		   // make sure files directory exists
	       File directory = new File("files");
	       directory.mkdirs();
					  
	       // file to be written
	       File file = new File(directory, filename);
		   
		   FileWriter writer = new FileWriter(file,true); //true will make the writer append
           writer.write(" "+line + "\n");
           writer.close();
		   System.out.println("Wrote to file.");
	   } catch(IOException e){
		   System.out.println(e);
	   }
   }
   
   public ArrayList<String> listFiles(){
	   ArrayList<String> fileList = new ArrayList<String>();
	   
	   // make sure files directory exists
	   File directory = new File("files");
	   directory.mkdirs();
	   
	   File[] listOfFiles = directory.listFiles();

	   for (int i = 0; i < listOfFiles.length; i++) {
		   fileList.add(listOfFiles[i].getName());
		}
		return fileList;
   }
}
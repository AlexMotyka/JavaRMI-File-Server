import java.io.*; 
import java.rmi.*;
import java.util.Scanner;

public class Client{
   public static void main(String argv[]) {
      try {
		  ServerInterface si = (ServerInterface) Naming.lookup("Server");
		  Scanner userIn = new Scanner(System.in);
		  while(true){
			  System.out.println("Enter a command: ");
			  String input = userIn.nextLine();
			  
			  // split user input to determine what to call
			  String[] tokens = input.split(" ");
			  if (tokens.length<2){
				  System.out.println("Proper usage: java Client [command] [filename] [filename/string]");
				  System.exit(0);
			  } else {
				  // collect tokens
				  String command = tokens[0];
				  String filename = tokens[1];
				  if (tokens.length >= 3){
					  String aux = tokens[2];
				  }
				  if (command.equalsIgnoreCase("download")){
					  // create a file and write to it
					  byte[] filedata = si.downloadFile(filename);
					  File file = new File(filename);
					  BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file.getName()));
					  output.write(filedata,0,filedata.length);
					  output.flush();
					  output.close();
					  System.out.println("File downloaded succesfully"); 
				} else if(command.equals("upload")){
					  System.out.println("Sending file...");
					  File file = new File(filename);
					  byte buffer[] = new byte[(int)file.length()];
					  BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
					  in.read(buffer,0,buffer.length);
					  in.close();
					  si.uploadFile(filename,buffer);
					  System.out.println("File sent.");
				} else if (command.equalsIgnoreCase("delete")){
					boolean deleted = si.deleteFile(filename);
					if (deleted){
						System.out.println("File deleted.");
					} else {
						System.out.println("File did not exist.");
					}
				}
			  }
		  }
      } catch(Exception e) {
         System.err.println("Server exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}
import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

   private String name;

   public ServerImpl(String s) throws RemoteException{
      super();
      name = s;
   }
   
   public String echo(String input) {
	   return input;
   }
   public byte[] downloadFile(String filename){
	  
      try {
         File file = new File(filename);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(filename));
         input.read(buffer,0,buffer.length);
         input.close();
         return(buffer);
      } catch(Exception e){
         System.out.println("ServerImpl: "+e.getMessage());
         e.printStackTrace();
         return(null);
      }
   }
}
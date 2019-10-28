import java.io.*;
import java.rmi.*;

public class Server {
   public static void main(String argv[]) {
      try {
         ServerInterface si = new ServerImpl("Server");
         Naming.rebind("//127.0.0.1/Server", si);
		 System.err.println("Server is ready!");
      } catch(Exception e) {
         System.out.println("Server: "+e.getMessage());
         e.printStackTrace();
      }
   }
}
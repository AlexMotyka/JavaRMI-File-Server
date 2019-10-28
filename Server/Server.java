import java.io.*;
import java.rmi.*;

public class Server {
   public static void main(String argv[]) {
      try {
         Interface inter = new Impl("Server");
         Naming.rebind("//127.0.0.1/Server", inter);
		 System.err.println("Server is ready!");
      } catch(Exception e) {
         System.out.println("Server: "+e.getMessage());
         e.printStackTrace();
      }
   }
}
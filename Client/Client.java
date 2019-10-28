import java.io.*; 
import java.rmi.*;

public class Client{
   public static void main(String argv[]) {
      try {
		  String name = "//" + argv[1] + "/Server";
		  ServerInterface si = (ServerInterface) Naming.lookup(name);
		  String response = si.echo("hello");
		  System.out.println(response);
      } catch(Exception e) {
         System.err.println("Server exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}
import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

   private String name;

   public ServerImpl(String s) throws RemoteException{
      super();
      name = s;
   }
}
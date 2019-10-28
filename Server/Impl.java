import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Impl extends UnicastRemoteObject implements Interface {

   private String name;

   public Impl(String s) throws RemoteException{
      super();
      name = s;
   }
}
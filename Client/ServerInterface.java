import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {
   public String echo(String input) throws RemoteException;
   public byte[] downloadFile(String filename) throws RemoteException;
   public void uploadFile(String filename, byte[] upFile) throws RemoteException;
   public boolean deleteFile(String filename) throws RemoteException;
   public void writeFile(String filename, String line) throws RemoteException;
   public ArrayList<String> listFiles() throws RemoteException;
}
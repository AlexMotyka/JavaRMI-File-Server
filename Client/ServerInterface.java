import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
   public String echo(String input) throws RemoteException;
   public byte[] downloadFile(String filename) throws RemoteException;
   public void uploadFile(String filename, byte[] upFile) throws RemoteException;
}
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ParkingInterface extends Remote {
    String validasiMasuk(String rfid) throws RemoteException;
    String validasiKeluar(String rfid) throws RemoteException;
    int cekSlot() throws RemoteException;
}
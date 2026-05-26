import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ParkingServer extends UnicastRemoteObject implements ParkingInterface {

    private final ParkingData data;

    public ParkingServer() throws RemoteException {
        super();
        data = new ParkingData();
    }

    @Override
    public String validasiMasuk(String rfid) throws RemoteException {
        System.out.println("Request masuk diterima dari RFID: " + rfid);
        return data.prosesMasuk(rfid);
    }

    @Override
    public String validasiKeluar(String rfid) throws RemoteException {
        System.out.println("Request keluar diterima dari RFID: " + rfid);
        return data.prosesKeluar(rfid);
    }

    @Override
    public int cekSlot() throws RemoteException {
        return data.getSlotTersedia();
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            ParkingServer server = new ParkingServer();

            Naming.rebind("rmi://localhost:1099/ParkingService", server);

            System.out.println("===================================");
            System.out.println(" Smart Parking Server Berjalan");
            System.out.println(" RMI Service: ParkingService");
            System.out.println(" Port: 1099");
            System.out.println("===================================");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
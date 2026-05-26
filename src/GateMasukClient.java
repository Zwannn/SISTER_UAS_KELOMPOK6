import java.rmi.Naming;
import java.util.Scanner;

public class GateMasukClient {
    public static void main(String[] args) {
        try {
            ParkingInterface server = (ParkingInterface) Naming.lookup("rmi://localhost:1099/ParkingService");

            Scanner input = new Scanner(System.in);

            System.out.println("===================================");
            System.out.println(" Gate Masuk Smart Parking");
            System.out.println("===================================");

            while (true) {
                System.out.print("Masukkan RFID kendaraan atau ketik exit: ");
                String rfid = input.nextLine();

                if (rfid.equalsIgnoreCase("exit")) {
                    break;
                }

                String hasil = server.validasiMasuk(rfid);
                System.out.println("Hasil Validasi: " + hasil);
                System.out.println("-----------------------------------");
            }

            input.close();
        } catch (Exception e) {
            System.out.println("Gate Masuk gagal terhubung ke server.");
            System.out.println("Pastikan ParkingServer sudah berjalan.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
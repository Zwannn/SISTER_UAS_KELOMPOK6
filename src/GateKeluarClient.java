import java.rmi.Naming;
import java.util.Scanner;

public class GateKeluarClient {
    public static void main(String[] args) {
        try {
            ParkingInterface server = (ParkingInterface) Naming.lookup("rmi://localhost:1099/ParkingService");

            Scanner input = new Scanner(System.in);

            System.out.println("===================================");
            System.out.println(" Gate Keluar Smart Parking");
            System.out.println("===================================");

            while (true) {
                System.out.print("Masukkan RFID kendaraan atau ketik exit: ");
                String rfid = input.nextLine();

                if (rfid.equalsIgnoreCase("exit")) {
                    break;
                }

                String hasil = server.validasiKeluar(rfid);
                System.out.println("Hasil Validasi: " + hasil);
                System.out.println("-----------------------------------");
            }

            input.close();
        } catch (Exception e) {
            System.out.println("Gate Keluar gagal terhubung ke server.");
            System.out.println("Pastikan ParkingServer sudah berjalan.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}   
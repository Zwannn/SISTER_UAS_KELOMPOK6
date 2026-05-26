import java.rmi.Naming;
import java.util.Scanner;

public class GateMasukClient {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println(" Gate Masuk Smart Parking");
        System.out.println("===================================");

        try {
            ParkingInterface server = (ParkingInterface) Naming.lookup("rmi://localhost:1099/ParkingService");

            while (true) {
                System.out.print("Masukkan RFID kendaraan atau ketik exit: ");
                String rfid = input.nextLine();

                if (rfid.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    String hasil = server.validasiMasuk(rfid);
                    System.out.println("Hasil Validasi: " + hasil);
                } catch (Exception e) {
                    System.out.println("Server validation tidak dapat diakses.");
                    System.out.println("Palang masuk tetap tertutup demi keamanan.");
                    System.out.println("Status: AKSES DITOLAK karena server mati atau koneksi terputus.");
                    System.out.println("Error: " + e.getMessage());
                }

                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Server validation tidak dapat diakses.");
            System.out.println("Palang masuk tetap tertutup demi keamanan.");
            System.out.println("Status: AKSES DITOLAK karena server belum berjalan.");
            System.out.println("Pastikan ParkingServer sudah dijalankan.");
            System.out.println("Error: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
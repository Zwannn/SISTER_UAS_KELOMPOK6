import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentAccessTest {
    public static void main(String[] args) {
        try {
            ParkingInterface server = (ParkingInterface) Naming.lookup("rmi://localhost:1099/ParkingService");

            String[] daftarRFID = {
                "RF001", "RF002", "RF003", "RF004", "RF005",
                "RF006", "RF007", "RF008", "RF009", "RF010"
            };

            List<Thread> threads = new ArrayList<>();

            System.out.println("===================================");
            System.out.println(" PENGUJIAN CONCURRENT ACCESS");
            System.out.println(" Simulasi 10 mobil masuk bersamaan");
            System.out.println("===================================");

            for (String rfid : daftarRFID) {
                Thread thread = new Thread(() -> {
                    try {
                        String hasil = server.validasiMasuk(rfid);
                        System.out.println(Thread.currentThread().getName()
                                + " | RFID: " + rfid
                                + " | " + hasil);
                    } catch (Exception e) {
                        System.out.println(Thread.currentThread().getName()
                                + " | RFID: " + rfid
                                + " | Gagal mengakses server: " + e.getMessage());
                    }
                });

                thread.setName("Mobil-" + rfid);
                threads.add(thread);
            }

            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("===================================");
            System.out.println(" Pengujian selesai.");
            System.out.println(" Sisa slot parkir: " + server.cekSlot());
            System.out.println("===================================");

        } catch (Exception e) {
            System.out.println("Gagal terhubung ke ParkingServer.");
            System.out.println("Pastikan ParkingServer sudah berjalan.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
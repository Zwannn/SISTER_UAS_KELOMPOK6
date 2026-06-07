import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentWebTest {

    public static void main(String[] args) {

        try {

            ParkingInterface server =
                    (ParkingInterface) Naming.lookup(
                            "rmi://localhost:1099/ParkingService"
                    );

            String[] daftarRFID = {
                    "RF001",
                    "RF002",
                    "RF003",
                    "RF004",
                    "RF005",
                    "RF006",
                    "RF007",
                    "RF008",
                    "RF009",
                    "RF010"
            };

            List<Thread> threads = new ArrayList<>();

            for (String rfid : daftarRFID) {

                Thread t = new Thread(() -> {

                    try {

                        String hasil =
                                server.validasiMasuk(rfid);

                        synchronized (System.out) {

                            System.out.println(
                                    rfid + "|" + hasil
                            );

                        }

                    } catch (Exception e) {

                        synchronized (System.out) {

                            System.out.println(
                                    rfid + "|ERROR"
                            );

                        }

                    }

                });

                threads.add(t);

            }

            for (Thread t : threads) {
                t.start();
            }

            for (Thread t : threads) {
                t.join();
            }

            System.out.println(
                    "SLOT|" + server.cekSlot()
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR|" + e.getMessage()
            );

        }

    }
}
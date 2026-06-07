import java.util.ArrayList;
import java.util.List;

public class SocketConcurrentTest {

    public static void main(String[] args) {

        System.out.println(
                "===================================="
        );

        System.out.println(
                "SOCKET CONCURRENT ACCESS TEST"
        );

        System.out.println(
                "===================================="
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

        List<Thread> threads =
                new ArrayList<>();

        for (String rfid : daftarRFID) {

            Thread t =
                    new Thread(() -> {

                        try {

                            Process process =
                                    Runtime.getRuntime()
                                            .exec(
                                                    "java SocketClientMasuk "
                                                            + rfid
                                            );

                            process.waitFor();

                            java.io.BufferedReader reader =
                                    new java.io.BufferedReader(
                                            new java.io.InputStreamReader(
                                                    process.getInputStream()
                                            )
                                    );

                            String line;

                            while ((line = reader.readLine()) != null) {

                                synchronized (System.out) {

                                    System.out.println(
                                            rfid + " | " + line
                                    );

                                }

                            }

                        }

                        catch (Exception e) {

                            synchronized (System.out) {

                                System.out.println(
                                        rfid + " | ERROR"
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

            try {

                t.join();

            }

            catch (Exception e) {

                e.printStackTrace();

            }

        }

        System.out.println(
                "\nPENGUJIAN SELESAI"
        );

    }

}
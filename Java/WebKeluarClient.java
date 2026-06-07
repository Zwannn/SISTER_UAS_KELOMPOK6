import java.rmi.Naming;

public class WebKeluarClient {

    public static void main(String[] args) {

        try {

            if(args.length == 0) {
                System.out.println("RFID tidak diberikan");
                return;
            }

            String rfid = args[0];

            ParkingInterface server =
                    (ParkingInterface) Naming.lookup(
                            "rmi://localhost:1099/ParkingService"
                    );

            String hasil = server.validasiKeluar(rfid);

            System.out.println(hasil);

        } catch (Exception e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }
}
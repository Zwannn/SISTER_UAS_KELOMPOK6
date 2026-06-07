import java.rmi.Naming;

public class WebResetClient {

    public static void main(String[] args) {

        try {

            ParkingInterface server =
                (ParkingInterface) Naming.lookup(
                    "rmi://localhost:1099/ParkingService"
                );

            server.resetParking();

            System.out.println(
                "RESET BERHASIL"
            );

        } catch(Exception e) {

            System.out.println(
                "ERROR: " + e.getMessage()
            );

        }

    }

}
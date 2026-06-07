import java.rmi.Naming;

public class WebCekSlotClient {

    public static void main(String[] args) {

        try {

            ParkingInterface server =
                    (ParkingInterface) Naming.lookup(
                            "rmi://localhost:1099/ParkingService"
                    );

            System.out.println(
                    server.cekSlot()
            );

        } catch (Exception e) {

            System.out.println(
                    "OFFLINE"
            );

        }
    }
}
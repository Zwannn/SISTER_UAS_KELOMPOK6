import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientMasuk {

    public static void main(String[] args) {

        if (args.length == 0) {

            System.out.println(
                    "Gunakan: java SocketClientMasuk RF001"
            );

            return;

        }

        String rfid = args[0];

        try {

            Socket socket =
                    new Socket(
                            "localhost",
                            4500
                    );

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true
                    );

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()
                            )
                    );

            out.println(
                    "MASUK:" + rfid
            );

            String response =
                    in.readLine();

            System.out.println(
                    response
            );

            socket.close();

        }

        catch (Exception e) {

            System.out.println(
                    "ERROR: "
                            + e.getMessage()
            );

        }

    }

}
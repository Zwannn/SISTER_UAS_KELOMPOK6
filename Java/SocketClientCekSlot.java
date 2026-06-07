import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientCekSlot {

    public static void main(String[] args) {

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

            out.println("SLOT");

            String response =
                    in.readLine();

            System.out.println(
                    "Slot tersedia: "
                            + response
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
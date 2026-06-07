import java.rmi.Naming;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SocketServer {

        public static void main(String[] args) {

                try {

                        ServerSocket serverSocket = new ServerSocket(4500);

                        System.out.println(
                                        "Socket Server berjalan di port 4500...");

                        while (true) {

                                Socket client = serverSocket.accept();

                                new Thread(
                                                () -> handleClient(client)).start();

                        }

                } catch (Exception e) {

                        e.printStackTrace();

                }

        }

        private static void handleClient(Socket client) {

                try {

                        BufferedReader in = new BufferedReader(
                                        new InputStreamReader(
                                                        client.getInputStream()));

                        PrintWriter out = new PrintWriter(
                                        client.getOutputStream(),
                                        true);

                        String request = in.readLine();

                        System.out.println(
                                        "Request diterima: "
                                                        + request);

                        String response;

                        if (request.startsWith("MASUK:")) {

                                String rfid = request.substring(6);

                                ParkingInterface server = (ParkingInterface) Naming.lookup(
                                                "rmi://localhost:1099/ParkingService");

                                response = server.validasiMasuk(rfid);

                        }


                        else {

                                response = "REQUEST TIDAK DIKENAL";

                        }

                        out.println(response);

                        client.close();

                }

                catch (Exception e) {

                        e.printStackTrace();

                }

        }

}
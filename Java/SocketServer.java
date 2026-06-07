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

                        // =====================
                        // PING
                        // =====================
                        if (request.equals("PING")) {

                                response = "SOCKET_OK";

                        }

                        // =====================
                        // MASUK
                        // =====================
                        else if (request.startsWith("MASUK:")) {

                                ParkingInterface server = (ParkingInterface) Naming.lookup(
                                                "rmi://localhost:1099/ParkingService");

                                String rfid = request.substring(6);

                                response = server.validasiMasuk(rfid);

                        }

                        // =====================
                        // KELUAR
                        // =====================
                        else if (request.startsWith("KELUAR:")) {

                                ParkingInterface server = (ParkingInterface) Naming.lookup(
                                                "rmi://localhost:1099/ParkingService");

                                String rfid = request.substring(7);

                                response = server.validasiKeluar(rfid);

                        }

                        // =====================
                        // SLOT
                        // =====================
                        else if (request.equals("SLOT")) {

                                ParkingInterface server = (ParkingInterface) Naming.lookup(
                                                "rmi://localhost:1099/ParkingService");

                                response = String.valueOf(
                                                server.cekSlot());

                        }

                        else {

                                response = "VALIDATION_SERVER_OFFLINE";

                        }

                        out.println(response);

                        client.close();

                }

                catch (Exception e) {

                        System.out.println(
                                        "[ERROR] Validation Server OFFLINE");

                        e.printStackTrace();

                }

        }

}
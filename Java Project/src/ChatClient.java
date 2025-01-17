import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

        private static final String SERVER_ADDRESS = "localhost"; // Change if needed
        private static final int PORT = 12345;

        public static void main(String[] args) {
            try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
                System.out.println("Connected to the chat server");
                System.out.println("Please start your conversation.....");

                // Thread to read messages from the server
                new Thread(new IncomingMessageHandler(socket)).start();

                // Sending messages to the server
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Connection error: " + e.getMessage());
            }
        }

        // Inner class to handle incoming messages
        private static class IncomingMessageHandler implements Runnable {
            private Socket socket;

            public IncomingMessageHandler(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server: " + e.getMessage());
                }
            }
        }


}

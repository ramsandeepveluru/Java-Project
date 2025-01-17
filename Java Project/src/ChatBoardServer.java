import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatBoardServer{

        private static final int PORT = 12345; // Port number
        private static Set<PrintWriter> clientWriters = new HashSet<>();

        public static void main(String[] args) {
            System.out.println("Chat server started...");
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    new ClientHandler(serverSocket.accept()).start();
                }
            } catch (IOException e) {
                System.out.println("Server error: " + e.getMessage());
            }
        }

        // Inner class to handle each client
        private static class ClientHandler extends Thread {
            private Socket socket;
            private PrintWriter out;
            private BufferedReader in;

            public ClientHandler(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);

                    synchronized (clientWriters) {
                        clientWriters.add(out);
                    }

                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received: " + message);
                        broadcastMessage(message);
                    }
                } catch (IOException e) {
                    System.out.println("Client disconnected: " + e.getMessage());
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("Error closing socket: " + e.getMessage());
                    }
                    synchronized (clientWriters) {
                        clientWriters.remove(out);
                    }
                }
            }

            private void broadcastMessage(String message) {
                synchronized (clientWriters) {
                    for (PrintWriter writer : clientWriters) {
                        writer.println(message);
                    }
                }
            }
        }


}

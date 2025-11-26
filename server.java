import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress( 5000));
            System.out.println("Server started on port " + port + ". Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ) {
            String type = dis.readUTF();

            if (type.equals("message")) {
                while (true) {
                    String msg = dis.readUTF();
                    System.out.println("Client: " + msg);

                    if (msg.equalsIgnoreCase("bye")) {
                        dos.writeUTF("Goodbye! Connection closed.");
                        System.out.println("Client ended the chat.");
                        break;
                    }

                    // Respond back
                    dos.writeUTF("Received: " + msg);
                }
            } else if (type.equals("file")) {
                String fileName = dis.readUTF();
                long fileSize = dis.readLong();
                File file = new File("received_" + fileName);

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] buffer = new byte[4096];
                    int read;
                    long totalRead = 0;

                    while ((read = dis.read(buffer, 0, Math.min(buffer.length, (int)(fileSize - totalRead)))) > 0) {
                        totalRead += read;
                        fos.write(buffer, 0, read);
                        if (totalRead >= fileSize) break;
                    }

                    System.out.println("File received: " + file.getAbsolutePath());
                }

                dos.writeUTF("File received successfully.");
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }
}

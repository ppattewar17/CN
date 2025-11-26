import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class trignoclient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            byte[] sendBuffer = new byte[1024];
            byte[] receiveBuffer = new byte[1024];

            System.out.println("Enter operation (sin, cos, tan) or 'exit' to quit:");
            String operation = sc.nextLine();
            sendBuffer = operation.getBytes();
            DatagramPacket opPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9876);
            socket.send(opPacket);

            if (!operation.equalsIgnoreCase("exit")) {
                System.out.println("Enter angle in degrees:");
                String angle = sc.nextLine();
                sendBuffer = angle.getBytes();
                DatagramPacket anglePacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9876);
                socket.send(anglePacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server says: " + result);
            }

            socket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
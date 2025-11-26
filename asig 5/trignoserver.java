// UDPServer.java
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class trignoserver {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            System.out.println("UDP Server is running...");
            while (true) {
                DatagramPacket opPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(opPacket);
                String operation = new String(opPacket.getData(), 0, opPacket.getLength()).trim();

                DatagramPacket anglePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(anglePacket);
                String angleStr = new String(anglePacket.getData(), 0, anglePacket.getLength()).trim();

                double angle = Double.parseDouble(angleStr);
                double result = 0;

                switch (operation.toLowerCase()) {
                    case "sin": result = Math.sin(Math.toRadians(angle)); break;
                    case "cos": result = Math.cos(Math.toRadians(angle)); break;
                    case "tan": result = Math.tan(Math.toRadians(angle)); break;
                    default:
                        System.out.println("Unknown operation received: " + operation);
                        result = Double.NaN;
                }
                String reply = "Result: " + result;
                InetAddress clientAddress = opPacket.getAddress();
                int clientPort = opPacket.getPort();

                sendBuffer = reply.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);

                System.out.println("Computed " + operation + "(" + angle + ") = " + result);
                if (operation.equalsIgnoreCase("exit")) {
                    System.out.println("Server shutting down...");
                    break;
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
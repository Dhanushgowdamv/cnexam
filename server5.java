import java.io.*;
import java.net.*;

public class server5 {

    // Helper method to find the index of a string in an array
    private static int indexOf(String[] array, String str) {
        str = str.trim(); // Remove any extra whitespace
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str)) {
                return i; // Return the index if found
            }
        }
        return -1; // Return -1 if not found
    }

    public static void main(String[] args) throws IOException {
        String[] hosts = { "yahoo.com", "gmail.com", "cricinfo.com", "facebook.com" };
        String[] ip = { "68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16" };

        System.out.println("DNS Server is running. Press Ctrl + C to quit.");

        DatagramSocket serverSocket = new DatagramSocket(1362); // Server socket on port 1362

        while (true) {
            try {
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

                // Receive packet from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String receivedHost = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Request for host: " + receivedHost);

                // Determine the response
                String response;
                int index = indexOf(hosts, receivedHost);
                if (index != -1) {
                    response = ip[index]; // IP found
                } else {
                    response = "Host Not Found"; // IP not found
                }

                sendData = response.getBytes();

                // Send response to client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

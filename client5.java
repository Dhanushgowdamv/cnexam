import java.io.*;
import java.net.*;

public class client5 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress ipAddress;
        if (args.length == 0) {
            ipAddress = InetAddress.getLocalHost(); // Default to localhost
        } else {
            ipAddress = InetAddress.getByName(args[0]); // Use provided IP address
        }

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        int portAddr = 1362;

        System.out.print("Enter the hostname: ");
        String sentence = br.readLine();
        sendData = sentence.getBytes();

        // Send request to the server
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portAddr);
        clientSocket.send(sendPacket);

        // Receive response from the server
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("IP  lsjdfnglsdkfxbf Address: " + response);

        clientSocket.close(); // Close the socket
    }
}

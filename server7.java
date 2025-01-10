import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server7 {
    public static void main(String[] args) {
        final int basePort = 9040;
        final int handshakePort = 8070;
        final String host = "localhost";

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of frames: ");
            int numberOfFrames = scanner.nextInt();

            if (numberOfFrames == 0) {
                System.out.println("No frames to send.");
                return;
            }

            // Send the total number of frames to the receiver
            try (Socket handshakeSocket = new Socket(host, handshakePort);
                 DataOutputStream out = new DataOutputStream(handshakeSocket.getOutputStream())) {
                out.writeInt(numberOfFrames);
            }

            // Send each frame
            for (int i = 0; i < numberOfFrames; i++) {
                System.out.print("Enter message for frame " + i + ": ");
                String message = scanner.next();
                System.out.println("Sending Frame " + i);

                try (Socket frameSocket = new Socket(host, basePort + i);
                     DataOutputStream frameOut = new DataOutputStream(frameSocket.getOutputStream());
                     DataInputStream frameIn = new DataInputStream(frameSocket.getInputStream())) {

                    frameOut.writeUTF(message);
                    int ack = frameIn.readInt(); // Receive acknowledgment
                    System.out.println("Acknowledgment received for Frame " + ack);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

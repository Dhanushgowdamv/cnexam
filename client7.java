import java.io.*;
import java.net.*;

public class client7 {
    public static void main(String[] args) {
        final int basePort = 9040;
        final int handshakePort = 8070;

        try (ServerSocket handshakeServer = new ServerSocket(handshakePort)) {
            System.out.println("Receiver is waiting for connection...");

            // Receive the total number of frames
            try (Socket handshakeSocket = handshakeServer.accept();
                 DataInputStream in = new DataInputStream(handshakeSocket.getInputStream())) {

                int numberOfFrames = in.readInt();
                System.out.println("Number of frames to receive: " + numberOfFrames);

                // Receive each frame
                for (int i = 0; i < numberOfFrames; i++) {
                    try (ServerSocket frameServer = new ServerSocket(basePort + i);
                         Socket frameSocket = frameServer.accept();
                         DataInputStream frameIn = new DataInputStream(frameSocket.getInputStream());
                         DataOutputStream frameOut = new DataOutputStream(frameSocket.getOutputStream())) {

                        String message = frameIn.readUTF(); // Receive the frame data
                        System.out.println("Frame " + i + " received: " + message);

                        frameOut.writeInt(i); // Send acknowledgment
                        System.out.println("Acknowledgment sent for Frame " + i);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

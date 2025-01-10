import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class clinet4 {
    public static void main(String[] args) {
        Socket c =null;
        String line;
        DataInputStream is,is1;
        PrintStream ps;
        
        try {
            InetAddress ia = InetAddress.getLocalHost();
            c = new Socket(ia ,9000);
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e);

        }

        try {
            ps = new PrintStream(c.getOutputStream());
            is = new DataInputStream(System.in);
            is1 =new DataInputStream(c.getInputStream());
            while(true){
                System.out.println("Client");
                line = is.readLine();
                ps.println(line);
                System.out.println ( "server"+is1.readLine());
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("socket is closed");
        }
    }
    
}

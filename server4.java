import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server4 {
    public static void main(String[] args) {
        ServerSocket s=null;
        String line;
        DataInputStream is;
        PrintStream ps;
        Socket c=null;
        try{
            s=new ServerSocket(9000);

        }catch(IOException e){
            System.out.println(e);
        }

        try {
            c =s.accept();
            is = new DataInputStream(c.getInputStream());
            ps = new PrintStream(c.getOutputStream());
            while(true){
                line = is.readLine();
                ps.println(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        
    }
}

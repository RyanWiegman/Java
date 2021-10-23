import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    private final int BUFFER_SIZE = 4096;
    private Socket connection;
    private DataInputStream socketIn;
    private DataOutputStream socketOut;
    private int bytes;
    private byte[] buffer = new byte[BUFFER_SIZE];
    PrintWriter pw;
    String buffer_string, buffer_length;
    StringBuffer sb = new StringBuffer();

    // NORMAL INPUT
    public Client(String host, int port, String filename) {
        try {
            connection = new Socket(host, port);
            pw = new PrintWriter("output.txt");

            socketIn = new DataInputStream(connection.getInputStream()); // Read data from server
            socketOut = new DataOutputStream(connection.getOutputStream()); // Write data to server

            socketOut.writeUTF(filename); // Write filename to server
            
            // Read file contents from server
            while (true) {
                bytes = socketIn.read(buffer, 0, BUFFER_SIZE); // Read from socket
                if (bytes <= 0) break; // Check for end of file
                System.out.println(bytes);

                for(byte b : buffer){
                    if(b == '\n'){
                        buffer_string = sb.toString();
                        break;
                    }
                    sb.append((char)b);
                }

                System.out.print(new String(buffer, StandardCharsets.UTF_8)); // Write to standard output
                System.out.println("buffer_string: " + buffer_string);
                pw.println(buffer_string);
            }

            pw.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public int byteLength(byte[] buffer) {
        sb.delete(0, buffer.length);

        for(byte b : buffer){
            if(b == '\n'){
                buffer_length = sb.toString();
                break;
            }
            sb.append((char)b);
        }

        return buffer_length.length();
    }

    // SPECIFIC BYTE INPUT.
    public Client(String host, int port, String[] args) {
        int count = 0;
        for(String s : args){
            System.out.println(count + " " + s);
            count++;
        }
        int start_byte = Integer.parseInt(args[2]);
        int end_byte = Integer.parseInt(args[4]);

        try {
            connection = new Socket(host, port);
            pw = new PrintWriter("output.txt");

            socketIn = new DataInputStream(connection.getInputStream()); // Read data from server
            socketOut = new DataOutputStream(connection.getOutputStream()); // Write data to server

            socketOut.writeUTF(args[0]); // Write filename to server
            
            // Read file contents from server
            while (true) {
                bytes = socketIn.read(buffer, 0, BUFFER_SIZE); // Read from socket
                if (bytes <= 0) break; // Check for end of file

                System.out.println("bytes: " + bytes + " buffer: " + buffer);
                System.out.println("start: " + start_byte + " end_byte: " + end_byte);
                
                for(int index = start_byte; index <= end_byte; index++){
                    System.out.println("index: " + index);
                    sb.append((char)buffer[index]);
                }
                buffer_string = sb.toString();

                System.out.println("buffer_string_length: " + byteLength(buffer));
                if(end_byte < start_byte || start_byte < 1 || end_byte > byteLength(buffer)){
                    System.out.println("Invalid bytes entered.");
                    break;
                }

                System.out.print(new String(buffer, StandardCharsets.UTF_8)); // Write to standard output
                System.out.println("buffer_string: " + buffer_string);
                pw.println(buffer_string);
            }

            pw.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void main(String[] args) {
        Client client;

        if(args.length == 1)
            client = new Client("127.0.0.1", 5000, args[0]);
        if(args.length > 1)
        client = new Client("127.0.0.1", 5000, args);
    }
}

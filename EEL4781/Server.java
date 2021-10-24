import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int BUFFER_SIZE = 4096;
    private Socket connection;
    private ServerSocket socket;
    private DataInputStream socketIn;
    private DataOutputStream socketOut;
    private FileInputStream fileIn;
    private String filename, add_to_file;
    private int bytes;
    private byte[] buffer = new byte[BUFFER_SIZE];
    StringBuffer file_sb = new StringBuffer();
    PrintWriter pw;
    boolean flag = false;

    // CHECK FILE EXISTENCE.
    public void check_file_exist(){
        try {
            File new_file = new File(filename);
            if(new_file.createNewFile()){
                System.out.println(filename + " created.");
                flag = true;
            }
            else{
                System.out.println(filename + " already exsists.");
                flag = false;
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }                 
    }

    public Server(int port, int debugger) {
        try {
            socket = new ServerSocket(port);

            // Wait for connection and process it
            while (true) {
                try {
                    connection = socket.accept(); // Block for connection request

                    socketIn = new DataInputStream(connection.getInputStream()); // Read data from client
                    socketOut = new DataOutputStream(connection.getOutputStream()); // Write data to client

                    filename = socketIn.readUTF(); // Read filename from client
                    check_file_exist();
                    fileIn = new FileInputStream(filename);

                    // Write file contents to client
                    while (true) {
                        bytes = fileIn.read(buffer, 0, BUFFER_SIZE); // Read from file
                        if (bytes <= 0){ // Check for end of file
                            break;
                        }

                        // WRITE FILE TO SERVER
                        if(flag){
                            pw = new PrintWriter(filename);

                            for(byte b : buffer){
                                if(b == '\n'){
                                    add_to_file = file_sb.toString();
                                    break;
                                }
                                file_sb.append((char)b);
                            }
                            pw.println(add_to_file);
                        }

                        // PRINT DEBUGGER MSG
                        if(debugger == 1){
                            System.out.println("Sending " + filename + " to " + connection.getRemoteSocketAddress());
                            
                            double interval = .1;
                            int percent = 10;
                            for(int data_perc = 0; data_perc < bytes; data_perc++){
                                if(data_perc >= (bytes * interval)){
                                    System.out.println("Sent " + percent + "% of " + filename);
                                    interval += .1;
                                    percent += 10;
                                }     
                            }
                        }
                        
                        socketOut.write(buffer); // Write bytes to socket
                        
                        if(debugger == 1)
                            System.out.println("Finished sending " + filename + " to " + connection.getRemoteSocketAddress());
                    }
                } catch (Exception ex) {
                    String error = "File Not Found.";

                    System.out.println("Error: " + ex);
                    socketOut.writeChars(error);
                } finally {
                    // Clean up socket and file streams
                    if (connection != null) {
                        connection.close();
                    }

                    if (fileIn != null) {
                        fileIn.close();
                    }
                }
            }
        } catch (IOException i) {
            System.out.println("Error: " + i);
        }
    }

    public static void main(String[] args) {
        int port = 5000;
        int debugger = 0;

        if(args.length != 0){
            if(args[0].equals("DEBUG=1"))
                debugger = 1;
        }

        Server server = new Server(port, debugger);
    }
}

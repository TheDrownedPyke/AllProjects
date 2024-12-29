package plzwork;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
/**
 * Method for Server
 */
public class Server implements Runnable {
/**
 * Declaring variable
 */
    private ServerSocket serverSocket;
    /**
     * Declaring variable
     */
    private List<ClientHandler>CLH= new ArrayList<>();
    /**
     * Declaring variable
     */
    int port;
/**
 * method initializaing Server
 * @param port port of where the server will be hosted
 */
    public Server(int port) {
        this.port = port;
        System.out.println("Server starting on port: " + port);
    }

    @Override
    /**
     * Method for running the server
     */
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
      
                sendMessageToAllClients("test");
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket,this)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
/**
 * class that will handle the clients
 */
    private class ClientHandler implements Runnable {
    	/**
    	 * Declaring variable
    	 */
        private Socket clientSocket;
        /**
         * Declaring variable
         */
        private BufferedReader in;
        /**
         * Declaring variable
         */
        private PrintWriter out;
        /**
         * Declaring variable
         */
        private Server server; 
/**
 * Method initializing  ClientHandler
 * @param clientSocket Socket of the clint
 * @param server Server being connect 2
 */
        public ClientHandler(Socket clientSocket,Server server) {
            this.clientSocket = clientSocket;
            this.server=server;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        /**
         * method for running the main parts of the ClientHandler
         */
        public void run() {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    // Process input and send response
                	if(inputLine.startsWith("CHAT:")) 
                	{
                        server.bcm(inputLine); // Broadcast chat message

                	}
                    System.out.println("Received from client: " + inputLine);
                    out.println("Echo: " + inputLine); // Example echo
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    server.CLH.remove(this); // Remove this client handler from the list
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /**
     * method for taking messages from all the clients
     * @param message message being sent
     */
    public synchronized void bcm(String message) 
    {
    	
    	for (ClientHandler ch : CLH) {
            ch.out.println(message);
        }
    }
    /**
     * method for sending message to clients form server
     * @param message message being sent
     */
    public synchronized void sendMessageToAllClients(String message) {
        for (ClientHandler ch : CLH) {
            ch.out.println("Server Message: " + message);
        }
    }

    
    
}




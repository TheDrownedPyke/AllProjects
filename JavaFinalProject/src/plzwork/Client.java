package plzwork;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Class that handles all clients
 */
public class Client {
	/**
	 * Declaring variable
	 */
	private Socket socket;
	/**
	 * Declaring variable
	 */
	private BufferedReader  input;
	/**
	 * Declaring variable
	 */
	private PrintWriter  output;
	/**
	 * Declaring variable
	 */
	private ConnectFour c4;
	
	
	/**
	 * Clint method
	 * @param ip ip of the server to connect 2
	 * @param port port of the server
	 * @param c4 connect 4 instance
	 */
	public Client(String ip,int port,ConnectFour c4) 
	{
		this.c4=c4;
        System.out.print("Attempting connection...");

		try {
		socket= new Socket(ip,port);
        JOptionPane.showInputDialog(this,"Conneced to server at"+ip+":"+port);
		System.out.print(socket);

        input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output= new PrintWriter(socket.getOutputStream(),true);
        
        new Thread(()->
        { 
        	try 
        	{
        		String fromserver;
        		while((fromserver=input.readLine())!=null) 
        		{
                    System.out.println("Server: " + fromserver);
                    	if(fromserver.startsWith("CHAT:")) 
                    	{
                    		String cmsg=fromserver.substring(5);
                    		SwingUtilities.invokeLater(() -> {
                                c4.outputArea.append(cmsg + "\n");
                            });                    	}
        		}
        	}catch(Exception e) 
        	{
                e.printStackTrace();

        	}
        	
        }).start();;
		}catch(IOException e) 
		{
			System.out.print("no connections ");

			e.printStackTrace();

		}
		
	
	}
	/**
	 * Method to send messages
	 * @param message message being sent
	 */
	public void sendmessage(String message) 
	{
		output.println(message);
		
	}
	/**
	 * Sending the output message to server
	 * @param message message being sent
	 */
	public void ChatSendmessage(String message) 
	{
		output.println("CHAT:"+message);
		
	}
	

}

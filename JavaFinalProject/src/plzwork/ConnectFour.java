package plzwork;


import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.net.URL;
/**
 * Author: Dereck Rock
 * student Number 041049245
 * Course:CST8221
 * Due: Feb 18th 2024
 */

/**
 * A JPanel with a background image 
 */

@SuppressWarnings("serial")
class BackgroundPanel extends JPanel {
    

	/**
	 * Declaring the background image of image type
	 */
    private Image backgroundImage;
/**
 * Constructs a BackgroundPanel with the specified image path
 * @param imagePath plath to the image
 */
    public BackgroundPanel(String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

    	backgroundImage = icon.getImage();
    }

    /**
     * Overrides the paintComponet method to draw the background image
     * @param g The Graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
/**
 * A JPanel Representing the game area
 */
class GamePanel extends BackgroundPanel {
	/**
 * Constructs a GamePanel with the specified image path
	 * @param imagePath The path to the background image
	 */
    public GamePanel(String imagePath) {
        super(imagePath);
        setLayout(new GridLayout(1,1));
    }


}
/**
 * A JPanel displaying player info and game time
 */
class PlayerInfoPanel extends JPanel {
	/**
	 * Declaring JTextArea variable for player info
	 */
    private JTextArea playerInfo;
	/**
	 * Declaring JLabel variable for time
	 */
    public JLabel timeLabel;
	/**
	 * Declaring JLabel variable for time
	 */
    public JLabel timeLabel2;
	/**
	 * Declaring variables to hold when the timer started 
	 */
    public long startTime;
	/**
	 * Declaring variable to hold time elapsed
	 */
    public long elapsedTime;
	/**
	 * Declaring variables
	 */
    private String playerName;
	/**
	 * Declaring variables
	 */
    private String playerName2;
	/**
	 * Declaring variables
	 */
    private ConnectFour connectFour;
	/**
	 * Declaring variables
	 */
    private long TStartTime;




    /**
 * Constructs a PlayerInfoPanel with the specified player name 
     * @param playerName player 1 name
     * @param playerName2 player 2 name
     * @param connectFours Connect 4 instance
     */
    public PlayerInfoPanel(String playerName,String playerName2,ConnectFour connectFours) {
    	this.playerName=playerName;
    	this.playerName2=playerName2;
    	this.connectFour=connectFours;



        setLayout(new BorderLayout());
        setOpaque(false); // Make the panel transparent

        // Create a panel to hold the arrow and playerInfo components
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0)); // Horizontal alignment
        contentPanel.setOpaque(false);
        // Add the arrow label
        JLabel arrowLabel = new JLabel("\u2192"); // Unicode for left arrow
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 24));
        arrowLabel.setForeground(Color.RED);
        arrowLabel.setOpaque(false);

        // Add the playerInfo text area
        playerInfo = new JTextArea(playerName, 1, 20);
        playerInfo.setEditable(false);
        playerInfo.setForeground(Color.BLACK);
        playerInfo.setFont(new Font("Arial", Font.BOLD, 16));
        playerInfo.setOpaque(false);


        // Add components to the contentPanel
        contentPanel.add(arrowLabel);
        contentPanel.add(playerInfo);

        // Add the contentPanel to the PlayerInfoPanel
        add(contentPanel, BorderLayout.CENTER);
        JPanel timerPanel= new JPanel(new GridLayout(2,1));
        timerPanel.setOpaque(false);
        timeLabel = new JLabel("0",SwingConstants.CENTER);
        timeLabel.setForeground(Color.black);
        
        
        timeLabel2 = new JLabel("0",SwingConstants.CENTER);
        timeLabel2.setForeground(Color.black);
        
        timerPanel.add(timeLabel,BorderLayout.CENTER);
        timerPanel.add(timeLabel2);

        add(timerPanel,BorderLayout.SOUTH);

        
        startTime=System.currentTimeMillis();
        TStartTime=System.currentTimeMillis();

        /**
         * Creating a New timer instance
         */
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            /**
             * Checks for action
             * @param e event variable
             */
            public void actionPerformed(ActionEvent e) {
                updateTime();
                turnTime();
            }
        });
        timer.start();
        
 
    }
    /**
     * Updates the time labels with elapsed time
     */
    private void updateTime() {
        long currentTime = System.currentTimeMillis();
         elapsedTime = (currentTime - startTime) / 1000; // Convert to seconds
        timeLabel.setText("\uD83D\uDD70\uFE0F " + elapsedTime + "s");
    } 
    /**
     * Method to count player timer
     */
    private void turnTime() {
        long currentTime = System.currentTimeMillis();
         long Ttime = (currentTime - TStartTime) / 1000; // Convert to seconds
        timeLabel2.setText("\uD83D\uDD5B" + Ttime + "s");

    } 
    /**
     * Creating a method that updates the player name by the turn
     * @param isplayer the current player boolean
     */
    public void updatePlayerName(boolean isplayer) 
    {
    	String currentp= isplayer? this.playerName:this.playerName2;
    	playerInfo.setText(currentp);
    	
    }
    /**
     * Creating a method to reset the timer
     */
    public void resetTT()

    {
    	this.TStartTime=System.currentTimeMillis();
    	turnTime();
    }
    
    }
/**
 * A JFrame representing the connect Four Game
 */
public class ConnectFour extends JFrame{
	/**
	 * declaring variables
	 */
	private int[][] gameState = new int[6][7];
	/**
	 * declaring variables
	 */
    final MainFrame mainFrame = new MainFrame();
	/**
	 * declaring variables
	 */
    final HelpScreen helpscreen=new HelpScreen(mainFrame);
	/**
	 * declaring variables
	 */
    public JTextArea  outputArea;
	/**
	 * declaring variables
	 */
    private JMenuBar topMenu;
	/**
	 * declaring variables
	 */
    private JMenu fileMenu;
	/**
	 * declaring variables
	 */
    private JMenuItem exitMenuItem;
	/**
	 * declaring variables
	 */
    private JMenuItem helpMenuItem;
	/**
	 * declaring variables
	 */
    private JMenuItem online;
	/**
	 * declaring variables
	 */
    private JMenuItem French;
	/**
	 * declaring variables
	 */
    private JButton submitButton;
	/**
	 * declaring variables
	 */
    private JLabel titleL;
	/**
	 * declaring variables
	 */
    private JPanel ButtonPanel;
	/**
	 * declaring variables
	 */
    private PlayerInfoPanel playerInfoPanel;
	/**
	 * declaring variables
	 */
    private int bwid;
	/**
	 * declaring variables
	 */
    private int bhid;
	/**
	 * declaring variables
	 */
    private ImageIcon icon2;
    /**
	 * declaring variables
	 */
    private JButton[][] buttons; // Array to hold the buttons representing the Connect Four grid
    /**
	 * declaring variables
	 */
    private boolean player1Turn = true; // Indicates whether it's Player 1's turn or not
    /**
   	 * declaring variables
   	 */
    private JMenuItem error;
    /**
   	 * declaring variables
     */
    private String player1;
    /**
   	 * declaring variables
   	 */
    private  Client cl;
    /**
   	 * declaring variables
   	 */
    private int m=1;
    /**
   	 * declaring variables
   	 */
    private Server serv;
    /**
   	 * declaring variables
   	 */
    private String player2;
    /**
     * Constructs a Connect Four game
     */
	 public ConnectFour() {
	        setTitle("Connect 4 ");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setSize(1080, 900);//Check
	        player1=JOptionPane.showInputDialog(this,"Enter your name player1/Entrez votre nom joueur1:\n"
	        		+ "(Click the surprise button in the file options/Cliquez sur le bouton surprise dans les options du fichier)");
	        player2=JOptionPane.showInputDialog(this,"Enter your name player2/Entrez votre nom joueur2:\n"
	        		+ "(Click the surprise button in the file options/Cliquez sur le bouton surprise dans les options du fichier)");
		    JOptionPane.showMessageDialog(this,"For full effect full screen this application/Pour un plein effet en plein écran, cette application\n"
		    		+ "(Click the surprise button in the file options/Cliquez sur le bouton surprise dans les options du fichier)");

	        mainFrame.dispose();
	        topMenu = new JMenuBar() ;
	       
	        topMenu.setBorder(BorderFactory.createEmptyBorder());
	        topMenu.setBackground(Color.PINK);
	        fileMenu = new JMenu("File");
	        exitMenuItem = new JMenuItem("Exit");
	        helpMenuItem = new JMenuItem("Help");
	        online = new JMenuItem("Online");
	        French= new JMenuItem("French");
	        error=new JMenuItem("\u274C (click this button for a fun surprise)");

	        fileMenu.setActionCommand("Filec");
	        exitMenuItem.setActionCommand("ExitC");
	        helpMenuItem.setActionCommand("HelpC");

	        online.setActionCommand("online");
	        French.setActionCommand("French");





	        JPanel mainPanel = new BackgroundPanel("/PNGS/forest.jpg");
	        JPanel gamePanel = new GamePanel("/PNGS/connect4gameboard.jpg");
	        JPanel leftPanel = new JPanel();
	        leftPanel.setBackground(Color.green);
	        
	        JTextField textField = new JTextField(15);
	        textField.setForeground(Color.red);
	        submitButton = new JButton("Submit");
	        JPanel inputPanel = new JPanel(new FlowLayout());
	        inputPanel.setOpaque(false);
	        JPanel topleftPanel = new JPanel();
	        topleftPanel.setBackground(Color.GREEN);

	        
	        outputArea = new JTextArea(33,30);
	        outputArea.setForeground(Color.white);
	        outputArea.setEditable(false); // Make it non-editable
	        outputArea.setBackground(Color.BLUE);
	        playerInfoPanel = new PlayerInfoPanel(player1,player2,this);

	        setJMenuBar(topMenu);
	        topMenu.add(fileMenu);
	        fileMenu.add(exitMenuItem);
	        fileMenu.add(helpMenuItem);
	        fileMenu.add(online);
	        fileMenu.add(French);
	        fileMenu.add(error);
	        mainPanel.add(gamePanel);
	        ImageIcon icon= new ImageIcon(getClass().getResource("/PNGS/connect4B.png"));
	
	  
	      icon2= new ImageIcon(getClass().getResource("/PNGS/connect4BB.png"));
	         

	        JPanel connect4Board=new JPanel(new GridLayout(6,7));
	        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
	        int maxW= (int)screenSize.getWidth();
	        int maxH= (int)screenSize.getHeight();
	        
	        double bmaxW= .08;
	        double bmaxH= .125;
	         bwid= (int)(maxW*bmaxW);
	         bhid= (int)(maxH*bmaxH);
	 
	        Image image = icon.getImage().getScaledInstance(bwid, bhid, Image.SCALE_SMOOTH);
	        buttons= new JButton[6][7];
	        for (int i = 0; i < 6; i++) {
	            for (int j = 0; j < 7; j++) {
	            	icon= new ImageIcon(image);
	                buttons[i][j] = new JButton(icon);
	                buttons[i][j].setPreferredSize(new Dimension(bwid,bhid));
	                buttons[i][j].setBorderPainted(false);
	                buttons[i][j].setContentAreaFilled(false);
	                buttons[i][j].setOpaque(false);
	               // buttons[i][j].addActionListener(new ButtonClickListener(j));
	                connect4Board.add(buttons[i][j]);
	            }
	        }
	        for (int i = 0; i < 6; i++) {
	            for (int j = 0; j < 7; j++) {
	                gameState[i][j] = 0; // Mark as empty
	            }
	        }
	        gamePanel.setLayout(new BorderLayout());
	        gamePanel.add(connect4Board, BorderLayout.CENTER);
	     // Add another row of buttons slightly above the Connect 4 buttons
	        ButtonPanel = new JPanel(new GridLayout(1, 7));
	        for (int j = 0; j < 7; j++) {
	            JButton button = new JButton("\u21E9");
	            button.setBackground(Color.MAGENTA);
	            //button.setText("\u2193");
	            button.addActionListener(new DropButtonListener(j));
	            
	            // Add your icon or text to the button
	            ButtonPanel.add(button);
	        }
	        
	        
	        gamePanel.add(ButtonPanel, BorderLayout.NORTH);
	        
	        JPanel centerPanel = new JPanel(new BorderLayout());
	        centerPanel.setBackground(Color.BLUE);
	        centerPanel.add(mainPanel, BorderLayout.CENTER);
	        centerPanel.add(leftPanel, BorderLayout.EAST); 

	        int leftPanelWidth = (int) (getWidth() * 0.3);
	        leftPanel.setPreferredSize(new Dimension(leftPanelWidth, getHeight()));
	        leftPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

	        inputPanel.add(textField,BorderLayout.CENTER);
	        inputPanel.add(submitButton, BorderLayout.EAST);
	        leftPanel.add(inputPanel, BorderLayout.NORTH);
	        leftPanel.add(playerInfoPanel, BorderLayout.CENTER);		

	     addComponentListener(new ComponentAdapter()
	     {
	    	 /**
	    	  * Declaring a componentResized to check current window size
	    	  * @param e calls a printwindow size
	    	  */
	    	 public void componentResized(ComponentEvent e) 
	    	 {
	    		 printWindowSize();
	    	 }
	    	 
	     });
	     /**
	      * Creating an Event when the button is clicked
	      */
	     exitMenuItem.addActionListener(new ActionListener()
	    		 {
	    	 		@Override
	                /** Checks if an action has been performed on the exitMenuItem Button
	    	 		 * 
	    	 		 * @param e event variable
	    	 		 */
	    	 		public void actionPerformed(ActionEvent e) 
	    	 		{
	    	 	        dispose();
	    	 		}
	    		 }
	    		 );
	     /**
	      * Creating an Event when the button is clicked
	      */
	     helpMenuItem.addActionListener(new ActionListener()
		 {
	 		@Override
            /** Checks if an action has been performed on the helpMenuIItem Button
	             * @param e event variable
	 			 */
	 		public void actionPerformed(ActionEvent e) 
	 		{
	 			startConnectFourHelp();
	 	        dispose();
	 		}
		 }
		 );
	     /**
	      * ActionListener for the submit button
	      */
	        submitButton.addActionListener(new ActionListener() {
	            @Override
	            /**
	             * creates text area that will allow for input
	             * @param e creates text area that will allow for input
	             */
	            
	            public void actionPerformed(ActionEvent e) {
	                String inputText = textField.getText();
	                outputArea.append(inputText + "\n"); // Append text to the JTextArea
	                textField.setText(""); 
	                if(!inputText.trim().isEmpty()&&m==1) 
	                {
	                	cl.ChatSendmessage(inputText);
		                textField.setText(""); 
	                }
	                else if(!inputText.trim().isEmpty()&&m==2) 
	                {
	                
	                }
	            }
	        });
	        error.addActionListener(new ActionListener() 
	        {
	        	 @Override
		            /**
		             * creates text area that will allow for input
		             * @param e creates text area that will allow for input
		             */
		            public void actionPerformed(ActionEvent e) {
	        	        JOptionPane.showInputDialog(null,"Hi, I hope this finds you well, You are probably wondering why \n"
	        	        		+ "I did this whole other project rather than fix the issues with my main and the simple answer\n"
	        	        		+ " is that i have made a snake game in every language that I know so I did this in less than 2 \n"
	        	        		+ " hour which is more than I can say for how long it would take to fix the issues with my java. \n"
	        	        		+ " I hope you enjoy playing this mini-game inside of my main game. Please Enjoy \n"
	        	        		+ "(: bonus marks would be appreciated but not necessary (please)\n"
	        	        		+ "(did however get some help from this sight: https://stackoverflow.com/questions/18951124/an-example-of-the-use-of-the-point-class )\n"
	        	        		+ "(click ok)");
		            JFrame snake= new JFrame("Snake game");
		            bonus gamePanel = new bonus(snake);
		            snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            snake.setResizable(false);
		            snake.add(gamePanel, BorderLayout.CENTER);
		            snake.pack();
		            snake.setLocationRelativeTo(null);
		            snake.setVisible(true);	        		 
		            dispose();	        		 
		            }
	        	
	        });
	        /**
	         * Creating an ActionListsener for when he button is clicked
	         */
	        French.addActionListener(new ActionListener() {
	        	private boolean test=true;

	            @Override
	            /** Checks if an action has been performed
	             * 
	             * @param e event variable
	             */
	            
	            public void actionPerformed(ActionEvent e) {

	            		
	                	if(test) {
	                	translateToFrench();
	                	test=false;
	                
	                	}
	                	else 
	                	{
	                		translateToEnglish(); 
	                		test=true;
	                	}
	                
	            }
	        });
	        online.addActionListener(new ActionListener() {
	            @Override
	            /**
	             * creates text area that will allow for input
	             * @param e creates text area that will allow for input
	             */
	            public void actionPerformed(ActionEvent e) {
	            	onlinebutton();
	            }
	        });
	        // Add mainPanel to the frame
	        getContentPane().add(centerPanel, BorderLayout.CENTER);
	        titleL= new JLabel("Connect 4",SwingConstants.CENTER);
	        titleL.setFont(new Font("Arial",Font.BOLD,40));
	        titleL.setBackground(Color.pink);
	        titleL.setOpaque(true); // Ensure that the label is opaque to show its background color
	        
	        getContentPane().add(titleL,BorderLayout.NORTH);

	        setVisible(true);
	  
	    }
	 /**
	  * Method for when the online button is pressed will as  ask if the user wants to connect,host or disconnect
	  */
	 public void onlinebutton() 
	 {
		 String[]option= {"Connect","Host","Disconnect"};
		 int choice=JOptionPane.showOptionDialog(
				 this, "Choose an online option:", "Online Options",
				 JOptionPane.DEFAULT_OPTION,
				 JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
		 switch(choice) 
		 {
		 case 0:
			 int maxtrys=1000;
			 int trys=0;
			 boolean t=true;
			 System.out.println("CONNECT");
			 while(trys<maxtrys) {
			        String namep=JOptionPane.showInputDialog(this,"Enter your name:");
			        
			        String address=JOptionPane.showInputDialog(this,"Enter your ip Address :");
			        String port=JOptionPane.showInputDialog(this,"Enter the port number of the server your trying to connect 2:");
			     
			        System.out.println(port);
			        int portint=0;
			        try {
			          portint=Integer.parseInt(port);
					     this.cl= new Client(address,portint,this);
					     cl.sendmessage("Am i connected");
					     break;

			        }catch(NumberFormatException e) 
			        {
				         port=JOptionPane.showInputDialog(this,"invaild port try again, Enter your port number: ");
				            trys++;

				         continue; // Restart the loop
			        }

			        //int addressint=Integer.parseInt(address);
			        
			       }
		//	 dispose();
			 break;
		 case 1:
			 int Maxtrys=1000;
			 int Trys=0;
			 
			 System.out.println("HOST");
			 while(Trys<Maxtrys) {
		        String namep=JOptionPane.showInputDialog(this,"Enter your name:");
		      
		
		        
		       
		        String port=JOptionPane.showInputDialog(this,"Enter your port number:");
		        int portint=0;
		        try {
			          portint=Integer.parseInt(port);
			          
			           serv= new Server(portint);
			           m=2;
				       new Thread(serv).start();
			           
				       JOptionPane.showMessageDialog(this, "Hosting on port: " + port);
			          break;
			        }catch(NumberFormatException e) 
			        {
				         port=JOptionPane.showInputDialog(this,"invaild port try again, Enter your port number: ");
			        	  Trys++;

				         continue; // Restart the loop
			        }
			 
		        

			 }

			 
			 break;
		 //disconnect
		 case 2:
			 System.out.println("DISCONNECT");
			 dispose();
			 break;
			 default:
				 System.out.println("checking");
				 break;
		 }
				 
		 
	 }
	 
		/**
	 	 * Creating a method that changes the lan from English to French
	 	 */
	 	public void translateToFrench() {
	 		
	 		//PlayerInfoPanel info = new PlayerInfoPanel("P1");
	 		
	 		Map<String, String> translationMapB = new HashMap<>();
			//JLabel[] pan= {info.timeLabel};
	 		Map<String, String> translationMapM = new HashMap<>();
			Map<String, String> translationMapMI = new HashMap<>();
			Map<String, String> translationMapL = new HashMap<>();
			Map<String, String> translationMapP = new HashMap<>();
			Map<String, String> translationMapBB = new HashMap<>();
			

	
	        translationMapP.put("Connect 4", "Connectez 4");

			JMenuItem[] Items= {exitMenuItem,helpMenuItem,online,French,error};
			translationMapMI.put( "Exit","Sortie");
	        translationMapMI.put( "Help","Aide");
	
	        translationMapMI.put( "Online","En ligne");
	        translationMapMI.put("French", "Anglais");
	        translationMapMI.put("\u274C (click this button for a fun surprise)", "\u274C (cliquez sur ce bouton pour une surprise amusante)");

	       
	        
	        JButton[][] Arrayb=buttons;
	        translationMapBB.put( "Drop","Baisse");
	        
	        JButton[] Buttons= {submitButton};
	        translationMapB.put("Submit", "Soumettre");

	        
	        JMenu[] Menus= {fileMenu};
	        translationMapM.put("File", "Déposer");
	        
	        JLabel[] label= {titleL};
	        translationMapL.put("Connect 4", "Connectez 4");


	        for(JMenuItem item: Items) 
			{
				String transB=translationMapMI.get(item.getText());
				if(transB!= null) 			
				{
					item.setText(transB);
				}
			} 
	        for(JMenu item: Menus) 
	  			{
	  				String transB=translationMapM.get(item.getText());
	  				if(transB!= null) 			
	  				{
	  					item.setText(transB);
	  				}
	  			}
	        for(JButton item: Buttons) 
  			{
  				String transB=translationMapB.get(item.getText());
  				if(transB!= null) 			
  				{
  					item.setText(transB);
  				}
  			}
	        
	        for(JLabel item: label) 
			{
				String transB=translationMapL.get(item.getText());
				if(transB!= null) 			
				{
					item.setText(transB);
				}
			}     
	        
	 }
	 	/**
	 	 * Creating a method that changes the lan from French to English
	 	 */
	 	public void translateToEnglish() {
	 		
	 		Map<String, String> translationMapB = new HashMap<>();
			Map<String, String> translationMapM = new HashMap<>();
			
			Map<String, String> translationMap = new HashMap<>();
			Map<String, String> translationMapL = new HashMap<>();
			Map<String, String> translationMapBB = new HashMap<>();


	        translationMap.put( "Sortie","Exit");
	        translationMap.put( "Aide","Help");
	  
	        translationMap.put( "En ligne","Online");
	        translationMap.put("Anglais", "French");
	        translationMap.put("\u274C (cliquez sur ce bouton pour une surprise amusante)", "\u274C (click this button for a fun surprise)");

	        JButton[][] Arrayb=buttons;
	        translationMapBB.put( "Baisse","Drop");

	        JLabel[] label= {titleL};
	        translationMapL.put("Connectez 4", "Connect 4");
	        JMenuItem[] Items= {exitMenuItem,helpMenuItem,online,French,error};
	        
	        JMenu[] Menus= {fileMenu};
		    translationMapM.put("Déposer", "File");

	        JButton[] Buttons= {submitButton};
	        translationMapB.put("Soumettre", "Submit");
	        
	        
	        for(JMenuItem item: Items) 
			{
				String transB=translationMap.get(item.getText());
				if(transB!= null) 			
				{
					item.setText(transB);
				}
			}
	        for(JMenu item: Menus) 
  			{
  				String transB=translationMapM.get(item.getText());
  				if(transB!= null) 			
  				{
  					item.setText(transB);
  				}
  			}
        for(JLabel item: label) 
			{
				String transB=translationMapL.get(item.getText());
				if(transB!= null) 			
				{
					item.setText(transB);
				}
			}
        for(JButton item: Buttons) 
		{
			String transB=translationMapB.get(item.getText());
			if(transB!= null) 			
			{
				item.setText(transB);
			}
		}
	 }
	 	/**
	 	 * creating a class that implements ActionListener for the drop down buttons and check the win, and player turn logic
	 	 */
	 private class DropButtonListener implements ActionListener
	 {
		 /**
		  * int for col
		  */
		 private int col;
		 /**
		  * sets col to col
		  * @param col int variable for col
		  */
		 public DropButtonListener(int col)
		 
		 {
			 this.col=col;
		 }
		 
	  
	 @Override
	 /**
	  * checks the actions for player turns
	  * @param e action event variable
	  */
	 public void actionPerformed(ActionEvent e) 

	 {
		 playerInfoPanel.elapsedTime=0;
		 System.out.println(playerInfoPanel.elapsedTime);
		 int row=findLowestEmptyRow(col);

		 if(row!=-1) 
		 {
			 gameState[row][col]=player1Turn ? 1:2;
			 buttons[row][col].setIcon(new ImageIcon(getClass().getResource("/PNGS/Player"+(player1Turn ?"1":"2")+".png")));
			 buttons[row][col].setPreferredSize(new Dimension(bwid,bhid));
			   if (checkHoriz() || checkVertical() || checkDiagonal()) {
	                System.out.println("Player" + (player1Turn ? "1" : "2") + " wins!");
	                int choice = JOptionPane.showConfirmDialog(null, "Player " + (player1Turn ? "1" : "2") + " wins! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
	                if(choice==JOptionPane.YES_OPTION)
	                {
		                resetGame();

	                }
	                else 
	                {
	                	System.exit(0);
	                }
	                // Consider disabling further input or showing a dialog to restart the game
	            } else {
	                // No win yet, proceed to switch turns
	                player1Turn = !player1Turn;
	                playerInfoPanel.updatePlayerName(player1Turn);
	                playerInfoPanel.resetTT();
	            } 
		 }
		 else 
		 {
			 colf();
			 System.out.println("col full");
			 
		 }
		 
	 }
	 }
	
 
	 /**
	  * Prints WindowSize
	  */
	 private void printWindowSize() 
	 {
		 Dimension size= getSize();
		 System.out.println("Current window size:"+size.width+"X "+size.height);
	 }
	 
	 
	 /**
	  * 
	  * @param col variable that keeps track of cols
	  * @return returns -1 if col is full
	  */
	 private int findLowestEmptyRow(int col) 
	 {
			    for (int row = 5; row >= 0; row--) {
			        if (gameState[row][col] == 0) { // Check if the slot is empty
			            return row;
			        }
			    }
			    return -1; // Column is full
			
		
		
	 }
	 /**
	  * creates a new help screen instance
	  */
	 private void startConnectFourHelp() {
	        System.out.println("Creating HelpScreen instance...");

	    	helpscreen.setVisible(true);
	        setVisible(false);
	    }
	 /**
	  *  checks if player wins vertically
	  * @return a boolean depending on if it found a win
	  */
	 private boolean checkHoriz() 
	 {
		 for(int row=0;row<6;row++)
		 {
			 for(int col=0;col<4;col++)
			 {
				 int player= gameState[row][col];
				 if(player!=0&&player==gameState[row][col+1]&&player==gameState[row][col+2]&&player==gameState[row][col+3])
				 {
					return true;
				 }
			 }
		 }
		 return false;
	 }
	 /**
	  *  checks if player wins vertically
	  * @return a boolean depending on if it found a win
	  */
	 private boolean checkVertical()
	 {
		 for(int c=0;c<7;c++)
		 {
			 for(int r=0;r<3;r++)
			 {
				 int player= gameState[r][c];
				 if(player!=0&&player==gameState[r+1][c]&&player==gameState[r+2][c]&&player==gameState[r+3][c])
				 {
					return true;
				 }
			 }
		 }
		 return false;
	 }
	 /**
	  *  checks if player wins vertically
	  * @return a boolean depending on if it found a win
	  */
	 private boolean checkDiagonal() 
	 {
		 for(int row=0;row<3;row++)
		 {
			 for(int col=0;col<4;col++)
			 {
				 int player= gameState[row][col];
		            if (player != 0 && player == gameState[row + 1][col + 1] && player == gameState[row + 2][col + 2] && player == gameState[row + 3][col + 3]) 
		            {
				 
					return true;
				 }
			 }
		 }
		 
		 
		 for(int row=0;row<3;row++)
		 {
			 for(int col=3;col<7;col++)
			 {
				 int player= gameState[row][col];
		            if (player != 0 && player == gameState[row + 1][col - 1] && player == gameState[row + 2][col - 2] && player == gameState[row + 3][col - 3]) {
				 
					return true;
				 }
			 }
		 }
		 
		 
		 return false;
	 }
	 /**
	  * method to restGame
	  */
	 private void resetGame() 
	 {

		 for(int row=0;row<6;row++) 
		 {
			 for(int col=0;col<7;col++) 
			 {
				 gameState[row][col]=0;		    
		           buttons[row][col].setIcon(icon2); 

			 }

		 }
		 player1Turn=true;
	 }
	 /**
	  * Method to check if col is full
	  */
	 private void colf() 
	 {
		 JOptionPane.showInputDialog(this,"Col is full");
	        //String player2=JOptionPane.showInputDialog(this,"Enter your name player2:");
	 }
	 
/**
 * The main method
 * @param args command line arguments
 */
	    public static void main(String[] args) {
	    	
	        SwingUtilities.invokeLater(ConnectFour::new);
	    }
}



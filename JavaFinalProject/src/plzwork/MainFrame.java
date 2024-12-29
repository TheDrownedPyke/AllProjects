package plzwork;
/**
 * Author: Dereck Rock
 * student Number 041049245
 * Course:CST8221
 * Due: Feb18th 2024
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Represents the main frame of the connect four game
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/**
	 * Declaring variables
	 */
    private ConnectFour connectFour;
	/**
	 * Declaring variables
	 */
    private HelpScreen helpscreen;
	/**
	 * Declaring variables
	 */
    private Settings settings;
	/**
	 * Declaring variables
	 */
    private JFrame frame;
	/**
	 * Declaring variables
	 */
    private JLabel backgroundImageLabel;
	/**
	 * Declaring variables
	 */
    private JButton startButton;
	/**
	 * Declaring variables
	 */
    private JButton Help;
	/**
	 * Declaring variables
	 */
    private JButton Exit;
	/**
	 * Declaring variables
	 */
	/**
	 * Declaring variables
	 */
    private JButton French;
	/**
	 * Declaring Getter for Start button
	 * @return returns startButton
	 */
	public JButton getStart() 
	{
		return startButton;
	}
	/**
	 * Declaring Getter for Help button
	 * @return returns Help

	 */
	public JButton getHelp() 
	{
		return Help;
	}	
	/**
	 * Declaring Getter for Exit button
	 * @return returns Exit

	 */
	public JButton getExit() 
	{
		return Exit;
	}
	/**
	 * Declaring Getter for French button
	 * @return returns French

	 */
	public JButton getFrench() 
	{
		return French;
	}

/**
 * Constructs a MainFrame object
 */
    public MainFrame() {
      
    	 frame = new JFrame("Main Menu");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(1080, 800);
         frame.setResizable(false);


     
        initializeComponents();

        addComponents();
        setupListeners();
        initSettings();
        frame.setVisible(true);

    }
    /**
     * Initializes  the components of the Main frame
     */
    private void initializeComponents() {
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/PNGS/java_bg.png"));
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        int fontSize = (int) (Math.min(frame.getWidth(), frame.getHeight()) * 0.04);

        
        
        startButton = new JButton("New");
        startButton.setContentAreaFilled(false);
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setForeground(Color.MAGENTA); // Set text color to purple
        startButton.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Set font size (adjust as needed)

        Help = new JButton("Help");
        Help.setContentAreaFilled(false);
        Help.setBorder(BorderFactory.createEmptyBorder());
        Help.setForeground(Color.MAGENTA); // Set text color to purple
        Help.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Set font size (adjust as needed)


        Exit = new JButton("Exit");
        Exit.setContentAreaFilled(false);
        Exit.setBorder(BorderFactory.createEmptyBorder());
        Exit.setForeground(Color.MAGENTA); // Set text color to purple
        Exit.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Set font size (adjust as needed)



        French= new JButton("French");
        French.setContentAreaFilled(false);
        French.setBorder(BorderFactory.createEmptyBorder());
        French.setForeground(Color.MAGENTA); // Set text color to purple
        French.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Set font size (adjust as needed)

    }
    /**
     * Adds the components to the frame with proper spacing and such
     */
    private void addComponents() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // 2 rows, 1 column
        GridBagConstraints gbc = new GridBagConstraints();
       
        
        int insetTop = (int) (frame.getHeight() * 0.2);
        int insetLeft = (int) (frame.getWidth() * 0.02);
        int insetBottom = (int) (frame.getHeight() * 0.02);
        int insetRight = (int) (frame.getWidth() * 0.02);
        
        int insetTop2 = (int) (frame.getHeight() * 0.02);
        int insetLeft2 = (int) (frame.getWidth() * 0.001);
        int insetBottom2 = (int) (frame.getHeight() * 0.02);
        int insetRight2 = (int) (frame.getWidth() * 0.002);
        
        gbc.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
        //top left righ bottom
 
        gbc.gridx=0;
        gbc.gridy=2;
        buttonPanel.add(startButton,gbc);
        startButton.setActionCommand("New");

        gbc.insets = new Insets(insetTop2, insetLeft2, insetBottom2, insetRight2);

        gbc.gridx=0;
        gbc.gridy=3;
        buttonPanel.add(Help,gbc);
        Help.setActionCommand("Help");


        gbc.gridx=0;
        gbc.gridy=4;
        buttonPanel.add(Exit,gbc);
        Exit.setActionCommand("Exit");

        
        gbc.gridx=0;
        gbc.gridy=6;
        buttonPanel.add(French,gbc);
        French.setActionCommand("French");

        int gap = (int) (frame.getWidth() * 0.1);
        buttonPanel.setOpaque(false);

        backgroundImageLabel.add(buttonPanel);
        frame.setContentPane(backgroundImageLabel);
    }
    /**
     * Sets the proportional size of the button 
     * @param button The button whose size to set
     */

    private void setButtonProportionalSize(JButton button) {
        Dimension frameSize = frame.getSize();
        int buttonWidth = (int) (frameSize.width * 0.1);
        int buttonHeight = (int) (frameSize.height * 0.1);

        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
    }
    
/**
 * Sets up the event listeners for the buttons
 */
    private void setupListeners() {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            /**
             * checks if componet to resize
             * @param e Component variable
             */
            public void componentResized(ComponentEvent e) {
                resizeBackgroundImage();
                setButtonProportionalSize(startButton);
                setButtonProportionalSize(Help);
                setButtonProportionalSize(Exit);
              //  setButtonProportionalSize(Load);

                addComponents();
            }
            
        });
    }

    /**
     * Resizes the background image
     */
    private void resizeBackgroundImage() {
        ImageIcon originalImage = (ImageIcon) backgroundImageLabel.getIcon();
        Image scaledImage = originalImage.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(),
                Image.SCALE_SMOOTH);
        backgroundImageLabel.setIcon(new ImageIcon(scaledImage));
    }
	/**
	 * Method that checks for event on on the startButton button
	 * @param L  event variable
	 */
	public void startEvent(ActionListener L) 
	{
	     dispose();
		startButton.addActionListener(L);
	}
	/**
	 * Method that checks for event on on the Help button
	 * @param L  event variable
	 */
	public void helpEvent(ActionListener L) 
	{

		Help.addActionListener(L);
	}
	/**
	 * Method that checks for event on on the Exit button
	 * @param L  event variable
	 */
	public void exitEvent(ActionListener L) 
	{

		Exit.addActionListener(L);
	}
	/**
	 * Method that checks for event on on the French button
	 * @param L  event variable
	 */
	public void FrenchEvent(ActionListener L) 
	{

		French.addActionListener(L);
	}
	  /**
	   * method for initializing settings
	   */
	 private void initSettings() {
	        this.settings = new Settings(helpscreen, this, connectFour);
	    }
	 

/**
 * Main method to start the appilcation
 * @param args Command-line args
 */
    public static void main(String[] args) {
    	Splash s= new Splash(5000);
    	/**
    	 *  invoking SwingUtil
    	 */
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(); // Create an instance of MainFrame
          //  mainFrame.setVisible(true); // Make MainFrame visible
        });
    }

}

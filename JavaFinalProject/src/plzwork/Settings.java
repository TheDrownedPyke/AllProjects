package plzwork;
/**
 * Author: Dereck Rock
 * student Number 041049245
 * Course:CST8221
 * Due: Feb 18th 2024
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

/**
 * Settings class 
 */
public class Settings implements ActionListener{
	/**
	 * declaring variables
	 */
	private HelpScreen help;
	/**
	 * declaring variables
	 */
	private MainFrame mainframe;
	/**
	 * declaring variables
	 */
	private ConnectFour connect4;
	/**
	 * declaring variables
	 */
	private boolean test=true;
	/**
	 * Creating a settings constructors
	 * @param help new help instance
	 * @param mainframe  new help mainframe
	 * @param connect4  new help connect4
	 */

	public Settings(HelpScreen help,MainFrame mainframe,ConnectFour connect4) 
	{
		if (mainframe == null) {
	        throw new IllegalArgumentException("MainFrame cannot be null");
		}
		this.help=help;
		this.mainframe=mainframe;
		this.connect4=connect4;
		addActionListeners();
	}
	/**
	 * method for ActionListeners
	 */
	private void addActionListeners() 
	{
		mainframe.startEvent(this);
		mainframe.helpEvent(this);

		mainframe.exitEvent(this);
		mainframe.FrenchEvent(this);
	}
	@Override
	/**
	 * @param e event variable
	 */
	public void actionPerformed(ActionEvent e) 
	{
		Object s=e.getSource();		
		switch(e.getActionCommand()) 
		{ 
		case"New":
			close();
			connect4S();
			break;
		case"Help":
			Helps();
			break;
		case"Exit":
			System.out.println("closing program");
            System.exit(0); // Exit the entire program
            break;
		case"French":
			System.out.println("hi");
			if(test) {
            	translateToFrench();
            	test=false;
            	}
            	else 
            	{
            		translateToEnglish(); 
            		test=true;
            	}
			break;	
		default:
			System.out.println("j");
			break;
		
		
		}
	}
	/**
	 * method that creates new connect4 instance
	 */
	private void connect4S() 
	{
		connect4 = new ConnectFour();
		connect4.setVisible(true);
		
		
	}
	/**
	 *	 
	 ** method that creates new HelpScreen instance

	 */
	private void Helps() 
	{
		help = new HelpScreen(mainframe);
		help.setVisible(true);
		
	}
	/**
	 * Method to translate From English To French
	 */
	public void translateToFrench() 
	{
		Map<String, String> translationMap = new HashMap<>();
        translationMap.put("New", "Nouveau");
        translationMap.put("Help", "Aide");
        translationMap.put("Exit", "Sortie");
        translationMap.put("French", "Anglais");
        JButton S= mainframe.getStart();
        JButton H= mainframe.getHelp();
        JButton E= mainframe.getExit();
        JButton F= mainframe.getFrench();

        
        JButton[] buttons= {S,H,E,F};
        for (JButton button:buttons ) {
        	String translate= translationMap.get(button.getText());
            if (translate != null ) {
            	button.setText(translate);
                }
            
        }
	}
     
	/**
	 * Method to translate From French To English
	 */
	public void translateToEnglish() 
	{
		Map<String, String> translationMap = new HashMap<>();
        translationMap.put("Nouveau", "New");
        translationMap.put("Aide", "Help");
        translationMap.put("Sortie", "Exit");
        translationMap.put("Anglais", "French");
        JButton S= mainframe.getStart();
        JButton H= mainframe.getHelp();
        JButton E= mainframe.getExit();
        JButton F= mainframe.getFrench();
        JButton[] buttons= {S,H,E,F};
        for (JButton button:buttons ) {
        	String translate= translationMap.get(button.getText());
            if (translate != null ) {
            	button.setText(translate);
                }
}
            
        }
	
	/**
	 * Method to close other screen
	 */
	private void close() 
	{
		if(mainframe!=null) 
		{
			mainframe.dispose();
			
		}
	}
    
}

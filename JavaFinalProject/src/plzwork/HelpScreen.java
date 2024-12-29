package plzwork;
/**
 * Author: Dereck Rock
 * student Number 041049245
 * Course:CST8221
 * Due: Feb 18th 2024
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Help screen where the user will be able to learn how to play the game
 */
public class HelpScreen extends JFrame {

    /**
	 * declaring variables
	 */
	private JButton exitButton;
	/**
	 * declaring variables
	 */
	private JButton French;
	/**
	 * declaring variables
	 */
	private JTextField eT;
	/**
	 * declaring variables
	 */
	private JTextField wT;
	/**
	 * declaring variables
	 */
	private JTextField cT;
	/**
	 * declaring variables
	 */
	private JTextArea eTA;
	/**
	 * declaring variables
	 */
	private JTextArea playear;
	/**
	 * declaring variables
	 */
	private JTextArea cTA;
	/**
	 * declaring variables
	 */
	private boolean test=true;

	
	/**
	 * declaring variables
	 */
    private MainFrame mainFrame;
    /**
     * Help main frame where all the conexts will be
     * @param mainFrame the main frame
     */
	public HelpScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setTitle("Help Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.red);
        setLayout(new BorderLayout());
        setResizable(false);
        JPanel eastP= new JPanel();
        JPanel westP= new JPanel();
        JPanel centerP= new JPanel();
        JPanel southP= new JPanel();

        eastP.setLayout(new BoxLayout(eastP,BoxLayout.Y_AXIS));
        westP.setLayout(new BoxLayout(westP,BoxLayout.Y_AXIS));
        centerP.setLayout(new BoxLayout(centerP,BoxLayout.Y_AXIS));

        eT= new JTextField("How to change the Languages");
        eT.setBackground(Color.orange);
        eT.setFont(new Font("Georgia",Font.BOLD,22));
        eT.setEditable(false);
        eTA= new JTextArea("Click on the settings button In the main screen");

        eTA.setFont(new Font("Arial",Font.PLAIN,16));
        eTA.setBackground(Color.orange);

        eTA.setEditable(false);

        wT= new JTextField("How to play Connect 4");
        wT.setFont(new Font("Georgia",Font.BOLD,22));
        wT.setBackground(Color.CYAN);

        wT.setEditable(false);

        playear= new JTextArea("1. Each player will have 30 seconds to place a chip,\n 2.Use the buttons along the top of the baord to place your chip \n 3.The first player go get four of there chips in a row,vertically or diagonally wins \n 4.You have the option to start as well if you'd like to play again");
        playear.setFont(new Font("Arial",Font.PLAIN,16));
        playear.setBackground(Color.CYAN);

        playear.setEditable(false);


        cT= new JTextField("General rules");
        cT.setFont(new Font("Georgia",Font.BOLD,22));
        cT.setBackground(Color.magenta);

        cT.setEditable(false);


        cTA= new JTextArea("If you do play online please remeber the chat is a place to \n Comunitate not to spread hate. Keep the things said in chat civel. \n And Dont forget to have fun (:");
        cTA.setFont(new Font("Arial",Font.PLAIN,16));
        cTA.setBackground(Color.magenta);

        cTA.setEditable(false);


        
        eastP.add(eT);
        eastP.add(eTA);

        westP.add(wT);
        westP.add(playear);
        centerP.add(cT);
        centerP.add(cTA);

        
        
        
        add(eastP, BorderLayout.EAST);
        add(westP, BorderLayout.WEST);
        add(centerP, BorderLayout.CENTER);
        add(southP, BorderLayout.SOUTH);

        
         exitButton = new JButton("Exit");

         French = new JButton("French");

        // exitButton.setActionCommand("ExitH");

         French.setActionCommand("FrenchH");

        southP.add(exitButton, BorderLayout.SOUTH);
        southP.add(French, BorderLayout.SOUTH);
        /**
         * Creating event for exitButton
         */
        exitButton.addActionListener(new ActionListener() 
        {
        	/**
        	 * checks for action
        	 * @param e event  variable
        	 */
        	@Override
            public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        	
        })   ;
        /**
         * Creating event for French button
         */
        French.addActionListener(new ActionListener() 
        {
        	/**
        	 * checks for Action
        	 * @param e event variable
        	 */
        	@Override
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
        	
        })   ;
        
pack();
        setLocationRelativeTo(null);

    }
	/**
	 * Changes the lan from English to French
	 */
	public void translateToFrench() 
	{
		Map<String, String> translationMapF = new HashMap<>();
		Map<String, String> translationMapA = new HashMap<>();
		Map<String, String> translationMap = new HashMap<>();
        translationMap.put( "Exit","Sortie");
        translationMap.put("French", "Anglais");
        
        translationMapF.put("How to change the Languages", "Comment changer les langues");
        translationMapF.put("How to play Connect 4", "Comment jouer à Connect 4");
        translationMapF.put("General rules", "Règles générales");

        
        
        translationMapA.put("Click on the settings button In the main screen", "Cliquez sur le bouton Paramètres Dans l'écran principal");
        
        translationMapA.put("1. Each player will have 30 seconds to place a chip,\n 2.Use the buttons along the top of the baord to place your chip \n 3.The first player go get four of there chips in a row,vertically or diagonally wins \n 4.You have the option to start as well if you'd like to play again",
        		"1. Chaque joueur aura 30 secondes pour placer un jeton,\n 2. Utilisez les boutons situés en haut du plateau pour placer votre jeton \n 3. Le premier joueur va en chercher quatre d'affilée, verticalement ou en diagonale, gagne \n 4.Vous avez également la possibilité de commencer si vous souhaitez rejouer");
       
        
        translationMapA.put("If you do play online please remeber the chat is a place to \n Comunitate not to spread hate. Keep the things said in chat civel. \n And Dont forget to have fun (:",
        		"Si vous jouez en ligne, n'oubliez pas que le chat est un endroit pour \n Communiquer pour ne pas répandre la haine. Gardez les choses dites dans le chat civel. \n Et n'oubliez pas de vous amuser (:");
 
		JButton[] buttons= {exitButton,French};
		JTextField[] fields= {eT,wT,cT};
		JTextArea[] areas= {eTA,playear,cTA};
		for(JButton button: buttons) 
		{
			String transB=translationMap.get(button.getText());
			if(transB!= null) 			
			{
				button.setText(transB);
			}
		} 
		
		for(JTextField field: fields) 
		{
			String transB=translationMapF.get(field.getText());
			if(transB!= null) 
			
			{
				field.setText(transB);
			}
		}
		for(JTextArea area: areas) 
		{
			String transB=translationMapA.get(area.getText());
			if(transB!= null) 
			
			{
				area.setText(transB);
			}
		}
	}
	/**
	 * Changes the lan from French to English
	 */
	public void translateToEnglish() 
	{
		Map<String, String> translationMapF = new HashMap<>();
		Map<String, String> translationMapA = new HashMap<>();

		Map<String, String> translationMap = new HashMap<>();
        translationMap.put( "Sortie","Exit");
        translationMap.put("Anglais", "French");
        
        translationMapF.put("Comment changer les langues", "How to change the Languages");
        translationMapF.put("Comment jouer à Connect 4", "How to play Connect 4");
        translationMapF.put("Règles générales", "General rules");
        
        translationMapA.put("Cliquez sur le bouton Paramètres Dans l'écran principal","Click on the settings button In the main screen");
        translationMapA.put("1. Chaque joueur aura 30 secondes pour placer un jeton,\n 2. Utilisez les boutons situés en haut du plateau pour placer votre jeton \n 3. Le premier joueur va en chercher quatre d'affilée, verticalement ou en diagonale, gagne \n 4.Vous avez également la possibilité de commencer si vous souhaitez rejouer","1. Each player will have 30 seconds to place a chip,\n 2.Use the buttons along the top of the baord to place your chip \n 3.The first player go get four of there chips in a row,vertically or diagonally wins \n 4.You have the option to start as well if you'd like to play again");


        translationMapA.put("Si vous jouez en ligne, n'oubliez pas que le chat est un endroit pour \n Communiquer pour ne pas répandre la haine. Gardez les choses dites dans le chat civel. \n Et n'oubliez pas de vous amuser (:",
        		"If you do play online please remeber the chat is a place to \n Comunitate not to spread hate. Keep the things said in chat civel. \n And Dont forget to have fun (:");

		JTextField[] fields= {eT,wT,cT};
		JTextArea[] areas= {eTA,playear,cTA};
		JButton[] buttons= {exitButton,French};
		for(JButton button: buttons) 
		{
			String transB=translationMap.get(button.getText());
			if(transB!= null) 
			
			{
				button.setText(transB);
			}
		}
		//make neon green text
		for(JTextField field: fields) 
		{
			String transB=translationMapF.get(field.getText());
			if(transB!= null) 
			
			{
				field.setText(transB);
			}
		}
		for(JTextArea area: areas) 
		{
			String transB=translationMapA.get(area.getText());
			if(transB!= null) 
			
			{
				area.setText(transB);
			}
		}
	}
	
	 
	
	/**
	 * this method will closed the current frame when called
	 */




}

package plzwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class for bonus Game
 */
public class bonus extends JPanel implements KeyListener, ActionListener {
	/**
	 * Declaring variable
	 */
    private ArrayList<Point> snake;
    /**
	 * Declaring variable
	 */
    private Point food;
    /**
	 * Declaring variable
	 */
    private Point level1;
    /**
	 * Declaring variable
	 */
    private int direction;
    /**
	 * Declaring variable
	 */
    private boolean gameOver;
    /**
	 * Declaring variable
	 */
    private Timer timer;
    /**
	 * Declaring variable
	 */
    public int playerlevel=0;
    
    /**
	 * Declaring variable
	 */
    private JPanel miniMap;
    /**
	 * Declaring variable
	 */
    private final int MINI_MAP_SIZE = 100;
    /**
	 * Declaring variable
	 */

    private static final int WIDTH = 1080;
    /**
	 * Declaring variable
	 */
    private static final int HEIGHT = 800;
    /**
	 * Declaring variable
	 */
    private static final int UNIT_SIZE = 20;
    /**
	 * Declaring variable
	 */
    private static final int DELAY = 100;
    /**
	 * Declaring variable
	 */
    JFrame frame;
    //SnakeGame
/**
 * Bonus game method
 * @param frame current frame
 */
public bonus(JFrame frame) {
    	this.frame=frame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(this);

        miniMap = new JPanel() {
            @Override
            /**
             * method for painting comp 
             * @param g Graphics variable
             */
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPlayerPosition(g);
            }
        };
        miniMap.setPreferredSize(new Dimension(MINI_MAP_SIZE, MINI_MAP_SIZE));
        miniMap.setBackground(Color.BLACK);
        miniMap.setOpaque(false);

        add(miniMap, BorderLayout.NORTH);

        snake = new ArrayList<>();
        snake.add(new Point(4, 4)); // Initial snake position
        direction = KeyEvent.VK_RIGHT;
        gameOver = false;
        placeFood();

        level1();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    /**
     * Method for paint componets
     * @param g Graphics variable
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            gameOver(g);
            return;
        }

        // Draw snake
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * UNIT_SIZE, p.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }

        // Draw food
        g.setColor(Color.RED);
        g.fillOval(food.x * UNIT_SIZE, food.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        
        
       //draw level 1 portal 
        g.setColor(Color.CYAN);
        g.fillOval(level1.x * UNIT_SIZE, level1.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
    }
/**
 * Method for mini map
 * @param g Graphics variable
 */
    private void drawPlayerPosition(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Player Position: (" + snake.get(0).x + ", " + snake.get(0).y + ")", 10, 20);
    }
/**
 * Method for moving the snake
 */
    private void move() {
        Point head = snake.get(0);
        Point newHead = (Point) head.clone();
        switch (direction) {
            case KeyEvent.VK_UP:
                newHead.y--;
                break;
            case KeyEvent.VK_DOWN:
                newHead.y++;
                break;
            case KeyEvent.VK_LEFT:
                newHead.x--;
                break;
            case KeyEvent.VK_RIGHT:
                newHead.x++;
                break;
        }

        // Check if the new head position is out of bounds or hits the snake itself
        if (newHead.x < 0 || newHead.x >= WIDTH / UNIT_SIZE ||
            newHead.y < 0 || newHead.y >= HEIGHT / UNIT_SIZE ||
            snake.contains(newHead)) {
            gameOver = true;
            timer.stop();
            repaint();
            return;
        }

        snake.add(0, newHead);

        // Check if the new head position is the food
        if (newHead.equals(food)) {
            placeFood();
        } else {
            snake.remove(snake.size() - 1); // Remove the tail if not eating food
        }
       
        if(newHead.equals(level1))
        {
        	doublelen();
        	level1();
        }
        repaint();
        miniMap.repaint(); // Repaint the mini-map
    }
    /**
     * Method for doubling food
     */
    private void doublelen() 
    {
    	int currentlen=snake.size();
    	Point tail = snake.get((snake.size())-1);
    	for(int i=0;i<currentlen;i++) 
    	{
    		snake.add((Point)tail.clone());
    	}
    	
    }
    /**
     * Method for Red food
     */
    private void placeFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / UNIT_SIZE);
        int y = rand.nextInt(HEIGHT / UNIT_SIZE);
        food = new Point(x, y);
    }
    /**
     * Method for blue food
     */
    private void level1() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / UNIT_SIZE);
        int y = rand.nextInt(HEIGHT / UNIT_SIZE);
        level1 = new Point(x, y);

    }

    /**
     * Method that when called will take user to game over screen
     * @param g Graphic variable
     */
    private void gameOver(Graphics g) {
    	timer.stop();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Game Over!", WIDTH / 2 - 100, HEIGHT / 2);
        String[]option= {"restart","join new connect4 game","Shut down"};
		 int choice=JOptionPane.showOptionDialog(
				 this, "Choose an online option:", "Online Options",
				 JOptionPane.DEFAULT_OPTION,
				 JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
        switch(choice) 
        {
        case 0:
            resetGame();

        	break;
        case 1:
            if (frame != null) {
                frame.dispose(); // Close current window
            }
            ConnectFour c4 = new ConnectFour();
            c4.setVisible(true);
        	break;
        case 2: 
        	System.exit(0);
        	break;
        
        default:
			 System.out.println("No option chosen");
			 frame.dispose();
			 break;
        
        }
    }

    @Override
    /**
     * Method that checks if any action is perfromed
     * @param e ActionEvent in question
     */
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
        }
    }

    @Override
    /**
     * Method to check if a key is pressed
     * @param e It is a KeyEvent waiting to a key pressed
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) ||
            (key == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT) ||
            (key == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) ||
            (key == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP)) {
            direction = key;
        }
    }
    /**
     * Method to reset the game
     */
    public void resetGame() 
    {
    	timer.start();
    	snake.clear();
    	snake.add(new Point(4,4));
    	direction = KeyEvent.VK_RIGHT; 
        gameOver = false; 
        placeFood(); 
        level1(); 
        timer.start(); 
        repaint();
    	
    }

    @Override
    /**
     * Method to check if a key is pressed
     * @param e It is a KeyEvent waiting to a key pressed
     */
    public void keyTyped(KeyEvent e) {
    }

    @Override
    /**
    * Method to check if a key is pressed
     * @param e It is a KeyEvent waiting to a key pressed
     */
    public void keyReleased(KeyEvent e) {
    }
/**
 * Main method
 * @param args String prama
 */
    
}


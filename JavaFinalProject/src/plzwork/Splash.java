package plzwork;

import javax.swing.*;
import java.awt.*;
/**
 *  Splash screen to display information to the user
 */
public class Splash extends JWindow {
	/**
	 *  Displays The spash screen for a set amount of time
	 * @param duration time to stay open
	 */
    public Splash(int duration) {
        // Create a JPanel with the layout
        JPanel content = (JPanel)getContentPane();
       // ImageIcon back= new ImageIcon(getClass().getResource("/PNGS/Ok.png"));
        content.setBackground(Color.red);

        int width = 500;
        int height = 500;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
 
        JLabel label = new JLabel();
        JLabel copyrt = new JLabel("Dereck Rock", JLabel.CENTER);
        copyrt.setFont(new Font("Arial", Font.BOLD, 20));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        
        setVisible(true);

        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(false);
    }
}

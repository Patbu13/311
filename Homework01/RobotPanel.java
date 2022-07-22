/**
 * Written by JJ Shepherd // Patrick Burroughs
 * This class creates a new JPanel that will specifically draw the robot's.
 */
import java.awt.*;
import javax.swing.*;

public class RobotPanel extends JPanel //Extends (inheritance) the functionality of the JPanel Component in order to draw Robots. 
{
	public static final int DEF_R_SIZE = 8; //Default number of Robots
	private SimpleRobot[] robots = new SimpleRobot[DEF_R_SIZE]; //Creates the array of robots to be updated and drawn
	/**
	 * Initializes elements of the JPanel. This method is to be called before added to a JFrame.
	 */
	public void init()
	{
		//Set's the JPanel's Preferred Size to be the same as the Frame.
		super.setPreferredSize(new Dimension(RobotThreadSimulator.FRAME_DIM,RobotThreadSimulator.FRAME_DIM));
		
		//Creates and adds all 8 robots to the array
		
		//2 Simple Robots
		robots[0] = new SimpleRobot(7, 0, Color.GREEN);
		robots[1] = new SimpleRobot(17, 10, Color.RED);
		//2 Left Right Robots
		robots[2] = new LRRobot(7,30, Color.BLUE, 3);
		robots[3] = new LRRobot(7,50, Color.BLUE, 5);
		//2 Diagonal Robots
		robots[4] = new DiagonalRobot(7, 70, Color.ORANGE, 3, 5);
		robots[5] = new DiagonalRobot(7,90, Color.ORANGE, 5, 3);
		//2 Wavy Robots
		robots[6] = new WavyRobot(7,310, Color.PINK, 7, 9, 20);
		robots[7] = new WavyRobot(7,420, Color.PINK, 12, 12, 8);
		//See assignment requirements for more details
		
		//Create and start threads for each robot
		Thread greenBot = new Thread(robots[0]);
		Thread redBot = new Thread(robots[1]);
		Thread slowLR = new Thread(robots[2]);
		Thread fastLR = new Thread(robots[3]);
		Thread slowDiag = new Thread(robots[4]);
		Thread fastDiag = new Thread(robots[5]);
		Thread slowWavy = new Thread(robots[6]);
		Thread fastWavy = new Thread(robots[7]);

		greenBot.start();
		redBot.start();
		slowLR.start();
		fastLR.start();
		slowDiag.start();
		fastDiag.start();
		slowWavy.start();
		fastWavy.start();
	}
	
	/**
	 *Overrides JPanel's paintComponent method in order to draw each robot.
	 */
	public void paintComponent(Graphics g)
	{
		//Calling super class' paintComponents first
		super.paintComponent(g);
		//For each robot in the array of robots
		for(SimpleRobot r : robots)
		{
			if(r == null)
				continue;
			//Sets the drawing color
			g.setColor(r.getrColor());
			//Draws the oval to the JPanel
			g.fillOval(r.getX(),r.getY(),SimpleRobot.ROBOT_SIZE,SimpleRobot.ROBOT_SIZE);
		}
	}
}

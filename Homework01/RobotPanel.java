/**
 * Written by JJ Shepherd
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
		
		//TODO Add Robots to the Array
		
		//2 Simple Robots

		
		//2 Left Right Robots

		
		//2 Diagonal Robots

		
		//2 Wavy Robots

		
		//See assignment requirements for more details
		
		//TODO Start each robot thread

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

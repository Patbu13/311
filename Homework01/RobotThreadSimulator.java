/**
 * Written by JJ Shepherd
 * This class creates the GUI, initializes the robots and drawing components, and starts the simulation.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RobotThreadSimulator
{
	public static final int FRAME_DIM = 512; //Image dimension assumed to be fixed
	public static RobotPanel rP = new RobotPanel();//Creates a new Robot Panel to be added to the Frame.
	public static JFrame frame = new JFrame("Robot Thread Simulation");	
	public static final int FRAME_TIMER_DELAY = 10;//10 milliseconds
	
	public static void main(String[] args) {
		
		//Frame for the program
		frame.setLayout(new FlowLayout());//Simple Layout for the Components
		frame.setBounds(0,0,FRAME_DIM,FRAME_DIM);
		
		//Initializes the Robot Panel
		rP.init();
		//Adds it to the frame
		frame.add(rP);
		//Ensures that it closes all threads after the frame is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Now we can see it!
		frame.setVisible(true);
		//Begin Animating
		startAnimation();
	}
	/**
	 * Used to call repaint on the frame using a timer (another thread) with a delay.
	 */
	public static void startAnimation()
	{
		//Sets up an ActionListener to be used by the Timer
		ActionListener al = new ActionListener()
				{
					//Overrides ActionListener's method actionPerformed using an Anonymous Class
					public void actionPerformed(ActionEvent e) 
					{
						frame.repaint();//Calls repaint on the Frame to redraw it and its components
					}
			
				};
		//Creates a timer with the timer delay and then action listener
		Timer timer = new Timer(FRAME_TIMER_DELAY,al);
		//Starts the timer thread
		timer.start();
	}

}













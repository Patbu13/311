/**
 * Written by JJ Shepherd // Patrick Burroughs
 * This class represents Simple Robots that runs and updates on a separate threads.
 */
import java.lang.Thread;
import java.awt.*;

public class LRRobot extends SimpleRobot //Extends (inheritance) the functionality of Thread in order to update the Robot's position
{
	private int x,y;//Location of the Robot
	private Color rColor = Color.DARK_GRAY;//Default Color
    private int rSpeed;
    private boolean direction = true;
	public static final int ROBOT_SIZE = 15;//Robot's are 15 pixel circles
	public static final int TIME_DELAY = 30;//Update is called every 30 milliseconds.
	public LRRobot(int aX, int aY, Color aC, int rSpeed)
	{
		super(aX, aY, aC);
        this.rColor = aC;
        this.rSpeed = rSpeed;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getrColor() {
		return rColor;
	}

	public void setrColor(Color rColor) {
		this.rColor = rColor;
	}
	
    public int getSpeed() {
		return rSpeed;
	}

	public void setSpeed(int speed) {
		this.rSpeed = speed;
	}

	/**
	 * Overrides the method run in Thread. This calls the method update and sleeps the thread for 30 milliseconds before calling update again.
	 */
	public void run()
	{
		while(true)
		{
			//Calls update on each robot
			this.update();
			//Sleeps this thread for 30 milliseconds before updating again
			try {Thread.sleep(TIME_DELAY);}catch(Exception e) {e.printStackTrace();}
		}
	}
	/**
	 * This updates the position of the robot based on the robot's type.
	 * This is meant to be overridden for other extended robot types.
	 */
	public void update()
	{
		//TODO determine how the robot will move. This type of robot does not move but other may need to override this, and set the X and Y coordinates.
        if (direction) {
            this.x += this.rSpeed;
            if (this.x >= RobotThreadSimulator.FRAME_DIM - ROBOT_SIZE*2) {
                direction = false;
            }
        }
        else {
            this.x -= this.rSpeed;
            if (this.x <= 0) {
                direction = true;
            }
        }
	}
}
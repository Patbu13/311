/**
 * Written by JJ Shepherd // Patrick Burroughs
 * This class represents Simple Robots that runs and updates on a separate threads.
 */
import java.lang.Thread;
import java.util.function.ToDoubleFunction;
import java.awt.*;

public class WavyRobot extends SimpleRobot //Extends (inheritance) the functionality of Thread in order to update the Robot's position
{
	private int x,y;//Location of the Robot
	private Color rColor = Color.DARK_GRAY;//Default Color
    private int xSpeed, initY, period, amplitude; //Speed in x direction, saves initial y position, period and amplitude for wave form
    /*
     * EXPERIMENT: "Bouncing" on L/R GUI border
     * found way to maintain vertical momentum when switching x direction
     * using xRunna; dot continues smooth motion instead of
     * inverting on a repeated path
     * 
     * private int xRunna
     */
    ;
    private boolean direction = true; //Tells robot to travel left or right
	public static final int ROBOT_SIZE = 15;//Robot's are 15 pixel circles
	public static final int TIME_DELAY = 30;//Update is called every 30 milliseconds.
	public WavyRobot(int aX, int aY, Color aC, int xS, int period, int amp)
	{
		super(aX, aY, aC);
        this.initY = aY;
        this.rColor = aC;
        this.xSpeed = xS;
        this.period = period;
        this.amplitude = amp;
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
	
    public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int speed) {
		this.xSpeed = speed;
	}

    public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

    public int getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(int amplitude) {
		this.amplitude = amplitude;
	}
	/**
	 * Overrides the method run in Thread. This calls the method update and sleeps the thread for 30 milliseconds before calling update again.
	 */
	public void run()
	{
        //xRunna = this.x;
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
		//Sets the x direction for the robot, bouncing when it hits the L/R edges
        if (direction) {
            this.x += this.xSpeed;
            if (this.x >= RobotThreadSimulator.FRAME_DIM - ROBOT_SIZE*2) {
                direction = false;
            }
        }
        else {
            this.x -= this.xSpeed;
            if (this.x <= 0) {
                direction = true;
            }
        }
        //xRunna+=xSpeed;
        //TO USE xRunna, must change below "this.x" to "xRunna" & remove all comment outs
        //Creates "sine" wave form for the robot
        this.y = (int)(initY + (Math.sin(this.x*period)*amplitude));
    }
}
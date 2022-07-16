/*
 * Written by Patrick Burroughs - CSCE 311
 * JJ Shepherd - 7/15/22
 * Round Robin Scheduler
 */
import java.util.*;
public class RRScheduler extends BasicScheduler {

    private int quantum; // Sets quantuminterval for the scheduler
    private int currentPTick; // Keeps track of ticks the current process has had consecutively

    public RRScheduler(int RR) {
        this.readyQ = new LinkedList<BasicPCB>();
        quantum = RR; //used to keep track of the quantum number for this instance
    }

	/**
	 * Modified to poll the linked list
	 */
    public void dispatch()
    {
		if(readyQ.isEmpty())
		{
			runningProcess = null;
			return;
		}        
        
		runningProcess = readyQ.poll();
    }

	/*
	 * Intended to check every 10 ticks to dispatch
	 */
    public void updateRunningProcess()
	{
        currentPTick++;
		if(runningProcess == null)
		{
            currentPTick = 0;
			dispatch();
			return;
		}
		runningProcess.nextLine();
		if(runningProcess.hasCompleted())
		{
			currentPTick = 0;
			runningProcess.setCompletionTick(tickCount);
			waitingTimeSum += (runningProcess.getCompletionTick() - runningProcess.getArrivalTick());
			dispatch();
		}   else if (currentPTick == quantum) {
            addProcess(runningProcess);
			currentPTick = 0;
            dispatch();
        }
	}

}

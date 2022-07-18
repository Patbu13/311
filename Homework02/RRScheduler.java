/*
 * Written by Patrick Burroughs - CSCE 311
 * JJ Shepherd - 7/15/22
 * Round Robin Scheduler
 */
import java.util.*;
public class RRScheduler extends BasicScheduler {

    private int quantum; // Sets quantuminterval for the scheduler
    
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
		if(runningProcess == null)
		{
			dispatch();
			return;
		}
		runningProcess.nextLine();
		if(runningProcess.hasCompleted())
		{
			runningProcess.setCompletionTick(tickCount);
			waitingTimeSum += (runningProcess.getCompletionTick() - runningProcess.getArrivalTick());
			dispatch();
		}   else if (runningProcess.getProgramCounter() % quantum == 0) {
			addProcess(runningProcess);
            dispatch();
        }
	}

}

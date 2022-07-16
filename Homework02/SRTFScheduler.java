/*
 * Written by Patrick Burroughs - CSCE 311
 * JJ Shepherd - 7/14/22
 * Shortest Remaining Time First Scheduler
 */
import java.util.*;

public class SRTFScheduler extends BasicScheduler {
    private int localCount; // Notifies updateRunningProcess when process is added
    public SRTFScheduler() {
        this.readyQ = new PriorityQueue<BasicPCB>();
    }

    /**
	 * Modified to POLL
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
		}
        else {
			/*
			 * Two conditions
			 * a) is the highest priority NOT running
			 * b) has there been a process added
			 */
            if (readyQ.peek() != runningProcess && localCount < totalProcesses) {
            
                localCount = totalProcesses + 1;
				getRunningProcess().setPriority(getRunningProcess().getTotalLines()-getRunningProcess().getProgramCounter());
                
            }
        }
	}

	/**
	 * When added, every process has its priority initialized correctly
	 * OR is updated to reflect progress
	 */
    public void addProcess(BasicPCB p)
	{
		totalProcesses++;
        p.setPriority(p.getTotalLines()-p.getProgramCounter());
		readyQ.add(p);
	}

}

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
	 * Selects the next process to become the running process
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
            if (readyQ.peek() != runningProcess && localCount < totalProcesses) {
                
                //addProcess(getRunningProcess());
                localCount = totalProcesses + 1;
				getRunningProcess().setPriority(getRunningProcess().getTotalLines()-getRunningProcess().getProgramCounter());
                //dispatch();
                
            }
        }
	}

    public void addProcess(BasicPCB p)
	{
		totalProcesses++;
        p.setPriority(p.getTotalLines()-p.getProgramCounter());
		readyQ.add(p);
	}

}

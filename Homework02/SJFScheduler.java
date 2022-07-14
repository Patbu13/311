/*
 * Written by Patrick Burroughs - CSCE 311
 * JJ Shepherd - 7/14/22
 * Shortest Job First Scheduler
 */

import java.util.*;

public class SJFScheduler extends BasicScheduler {
    
    public SJFScheduler() {
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

    public void addProcess(BasicPCB p)
	{
		totalProcesses++;
        p.setPriority(p.getTotalLines());
		readyQ.add(p);
	}
}

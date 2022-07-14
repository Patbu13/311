import java.util.*;
public class SJFScheduler extends BasicScheduler {
    
    public SJFScheduler() {
        this.readyQ = new PriorityQueue<BasicPCB>();
    }
}

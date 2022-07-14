import java.util.*;
public class RRScheduler extends BasicScheduler {
    
    public RRScheduler(int RR) {
        this.readyQ = new PriorityQueue<BasicPCB>();
        
    }
}

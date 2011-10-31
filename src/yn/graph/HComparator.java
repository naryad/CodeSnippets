package yn.graph;

import java.util.Comparator; 

public class HComparator<S extends State> implements Comparator<S> {
 HeuristicFunction<S> h;
 S goal; 

 public HComparator(HeuristicFunction<S> h,S goal){
   this.h = h;
   this.goal = goal;
 }
 @Override
 public int compare(S o1, S o2) {
   Double e1 = o1.getDistance()+h.getEvaluation(o1,goal);
   Double e2 = o2.getDistance()+h.getEvaluation(o2,goal);
   if(e1.doubleValue() == e2.doubleValue())
     return o2.getDistance().compareTo(o1.getDistance());
   else
    return e1.compareTo(e2);
   }
}

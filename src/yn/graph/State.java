package yn.graph;

import java.util.Set; 

public abstract class State implements Cloneable {
 Integer distance;
 State previous; 

 public Integer getDistance() {
   return distance;
 } 

 public void setDistance(int distance) {
   this.distance = distance;
 } 

 public State getPrevious() {
   return previous;
 } 

 public void setPrevious(State previous) {
   this.previous = previous;
 } 

 @SuppressWarnings("unchecked")
 public abstract Set getNeighbours() ;
}

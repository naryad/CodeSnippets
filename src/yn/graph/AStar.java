package yn.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet; 

import java.util.LinkedList;
import java.util.Set; 

public class AStar<S extends State> { 

 private S init;
 private S goal;
 private int expendedStates;
 private HeuristicFunction<S> h;
 private ArrayList<S> openSet;
 private HashSet<S> closedSet; 

 public int getExpendedStates() {
 return expendedStates;
 } 

 public AStar(S init,S goal,HeuristicFunction<S> h ){
   this.init = init;
   this.goal = goal;
   this.h = h;
   expendedStates = -1;
   init.setDistance(0);
 } 

 @SuppressWarnings("unchecked")
 public LinkedList<S> find(){
   HComparator hcomp = new HComparator(h,goal);
   openSet = new ArrayList<S>();
   closedSet = new HashSet<S>();
   S current = init;
   boolean foundOptimal = false;
   openSet.add(current);
   while(openSet.size()>0){
     Collections.sort(openSet, hcomp);//check
     current = openSet.get(0);
     ++expendedStates;
     foundOptimal = current.equals(goal);
     if(foundOptimal)
       break;
     removeState(current);
     Set<S> ne = current.getNeighbours();
     for (S state : ne){
       if(!closedSet.contains(state)){
         if(openSet.contains(state)){
           int stIndex = openSet.indexOf(state);
           if(state.getDistance()<openSet.get(stIndex).getDistance()){
            openSet.get(stIndex).setDistance(state.getDistance());
            openSet.get(stIndex).setPrevious(current);
           }
       }
       else
         openSet.add(state);
       }
     } 

   }
   LinkedList<S> result = new LinkedList<S>();
   if(!foundOptimal)
     return result;
   while(current.getPrevious()!= null){
     result.addFirst(current);
     current = (S)current.getPrevious();
   }
   result.addFirst(init);
   return result;
 } 

 private void removeState(S s){
   closedSet.add(s);
   openSet.remove(s);
 }
}

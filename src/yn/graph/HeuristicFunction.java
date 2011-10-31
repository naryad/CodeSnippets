package yn.graph;
public interface HeuristicFunction<N extends State> {
 public double getEvaluation(N current,N goal);
}

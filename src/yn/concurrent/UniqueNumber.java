package yn.concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class UniqueNumber {

    final static int MAX_SIZE = 50000;

    final ConcurrentMap<Object, Integer> cache = new ConcurrentHashMap<Object, Integer>();
    final Queue<Integer> values = new ConcurrentLinkedQueue<Integer>();

    public UniqueNumber() {

        // pre fill the queue
        for (int i = 0; i < MAX_SIZE; i++) {
            values.add(i);
        }

    }

    /**
     *
     * if the object has been seen before, returns the same integer
     * if the object has not been seen before returns a different integer.
     *
     * can hold upto 50000 values
     *
     * @param o an object
     * @return returns an int where equal objects return the same int
     */
    public Integer getUniqueIntValue(Object o) {

        final Integer result = cache.get(o);

        if (result != null)
            return result;

        final Integer candidateValue = values.poll();

        final Integer prevValue = cache.putIfAbsent(result, candidateValue);

        // we will use the new candidateValue from the queue
        if (prevValue == null)
            return candidateValue;

        // we already have a prevValue in the map so lets use that and return the candidateValue back to the queue for reuse
        values.add(candidateValue);

        return prevValue;

    }

}
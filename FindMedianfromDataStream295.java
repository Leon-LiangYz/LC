package HeapAndPriorityQueueRelated;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;


//Time: 5 * O(logn)
//Space: O(n)
public class FindMedianfromDataStream295 {


    //A min-heap to store the larger half of the input numbers
    PriorityQueue<Integer> minHeap;

    //A max-heap to store the smaller half of the input numbers
        //The max-heap lo is allowed to store, at worst, one more element more than the min-heap hi.
    PriorityQueue<Integer> maxHeap;

    public FindMedianfromDataStream295() { //Median finder
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(100, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() + minHeap.peek() / 2);
        } else {
            return maxHeap.peek();
        }
    }

    @Test
    public void test() {
        int[] dataStream = new int[]{9,5,2,2,3,0,7,12,17,102};
        for (int num : dataStream) {
            addNum(num);
        }
        System.out.println(findMedian());
    }
}

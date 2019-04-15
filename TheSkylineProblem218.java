package HeapAndPriorityQueueRelated;

/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).


The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

import org.junit.Test;

import java.util.*;

public class TheSkylineProblem218 {
    @Test
    public void test() {
        int[][] buildings = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12},
                                        {15, 20, 10}, {19, 24, 8}};
        List<int[]> res = getSkyline(buildings);

        System.out.println();
        for (int[] temp : res) {
            for (int num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

//time : O(n^2), 因为pq中的remove是O(n)
//     * space : O(n)
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        for(int[] b:buildings) {
            heights.add(new int[]{b[0], -b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        for (int[] temp : heights) {
            for (int num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        for (int[] temp : heights) {
            for (int num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(int[] h:heights) {
            if(h[1] < 0) {
                pq.offer(-h[1]); // O(logn)
            } else {
                pq.remove(h[1]); // O(n)
            }
            int cur = pq.peek();
            if(prev != cur) {
                res.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return res;
    }

    //time : O(n^2), 因为pq中的remove是O(n)
    //space : O(n) worst case
    @Test
    public void test2() {
        int[][] buildings = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12},
                {15, 20, 10}, {19, 24, 8}};
        List<int[]> res = getSkyline2(buildings);

        System.out.println();
        for (int[] temp : res) {
            for (int num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private class EndPoint implements Comparable<EndPoint> {
        int x;
        int height;
        boolean isEnd;

        public EndPoint (int x, int height, boolean isEnd) {
            this.x = x;
            this.height = height;
            this.isEnd = isEnd;
        }

        @Override
        public int compareTo(EndPoint that) {
            if (this.x != that.x) {
                return this.x - that.x;
            } else {
                if (!this.isEnd && !that.isEnd) { //both left points: height desc
                    return that.height - this.height;
                } else if (this.isEnd && that.isEnd) { // both right points: height aces
                    return this.height - that.height;
                } else { // one left and one right point: left point first
                    return this.isEnd ? 1 : -1;
                }
            }
        }

    }

    public List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0
                || buildings[0] == null || buildings[0].length == 0) {
            return res;
        }

        List<EndPoint> endPoints = new ArrayList<>();

        for (int[] b : buildings) {
            endPoints.add(new EndPoint(b[0], b[2], false));
            endPoints.add(new EndPoint(b[1], b[2], true));
        }

        Collections.sort(endPoints);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (EndPoint ep : endPoints) {
            if (!ep.isEnd) { // left point
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    res.add(new int[]{ep.x, ep.height});
                }
                maxHeap.offer(ep.height);
            } else { // right point
                maxHeap.remove(ep.height); // O(n)
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    res.add(new int[]{ep.x, maxHeap.isEmpty() ? 0 : maxHeap.peek()});
                }
            }
        }
        return res;
    }




    //time : O(nlogn), 因为treeset中的remove是O(logn)
    //space : O(n) worst case
    //利用自定义的endPoint, 让treeset能够除了保存各个点的属性外，还保存被加入list时的index
    @Test
    public void test3() {
        int[][] buildings = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12},
                {15, 20, 10}, {19, 24, 8}};
        List<int[]> res = getSkyline2(buildings);

        System.out.println();
        for (int[] temp : res) {
            for (int num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }


    public class EndPointwithIndex implements Comparable<EndPointwithIndex> {
        int x, height, index;
        boolean isEnd;

        public EndPointwithIndex(int x, int height, int index, boolean isEnd) {
            this.x = x;
            this.height = height;
            this.index = index;
            this.isEnd = isEnd;
        }

        @Override
        public int compareTo(EndPointwithIndex that) {
            if (this.x != that.x) {
                return this.x - that.x;
            } else if (!this.isEnd && !that.isEnd){ // both left, sort by height desc
                return that.height - this.height;
            } else if (this.isEnd && that.isEnd) {
                return this.height - that.height; // both right point, sort by height aces
            } else {
                return this.isEnd ? 1 : -1; // one left and one right point: left point first
            }
        }
    }

    public List<int[]> getSkyline3(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0
                || buildings[0] == null || buildings[0].length == 0) {
            return res;
        }

        List<EndPointwithIndex> endPoints = new ArrayList<>();
        List<EndPointwithIndex> leftPoints = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] b = buildings[i];
            endPoints.add(new EndPointwithIndex(b[0], b[2], i,false));
            endPoints.add(new EndPointwithIndex(b[1], b[2], i,true));
            leftPoints.add(new EndPointwithIndex(b[0], b[2], i,false));
        }

        Collections.sort(endPoints);

        Comparator<Integer> comparator = (a, b) -> leftPoints.get(a).height != leftPoints.get(b).height ?
                Integer.compare(leftPoints.get(b).height, leftPoints.get(a).height) : a - b;

        TreeSet<Integer> heap = new TreeSet<>(comparator);

        for (EndPointwithIndex ep : endPoints) {
            if (!ep.isEnd) { // left point
                if (heap.isEmpty() || ep.height > leftPoints.get(heap.first()).height) {
                    res.add(new int[]{ep.x, ep.height});
                }
                heap.add(ep.index);
            } else { // right point
                heap.remove(ep.index); // O(logn)
                if (heap.isEmpty() || ep.height > leftPoints.get(heap.first()).height) {
                    res.add(new int[]{ep.x, heap.isEmpty() ? 0 : leftPoints.get(heap.first()).height});
                }
            }
        }
        return res;
    }

}

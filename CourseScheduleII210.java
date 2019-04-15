package GraphRelated;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII210 {
    @Test
    public void test() {
        int numCourses = 3;
        int[][] prerequisites = new int[][]{{1, 0}, {1, 2}};
        int[] res = findOrder(numCourses, prerequisites);
        for (int val : res) {
            System.out.print(val + " ");
        }
    }

    //time : O(V + E)
    //space : O(n)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        int[] res = new int[numCourses];

        for (int[] pair : prerequisites) {
            inDegree[pair[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        int k = 0; // index of res
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                res[k++] = i;
            }
        }

        while (!queue.isEmpty()) {
            int preCourse = queue.poll();

            for (int[] pair : prerequisites) {
                if (pair[1] == preCourse) {
                    inDegree[pair[0]]--;
                    if (inDegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                        res[k++] = pair[0];
                    }
                }
            }
        }
        return k == numCourses ? res : new int[0];

    }

    @Test
    public void test2() {
        int numCourses = 3;
        int[][] prerequisites = new int[][]{{1, 0}, {1, 2}};
        int[] res = findOrder2(numCourses, prerequisites);
        for (int val : res) {
            System.out.print(val + " ");
        }
    }

    public enum Status {
        INITIAL,
        PROCESSING,
        DONE;
    }

    public class V {
        int label;
        public List<Integer> nexts;
        public Status status;

        public V (int val) {
            this.label = val;
            nexts = new ArrayList<>();
            this.status = Status.INITIAL;
        }
    }
    private int curIdx = 0;
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
//        if (numCourses < 1 || prerequisites == null
//                || prerequisites.length == 0 || prerequisites[0] == null || prerequisites[0].length == 0) {
//            return false;
//        }

        V[] courses = new V[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new V(i);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int preCourse = prerequisites[i][1];
            int nextCourse = prerequisites[i][0];
            courses[preCourse].nexts.add(nextCourse);
        }

        int[] res = new int[numCourses];
        curIdx = numCourses - 1;
        //check if there is cycle
        for (int i = 0; i < numCourses; i++) {
            if (isCycle(courses[i], courses, res)) {
                return new int[0];
            }
        }
        return res;
    }

    private boolean isCycle(V curCourse, V[] courses, int[] res) {
        if (curCourse.status == Status.DONE) return false;// pruning
        if (curCourse.status == Status.PROCESSING) return true;
        curCourse.status = Status.PROCESSING;

        for (int next : curCourse.nexts) {
            if (isCycle(courses[next], courses, res)) {
                return true;
            }
        }
        curCourse.status = Status.DONE;
        res[curIdx--] = curCourse.label;
        return false;
    }
}

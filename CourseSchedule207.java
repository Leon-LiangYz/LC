package GraphRelated;
/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule207 {
    @Test
    public void test() {
        int numCourses = 3;
        int[][] prerequisites = new int[][]{{1, 0}, {1, 2}};
        boolean res = canFinish(numCourses, prerequisites);
        System.out.println(res);
    }
    //time: O(V + E), V is number of course, E is length of prerequisites
    //Space: O(n)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        int res = numCourses;

        //initialize the in degree for each course
        for (int[] pair : prerequisites) {
            inDegree[pair[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i); // course is ready to be taken when in-degree is 0

            }
        }

        while (!queue.isEmpty()) {
            int preCourse = queue.poll();
            res--;

            for (int[] pair : prerequisites) {
                if (pair[1] == preCourse) {
                    inDegree[pair[0]]--;
                    if (inDegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }
        return res == 0;
    }

    @Test
    public void test2() {
        int numCourses = 3;
        int[][] prerequisites = new int[][]{{1, 0}, {1, 2}};
        boolean res = canFinish2(numCourses, prerequisites);
        System.out.println(res);
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

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
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

        //check if there is cycle
        for (int i = 0; i < numCourses; i++) {
            if (isCycle(courses[i], courses)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCycle(V curCourse, V[] courses) { // if isCycle, not topo
        if (curCourse.status == Status.DONE) return false;// pruning
        if (curCourse.status == Status.PROCESSING) return true;
        curCourse.status = Status.PROCESSING;

        for (int next : curCourse.nexts) {
            if (isCycle(courses[next], courses)) {
                return true;
            }
        }
        curCourse.status = Status.DONE;
        return false;
    }
}

package HeapAndPriorityQueueRelated;

/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.

 */

import DPRelated.EditDistance72;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeFreeTime759 {

    public class Interval {
      int start;
      int end;
      public Interval() {
          start = 0;
          end = 0;
      }
      public Interval(int s, int e) {
          start = s;
          end = e;
      }
    }

    @Test
    public void test() {
        List<List<Interval>> schedule = new ArrayList<>();
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(5, 6);
        Interval i3 = new Interval(1, 3);
        Interval i4 = new Interval(4, 10);
        //Interval i5 = new Interval(9, 12);

        List<Interval> l1 = new ArrayList<>();
        l1.add(i1);
        List<Interval> l2 = new ArrayList<>();
        l2.add(i2);
        List<Interval> l3 = new ArrayList<>();
        l3.add(i3);
        List<Interval> l4 = new ArrayList<>();
        l4.add(i4);
//        List<Interval> l5 = new ArrayList<>();
//        l5.add(i5);

        schedule.add(l1);
        schedule.add(l2);
        schedule.add(l3);
        schedule.add(l4);
        //schedule.add(l5);

        List<Interval> res = employeeFreeTime(schedule);
        for (Interval itv : res) {
            System.out.println(itv.start + "," + itv.end);
        }

    }

    public class EndPoint implements Comparable<EndPoint> {
        int val;
        boolean isStart;
        public EndPoint (int x, boolean isStart) {
            this.val = x;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(EndPoint ep) {
            if (this.val != ep.val) {
                return this.val - ep.val;
            } else {
                if (this.isStart) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
    //Time: O(nlogn)
    //Space: O(n)
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if (schedule == null || schedule.size() == 0) {
            return res;
        }
        List<Interval> result = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();

        for (List<Interval> list : schedule) {
            for (Interval itv : list) {
                timeLine.add(new Interval(itv.start, itv.end));
            }
        }

        Collections.sort(timeLine, (a, b) -> (a.start - b.start));

        int start = timeLine.get(0).start;
        int end = timeLine.get(0).end;
        for (Interval cur : timeLine) {
            if (cur.start <= end) {
                end = Math.max(end, cur.end);
            } else {
                result.add(new Interval(end, cur.start));
                end = cur.end;
            }
        }
        return result;
    }

    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if (schedule == null || schedule.size() == 0) {
            return res;
        }

        List<EndPoint> endPoints = new ArrayList<>();

        for (List<Interval> list : schedule) {
            for (Interval itv : list) {
                endPoints.add(new EndPoint(itv.start, true));
                endPoints.add(new EndPoint(itv.end, false));
            }
        }

        Collections.sort(schedule, (a, b) -> (a.get(0).start - b.get(0).start));
        for (List<Interval> list : schedule) {
            for (Interval itv : list) {
                System.out.println(itv.start + "," + itv.end);
            }
        }


//        for (int i = 0; i < endPoints.size(); i++) {
//            if (endPoints.get(i).isStart) {
//                continue;
//            } else if (!endPoints.get(i).isStart) {
//                if (i != endPoints.size() - 1 && endPoints.get(i + 1).isStart
//                        && endPoints.get(i).val != endPoints.get(i + 1).val) {
//                    System.out.println(endPoints.get(i).val + "," + endPoints.get(i + 1).val);
//                    res.add(new Interval(endPoints.get(i).val, endPoints.get(i + 1).val));
//                }
//            }
//        }

        int start = schedule.get(0).get(0).start;
        int end = schedule.get(0).get(0).end;

        for (List<Interval> list : schedule) {
            for (Interval itv : list) {
                int curStart = itv.start;
                int curEnd = itv.end;
                if (curStart > end) {
                    res.add(new Interval(end, curStart));
                }
                end = Math.max(end, curEnd);
            }
        }

        for (Interval itv : res) {
            System.out.println(itv.start + "," + itv.end);
        }

        return res;
    }
}

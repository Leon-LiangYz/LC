package DesignRelated;
/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6]

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZigzagIterator281 {

    private Iterator<Integer> i, j, temp;
    public static void main(String[] args) {
        List<Integer> v1 = new ArrayList<>();
        v1.add(1);
        v1.add(2);
        v1.add(5);
//        v1.add(6);

        List<Integer> v2 = new ArrayList<>();
        v2.add(3);
//        v2.add(4);
//        v2.add(5);
//        v2.add(6);

        ZigzagIterator281 ob = new ZigzagIterator281(v1, v2);
        int res = ob.next();
        System.out.println(res);
        System.out.println(ob.next());
        System.out.println(ob.next());
        System.out.println(ob.next());
//        System.out.println(ob.next());
//        System.out.println(ob.next());
        //System.out.println(ob.next());

    }


    public ZigzagIterator281(List<Integer> v1, List<Integer> v2) {
        i = v1.iterator();
        j = v2.iterator();
    }

    public int next() {
        if (i.hasNext()) {
            temp = i;
            i = j;
            j = temp;
        }
        return j.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}

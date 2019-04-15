package MathRelated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaxPointsonaLine149 {
    class Point {
        int x;
        int y;
        public Point() {
            this.x = 0;
            this.y = 0;
        }
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Test
    public void test() {
        Point[] points = new Point[3];
        List<Point> pl = new ArrayList<>();
        Point a = new Point(0, 1);
        Point b = new Point(0, 2);
        Point c = new Point(0, 9);
        pl.add(a);
        pl.add(b);
        pl.add(c);
        for (int i = 0; i < points.length; i++) {
            points[i] = pl.get(i);
        }
        int res = maxPoints(points);
        System.out.println(res);
    }

    public int maxPoints(Point[] points) {
        if (points == null) return 0;
        if (points.length <= 2) return points.length;

        int res = 2;

        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int samePoints = 0;
            int curMaxPoints = 0;
            for (int j = i + 1; j < points.length; j++) {
                //defining delta X and delta Y
                int dX = points[j].x - points[i].x;
                int dY = points[j].y - points[i].y;
                if (dX == 0 && dY == 0) {
                    samePoints++;
                    continue;
                }

                //standardizing dX and dY
                int gcd = generateGCD(dX, dY);
                if (gcd != 0) {
                    dX /= gcd;
                    dY /= gcd;
                }

                if (map.containsKey(dX)) {
                    if (map.get(dX).containsKey(dY)) {
                        map.get(dX).put(dY, map.get(dX).get(dY) + 1);
                    } else {
                        map.get(dX).put(dY, 1);
                    }
                } else {
                    //tmpMap -> Key: dY of dX; Value: count of points on the same line with std dX and dY
                    HashMap<Integer, Integer> tmpMap = new HashMap<>();
                    tmpMap.put(dY, 1);
                    map.put(dX, tmpMap);
                }
                curMaxPoints = Math.max(curMaxPoints, map.get(dX).get(dY));
            }
            res = Math.max(res, curMaxPoints + samePoints + 1);
        }
        return res;
    }
    private int generateGCD(int a,int b){
        if (b == 0) return a;
        else return generateGCD(b,a % b);
    }
}

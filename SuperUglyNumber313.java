package HeapAndPriorityQueueRelated;

import org.junit.Test;

import java.util.*;

//Time: O(nlogk)
//space: O(max(n, k))
public class SuperUglyNumber313 {

    class Num {
        int val;
        int index;
        int prime;
        public Num (int x, int index, int prime) {
            this.val = x;
            this.index = index;
            this.prime = prime;
        }
    }
//    @Test
//    public void test1() {
//        List<int[]> primesList = new ArrayList<>();
//        int[] primes1 = new int[]{2, 7, 13, 19};
//        int[] primes2 = new int[]{2, 3, 5};
//        int[] primes3 = new int[]{2, 3};
//        primesList.add(primes1);
////        primesList.add(primes2);
////        primesList.add(primes3);
//        int n = 8;
//        int i = 1;
//        for (int[] primes : primesList) {
//            int res = nthSuperUglyNumber(n, primes);
//            System.out.println("Nth super ugly number in primes " + i + ": " + res);
//            i++;
//        }
//    }
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;

        PriorityQueue<Num> pq = new PriorityQueue<>(new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
//                if (o1.val == o2.val) {
//                    return o1.index - o2.index;
//                }
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < primes.length; i++) {
            pq.add(new Num(primes[i], 1, primes[i]));
        }
        for (int i = 1; i < n; i++) {
            res[i] = pq.peek().val;
            //System.out.println(res[i] + " " + pq.peek().val);
            while (pq.peek().val == res[i]) {
                Num next = pq.poll();
                pq.add(new Num(next.prime * res[next.index], next.index + 1, next.prime));
//                for (Num num : pq) {
//                    System.out.print(num.val + " " + num.index + " " + num.prime);
//                    System.out.println();
//                }
//                System.out.println("-----------");
//                System.out.print(res[i] + " ");
//                System.out.print(pq.peek().val + " " + pq.peek().index + " " + pq.peek().prime);
//                System.out.println();
            }
        }

//        for (int num : res) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
        return res[n - 1];
    }

    @Test
    public void test2() {
        List<int[]> primesList = new ArrayList<>();
        int[] primes1 = new int[]{2, 3};
        int[] primes2 = new int[]{2, 3, 5};
        int[] primes3 = new int[]{2, 3};
        primesList.add(primes1);
//        primesList.add(primes2);
//        primesList.add(primes3);
        int n = 8;
        int i = 1;
        for (int[] primes : primesList) {
            int res = nthSuperUglyNumber2(n, primes);
            System.out.println("Nth super ugly number in primes2 " + i + ": " + res);
            i++;
        }
    }
    //https://massivealgorithms.blogspot.com/search?q=super+ugly+number
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);

        int next = 1;
        for (int i = 0; i < n; i++) {
            ugly[i] = next;

            next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                //skip duplicate and avoid extra multiplication
                if (val[j] == ugly[i]) {
                    val[j] = ugly[idx[j]++] * primes[j];
                }
                //find next ugly number
                next = Math.min(next, val[j]);
            }
        }

        return ugly[n - 1];
    }

}

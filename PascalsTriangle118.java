package DPRelated;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle118 {
    public static void main(String[] args) {
        int numRows = 3;
        PascalsTriangle118 pt118 = new PascalsTriangle118();
        List<List<Integer>>  res = pt118.generate(numRows);
        System.out.println(res);
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows < 0) return result;
        List<Integer> tmpList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            tmpList.add(0, 1);
            for (int j = 1; j < tmpList.size() - 1; j++) {
                int preLeft = tmpList.get(j);
                int preRight = tmpList.get(j + 1);
                tmpList.set(j, preLeft + preRight);
            }
            result.add(new ArrayList<>(tmpList));
        }
        return result;
    }

}

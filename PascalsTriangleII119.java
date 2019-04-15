package DPRelated;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII119 {
    public static void main(String[] args) {
        int rowIndex = 3;
        PascalsTriangleII119 pt119 = new PascalsTriangleII119();
        List<Integer> res = pt119.getRow(rowIndex);
        System.out.println(res);
    }
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0) return res;

        for (int i = 0; i <= rowIndex; i++) {
            res.add(0, 1);
            System.out.println(res);
            for (int j = 1; j < res.size() - 1; j++) {
                int preLeft = res.get(j);
                int preRight = res.get(j + 1);
                res.set(j, preLeft + preRight);
            }
        }

        return res;
    }
}

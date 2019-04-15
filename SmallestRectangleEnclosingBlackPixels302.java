package BinarySearchRelated;

import org.junit.Test;

public class SmallestRectangleEnclosingBlackPixels302 {
    @Test
    public void test () {
        char[][] image = new char[][]{{'0','0','1','0'}, {'0','1','1','0'},{'0','1','0','0'}};
        int x = 0, y = 2;
        int res = minArea(image, x, y);
        System.out.println(res);
    }
    //Assuming m rows, n cols
    //Time: O(mlogn + nlogm)
    //Space: O(1)
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0 || x < 0 || y < 0) {
            return 0;
        }
        int rowTot = image.length;
        int colTot = image[0].length;

        int left = binarySearchLeft(image, 0, y, true);
        int right = binarySearchRight(image, y, colTot - 1, true);
        int top = binarySearchLeft(image, 0, x, false);
        int bottom = binarySearchRight(image, x, rowTot - 1, false);

        return (right - left + 1) * (bottom - top + 1);
    }

    private int binarySearchLeft(char[][] image, int start, int end, boolean isHorizontal) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (hasBlackPixel(image, mid, isHorizontal)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (hasBlackPixel(image, start, isHorizontal)) {
            return start;
        }
        return end;
    }

    private int binarySearchRight(char[][] image, int start, int end, boolean isHorizontal) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (hasBlackPixel(image, mid, isHorizontal)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (hasBlackPixel(image, end, isHorizontal)) {
            return end;
        }
        return start;
    }

    private boolean hasBlackPixel(char[][] image, int index, boolean isHorizontal) {
        if (isHorizontal) {
            for (int i = 0; i < image.length; i++) {
                if (image[i][index] == '1') {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < image[0].length; i++) {
                if (image[index][i] == '1') {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test2 () {
        char[][] image = new char[][]{{'0','0','1','0'}, {'0','1','1','0'},{'0','1','0','0'}};
        int x = 0, y = 2;
        int res = minArea2(image, x, y);
        System.out.println(res);
    }
    //Assuming m rows, n cols
    //Time: O(mlogn + nlogm)
    //Space: O(1)
    public int minArea2(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0 || x < 0 || y < 0) {
            return 0;
        }
        int rowTot = image.length;
        int colTot = image[0].length;

        int left = binarySearchLeft2(image, 0, y, true);
        int right = binarySearchRight2(image, y, colTot - 1, true);
        int top = binarySearchLeft2(image, 0, x, false);
        int bottom = binarySearchRight2(image, x, rowTot - 1, false);

        return (right - left + 1) * (bottom - top + 1);
    }

    private int binarySearchLeft2(char[][] image, int start, int end, boolean isCheckRow) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (hasBlackPixel2(image, mid, isCheckRow)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
//        if (hasBlackPixel(image, start, isCheckRow)) {
//            return start;
//        }
//        return end;
        return start;
    }

    private int binarySearchRight2(char[][] image, int start, int end, boolean isCheckRow) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (hasBlackPixel2(image, mid, isCheckRow)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
        // if (hasBlackPixel2(image, end, isCheckRow)) {
        //     return end;
        // }
        //return end - 1;

    }

    private boolean hasBlackPixel2(char[][] image, int index, boolean isCheckRow) {
        if (isCheckRow) {
            for (int i = 0; i < image.length; i++) {
                if (image[i][index] == '1') {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < image[0].length; i++) {
                if (image[index][i] == '1') {
                    return true;
                }
            }
        }
        return false;
    }
}

package BitRelated;
import org.junit.Test;


public class SingleNumber136 {
    int[] nums;
    public SingleNumber136 () {
        nums = new int[]{2, 1, 2};
    }

    @Test
    public void test1() {
        int result = singleNumber(nums);
        System.out.println(result);
    }


    public int singleNumber(int[] nums) {
        int res = 0;
        //需要知道xor有交换律
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}

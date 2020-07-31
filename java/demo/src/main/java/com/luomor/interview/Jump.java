package com.luomor.interview;

public class Jump {
    /**
     * h
     * 3 5 7 2 1 1 1    int
     * 3 9 7 8
     * <p>
     * 1. 每个元素值意思是可以跳跃的距离（往后的元素个数）
     * 2. length = n, elem > 0
     * 3. 跳跃大于数组长度就算跳出，不必到最后一个位置
     * 4. 要求最快跳出，最快意思是跳的次数最少
     **/
    public int getJumpFast(int[] nums) {
        int step = 0;
        for(int i = 0 ; i < nums.length - 1 ; i++) {
            if(i + nums[i] > step) {
                step = i + nums[i];
            }
            for(int j = 1 ; j < step ; j++) {
                if(i + nums[i + j] >= nums.length) {
                    return i + j;
                }
            }
        }
        return nums.length - 1;
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个位置。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int step = 0;
        while (step != nums.length - 1 && nums[step] != 0) {
            if (nums[step] >= nums.length - step - 1) {
                step = nums.length - 1;
                break;
            }
            int max = -1;
            int maxj = -1;
            for (int j = step + 1; j < nums[step] + step + 1 && j < nums.length; j++) {
                if (j + nums[j] > max) {
                    max = j + nums[j];
                    maxj = j;
                }
            }
            step = maxj;
        }
        if (step == nums.length - 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Jump jump = new Jump();
        int[] nums = {3,5,7,2,1,1,1};
        System.out.println(jump.getJumpFast(nums));
    }
}

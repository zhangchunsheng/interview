package com.luomor.demo.leetcode;

public class MaxDistToClosest {
    public int maxDistToClosest(int[] seats) {
        int start = -1;//连续0起始位置
        int end = -1;//连续0结束位置
        int max = 0; // 连续0数量
        int dis = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0 && max == 0) {
                max += 1;
                start = i;
            } else if (seats[i] == 0 && max != 0) {
                max += 1;
                end = i;
            } else {
                if (start == 0 && max > 0) {
                    dis = max;
                } else if (end == seats.length - 1 && max > 0) {
                    dis = dis > max ? dis : max;
                } else if (max > 0) {
                    dis = dis > (max + 1) / 2 ? dis : (max + 1) / 2;
                }
                max = 0;
                start = -1;
                end = -1;
            }
        }
        if (end == seats.length - 1 && max > 0) {
            return dis = dis > max ? dis : max;
        }
        return dis;
    }
}

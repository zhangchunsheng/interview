package com.luomor.interview;

import java.util.Scanner;

/**
 * 问题分解
 * 华为这道题出的比较难，问题不仅涉及动态规划，更涉及到后续洗杯子的问题。
 * 所以解题分为两部分：
 *
 * 通过动态规划，计算每个咖啡机锁需要煮的咖啡数目
 * 通过某种策略，计算洗杯子所需要的最小时间
 * 动态规划求解咖啡机的任务分配
 * 动态规划的两大要点 ==》
 *
 * 定义子问题的形式
 * 定义在所有子问题上通用的处理逻辑
 * 子问题的形式
 * 在m个咖啡机的前提下，由j（1<=j<=n）来唯一的定义一个子问题。
 *
 * 处理逻辑
 * 当j等于1时，我们必然选择生产速度最快的咖啡机
 * 当j>1时，我们已知j-1时最小化生产时间的任务分配计划，我们计算每一台咖啡机增加一杯咖啡所后需要花费的总时间，然后我们选择总时间最小的那一个。当总时间相等时，选择生产速度快的那台。
 *
 * 实现方式
 * 在处理逻辑这一小节中，我们看出来，在每个子问题中，我们都只需要选择最优的那一台咖啡机。
 * 在这种情况下，我们使用堆排序的方式，将大大减小选择时间。
 * 我们定义了一个子类，用来描述咖啡机的任务数，工作速度，索引编号，该咖啡机生产的咖啡中已清洗的杯子数。
 * 按照处理逻辑中的选择方式重新定义了子类的compareTo方法，实现堆排序，不断选择最优的咖啡机，对其任务信息进行增加，直到j==n为止。
 *
 * 贪心策略，计算洗杯子所需要的最小时间
 * 这里我们选择了贪心策略来解决这一问题，由于是笔试完之后想出来的，所以没有在线上测试。后经过同学提醒，这种策略，在某种情况下回出错。因为贪心策略只是求解局部最优，而非全局最优。
 *
 * 本文中采取的策略是，根据第一步中生成的任务分配信息，从t=0开始计算下一个生成的咖啡时间，然后计算当前使用咖啡机更快，还是任其自由风干更快，如果使用咖啡机则需要改变咖啡机的可用时间。
 *
 * 第一行输入n, m, x, y，表示n个人，m台咖啡机，洗一个杯子的时间，咖啡自然挥发的时间
 * 第二行输入m个数字，分别表示每台咖啡机煮一杯咖啡所需的时间
 *
 * 6 2 0 0
 * 6 2 0 0
 * 6 10
 * 分配之后的任务信息：
 * taskIndex: 0 taskTime: 6 taskNumber: 4 hasWashed: 0
 * taskIndex: 1 taskTime: 10 taskNumber: 2 hasWashed: 0
 */
public class Task {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m, x, y;
        int num = sc.nextInt();
        String noUse = sc.nextLine();

        int[] results = new int[num];
        int idx = 0;
        while (num > 0) {
            num--;
            String firstLine = sc.nextLine();
            String[] nums = firstLine.split(" ");
            n = Integer.valueOf(nums[0]);
            m = Integer.valueOf(nums[1]);
            x = Integer.valueOf(nums[2]);
            y = Integer.valueOf(nums[3]);

            int[] V = new int[m];
            for (int i = 0; i < m; i++) {
                V[i] = sc.nextInt();
            }
            noUse = sc.nextLine();
            int[] temp = new int[n + 1];
            temp[0] = x;
            for (int i = 1; i < temp.length; i++) {
                temp[i] = y;
            }
            results[idx++] = getRes(n, m, x, y, V);
        }

        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i]);
            if (i != results.length - 1) {
                System.out.println();
            }
        }

    }

    /**
     * @param n:              任务数，在本题中为需要咖啡的人数
     * @param m:              机器数，在本题中为咖啡机的个数
     * @param x:              洗杯机每洗一只杯子需要的时间
     * @param y:              每个人手动洗杯子需要的时间
     * @param V：每台机器完成任务所需的时间
     * @return 所有人喝完咖啡所需的最小时间
     */
    public static int getRes(int n, int m, int x, int y, int[] V) {
        int washMathineFinishTime = -1;
        int washAllFinishTime = 0;

        //step1: 初始化，每台机器的任务情况，初始任务数都为0
        taskInfo[] taskInfos = new taskInfo[m];
        for (int i = 0; i < m; i++) {
            taskInfos[i] = new taskInfo(V[i], i);
        }

        //step2: 计算能让每个人都最快喝上coffee的分配计划
        MinHeap.buildHeap(taskInfos);
        MinHeap.heapSize = m;
        for (int i = 0; i < n; i++) {
            taskInfos[0].taskNumber += 1;
            MinHeap.doHeapify(taskInfos, 1);
        }

        System.out.println("分配之后的任务信息： ");
        for (int i = 0; i < m; i++) {
            System.out.println(taskInfos[i]);
        }

        //step3: 计算洗杯子的最小时间
        boolean isWashFinish = false;
        while (true) {
            isWashFinish = true;
            int earlistTime = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < taskInfos.length; i++) {
                if (taskInfos[i].hasWashed < taskInfos[i].taskNumber) {
                    isWashFinish = false;
                    if ((taskInfos[i].hasWashed + 1) * taskInfos[i].taskTime < earlistTime) {
                        earlistTime = (taskInfos[i].hasWashed + 1) * taskInfos[i].taskTime;
                        index = i;
                    }
                }

            }
            if (isWashFinish) return washAllFinishTime;

            taskInfos[index].hasWashed += 1;
            if (washMathineFinishTime < 0) {
                if (x < y) {
                    washMathineFinishTime = earlistTime + x;
                    washAllFinishTime = earlistTime + x;
                } else {
                    washAllFinishTime = earlistTime + y;
                }
            } else {
                if ((washMathineFinishTime + x) < earlistTime + y) {
                    washMathineFinishTime = washMathineFinishTime + x;
                    washAllFinishTime = washMathineFinishTime;
                } else {
                    washAllFinishTime = earlistTime + y;
                }
            }

        }

    }

    static class MinHeap {
        static int heapSize = 0;

        static int leftChild(int i) {
            return 2 * i;
        }

        static int rightChild(int i) {
            return 2 * i + 1;
        }

        static void buildHeap(taskInfo[] data) {
            for (int i = data.length / 2; i >= 1; i--) {
                doHeapify(data, i);
            }
        }

        static void doHeapify(taskInfo[] data, int index) {
            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);
            int minIndex;
            if (leftIndex <= heapSize && data[index - 1].compareTo(data[leftIndex - 1]) > 0) {
                minIndex = leftIndex;
            } else {
                minIndex = index;
            }
            if (rightIndex <= heapSize && data[minIndex - 1].compareTo(data[rightIndex - 1]) > 0) {
                minIndex = rightIndex;
            }
            if (minIndex != index) {
                taskInfo temp = data[minIndex - 1];
                data[minIndex - 1] = data[index - 1];
                data[index - 1] = temp;

                doHeapify(data, minIndex);
            }
        }

    }

    static class taskInfo implements Comparable<taskInfo> {
        int taskNumber = 0;
        int taskTime;
        int taskIndex;
        int hasWashed = 0;

        taskInfo(int time, int idx) {
            taskTime = time;
            taskIndex = idx;
        }

        public int compareTo(taskInfo info2) {
            if (((info2.taskNumber + 1) * info2.taskTime > (this.taskNumber + 1) * this.taskTime) ||
                    ((info2.taskNumber + 1) * info2.taskTime == (this.taskNumber + 1) * this.taskTime && info2.taskTime > this.taskTime)) {
                return -1;
            }
            return 1;
        }

        @Override
        public String toString() {
            return "taskIndex: " + taskIndex + " taskTime: " + taskTime + " taskNumber: " + taskNumber + " hasWashed: " + hasWashed;
        }
    }
}

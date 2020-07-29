package com.luomor.interview;

import java.util.Scanner;

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

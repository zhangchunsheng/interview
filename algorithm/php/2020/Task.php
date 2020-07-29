<?php
/**
 * Created by PhpStorm.
 * User: peterzhang
 * Date: 2020/7/29
 * Time: 3:30 PM
 */

/**
 * 假定有N个相同的任务需要调度到M台机器上, 假定每台机器的执行任务的时间不同,
 * 例如有a,b两台机器, 执行一个任务需要6min和10min, 有6个task待调度, 如果平均分配各执行3个,
 * 则最短需要30min执行完, 假定a机器执行4个任务, b执行2个task, 则最短24分钟执行,
 * 求N个任务,M台机器执行完的最短时间的调度设计?
 *
 * https://www.jianshu.com/p/e38a839a30b0 最小堆
 * 输入为m台服务器，每台机器处理一个任务的时间为t[i]，完成n个任务，输出n个任务在m台服务器的分布
 */
class Task {
    public static function minTask(array $taskMachine, int $machineNum) {
        $tmp = $taskMachine[0];
        $min = 0;
        for($i = 0 ; $i < $machineNum ; $i++) {
            if($tmp > $taskMachine[$i]) {
                $tmp = $taskMachine[$i];
                $min = $i;
            }
        }
        return $min;
    }


    public static function getShortTime(int $taskNum = 6, int $machineNum = 2, array $time = array(6, 10)): int {
        return 1;
    }
}

$time = array(10, 6);
echo Task::getShortTime(6, 2, $time);
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
        $taskMachine = array();
        $tasks = array();
        for ($i = 0; $i < $taskNum; $i++) {
            $taskMachine[] = 0;
            $tasks[] = array();
        }
        // 动态调度算法

        for($i = 0 ; $i < $machineNum ; $i++) {
            $j = self::minTask($taskMachine, $machineNum);
		    $tj = $taskMachine[$j];
            $taskMachine[$j] = $tj + $time[$i];
            $tasks[$j][] = $i + 1;
		}
        for($i = 0 ; $i < count($tasks) ; $i++) {
            for($j = 0 ; $j < count($tasks[$i]) ; $j++) {
                echo $tasks[$i][$j] . "\n";
            }
        }

        return 1;
    }
}

$time = array(6, 10);
echo Task::getShortTime(6, 2, $time);
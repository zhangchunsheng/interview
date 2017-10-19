<?php
// 一个整数数组 一个整数 求比整数大的最小组合
$a = array(2, 7, 3);
$num = 1234;

function quickSort(&$data, $left, $right) {
    if($left < $right) {
        $i = $left;
        $j = $right;
        $x = $data[$i];
        while($i < $j) {
            while($i < $j && $data[$j] >= $x) {
                $j--;
            }
            if($i < $j) {
                $data[$i] = $data[$j];
                $i++;
            }
            while($i < $j && $data[$i] < $x) {
                $i++;
            }
            if($i < $j) {
                $data[$j] = $data[$i];
                $j--;
            }
        }
        $data[$i] = $x;
        quickSort($data, $left, $i - 1);
        quickSort($data, $i + 1, $right);
    }
}

quickSort($a, 0, count($a) - 1);

function getMinNum($a, $num) {
    $minNum = 0;
    $length = strlen($num);

    $i = $length - 1;

    $data = array();

    list($index) = hasMaxNum($a, $num, $i, $data);
    if($index >= 0) {
        for($j = $i ; $j >= $index ; $j--) {
            $dive = pow(10, $j);
            $minNum += (int)$data[$j] * $dive;
        }
        for($j = $index - 1 ; $j >= 0 ; $j--) {
            $dive = pow(10, $j);
            $minNum += (int)$a[0] * $dive;
        }
    } else {
        // add length
        $dive = pow(10, $length);
        $minNum += (int)$a[0] * $dive;
        for($j = $length - 1 ; $j >= 0 ; $j--) {
            $dive = pow(10, $j);
            $minNum += (int)$a[0] * $dive;
        }
    }

    return $minNum;
}

function hasMaxNum($a, $num, $index, &$data) {
    $dive = pow(10, $index);
    $x = (int)($num / $dive);

    $value = -1;
    for($j = 0 ; $j < count($a) ; $j++) {
        if($a[$j] >= $x) {
            $value = $a[$j];
            break;
        }
    }
    $data[$index] = $value;
    if($value > $x) {
        return array($index);
    } elseif($value == $x) {
        $nNum = (int)($num % $dive);
        $index--;
        return hasMaxNum($a, $nNum, $index, $data);
    } else {
        return array(-1);
    }
}

echo getMinNum($a, $num);
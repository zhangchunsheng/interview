<?php
// 一根N米绳子，奇数剪一米，偶数剪一半，直到1米或2米，有多少段
// 1 12
// 1 6 6
// 1 3 3 3 3
// 1 1 2 1 2 1 2 1 2
$length = 13;
$num = 0;

function cutLine($length, &$num) {
    if($length <= 0) {
        return;
    }
    if($length == 2) {
        $num += 1;
        return;
    }
    if($length % 2 == 0) {
        $length /= 2;
        cutLine($length, $num);
        cutLine($length, $num);
    } else {
        $length -= 1;
        $num += 1;
        cutLine($length, $num);
    }
}

cutLine($length, $num);
echo $num;
<?php
// 判断括号是否闭合
// (a+b) + ((a+b))
// ())(

$str = "(a+b) + ((a+b))";
$str = "())(";
echo isClose($str);

function isClose($str) {
    $left = 0;
    $right = 0;
    for($i = 0 ; $i < strlen($str) ; $i++) {
        $ch = $str[$i];
        switch($ch)    {
            case '(':
                $left++;
                break;
            case ')':
                $right = 1;
            default:
                if($right == 1) {
                    if($left <= 0) {
                        return 0;
                    }
                    $right = 0;
                    $left--;
                }
        }
    }

    return 1;
}
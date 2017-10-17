<?php
function matmul1($a, $b) {
    $c = array();
    for($i = 0 ; $i < count($a) ; $i++) {
        $c[$i] = array();
    }

    for($i = 0 ; $i < count($a) ; $i++) {
        for($k = 0 ; $k < count($a[0]) ; $k++) {
            if($a[$i][$k] != 0) {
                for($j = 0 ; $j < count($b[0]) ; $j++) {
                    if($b[$k][$j] != 0) {
                        if(!isset($c[$i][$j])) {
                            $c[$i][$j] = 0;
                        }
                        $c[$i][$j] += $a[$i][$k] * $b[$k][$j];
                    }
                }
            }
        }
    }

    return $c;
}

function matmul2($a, $b) {
    $c = array();
    $d = array();
    for($i = 0 ; $i < count($a) ; $i++) {
        $c[$i] = array();
    }
    for($i = 0 ; $i < count($a) ; $i++) {
        $d[$i] = array();
        for($k = 0 ; $k < count($a[0]) ; $k++) {
            if($a[$i][$k] != 0) {
                array_push($d[$i], array($k, $a[$i][$k]));
            }
        }
    }
    for($i = 0 ; $i < count($a) ; $i++) {
        for($k = 0 ; $k < count($d[$i]) ; $k++) {
            $col = $d[$i][$k][0];
            $val = $d[$i][$k][1];
            for($j = 0 ; $j < count($b[0]) ; $j++) {
                if($b[$col][$j] != 0) {
                    if(!isset($c[$i][$j])) {
                        $c[$i][$j] = 0;
                    }
                    $c[$i][$j] += $val * $b[$col][$j];
                }

            }
        }
    }

    return $c;
}

$a = array(
    array(1, 0, 0),
    array(-1, 0, 3),
);

$b = array(
    array(7, 0, 0),
    array(0, 0, 0),
    array(0, 0, 1),
);

print_r(matmul1($a, $b));
print_r(matmul2($a, $b));
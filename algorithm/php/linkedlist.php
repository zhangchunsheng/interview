<?php
//两个有序链表，生成新的有序链表
class LinkedList {
    public $value;
    public $next = null;
}

$num = 1;
for($i = "a" ; $i <= "e" ; $i++) {
    $$i = new LinkedList();
    $$i->value = $num;
    $num += 2;
}
for($i = "a" ; $i <= "e" ; $i++) {
    if($i == "e") {
        $$i->next = null;
    } else {
        $index = ord($i) + 1;
        $chr = chr($index);
        $$i->next = $$chr;
    }
}

$num = 2;
for($j = "f" ; $j <= "i" ; $j++) {
    $$j = new LinkedList();
    $$j->value = $num;
    $num += 2;
}
for($j = "f" ; $j <= "i" ; $j++) {
    if($j == "i") {
        $$j->next = null;
    } else {
        $index = ord($j) + 1;
        $chr = chr($index);
        $$j->next = $$chr;
    }
}
//$h->value = 100;
//$i->value = 101;

function printLinkedList($linkedList) {
    while(true) {
        print_r($linkedList->value . "\n");
        if($linkedList->next == null) {
            break;
        }
        $linkedList = $linkedList->next;
    }
}

function mergeLinkedList($a, $b) {
    $c = array();

    $i = 0;
    while($a && $b) {
        if($a->value <= $b->value) {
            $c[$i] = new LinkedList();
            $c[$i]->value = $a->value;
            if($a->next) {
                $a = $a->next;
            } else {
                $a = null;
            }
        } else {
            $c[$i] = new LinkedList();
            $c[$i]->value = $b->value;
            if($b->next) {
                $b = $b->next;
            } else {
                $b = null;
            }
        }
        $i++;
    }
    while($a) {
        $c[$i] = new LinkedList();
        $c[$i]->value = $a->value;
        $i++;
        if($a->next) {
            $a = $a->next;
        } else {
            $a = null;
        }
    }
    while($b) {
        $c[$i] = new LinkedList();
        $c[$i]->value = $b->value;
        $i++;
        if($b->next) {
            $b = $b->next;
        } else {
            $b = null;
        }
    }

    for($j = 0 ; $j < count($c) - 1 ; $j++) {
        $c[$j]->next = $c[$j + 1];
    }

    return $c[0];
}

printLinkedList($a);
printLinkedList($f);

printLinkedList(mergeLinkedList($a, $f));
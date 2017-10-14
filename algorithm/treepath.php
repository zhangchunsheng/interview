<?php
define("MaxSize", 100);

class Node {
    public $data = "";
    public $lchild = null;
    public $rchild = null;
}

function main() {
    $node = new Node();

    $path = array();
    $i = 0;
    CreateBiTNode($node);
    printf("二叉树b:");

    DispBiTNode($node);
    printf("\n");

    Path($node, $path, 0);
}

main();

//创建二叉树 A(B,C(D,E))#
function CreateBiTNode($node) {
    $St = array();
    $p = NULL;
    $top = -1;
    $k = 0;
    $j = 0;
    $a = 0;
    $ch0 = "";
    $ch = "";
    $str = array();
    $node = NULL;

    printf("请输入一个格式为A(B,C)的二叉树：\n以#结束\n");
    $ch0 = fgetc(STDIN);
    for($a = 0; $ch0 != '#' ; $a++) {
        $ch0 = fgetc(STDIN);
        $str[$a] = $ch0;
    }
    $ch = $str[$j];
    while($ch != '#') {
        switch($ch)    {
            case '(':
                $top++;
                $St[$top] = $p;
                $k = 1;
                break;
            case ')':
                $top--;
                break;
            case ',':
                $k = 2;
                break;
            default:
                $p = new Node();
                $p->data = $ch;
                $p->lchild = $p->rchild = NULL;
                if($node == NULL)
                    $node = $p;
                else {
                    switch($k) {
                        case 1:
                            $St[$top]->lchild = $p;
                            break;
                        case 2:
                            $St[$top]->rchild = $p;
                            break;
                    }
                }
        }
        $j++;
        $ch = $str[$j];
    }
}

function DispBiTNode($node) {
    if($node != NULL)  {
        printf("%c", $node->data);
        if($node->lchild != NULL || $node->rchild != NULL) {
            printf("(");
            DispBiTNode($node->lchild);
            if($node->rchild != NULL)
                printf(",");
            DispBiTNode($node->rchild);
            printf(")");
        }
    }
}

function Path($node, $path, $pathlen) {
    $i = 0;
    if($node != NULL) {
        if($node->lchild == NULL && $node->rchild == NULL) {
            printf("%c到根结点路径:%c", $node->data, $node->data);
            for($i = $pathlen - 1 ; $i >= 0 ; $i--)
                printf("%c", $path[$i]);
            printf("\n");
        } else {
            $path[$pathlen] = $node->data;
            $pathlen++;
            Path($node->lchild, $path, $pathlen);
            Path($node->rchild, $path, $pathlen);
            $pathlen--;
        }
    }
}

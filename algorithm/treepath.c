#include <stdio.h>
#include <stdlib.h>
#define MaxSize 100

typedef char ElemType;
typedef struct node {
    ElemType data;
    struct node *lchild;
    struct node *rchild;
} BiTNode;

void CreateBiTNode(BiTNode *&b);
void DispBiTNode(BiTNode *b);
void Path(BiTNode *b, ElemType path[], int pathlen);

void main() {
    BiTNode *b;
    ElemType path[MaxSize];
    int i = 0;
    CreateBiTNode(b);
    printf("二叉树b:");

    DispBiTNode(b);
    printf("\n");

    Path(b, path, 0);
}

//创建二叉树 A(B,C(D,E))#
void CreateBiTNode(BiTNode *&b) {
    BiTNode *St[MaxSize], *p = NULL;
    int top = -1, k, j = 0, a;
    char ch0, ch;
    char str[MaxSize];
    b = NULL;
    printf("请输入一个格式为A(B,C)的二叉树：\n以#结束\n");
    ch0 = getchar();
    for(a = 0; ch0 != '#' ; a++) {
        str[a] = ch0;
        ch0 = getchar();
    }
    ch = str[j];
    while(ch != '#') {
        switch(ch)    {
            case '(':
                top++;
                St[top] = p;
                k = 1;
                break;
            case ')':
                top--;
                break;
            case ',':
                k = 2;
                break;
            default:
                p = (BiTNode *)malloc(sizeof(BiTNode));
                p->data = ch;
                p->lchild = p->rchild = NULL;
                if(b == NULL)
                    b = p;
                else {
                    switch(k) {
                        case 1:
                            St[top]->lchild = p;
                            break;
                        case 2:
                            St[top]->rchild = p;
                            break;
                    }
                }
        }
        j++;
        ch = str[j];
    }
}

void DispBiTNode(BiTNode *b) {
    if(b != NULL)  {
        printf("%c",b->data);
        if(b->lchild != NULL || b->rchild != NULL) {
            printf("(");
            DispBiTNode(b->lchild);
            if(b->rchild != NULL)
                printf(",");
            DispBiTNode(b->rchild);
            printf(")");
        }
    }
}

void Path(BiTNode *b, ElemType path[], int pathlen) {
    int i;
    if(b != NULL) {
        if(b->lchild == NULL && b->rchild == NULL) {
            printf("%c到根结点路径:%c", b->data, b->data);
            for(i = pathlen - 1 ; i >= 0 ; i--)
                printf("%c", path[i]);
            printf("\n");
        } else {
            path[pathlen] = b->data;
            pathlen++;
            Path(b->lchild, path, pathlen);
            Path(b->rchild, path, pathlen);
            pathlen--;
        }
    }
}
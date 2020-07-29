#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/**
 * https://mp.weixin.qq.com/s/-cR07rHU91owk8nMOfhz9w
 题目：任务调度在分布式调度系统中是一个很复杂很有挑战的问题。这里我们考虑一个简化的场景：假设一个中央调度机，有n个相同的任务需要调度到m台服务器上去执行。由于每台服务器的配置不一样，因此服务器执行一个任务所花费的时间也不同。现在假设第i个服务器执行一个任务需要的时间为t[i]。
 例如：有2个执行机a, b. 执行一个任务分别需要7min，10min，有6个任务待调度。如果平分这6个任务，即a，b各分三个任务，则最短需要30min执行完所有。如果a分这4个任务，b分2个，则最短28min执行完。
 请设计调度算法，使得所有任务完成所需的时间最短
 1) 简述思路
 2) 请用你熟悉的编程语言编码实现以下方法，输入为m台服务器，每台机器处理一个任务的时间为t[i]，完成n个任务，输出n个任务在m台服务器的分布：
 int estimate_process_time(int[] t, int m, int n);
 */
struct nt {
    int n;
    int t;
    int i;
    nt (int e1, int e2) : n(0), t(e1), i(e2) {}
};

class CCompNt {
public:
    bool operator() (nt e1, nt e2)
    {
        return (e2.n+1) * e2.t < (e1.n+1) * e1.t
            || ((e2.n+1) * e2.t == (e1.n+1) * e1.t && e2.t <= e1.t);
    }
} CompNt;

class CCompNt2 {
public:
    bool operator() (nt e1, nt e2)
    {
        return e1.i < e2.i;
    }
} CompNt2;

void printNt(nt e) {
    cout << "(" << e.i << "," << e.t << "," << e.n << ") ";
}

vector<nt> estimate_process_time(const vector<int> & vect, int m, int n) {
    vector<nt> vecNt;
    for (int i = 0 ; i < m ; ++i) {
        vecNt.push_back(nt(vect[i], i));
    }

    make_heap(vecNt.begin(), vecNt.end(), CompNt);

    for (int i = 0 ; i < n ; ++i) {
        pop_heap(vecNt.begin(), vecNt.end(), CompNt);
        auto nt1 = vecNt.back();
        vecNt.pop_back();

        nt1.n += 1;
        vecNt.push_back(nt1);
        push_heap(vecNt.begin(), vecNt.end(), CompNt);
    }

    sort(vecNt.begin(), vecNt.end(), CompNt);

    return vecNt;
}

int main(int argc, char ** argv) {
    int n = 6;
    int m = 2;
    vector<int> t = {
        7, 10
    };

    auto vecNt = estimate_process_time(t, m, n);

    cout << "(index, t, n)" << endl;
    for_each(vecNt.begin(), vecNt.end(), printNt);

    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

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
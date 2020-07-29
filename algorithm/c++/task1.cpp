#include <iostream>
#include <vector>
using namespace std;

int minTask(int *t,int n) {
	int tmp = t[0];
	int min = 0;
	for(int i = 0 ; i < n ; i++) {
		if(tmp > t[i]) {
			tmp = t[i];
			min = i;
		}
	}
	return min;
}

void makeSpanScheduling(int *t,int n,int num) {
	int *T = new int[num];
	vector<vector<int> > M;
	for(int i = 0 ; i < num ; i++) {
		T[i] = 0;
		vector<int> tmp;
		M.push_back(tmp);
	}
	for(int i = 0 ; i < n ; i++) {
		int j = minTask(T,num);
		int tj = T[j];
		T[j] = tj + t[i];
		M[j].push_back(i + 1);
	}
	for(int i = 0 ; i < M.size() ; i++) {
		for(int j = 0 ; j < M[i].size() ; j++)
			cout<<M[i][j]<<"\t";
		cout<<endl;
	}
}

int main(int argc, char** argv) {
	int t[6] = {1, 2, 3, 4, 5, 6};
	makeSpanScheduling(t, 6, 3);
	return 0;
}
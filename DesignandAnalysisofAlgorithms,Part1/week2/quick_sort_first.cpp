#include <bits/stdc++.h>
using namespace std;

int c = 0;


int partition(vector<int> &v, int l, int r) {
    int p = v[l], t;
    int i = l;
    for(int j=i+1; j <= r; ++j) {
        if(v[j] < p) {
            i++;
            t = v[i];
            v[i] = v[j];
            v[j] = t;
        }
    }
    t = v[l];
    v[l] = v[i];
    v[i] = t;

    return i;
}

void quickSort(vector<int> &v, int l, int r) {
    if(l>r)
        return;
    c += (r-l);
    int q = partition(v, l, r);
    quickSort(v, l, q-1);
    quickSort(v, q+1, r);
}

int main() {
    vector<int> v;
    int n;
    for(int i=0; i<10000; ++i) {
        cin >> n;
        v.push_back(n);
    }

    quickSort(v, 0, 9999);

//    for(int i=0; i<10000; ++i) {
//        cout << v[i] << " ";
//    }

    cout << c ;

}
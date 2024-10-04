#include <bits/stdc++.h>
using namespace std;


int main(){
    int T, N;

    cin >> T;

    for (int i = 0; i < T; i++){
        for (int k = 0; k <= i; k++){
            cout << N;
            N++;
            if (N == 10){
                N = 0;
            }
        }
        cout << "\n";
    }
}
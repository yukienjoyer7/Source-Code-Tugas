#include <bits/stdc++.h>  
using namespace std;
typedef long long ll;
 
int main(){
    int N;
    cin >> N;

    vector <int> data;
    for (int i = 0; i < N; i++){
        int x;
        cin >> x;
        data.push_back(x);
    }

    int check_1 = -100000;
    int check_2 = 100000;
    for (int i:data){
        if (i > check_1){
            check_1 = i;
        }

        if (i < check_2){
            check_2 = i;
        }
    }

    cout << check_1 << " " << check_2;
}
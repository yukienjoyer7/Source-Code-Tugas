#include <bits/stdc++.h>  
using namespace std;
typedef long long ll;
 
int main(){
    int A,B,K,x;
    cin >> A >> B >> K >> x;

    for (int i = 1; i <= K; i++){
        x = abs (A*x + B);
    }
    cout << x;
}
 
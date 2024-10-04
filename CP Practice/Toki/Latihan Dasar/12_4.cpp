#include <bits/stdc++.h>  
using namespace std;
typedef long long ll;
 
int main(){
    int N,P,M;
    cin >> N >> P >> M;
    int matrix1 [N][P];
    int matrix2 [P][M];
    int number;

    //TAKING INPUT OF MATRICES
    for (int i = 0; i < N; i++){
        for (int j = 0; j < P; j++){
            cin >> number;
            matrix1 [i][j] = number;
        }
    }
    
    for (int i = 0; i < P; i++){
        for (int j = 0; j < M; j++){
            cin >> number;
            matrix2 [i][j] = number;
        }
    }

    // MULTIPLYING THE MATRICES
    for (int i = 0; i <= N-1; i++){
        for (int j = 0; j <= M-1; j++){
            int result = 0;
            int counter = 0;
            while(counter != P){
                result += matrix1[i][counter] * matrix2[counter][j];
                counter++;
            }
            cout << result << " ";
        }
        cout << endl;
    }

}
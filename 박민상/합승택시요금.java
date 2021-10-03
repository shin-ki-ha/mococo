package programmers;

import java.util.*;

public class 합승택시요금 {
    static final int INF = 20000001;
    public static void main(String[] args) {
        solution(6, 4, 6, 2, new int[][]
                {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24},{4, 6, 50},{2, 4, 66},{2, 3, 22},{1, 6, 25}});
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;

        int[][] adj = new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(adj[i], INF);
        }

        for(int [] fare : fares){
            int from = fare[0]-1;
            int to = fare[1]-1;
            int f = fare[2];

            adj[from][to]=f;
            adj[to][from]=f;
        }

        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(i==j) adj[i][j]=0;
                    adj[i][j] = Math.min(adj[i][j], adj[i][k]+adj[k][j]);
                }
            }
        }

        for(int k=0; k<n; k++){
            answer = Math.min(answer, adj[s-1][k]+adj[k][a-1]+adj[k][b-1]);
        }

        System.out.println(answer);
        return answer;
    }

}

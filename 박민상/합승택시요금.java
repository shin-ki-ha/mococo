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

//정확성  테스트
//테스트 1 〉	통과 (0.04ms, 75.9MB)
//테스트 2 〉	통과 (0.08ms, 78.7MB)
//테스트 3 〉	통과 (0.05ms, 77.4MB)
//테스트 4 〉	통과 (0.10ms, 77.9MB)
//테스트 5 〉	통과 (0.13ms, 77.1MB)
//테스트 6 〉	통과 (0.20ms, 77.4MB)
//테스트 7 〉	통과 (0.15ms, 75.2MB)
//테스트 8 〉	통과 (0.25ms, 72.9MB)
//테스트 9 〉	통과 (0.49ms, 69.9MB)
//테스트 10 〉	통과 (0.64ms, 84.6MB)
//효율성  테스트
//테스트 1 〉	통과 (21.71ms, 51.9MB)
//테스트 2 〉	통과 (34.43ms, 53.6MB)
//테스트 3 〉	통과 (34.64ms, 53.1MB)
//테스트 4 〉	통과 (31.04ms, 52.4MB)
//테스트 5 〉	통과 (42.95ms, 52.7MB)
//테스트 6 〉	통과 (38.11ms, 52.3MB)
//테스트 7 〉	통과 (69.47ms, 62.3MB)
//테스트 8 〉	통과 (44.85ms, 65.9MB)
//테스트 9 〉	통과 (41.33ms, 64.3MB)
//테스트 10 〉	통과 (63.92ms, 63.8MB)
//테스트 11 〉	통과 (50.07ms, 63.4MB)
//테스트 12 〉	통과 (57.56ms, 58.2MB)
//테스트 13 〉	통과 (71.13ms, 59.4MB)
//테스트 14 〉	통과 (41.46ms, 56.9MB)
//테스트 15 〉	통과 (75.82ms, 59.6MB)
//테스트 16 〉	통과 (34.20ms, 52.4MB)
//테스트 17 〉	통과 (32.30ms, 52.9MB)
//테스트 18 〉	통과 (45.06ms, 52.3MB)
//테스트 19 〉	통과 (114.88ms, 66.8MB)
//테스트 20 〉	통과 (51.09ms, 55.3MB)
//테스트 21 〉	통과 (49.64ms, 55.3MB)
//테스트 22 〉	통과 (35.68ms, 56.9MB)
//테스트 23 〉	통과 (56.72ms, 59.7MB)
//테스트 24 〉	통과 (67.56ms, 60MB)
//테스트 25 〉	통과 (37.44ms, 52MB)
//테스트 26 〉	통과 (35.11ms, 52.3MB)
//테스트 27 〉	통과 (35.02ms, 53.7MB)
//테스트 28 〉	통과 (38.38ms, 53MB)
//테스트 29 〉	통과 (19.27ms, 52.1MB)
//테스트 30 〉	통과 (17.50ms, 52.6MB)
//

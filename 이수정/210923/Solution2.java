/* 
210923 - 2. 무지의 먹방 라이브 (실패)
https://programmers.co.kr/learn/courses/30/lessons/42891

- 내 코드에서 아쉬운 점 :(
    (1) for, while이 너무 많다. 간략화 필요
    (2) 사용된 알고리즘이 무엇일지 곰곰히 생각해보자. (투포인터? 슬라이딩 윈도우?)

- 비슷한 문제 : 백준 회전초밥 https://www.acmicpc.net/problem/15961    
*/

import java.util.*;

class Solution2 {
    public int solution(int[] food, long k) {
        int answer = 0;
        
        long curr = k%food.length-1;
        curr = (curr<0)?curr+food.length:curr;
        long minus = k/food.length;
        long cnt = 0; long sum = 0;
        for(int i=0; i<food.length; i++) {
            if(i<=curr) {
                if(food[i]>=minus+1) {
                    food[i]-=(minus+1);
                    cnt += minus+1;
                } else {
                    cnt += food[i];
                    sum += (minus+1-food[i]);
                    food[i] = 0;
                }
            } else {
                if(food[i]>=minus) {
                    food[i] -= minus;
                    cnt += minus;
                } else {
                    cnt += food[i];
                    sum += (minus-food[i]);
                    food[i] = 0;
                }
            }
        }
        
        while(cnt+sum==k && !allZero(food)) {
            if(food[(int)curr]>0) {
                food[(int)curr]-=1;
                sum--;
                cnt++;
            }
            if(cnt==k && sum==0) break;
            curr ++;
            if(curr>=food.length) curr = 0;
        }
        
        if(allZero(food)) answer = -1;
        else {
            while(true) {
                if(curr>=food.length) curr = 0;
                if(food[(int)curr]!=0) {
                    answer = (int) curr+1;
                    break;
                }
                curr ++;
            }
        }
        
        return answer;
    }
    
    public static boolean allZero(int[] food) {
        int[] f = new int[food.length];
        for(int i=0; i<food.length; i++) {f[i]=food[i];}
        
        Arrays.sort(f);
        
        if(f[f.length-1]==0) return true;
        return false;
    }
}


/*
정확성  테스트
테스트 1 〉	실패 (0.82ms, 76.7MB)
테스트 2 〉	실패 (0.78ms, 79.7MB)
테스트 3 〉	실패 (0.37ms, 74.9MB)
테스트 4 〉	실패 (0.34ms, 73.9MB)
테스트 5 〉	실패 (0.46ms, 77.8MB)
테스트 6 〉	실패 (0.39ms, 72.7MB)
테스트 7 〉	실패 (0.35ms, 73.3MB)
테스트 8 〉	실패 (0.37ms, 66.5MB)
테스트 9 〉	실패 (0.49ms, 76.3MB)
테스트 10 〉	실패 (0.50ms, 76.1MB)
테스트 11 〉	실패 (0.71ms, 78.2MB)
테스트 12 〉	실패 (0.43ms, 80.3MB)
테스트 13 〉	실패 (0.50ms, 77.5MB)
테스트 14 〉	통과 (0.47ms, 81MB)
테스트 15 〉	통과 (0.58ms, 89.2MB)
테스트 16 〉	통과 (0.53ms, 73.9MB)
테스트 17 〉	통과 (0.50ms, 76.9MB)
테스트 18 〉	통과 (0.53ms, 76.7MB)
테스트 19 〉	통과 (0.52ms, 75.6MB)
테스트 20 〉	통과 (0.53ms, 75.7MB)
테스트 21 〉	실패 (13.08ms, 84.6MB)
테스트 22 〉	실패 (16.73ms, 82.2MB)
테스트 23 〉	통과 (19.94ms, 83MB)
테스트 24 〉	실패 (178.57ms, 92.7MB)
테스트 25 〉	실패 (12.39ms, 77.1MB)
테스트 26 〉	실패 (719.96ms, 139MB)
테스트 27 〉	실패 (1278.34ms, 147MB)
테스트 28 〉	실패 (0.37ms, 74.9MB)
테스트 29 〉	실패 (0.32ms, 77MB)
테스트 30 〉	통과 (0.47ms, 78.2MB)
테스트 31 〉	통과 (0.44ms, 73.3MB)
테스트 32 〉	실패 (0.39ms, 76.7MB)

효율성  테스트
테스트 1 〉	실패 (시간 초과)
테스트 2 〉	통과 (20.22ms, 65MB)
테스트 3 〉	실패 (시간 초과)
테스트 4 〉	실패 (시간 초과)
테스트 5 〉	실패 (시간 초과)
테스트 6 〉	실패 (시간 초과)
테스트 7 〉	실패 (시간 초과)
테스트 8 〉	실패 (시간 초과)

채점 결과
정확성: 13.4
효율성: 7.1
합계: 20.5 / 100.0 
 */
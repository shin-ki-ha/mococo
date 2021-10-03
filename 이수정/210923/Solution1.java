/* 
210923 - 1. 2018 KAKAO BLIND RECRUITMENT [1차] 셔틀버스 (실패)
https://programmers.co.kr/learn/courses/30/lessons/17678

- 내 코드에서 아쉬운 점 :(
    (1) 문자열 사용에 익숙하지 못한듯 하다.
    (2) 숫자로 변환해 시간을 계산하는 과정을 다시 생각해봐야할듯
    (3) bus[]는 앞에서부터 확인, answer는 뒤에서부터 확인하기  
*/

import java.util.*;

class Solution1 {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] bus = new int[n]; //해당 타임에 줄 선 크루 수
        int[][] time = new int[n][2]; //[시간, 분]
        for(int i=0; i<n; i++) {
            time[i][0] = 9+i*(t/60);
            time[i][1] = 0+i*(t%60);
        }
        Arrays.sort(timetable);
        
        int curr = 0;
        int curr_h = 9;
        int curr_m = 0;
        for(int i=0; i<timetable.length; i++) {
            String[] hnm = timetable[i].split(":");
            int hour = Integer.valueOf(hnm[0]);
            int min = Integer.valueOf(hnm[1]);
            boolean flag = false;
            while(curr<n) {
                if(hour*100+min <= time[curr][0]*100+time[curr][1] && bus[curr]<m) {
                    bus[curr]++;
                    flag = true;
                } else 
                    curr++;
                
                if(flag) 
                    break;
            }
            if(curr>=n) break;
        }
        
        System.out.println(Arrays.toString(bus));
        for(int i=0; i<time.length; i++) {System.out.println(Arrays.toString(time[i]));}
        
        for(int i=n-1; i>=0; i--) {
            if(bus[i]<m) {
                if(time[i][0]<10) answer+="0";
                answer += String.valueOf(time[i][0]) + ":";
                if(time[i][1]<10) answer+="0";
                answer += String.valueOf(time[i][1]);
                break;
            } else {
                if(i==0) {
                    String[] times = timetable[0].split(":");
                    int times2int = Integer.valueOf(times[0]+times[1])-1;
                    if(times2int<1000) answer+="0";
                    answer += String.valueOf(times2int/100)+":";
                    if(times2int%100<10) answer+="0";
                    if(times2int%100>60) times2int = times2int%100-40;
                    answer += String.valueOf(times2int%100);
                } else if(i-bus[i]>=0) {
                    String[] time1 = timetable[i-bus[i]+1].split(":");
                    String[] time0 = timetable[i-bus[i]].split(":");
                    if(Integer.valueOf(time1[0]+time1[1])-t>=Integer.valueOf(time0[0]+time0[1])) {
                        int times2int = Integer.valueOf(time1[0]+time[1])-1;
                        if(times2int<1000) answer+="0";
                        answer += String.valueOf(times2int/100)+":";
                        if(times2int%100<10) answer+="0";
                        if(times2int%100>60) times2int = times2int%100-40;
                        answer += String.valueOf(times2int%100);
                    }
                }
            }
        
        }
        
        return answer;
    }
}

/*
정확성  테스트
테스트 1 〉	통과 (26.40ms, 71.8MB)
테스트 2 〉	실패 (20.78ms, 73.4MB)
테스트 3 〉	통과 (38.05ms, 84.6MB)
테스트 4 〉	통과 (14.10ms, 76.8MB)
테스트 5 〉	실패 (런타임 에러)
테스트 6 〉	통과 (16.30ms, 78.8MB)
테스트 7 〉	실패 (16.82ms, 85.1MB)
테스트 8 〉	통과 (36.04ms, 86.8MB)
테스트 9 〉	통과 (17.50ms, 74.7MB)
테스트 10 〉	통과 (13.57ms, 77.3MB)
테스트 11 〉	통과 (12.45ms, 75.2MB)
테스트 12 〉	통과 (16.29ms, 74.8MB)
테스트 13 〉	통과 (22.79ms, 71.1MB)
테스트 14 〉	실패 (12.62ms, 82.7MB)
테스트 15 〉	실패 (17.02ms, 75.7MB)
테스트 16 〉	실패 (19.57ms, 73.2MB)
테스트 17 〉	실패 (18.71ms, 71.1MB)
테스트 18 〉	통과 (32.96ms, 77MB)
테스트 19 〉	실패 (14.46ms, 76.8MB)
테스트 20 〉	실패 (15.05ms, 80.6MB)
테스트 21 〉	실패 (19.51ms, 77.4MB)
테스트 22 〉	실패 (16.02ms, 79.3MB)
테스트 23 〉	실패 (11.45ms, 90.1MB)
테스트 24 〉	실패 (15.35ms, 76.5MB)

채점 결과
정확성: 45.8
합계: 45.8 / 100.0
*/

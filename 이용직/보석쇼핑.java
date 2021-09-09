import java.util.*;
class Solution {
    HashSet<String>[][] map;
    public int[] solution(String[] gems) {
        
        int count = gems.length;
        HashSet<String> test = new HashSet<>();
        
        
        for(int i=0; i<count; i++){
            test.add(gems[i]);    
        }
        
        int len = test.size();
        map = new HashSet[count+1][count+1];
        
        for(int i=0; i<=count;i++){
            for(int j=0; j<=count;j++){
                map[i][j] = new HashSet<>();
            }
        }
        
        for(int i=1; i<=count; i++){
            for(int j=i; j<=count;j++){
                
                // 이전꺼 누적
                for(String s : map[i][j-1]){
                    map[i][j].add(s);
                }
                //현재 위치 꺼 넣기
                map[i][j].add(gems[j-1]);
            }
        }
        int size = Integer.MAX_VALUE;
        int start = 1;
        int end = 1;
        for(int i=1; i<=count; i++){
            for(int j=i; j<=count;j++){
                // 개수가 보석 수이고 길이가 제일 짧은거
                if(map[i][j].size() == len && (j-i) < size){
                    start = i;
                    end = j;
                    size = j-i;
                }
                
            }
        }
        
        int[] answer = {start,end};
        return answer;
    }
}
테스트 1 〉	통과 (0.27ms, 59.9MB)
테스트 2 〉	통과 (5.99ms, 77.3MB)
테스트 3 〉	통과 (69.11ms, 111MB)
테스트 4 〉	통과 (1313.93ms, 1.08GB)
테스트 5 〉	통과 (59.23ms, 93.9MB)
테스트 6 〉	통과 (0.17ms, 72.9MB)
테스트 7 〉	통과 (0.16ms, 59.1MB)
테스트 8 〉	통과 (1489.59ms, 1.31GB)
테스트 9 〉	통과 (1195.32ms, 1.04GB)
테스트 10 〉	실패 (메모리 초과)
테스트 11 〉	실패 (메모리 초과)
테스트 12 〉	실패 (메모리 초과)
테스트 13 〉	실패 (메모리 초과)
테스트 14 〉	실패 (메모리 초과)
테스트 15 〉	실패 (메모리 초과)
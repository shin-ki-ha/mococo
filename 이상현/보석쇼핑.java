import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int[] solution(String[] gems) {
        int length = Integer.MAX_VALUE;
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<String>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        // 보석 종류의 수를 구하기 위해 set, map에 데이터 저장
        int cnt = 0;
        for(int i = 0; i < gems.length; i++) {
            if(set.add(gems[i])) map.put(gems[i], cnt++);
        }
        
        // 보석 종류 별 인덱스를 저장할 배열 생성
        int[] idx = new int[set.size()];
        // 적당히 큰 수로 초기화
        for(int i = 0; i < idx.length; i++) idx[i] = 1000000;
        
        // 뒤에서부터 탐색
        for(int i = gems.length - 1; i >= 0; i--) {
            // 현재 위치의 보석 인덱스 재설정
            idx[map.get(gems[i])] = i;
            
            // 보석 종류 중 가장 먼 보석의 인덱스 선택
            int x = 0;
            for(int j = 0; j < idx.length; j++) x = x < idx[j] ? idx[j] : x;

            // 길이가 더 짧을 경우 answer 설정
            if(length >= (x - i)) {
                length = x - i;
                answer[0] = i;
                answer[1] = x;
            }
        }
        answer[0]++;
        answer[1]++;
        
        return answer;
    }
}

정확성  테스트
테스트 1 〉	통과 (0.05ms, 59.7MB)
테스트 2 〉	통과 (0.10ms, 61.5MB)
테스트 3 〉	통과 (0.27ms, 60MB)
테스트 4 〉	통과 (3.15ms, 59.3MB)
테스트 5 〉	통과 (0.24ms, 72.6MB)
테스트 6 〉	통과 (0.05ms, 59.2MB)
테스트 7 〉	통과 (0.05ms, 69.9MB)
테스트 8 〉	통과 (1.70ms, 60.9MB)
테스트 9 〉	통과 (1.08ms, 73.6MB)
테스트 10 〉	통과 (5.30ms, 59.8MB)
테스트 11 〉	통과 (8.26ms, 59.2MB)
테스트 12 〉	통과 (2.72ms, 61MB)
테스트 13 〉	통과 (2.46ms, 62.1MB)
테스트 14 〉	통과 (10.85ms, 74MB)
테스트 15 〉	통과 (3.88ms, 63MB)
효율성  테스트
테스트 1 〉	통과 (16.79ms, 54.8MB)
테스트 2 〉	통과 (51.97ms, 56.1MB)
테스트 3 〉	통과 (20.44ms, 59.9MB)
테스트 4 〉	통과 (145.21ms, 60MB)
테스트 5 〉	통과 (57.73ms, 63.5MB)
테스트 6 〉	통과 (28.96ms, 63.4MB)
테스트 7 〉	통과 (93.68ms, 64.6MB)
테스트 8 〉	통과 (88.19ms, 69.6MB)
테스트 9 〉	통과 (32.24ms, 68.1MB)
테스트 10 〉	통과 (68.26ms, 75.6MB)
테스트 11 〉	통과 (171.32ms, 79.2MB)
테스트 12 〉	통과 (356.49ms, 79.6MB)
테스트 13 〉	통과 (645.01ms, 79.6MB)
테스트 14 〉	통과 (96.49ms, 83MB)
테스트 15 〉	통과 (260.53ms, 81.1MB)
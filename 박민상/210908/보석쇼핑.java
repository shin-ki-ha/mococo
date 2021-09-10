import java.util.*;

class Solution {
    static Map<String, Integer> gemidx;
    static Set<String> gemset;

    public static int[] solution(String[] gems) {
        gemidx = new HashMap<>();
        gemset = new HashSet<>();

        int cnt=0;
        for(String gem : gems){
            if(gemset.add(gem)){
                gemidx.put(gem, cnt++);
            }
        }

        Map<String, Integer> gemcnt = new HashMap<>();

        int start=0, end=0;
        int left=0, right=0, anslength = gems.length+1;
        while(true){
            if(end==gems.length && gemcnt.size()<cnt) break;
            // 보석 부족
            if(gemcnt.size()<cnt){
                gemcnt.put(gems[end], gemcnt.get(gems[end])==null?1 : 1+gemcnt.get(gems[end]));
                end++;
            }else {
                int startcnt = gemcnt.get(gems[start]);
                if (startcnt == 1) {
                    gemcnt.remove(gems[start]);
                }else{
                    gemcnt.put(gems[start], startcnt-1);
                }
                start++;
            }

            if (gemcnt.size()==cnt && end - start < anslength) {
                anslength = end - start;
                left = start+1;
                right = end-1+1;
            }
        }

        int[] answer = new int[]{left, right};
        return answer;
    }
}

//    정확성  테스트
//테스트 1 〉	통과 (0.05ms, 60MB)
//테스트 2 〉	통과 (0.21ms, 67MB)
//테스트 3 〉	통과 (0.44ms, 73.4MB)
//테스트 4 〉	통과 (0.41ms, 58.6MB)
//테스트 5 〉	통과 (0.53ms, 60.7MB)
//테스트 6 〉	통과 (0.05ms, 69.9MB)
//테스트 7 〉	통과 (0.07ms, 71.8MB)
//테스트 8 〉	통과 (0.70ms, 75.5MB)
//테스트 9 〉	통과 (0.97ms, 71.2MB)
//테스트 10 〉	통과 (0.61ms, 62.7MB)
//테스트 11 〉	통과 (0.83ms, 71.6MB)
//테스트 12 〉	통과 (1.22ms, 76.9MB)
//테스트 13 〉	통과 (1.51ms, 59MB)
//테스트 14 〉	통과 (2.46ms, 60.2MB)
//테스트 15 〉	통과 (2.59ms, 62.1MB)
//효율성  테스트
//테스트 1 〉	통과 (4.14ms, 54.5MB)
//테스트 2 〉	통과 (9.59ms, 56.1MB)
//테스트 3 〉	통과 (13.69ms, 58.4MB)
//테스트 4 〉	통과 (15.05ms, 61.1MB)
//테스트 5 〉	통과 (19.18ms, 61.8MB)
//테스트 6 〉	통과 (24.74ms, 63.4MB)
//테스트 7 〉	통과 (22.43ms, 67.1MB)
//테스트 8 〉	통과 (33.13ms, 69.8MB)
//테스트 9 〉	통과 (37.28ms, 69.6MB)
//테스트 10 〉	통과 (43.38ms, 74.1MB)
//테스트 11 〉	통과 (40.32ms, 79.7MB)
//테스트 12 〉	통과 (45.88ms, 80.6MB)
//테스트 13 〉	통과 (48.53ms, 81.9MB)
//테스트 14 〉	통과 (46.41ms, 79.9MB)
//테스트 15 〉	통과 (58.35ms, 82.8MB)
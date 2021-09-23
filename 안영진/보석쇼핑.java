import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        // 보석의 종류를 알기위해 해시맵에 넣어서 중복 제거
        HashSet <String> hs = new HashSet<>();
        int gems_length = gems.length;
        for (int i =0; i< gems_length; i++){
            hs.add(gems[i]);
        }
        
        int gems_size = hs.size();
        
        System.out.print(gems_size);
        //그 다음에 뭔가 탐색을 해야할거같은데 떠오르지가않는다ㅏㅏㅏ
        
        
        return answer;
    }
}
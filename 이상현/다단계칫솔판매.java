import java.util.HashMap;

class Solution {
    // 노드를 저장할 클래스
    class Data {
        // 부모
        public Data p;
        // 현재 노드의 이익
        public int profit;
        
        public Data(Data p) {
            this.p = p;
        }
        
        // 이익 계산
        public void add(int amount) {
            int x = amount;
            int y = (int)(amount * 0.1f);
            if(y != 0) {
                // 부모가 null이 아니라면 이익 전달
                if(p != null) p.add(y);
                x -= y;
            }
            profit += x;
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String, Data> map = new HashMap<String, Data>();
        Data[] ds = new Data[enroll.length];
        
        // 노드 초기화
        for(int i = 0; i < enroll.length; i++) {
            ds[i] = new Data(referral[i].equals("-") ? null : map.get(referral[i]));
            map.put(enroll[i], ds[i]);
        }
        
        // 노드에 이익 전달 후 결과 저장
        for(int i = 0; i < seller.length; i++) map.get(seller[i]).add(amount[i] * 100);
        for(int i = 0; i < ds.length; i++) answer[i] = ds[i].profit;
        
        return answer;
    }
}

정확성  테스트
테스트 1 〉	통과 (0.17ms, 60.2MB)
테스트 2 〉	통과 (0.29ms, 74.4MB)
테스트 3 〉	통과 (0.27ms, 58.9MB)
테스트 4 〉	통과 (0.49ms, 71.1MB)
테스트 5 〉	통과 (0.70ms, 62.6MB)
테스트 6 〉	통과 (5.01ms, 92.9MB)
테스트 7 〉	통과 (7.15ms, 75.6MB)
테스트 8 〉	통과 (4.24ms, 95.7MB)
테스트 9 〉	통과 (10.90ms, 80.1MB)
테스트 10 〉	통과 (14.43ms, 104MB)
테스트 11 〉	통과 (11.03ms, 122MB)
테스트 12 〉	통과 (20.87ms, 124MB)
테스트 13 〉	통과 (16.70ms, 118MB)

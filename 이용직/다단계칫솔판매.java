import java.util.*;

class Solution {
    
    class Person{
        String self;
        String parent;
        int idx;
        int money = 0;
        Person(){}
        Person(String self){
            this.self = self;
        }
        Person(String self, String parent){
            this.self = self;
            this.parent = parent;
        }
        
        Person(String self, String parent,int money){
            this.self = self;
            this.parent = parent;
            this.money = money;
        }
    }
    Map<String, Person> m = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int person_count = enroll.length;
        int[] answer = new int[person_count];
        
        
        // 사람 등록 및 부모 등록
        for(int p = 0; p< person_count;p++){
            Person person = new Person();
            person.self = enroll[p];
            person.parent = referral[p];
            person.idx = p;
            
            m.put(enroll[p],person);
        }
        
        // 판매 순서대로 진행
        for(int i=0; i<seller.length; i++){
            
            Person p = m.get(seller[i]);
            
            dfs(p,amount[i]*100);
            
        }
        
        
        // 사람들 전부 가격확인
        for(int p=0; p<person_count; p++){
            Person person = m.get(enroll[p]);
            answer[p] = person.money;
        }
        
        return answer;
    }
    
    void dfs(Person p, int price){
        
        
        String name = p.self;
        String parent = p.parent;
        int money = p.money;

        
        // 현재 자신의 이익의 10퍼센트를 부모로 보내고 나머지는 가지기
        int give_parent = price/10;
        int mine = price - give_parent;
        
        // 부모가 민호라서 돈을 민호한테 주는 경우
        if(parent.equals("-")){
            // 현재 돈의 10퍼센트만 가지고 민호한테 주면서 리턴
            m.put(name, new Person(name,parent,money+mine));
            return;
        }
        // 몫이 0인 경우 자기가 다 가지고 리턴
        else if(give_parent==0){
            m.put(name, new Person(name,parent,money+mine));
            
            return;
        }
        
        // 더 이어가야하는 경우
        else{
            // 자신의 이익 더하기
            m.put(name, new Person(name,parent,money+mine));

            // 부모로 가격 넘기기
            dfs(m.get(parent),give_parent);            

            
        }
        
        
        

        
        
    }
}
테스트 1 〉	통과 (0.18ms, 59.6MB)
테스트 2 〉	통과 (0.27ms, 72.7MB)
테스트 3 〉	통과 (0.33ms, 69.9MB)
테스트 4 〉	통과 (0.40ms, 73.1MB)
테스트 5 〉	통과 (1.11ms, 61.2MB)
테스트 6 〉	통과 (3.13ms, 92.3MB)
테스트 7 〉	통과 (3.61ms, 73.8MB)
테스트 8 〉	통과 (7.45ms, 76MB)
테스트 9 〉	통과 (21.56ms, 81.7MB)
테스트 10 〉	통과 (55.33ms, 125MB)
테스트 11 〉	통과 (32.07ms, 129MB)
테스트 12 〉	통과 (27.81ms, 125MB)
테스트 13 〉	통과 (28.92ms, 119MB)
import java.util.*;

class Solution {
    // enroll에 있는 이름의 순서를 저장하기위한 map입니다.
    static Map<String, Integer> idxMap;
    // referral을 static으로 사용하기 위한 변수입니다.
    static String[] sreferral;
    static int[] answer;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        sreferral = referral;

        idxMap = new HashMap<>();

        //enroll에 있던 이름에 순서를 매겨 map에 저장합니다
        for(int i=0; i<enroll.length; i++){
            idxMap.put(enroll[i], i);
        }

        answer = new int[enroll.length];

        //func함수는 판매자, 판매금액을 인자로 받습니다
        for(int i=0; i<seller.length; i++){
            func(seller[i], amount[i]*100);
        }

        return answer;
    }

    //func는 재귀함수 구조로 본인의 몫을 뗀 후 추천인을 다시 func함수로 호출합니다.
    static void func(String name, int money){
        //기저 조건은 추천인이 민호인 경우입니다.
        if(name.equals("-")) return;

        //현재 판매한 사람의 순번과 부모의 이름을 저장하는 부분입니다.
        int idx=idxMap.get(name);
        String parent = sreferral[idx];

        // int parentmoney=money/10+(money%10>=5?1:0); 처음 코드 작성시 틀렸던 부분입니다. 추천인에게 주는 돈은 절삭하는 것이므로 10으로 나눈 몫입니다.
        int parentmoney=money/10; //추천인에게 줄 돈
        int mymoney=money-parentmoney; //내가 가질 돈은 추천인에게 줄 돈을 뺀 나머지

        answer[idx]+=mymoney; //나는 idx번째이므로 answer[idx]에 내 돈을 추가합니다.

        // 추천인에게 줄 돈이 있는 경우 재귀호출합니다
        if(parentmoney>0){
            func(parent, parentmoney);
        }
    }
}
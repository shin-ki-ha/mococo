'''
210908 - 2. 다단계 칫솔 판매
https://programmers.co.kr/learn/courses/30/lessons/77486

- 아쉬웠던 점 :(
    (1) 문제가 길어서 제대로 읽어보지 않았고, 그래프 문제 유형(선호하지 않는 유형)이라고 생각해서 손도 대지 않았던 점
    (2) 리스트에서 접근 시, 해당 요소의 인덱스를 찾고, 그 인덱스의 요소 값을 받는 일련의 과정에서 시간이 오래 걸림
        => 딕셔너리 사용으로 시간초과 해결.

'''

# Try 1 - (시간초과 / 정확성: 69.2, 합계: 69.2 / 100.0)
def solution(enroll, referral, seller, amount):
    # 각 판매원 별 이익을 저장할 리스트 생성
    answer = [0]*len(enroll)
    
    for i in range(len(seller)):
        profit = amount[i]*100
        curr = seller[i] # 현재 판매원의 이름
        idx = enroll.index(curr) # 몇번째 판매원인지 찾기
        
        while True:
            answer[idx] += profit
            profit//=10
            answer[idx] -= profit

            # 센터에 도달하면 종료하기
            if referral[idx]=="-":
                break

            # 현재 판매원과 그 인덱스 업데이트해주기
            curr = referral[idx]
            idx = enroll.index(curr)
    
    return answer


# Try 2 - (성공)
def solution(enroll, referral, seller, amount):
    # 판매원 별 이익금, 판매원 별 추천인 딕셔너리 생성
    money = {e : 0 for e in enroll}
    parent = {enroll[i] : referral[i] for i in range(len(enroll))}
    
    for i in range(len(seller)) :
        curr = seller[i] # 현재 판매원의 이름
        profit = amount[i] * 100 #이익금
        
        while True:
            money[curr] += profit
            profit//=10
            money[curr] -= profit # 이익금의 10%는 추천인에게 간다.
            curr = parent[curr] # 추천인 찾아서 타고 올라가기.
            # 추천인이 center(-)이거나 이익금의 10%가 0원이면 더 탐색할 필요 없으니 종료
            if profit==0 or curr=="-":
                break
            
    
    return [money[e] for e in enroll]


'''
정확성  테스트
    테스트 1 〉	통과 (0.02ms, 10.2MB)
    테스트 2 〉	통과 (0.07ms, 10.3MB)
    테스트 3 〉	통과 (0.05ms, 10.3MB)
    테스트 4 〉	통과 (0.13ms, 10.1MB)
    테스트 5 〉	통과 (0.93ms, 10.3MB)
    테스트 6 〉	통과 (2.54ms, 12.5MB)
    테스트 7 〉	통과 (2.68ms, 12.6MB)
    테스트 8 〉	통과 (3.95ms, 12.4MB)
    테스트 9 〉	통과 (15.09ms, 13.7MB)
    테스트 10 〉	통과 (130.14ms, 21.3MB)
    테스트 11 〉	통과 (123.55ms, 20.6MB)
    테스트 12 〉	통과 (120.79ms, 20.4MB)
    테스트 13 〉	통과 (109.72ms, 20.6MB)

채점 결과
    정확성: 100.0
    합계: 100.0 / 100.0
'''
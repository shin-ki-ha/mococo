'''
테스트 1 〉	통과 (0.03ms, 10.3MB)
테스트 2 〉	통과 (0.12ms, 10.3MB)
테스트 3 〉	통과 (0.08ms, 10.3MB)
테스트 4 〉	통과 (0.20ms, 10.2MB)
테스트 5 〉	통과 (2.21ms, 10.3MB)
테스트 6 〉	통과 (8.95ms, 13.3MB)
테스트 7 〉	통과 (9.32ms, 13.3MB)
테스트 8 〉	통과 (9.77ms, 13.6MB)
테스트 9 〉	통과 (23.63ms, 14.3MB)
테스트 10 〉	통과 (174.77ms, 21.6MB)
테스트 11 〉	통과 (138.53ms, 21.1MB)
테스트 12 〉	통과 (128.92ms, 21.1MB)
테스트 13 〉	통과 (132.95ms, 21.1MB)
'''

def solution(enroll, referral, seller, amount):
    answer = []
    # [자식1, 자식2, 부모, 돈]에 대한 트리 생성
    # 1번 인덱스를 center로 사용하기 때문에 +2만큼 트리 생성
    tree = [[0, 0, 0, 0] for _ in range(len(enroll) + 2)]
    # 조직 구성원에 대해 index를 value로 설정하기 위한 딕셔너리
    # 인덱스를 트리와 맞추기 위해 +2
    enroll_dict = {}
    for i in range(2, len(enroll) + 2):
        enroll_dict[enroll[i - 2]] = i
    
    # 부모, 자식 트리에 적용하기
    for i in range(1, len(referral) + 1):
        # 부모 인덱스 찾기
        if referral[i - 1] == '-': # 부모가 center일 때
            parent = 1
        else: # 부모가 enroll일 때
            parent = enroll_dict[referral[i - 1]]
            
        child = enroll_dict[enroll[i - 1]] # 자식 인덱스 찾기
        
        # 부모 노드에 자식 노드 연결
        tree[child][2] = parent
        if tree[parent][0] == 0: # 자식이 없다면 0번 인덱스에 자식 넣기
            tree[parent][0] = child
        else: # 자식이 있다면 1번 인덱스에 자식 넣기
            tree[parent][1] = child
        

    for i in range(len(seller)):
        money = amount[i] * 100 # 수입
        stack = [enroll_dict[seller[i]]] # 판매원의 트리 인덱스를 찾기 위한 stack 설정
        
        while stack:
            tree_num = stack.pop() # 판매원의 트리 인덱스
            my_money = money - (money // 10) # 본인에게 남는 돈
            money //= 10 # 10% 수수료
            tree[tree_num][3] += my_money # 본인의 이익 넣기
            
            # 백트레킹
            # 올라갈 돈이 없다면 순회하는 의미가 없기 때문에 가지치기
            if money < 1:
                break
            
            # 부모 노드가 있다면 부모로 올라가기 위한 stack
            if tree[tree_num][2] != 0:
                stack.append(tree[tree_num][2])

    for i in range(2, len(tree)):
        answer.append(tree[i][3])
    
    return answer
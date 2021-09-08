'''
210908 - 2. 보석 쇼핑 (실패 - 시간초과)
https://programmers.co.kr/learn/courses/30/lessons/67258

- 내 코드에서 아쉬운 점 :(
    (1) 실패(시간초과) 원인 : gems 배열의 크기는 1 이상 100,000 이하, 이중for문
    (2) 배열의 일정 부분을 Set으로 만들어주는, 혹은 보석 종류의 수를 확인하는 코드에서도 시간이 오래 걸리는 것으로 예상됨
    (3) 결국 모든 경우를 확인해야 하기 때문에, 그나마 투포인터로 풀면 효율성 개선될 것으로 추정됨
        => 역시 효율성에서 많이 틀린다. 더 좋은 해결방법을 찾고싶다 ㅠ.ㅠ

'''

# Try - 1 (시간초과 / 정확성: 20.0, 효율성: 0.0, 합계: 20.0 / 100.0)
def solution(gems):
    # 배열을 Set으로 만들고, 그 Set을 다시 리스트로 만든다
    gs = list(set(gems))

    # 모든 보석이 존재하는 길이를 최댓값으로 시작
    length = len(gems)
    answer = [1, length]

    for i in range(0, len(gems)-len(gs)+1):
        # 모든 보석의 갯수만큼의 길이부터 시작해서 for문을 돌고자 했다.
        for j in range(i+len(gs)-1, len(gems)):
            curr = list(set(gems[i:j+1]))
            # 만약 현재 구간에 모든 보석이 존재하고, 구간 길이가 최소라면?
            # break 해서 다음 i값, 즉 다음 시작 진열대 번호에서부터 다시 확인해준다
            if len(curr)==len(gs) and j-i+1<length:
                length = j-i+1
                answer = [i+1, j+1]
                break
    
    return answer


# Try - 2 (시간초과 / 정확성: 28.9, 효율성: 0.0, 합계: 28.9 / 100.0)
def solution(gems):
    gs = list(set(gems))
    length = len(gems)
    answer = [1, length]
    
    for i in range(0, len(gems)-len(gs)+1):
        # 만약 가장 짧은 구간이 여러 개라면 시작 진열대 번호가 가장 작은 구간을 return해야 하기 때문에 그냥 for문 종료시켜 버림
        if length==len(gs):
            break

        # 보석 종류의 수를 최소 구간길이로 보장한다.
        j = i+len(gs)-1

        # 진열대 끝점을 늘려가며 확인해준다.
        while True:
            # 진열대를 벗어나거나, 현재 최소 길이를 넘어서는 구간이면 바로 종료
            if j>=len(gems) or j-i+1>length:
                break
            
            curr = list(set(gems[i:j+1]))
            if len(curr)==len(gs) and j-i+1<length:
                length = j-i+1
                answer = [i+1, j+1]
                break
            j+=1
    
    return answer


# Try 3 - (시간초과 / 정확성: 33.3, 효율성: 4.4, 합계: 37.8 / 100.0)
'''
투 포인터 (Two Pointers) 활용
- 시작점(start)과 끝점(end)이 첫 번째 원소의 인덱스(0)를 가리키도록 한다
- 현재 구간 내 보석 종류 수가 M보다 작다면, end를 1 증가시킨다
- 현재 구간 내 보석 종류 수가 M보다 크거나 같다면, start를 1 증가시킨다
- 모든 경우를 확인(start=end=N)할 때까지 반복한다

참고: https://freedeveloper.tistory.com/393
'''
def solution(gems):
    N = len(gems)
    M = len(set(gems))
    answer = [1, N]
    length = N
    
    end = 0
    for start in range(N):
        while len(set(gems[start:end+1])) < M and end < N:
            end += 1
        if len(set(gems[start:end+1])) == M and end-start+1<length:
            length = end-start+1
            answer = [start+1, end+1]
        
    return answer


# Try 4 - (시간초과 / 정확성: 33.3, 효율성: 13.3, 합계: 46.7 / 100.0)
def solution(gems):
    gs = list(set(gems))
    N = len(gems)
    M = len(gs)
    
    answer = [1, N]
    length = N
    
    end = 0
    # 딕셔너리 생성하여 start~end 사이 각 보석 종류의 수 확인
    curr = {g : 0 for g in gs} 
    curr[gems[0]] += 1
    
    for start in range(N):
        while counting(curr) < M and end < N:
            end += 1
            if end < N:
                curr[gems[end]] += 1
        if counting(curr) == M and start<=end and end-start+1<length:
            length = end-start+1
            answer = [start+1, end+1]
        curr[gems[start]] -= 1
        
    return answer

def counting(dic):
    cnt = 0
    for d in dic:
        if dic[d]>0:
            cnt += 1
    return cnt


'''
정확성  테스트
    테스트 1 〉	통과 (0.04ms, 10.2MB)
    테스트 2 〉	통과 (0.22ms, 10.3MB)
    테스트 3 〉	통과 (0.71ms, 10.1MB)
    테스트 4 〉	통과 (37.21ms, 10.3MB)
    테스트 5 〉	통과 (0.40ms, 10.3MB)
    테스트 6 〉	통과 (0.02ms, 10.2MB)
    테스트 7 〉	통과 (0.03ms, 10.3MB)
    테스트 8 〉	통과 (12.77ms, 10.3MB)
    테스트 9 〉	통과 (8.33ms, 10.3MB)
    테스트 10 〉	통과 (79.83ms, 10.3MB)
    테스트 11 〉	통과 (124.73ms, 10.3MB)
    테스트 12 〉	통과 (12.80ms, 10.3MB)
    테스트 13 〉	통과 (17.67ms, 10.4MB)
    테스트 14 〉	통과 (257.11ms, 10.4MB)
    테스트 15 〉	통과 (34.04ms, 10.5MB)
효율성  테스트
    테스트 1 〉	통과 (87.74ms, 10.6MB)
    테스트 2 〉	실패 (시간 초과)
    테스트 3 〉	실패 (시간 초과)
    테스트 4 〉	실패 (시간 초과)
    테스트 5 〉	실패 (시간 초과)
    테스트 6 〉	통과 (265.58ms, 12.2MB)
    테스트 7 〉	실패 (시간 초과)
    테스트 8 〉	실패 (시간 초과)
    테스트 9 〉	통과 (802.35ms, 13.5MB)
    테스트 10 〉	실패 (시간 초과)
    테스트 11 〉	실패 (시간 초과)
    테스트 12 〉	실패 (시간 초과)
    테스트 13 〉	실패 (시간 초과)
    테스트 14 〉	실패 (시간 초과)
    테스트 15 〉	실패 (시간 초과)
채점 결과
    정확성: 33.3
    효율성: 13.3
    합계: 46.7 / 100.0
'''
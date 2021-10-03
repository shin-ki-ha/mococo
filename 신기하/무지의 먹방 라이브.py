'''
효율성 문제
맨 앞의 음식 크기 * 음식의 수보다 k가 크다면 그만큼 음식이 돌아가기 때문에 크기 순으로 정렬하는 것이 매우 중요
음식이 k만큼 순환하지 않는다면 중간에 정전이 일어나기 때문에 그 부분을 추출하면 정답
'''

import heapq

def solution(food_times, k):
    if sum(food_times) <= k:
        return -1
    
    hq = []
    
    # 크기 순으로 정렬하기 heapq가 좋은 알고리즘이라 사용하였지만 lambda x : x[1]로 하여 크기 순으로 sort도 가능
    for i, v in enumerate(food_times):
        heapq.heappush(hq, (v, i+1))
    
    sum_time = 0
    prev = 0
    length = len(food_times)
    
    # 작은 음식 순서로 계속 추출
    while 1:
        if sum_time + (hq[0][0] - prev) * length >= k:
            break
        now = heapq.heappop(hq)
        sum_time += (now[0] - prev) * length # 지금까지 지난 시간
        length -= 1 # 한 가지 음식을 다 먹었기 때문에 -1
        prev = now[0] # 그 음식의 바퀴 수만큼 빼주어 중복 제거
    
    hq.sort(key = lambda x : x[1])
    k -= sum_time
    answer = hq[k%length][1]
    
    return answer
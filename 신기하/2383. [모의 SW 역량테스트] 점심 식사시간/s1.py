import sys
sys.stdin = open("input.txt", "r")

# 조건 : 계단의 수는 2개 => 계단을 이용하는 모든 경우의 수를 power_set을 이용해 구함
def power_set(visit, n, k):
    global min_time

    # 경우의 수가 구해지면 계단 내려가는 시뮬레이션 시작
    if n == k:
        dis_list = [0] # 1번 인덱스부터 시작하기 위한 0번째 인덱스 추가
        for i in range(1, k):
            distance = abs(person[i][0] - step[visit[i]][0]) + abs(person[i][1] - step[visit[i]][1]) # 해당 사람의 계단까지 거리 구하기
            dis_list.append([distance, visit[i]]) # 해당 사람의 인덱스에 거리와 계단 번호 적기
        i = 1
        stack = [0, [], []] # 1번 계단, 2번 계단 넣을 스택 만들기
        check_person = [0] * (k - 1) # 게단 내려간 사람 체크 할 리스트

        while 1:
            # 백트래킹 조건 : 계단에 아무도 없고 모든 사람이 체크되면 모든 사람이 내려감
            if 0 not in check_person and len(stack[1]) == 0 and len(stack[2]) == 0:
                break
            
            # 계단 내려가는 조건
            for s in range(1, 3):
                if len(stack[s]) == 1: # 계단을 1명이 내려갈 때
                    stack[s][0][1] += 1
                    if stack[s][0][1] == step[s][2]:
                        stack[s].pop(0)

                elif len(stack[s]) == 2: # 계단을 2명이 내려갈 때
                    stack[s][0][1] += 1
                    stack[s][1][1] += 1
                    if stack[s][1][1] == step[s][2]:
                        stack[s].pop(1)
                    if stack[s][0][1] == step[s][2]:
                        stack[s].pop(0)

                elif len(stack[s]) >= 3: # 계단을 3명이 내려갈 때
                    stack[s][0][1] += 1
                    stack[s][1][1] += 1
                    stack[s][2][1] += 1
                    if stack[s][2][1] == step[s][2]:
                        stack[s].pop(2)
                    if stack[s][1][1] == step[s][2]:
                        stack[s].pop(1)
                    if stack[s][0][1] == step[s][2]:
                        stack[s].pop(0)

            # 계단에 도착하면 내려갈 수 있도록 스택에 넣기
            for idx in range(1, k):
                if len(dis_list[idx]) > 1:
                    d, step_num = dis_list[idx]
                    if d <= i and check_person[idx - 1] == 0:
                        stack[step_num].append([idx, 0])
                        check_person[idx - 1] = 1

            i += 1
        
        # 최소값 구하기
        if i < min_time:
            min_time = i
            return
        if i >= min_time:
            return

    # 경우의 수를 재귀를 통해 구함
    visit[n] = 1
    power_set(visit, n + 1, k)
    visit[n] = 2
    power_set(visit, n + 1, k)

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    person = [0]
    step = [0]
    min_time = 99999999999

    for c in range(N):
        for r in range(N):
            if matrix[c][r] == 0:
                continue
            if matrix[c][r] == 1:
                person.append([c, r])
            else:
                step.append([c, r, matrix[c][r]])

    visit = [0 for _ in range(len(person))]

    power_set(visit, 1, len(visit))

    print('#{} {}'.format(tc, min_time))


import sys
sys.stdin = open("input.txt", "r")

def power_set(visit, n, k):
    global min_time

    if n == k:
        dis_list = [0]
        for i in range(1, k):
            distance = abs(person[i][0] - step[visit[i]][0]) + abs(person[i][1] - step[visit[i]][1])
            dis_list.append([distance, visit[i]])
        i = 1
        stack = [0, [], []]
        check_person = [0] * (k - 1)

        while 1:
            if 0 not in check_person and len(stack[1]) == 0 and len(stack[2]) == 0:
                break

            for s in range(1, 3):
                if len(stack[s]) == 1:
                    stack[s][0][1] += 1
                    if stack[s][0][1] == step[s][2]:
                        stack[s].pop(0)

                elif len(stack[s]) == 2:
                    stack[s][0][1] += 1
                    stack[s][1][1] += 1
                    if stack[s][1][1] == step[s][2]:
                        stack[s].pop(1)
                    if stack[s][0][1] == step[s][2]:
                        stack[s].pop(0)

                elif len(stack[s]) >= 3:
                    stack[s][0][1] += 1
                    stack[s][1][1] += 1
                    stack[s][2][1] += 1
                    if stack[s][2][1] == step[s][2]:
                        stack[s].pop(2)
                    if stack[s][1][1] == step[s][2]:
                        stack[s].pop(1)
                    if stack[s][0][1] == step[s][2]:
                        stack[s].pop(0)

            for idx in range(1, k):
                if len(dis_list[idx]) > 1:
                    d, step_num = dis_list[idx]
                    if d <= i and check_person[idx - 1] == 0:
                        stack[step_num].append([idx, 0])
                        check_person[idx - 1] = 1

            i += 1
        if i < min_time:
            min_time = i
            return
        if i >= min_time:
            return
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


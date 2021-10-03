def solution(n, s, a, b, fares):
    # 초기 설정
    inf = int(1e9)
    answer = inf
    matrix = list([inf for _ in range(n)] for _ in range(n))

    # 자신에게 가는 것은 비용이 들지 않음
    for i in range(n):
        matrix[i][i] = 0

    # fare 설정
    for fare in fares:
        matrix[fare[0] - 1][fare[1] - 1] = fare[2]
        matrix[fare[1] - 1][fare[0] - 1] = fare[2]

    # 플로이드 와샬 알고리즘
    # 모든 점 간의 최소 거리를 구함
    for k in range(n):  # 경유지
        for i in range(n):  # 출발지
            for j in range(i, n):  # 목적지
                if i != j:
                    result = min(matrix[i][j], matrix[i][k] + matrix[k][j])
                    matrix[i][j] = matrix[j][i] = result

    # 모든 점에서 S, A, B를 가는데 걸리는 비용을 더해서 최소값이 답
    for i in range(n):
        result = matrix[s - 1][i] + matrix[a - 1][i] + matrix[b - 1][i]
        if answer > result:
            answer = result

    return answer
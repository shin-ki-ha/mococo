import sys
sys.stdin = open("input.txt", "r")

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    max_score = 0

    ny = [-1, 1, 0, 0]
    nx = [0, 0, -1, 1]

    for c in range(N):
        for r in range(N):
            if matrix[c][r] == 0: # 구슬 놓을 위치 찾기
                for m in range(4): # 상하좌우 방향 이동
                    score = 0
                    go_x, go_y = r, c
                    mode = m # mode가 0 : 상, 1 : 하, 2 : 좌, 3 : 우
                    while 1:
                        go_y = go_y + ny[mode]
                        go_x = go_x + nx[mode]

                        # 시작 위치로 되돌아오면 끝
                        if go_y == c and go_x == r:
                            if score > max_score:
                                max_score = score
                            break

                        if go_y == -1: # 위에 벽 부딪침
                            mode = 1
                            score += 1
                            continue

                        elif go_y == N: # 밑에 벽 부딪침
                            mode = 0
                            score += 1
                            continue

                        elif go_x == -1: # 왼벽 부딪침
                            mode = 3
                            score += 1
                            continue

                        elif go_x == N: # 오른벽 부딪침
                            mode = 2
                            score += 1
                            continue

                        if matrix[go_y][go_x] == 0: # 통로인 경우 진행
                            continue
                        
                        # 사물에 부딪치는 경우
                        elif matrix[go_y][go_x] == 1:
                            if mode == 0:
                                mode = 1
                            elif mode == 1:
                                mode = 3
                            elif mode == 2:
                                mode = 0
                            else:
                                mode = 2
                            score += 1
                            continue

                        elif matrix[go_y][go_x] == 2:
                            if mode == 0:
                                mode = 3
                            elif mode == 1:
                                mode = 0
                            elif mode == 2:
                                mode = 1
                            else:
                                mode = 2
                            score += 1
                            continue

                        elif matrix[go_y][go_x] == 3:
                            if mode == 0:
                                mode = 2
                            elif mode == 1:
                                mode = 0
                            elif mode == 2:
                                mode = 3
                            else:
                                mode = 1
                            score += 1
                            continue

                        elif matrix[go_y][go_x] == 4:
                            if mode == 0:
                                mode = 1
                            elif mode == 1:
                                mode = 2
                            elif mode == 2:
                                mode = 3
                            else:
                                mode = 0
                            score += 1
                            continue

                        elif matrix[go_y][go_x] == 5:
                            if mode == 0:
                                mode = 1
                            elif mode == 1:
                                mode = 0
                            elif mode == 2:
                                mode = 3
                            else:
                                mode = 2
                            score += 1
                            continue
                        
                        # 블랙홀에 들어가는 경우
                        elif matrix[go_y][go_x] == -1:
                            if score > max_score:
                                max_score = score
                            break
                        
                        # 웜홀에 들어가는 경우 => 반대편 웜홀 찾아서 내보내기
                        elif matrix[go_y][go_x] >= 6:
                            hole = matrix[go_y][go_x]
                            check_hole = False
                            for i in range(N):
                                for j in range(N):
                                    if matrix[i][j] == hole:
                                        if i != go_y or j != go_x:
                                            go_y, go_x = i, j
                                            check_hole = True
                                            break
                                if check_hole:
                                    break

    print('#{} {}'.format(tc, max_score))
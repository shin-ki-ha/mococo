import sys
sys.stdin = open("input.txt", "r")

def cafe(mode, first, x, y, f_x, f_y, stack):
    global total
    # mode (우하), (좌하), (좌상), (우상)
    if 0 <= x < N and 0 <= y < N:
        if mode == 2:
            if y <= f_y:
                return

        if mode == 3:
            if x == f_x and y == f_y:
                result = len(stack)
                total = max(total, result)
                return
            elif x >= f_x or y <= f_y:
                return

        now = matrix[y][x]
        if now in stack:
            return
        else:
            stack.append(now)
            if first:
                cafe(0, False, x + 1, y + 1, f_x, f_y, stack)
            else:
                if mode == 0:
                    cafe(0, first, x + 1, y + 1, f_x, f_y, stack)
                    cafe(1, first, x - 1, y + 1, f_x, f_y, stack)
                    stack.pop()
                elif mode == 1:
                    cafe(1, first, x - 1, y + 1, f_x, f_y, stack)
                    cafe(2, first, x - 1, y - 1, f_x, f_y, stack)
                    stack.pop()
                elif mode == 2:
                    cafe(2, first, x - 1, y - 1, f_x, f_y, stack)
                    cafe(3, first, x + 1, y - 1, f_x, f_y, stack)
                    stack.pop()
                else:
                    cafe(3, first, x + 1, y - 1, f_x, f_y, stack)
                    stack.pop()
    else:
        return

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    total = 0
    for c in range(N - 1):
        for r in range(1, N - 1):
            cafe(0, True, r, c, r, c, [])
    if total == 0:
        total = -1
    print('#{} {}'.format(tc, total))
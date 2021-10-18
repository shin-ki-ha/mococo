import sys
sys.stdin = open("input.txt", "r")

def checkFilm(film):
    for x in range(W):
        cnt = 0
        now = film[0][x]
        for y in range(1, D):
            if film[y][x] == now:
                cnt += 1
            else:
                if cnt >= K - 1:
                    continue
                else:
                    cnt = 0
                    now = film[y][x]
        if cnt < K - 1:
            return False
    return True

def makeFilm(cnt, idx):
    global result
    if cnt >= result:
        return

    if checkFilm(films):
        result = min(result, cnt)

    for i in range(idx + 1, D):
        layer = films[i][:]
        for j in range(W):
            films[i][j] = 0
        makeFilm(cnt + 1, i)

        for j in range(W):
            films[i][j] = 1
        makeFilm(cnt + 1, i)

        for j in range(W):
            films[i][j] = layer[j]



for tc in range(1, int(input()) + 1):
    D, W, K = map(int, input().split())
    films = list(list(map(int, input().split())) for _ in range(D))
    result = K
    makeFilm(0, -1)
    print('#{} {}'.format(tc, result))

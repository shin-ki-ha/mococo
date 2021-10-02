def build_check(result):
    for x, y, what in result:
        # 기둥인 경우
        if what == 0:
            if y == 0 or (x - 1, y, 1) in result or (x, y, 1) in result or (x, y - 1, 0) in result:
                continue
            else:
                return False
        # 보인 경우
        else:
            if (x, y - 1, 0) in result or (x + 1, y - 1, 0) in result or (
                    (x - 1, y, 1) in result and (x + 1, y, 1) in result):
                continue
            else:
                return False
    return True


def solution(n, build_frame):
    result = set()
    for x, y, what, how in build_frame:
        # 설치
        if how == 1:
            result.add((x, y, what))
            check = build_check(result)
            if check:
                continue
            else:
                result.remove((x, y, what))

        # 제거
        else:
            if (x, y, what) in result:
                result.remove((x, y, what))
                check = build_check(result)
                if check:
                    continue
                else:
                    result.add((x, y, what))
    result = [list(i) for i in result]
    return sorted(result, key=lambda x: (x[0], x[1], x[2]))
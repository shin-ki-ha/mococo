'''
테스트 1 〉	통과 (0.03ms, 10.2MB)
테스트 2 〉	통과 (0.02ms, 10.1MB)
테스트 3 〉	통과 (258.57ms, 11.7MB)
테스트 4 〉	통과 (134.77ms, 11.2MB)
테스트 5 〉	통과 (209.02ms, 11.5MB)
테스트 6 〉	통과 (215.10ms, 11.8MB)
테스트 7 〉	통과 (220.25ms, 12MB)
테스트 8 〉	통과 (131.68ms, 11.2MB)
테스트 9 〉	통과 (200.49ms, 12MB)
테스트 10 〉	통과 (159.00ms, 11.4MB)
테스트 11 〉	통과 (146.20ms, 11.4MB)
'''

def solution(rows, columns, queries):
    answer = []
    result = []
    
    # matrix 만들기
    i = 1
    for _ in range(rows):
        answer_list = []
        for _ in range(columns):
            answer_list.append(i)
            i += 1
        answer.append(answer_list)
    
    # queries 받아오기
    for query in queries:
        # x1, y1, x2, y2 설정
        x1, y1 = query[0] - 1, query[1] - 1
        x2, y2 = query[2] - 1, query[3] - 1
        
        # 이동시키는 숫자의 최소값을 구하기 위해 min_val을 크게 설정
        min_val = 99999999
        
        # now는 이동하는 방향을 알려주는 숫자
        # now = 1 => 오른 방향
        # now = 2 => 아래 방향
        # now = 3 => 왼쪽 방향
        # now = 4 => 위 방향
        now = 1
        c, r = x1, y1 # 현재 행렬 인덱스
        
        # 이동할 인덱스의 값을 넣고 빼기 위해 스택 활용
        stack = []
        stack.append(answer[c][r])
        
        # 꺾는 상황엔 stack에 추가하지 않아 stack이 비는 상황이 생기기 때문에 꺾는 지 판단(1이면 진행, 0이면 꺾음)
        handle = 1
        
        while 1:
            # 진행 상황일 때, 다음 값에 넣기 위해 stack에서 빼줌
            if handle:
                change_val = stack.pop()
            
            # 바꿀 값이 최소값보다 작다면 그 값이 최소값이기 때문에 최신화
            if change_val < min_val:
                min_val = change_val
            
            # 오른쪽 진행 상황
            if now == 1:
                # 현재 row가 y2보다 작다면 오른쪽에 바꿀 값 존재
                # 현재 값을 오른쪽으로 바꿔주고 오른쪽 값은 stack에 넣은 뒤 한 칸 이동
                if r < y2:
                    stack.append(answer[c][r+1])
                    answer[c][r+1] = change_val
                    r += 1
                # 현재 row가 y2랑 같다면 오른쪽에 바꿀 값이 존재하지 않음
                # 방향을 꺾어야 하며 이 때 stack에 추가할 값이 없어 handle을 0으로 바꿔 stack에서 값을 빼지 않도록 함
                else:
                    now = 2
                    handle = 0
            
            # 아래 진행 상황
            elif now == 2:
                if c < x2:
                    stack.append(answer[c+1][r])
                    answer[c+1][r] = change_val
                    c += 1
                    handle = 1 # 다시 진행을 하기 위해 handle을 활성화
                else:
                    now = 3
                    handle = 0
            
            # 왼쪽 진행 상황
            elif now == 3:
                if r > y1:
                    stack.append(answer[c][r-1])
                    answer[c][r-1] = change_val
                    r -= 1
                    handle = 1
                else:
                    now = 4
                    handle = 0
            
            # 위 진행 상황
            elif now == 4:
                if c > x1:
                    stack.append(answer[c-1][r])
                    answer[c-1][r] = change_val
                    c -= 1
                    handle = 1
                # colum이 x1이 되면 시작 위치에 도착했기 때문에 반복문을 끝냄
                else:
                    break
    
        result.append(min_val)
    
    return result
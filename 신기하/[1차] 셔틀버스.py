'''
버스를 순차적으로 보내며 태울 수 있는 승객을 체크하는 시뮬레이션 방식
'''

def solution(n, t, m, timetable):
    answer = ''
    new_timetable = []
    # 승객의 시간을 int로 변환
    for time in timetable:
        new_time = int(time[3:]) + (int(time[0:2]) * 60)
        new_timetable.append(new_time)
    new_timetable.sort(reverse=True) # pop을 사용하기 위해 반대로 정렬
    
    bus_table = [540+(i*t) for i in range(n)] # 버스의 출발 시간 만들기
    
    bus_idx = 0
    last_time = 0
    
    while bus_idx < len(bus_table):
        stack = []
        # stack의 길이가 m과 같다면 만석
        while len(stack) != m:
            # 버스를 탈 수 있는지 조건 체크
            if len(new_timetable) > 0 and new_timetable[-1] <= bus_table[bus_idx]:
                stack.append(new_timetable.pop())
            else:
                break
        
        if bus_idx == len(bus_table) - 1:
            if len(stack) == m: # 마지막 버스에 마지막 승객이 탔다면 그 승객보다 1분 일찍 타야함
                last_time = stack.pop() - 1
            else: # 마지막 버스에 승객이 널널하다면 버스 시간에 맞춤
                last_time = bus_table[bus_idx]
        bus_idx += 1
    
    # 시간 변환
    hh = last_time // 60
    mm = last_time % 60
    
    if hh < 10:
        hh = '0' + str(hh)
    else:
        hh = str(hh)
    
    if mm < 10:
        mm = '0' + str(mm)
    else:
        mm = str(mm)
        
    answer = hh + ':' + mm    
    
    return answer
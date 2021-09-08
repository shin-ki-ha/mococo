def safe(x, y, x1, y1, x2, y2):
    return x>=x1 and y>=y1 and x<=x2 and y<=y2    

def solution(rows, columns, queries):
    answer = []
    board = [[(i-1)*columns+j for j in range(columns+1)] for i in range(rows+1)]
    
    dx=[1,0,-1,0]
    dy=[0,1,0,-1]
    for q in queries:
        y1, x1, y2, x2 = q
        x, y=x1, y1
        nx, ny=x1 + 1, y1
        
        dir=0
        preVal=board[y][x]
        curVal=board[y][x]
        minVal=curVal
        
        while nx!=x1 or ny!=y1:
            nx,ny=x+dx[dir],y+dy[dir]
            if safe(nx,ny,x1,y1,x2,y2)==False:
                dir=(dir+1)%4
                continue
                
            minVal=min(minVal,board[ny][nx])
            curVal=board[ny][nx]
            board[ny][nx]=preVal
            preVal=curVal
            x,y=nx,ny
            
        answer.append(minVal)
    return answer
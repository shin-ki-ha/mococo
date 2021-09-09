import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        int n=1;
        int[][] arr=new int[rows+1][columns+1];
        
        //일단 배열 생성
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                arr[i][j]=n++;    
            }
        }
        int an=0;
        answer=new int[queries.length];
        
        //회전하기 위하여 q 생성
        Queue<Integer> q=new LinkedList<>();
        
        for(int i=0; i<queries.length; i++){
            //시작 인덱스
            int sx=queries[i][1];
            int sy=queries[i][0];
            //끝 인덱스
            int ex=queries[i][3];
            int ey=queries[i][2];
            
            int min=Integer.MAX_VALUE;
            //q에 숫자를 넣는다 -> 시계방향으로 돌려야 하기 때문에 q에는 반시계 방향으로 원소를 넣는다.
            
            //↓ 방향으로 
            for(int y=sy; y<=ey; y++){
                q.offer(arr[y][sx]);
                min=Math.min(arr[y][sx],min);
            }
            
            //→ 방향
            for(int x=sx+1; x<=ex; x++){
                q.offer(arr[ey][x]);
                min=Math.min(arr[ey][x],min);
            }
            //↑ 방향
             for(int y=ey-1; y>=sy; y--){
                q.offer(arr[y][ex]);
                min=Math.min(arr[y][ex],min);
            } 
             //← 방향
            for(int x=ex-1; x>=sx+1; x--){
                q.offer(arr[sy][x]);
                min=Math.min(arr[sy][x],min);
            }
            // System.out.println(min);
            //다 넣었으면 회전 시킨다.
            q.offer(q.poll());
            // System.out.println(q.toString());
            
            ///큐에 집어 넣으면서 찾은 최솟값 저장 
            answer[an++]=min;
            
            //다시 같은 형식으로 q에 있는거를 배열에 넣는다.
            for(int y=sy; y<=ey; y++){
                arr[y][sx]=q.poll();
                
            }
            for(int x=sx+1; x<=ex; x++){
                arr[ey][x]=q.poll();
                
            }
             for(int y=ey-1; y>=sy; y--){
                arr[y][ex]=q.poll();
            } 
            for(int x=ex-1; x>=sx+1; x--){
                arr[sy][x]=q.poll();
            }
            // System.out.println(q.toString());
            q.clear();
            // print(arr,rows,columns);
        }
        
        
        
        
        
        
        
        return answer;
    }
    
    public static void print(int[][] map,int rows, int columns){
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                System.out.print(map[i][j]+" "); 
            }
            System.out.println();
        }
    }
}

/***
 * 테스트 1 〉	통과 (0.22ms, 71.3MB)
테스트 2 〉	통과 (0.15ms, 59.8MB)
테스트 3 〉	통과 (57.35ms, 80.8MB)
테스트 4 〉	통과 (51.91ms, 80.8MB)
테스트 5 〉	통과 (47.67ms, 102MB)
테스트 6 〉	통과 (49.22ms, 81.9MB)
테스트 7 〉	통과 (52.39ms, 81.5MB)
테스트 8 〉	통과 (37.33ms, 82.7MB)
테스트 9 〉	통과 (50.32ms, 79.1MB)
테스트 10 〉	통과 (56.39ms, 102MB)
테스트 11 〉	통과 (45.03ms, 81.7MB)
채점 결과
정확성: 100.0
합계: 100.0 / 100.0
 */



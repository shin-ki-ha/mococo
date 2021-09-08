/* 
210908 - 1. 행렬 테두리 회전하기 (성공)
https://programmers.co.kr/learn/courses/30/lessons/77485

- 내 코드에서 아쉬운 점 :(
    (1) Deque를 처음 써봐서, 더 효과적으로 활용하지 못한 것 같다.
	(2) "테두리 돌기" 과정의 코드를 간략화했으면 더 좋았을 것 같다.
    
*/

import java.util.*;

public class Solution1 {

	// public static void main(String[] args) {
	// 	// TODO Auto-generated method stub
	// }

    public int[] solution(int rows, int cols, int[][] queries) {
        int[] answer = new int[queries.length];

		// 처음에 행렬에는 가로 방향으로 숫자가 1부터 하나씩 증가하면서 적혀있습니다.
		// 즉, 아무 회전도 하지 않았을 때, i 행 j 열에 있는 숫자는 ((i-1) x columns + j)입니다.
        int[][] map = new int[rows][cols];
        int num = 0;
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                map[i][j] = ++num; 
            }
        }
        
        for(int i=0; i<queries.length; i++) {
            answer[i] = rotate(map, queries[i], i);
        }
        
        return answer;
    }
    
    public int rotate(int[][] map, int[] query, int curr) {
        int x1 = query[0]-1; int y1 = query[1]-1;
        int x2 = query[2]-1; int y2 = query[3]-1;
        int min = map[x1][y1];
        
		// Deque에서 Queue와 같이 offer(), poll() 하려면? 
		// offer() -> offerLast()
		// poll() -> pollFirst()
        Deque<Integer> que = new LinkedList<Integer>();
        
        // "테두리를 돈다"
        // (1) 행이 x1이고 y1->y2 
		// (2) 열이 y2이고 x1->x2
		// (3) 행이 x2이고 y2->y1
		// (4) 열이 y1이고 x2->x1 

		// 시작 위치 (r, c)
        int r=x1; int c=y1;
        while(c!=y2) { //(1)
            if(map[r][c]<min) min = map[r][c];
            que.offerLast(map[r][c]);
            c++;
        }
        while(r!=x2) { //(2)
            if(map[r][c]<min) min = map[r][c];
            que.offerLast(map[r][c]);
            r++;
        }
        while(c!=y1) { //(3)
            if(map[r][c]<min) min = map[r][c];
            que.offerLast(map[r][c]);
            c--;
        }
        while(r!=x1) { //(4)
            if(map[r][c]<min) min = map[r][c];
            que.offerLast(map[r][c]);
            r--;
        }

		// "시계방향으로 회전" 
		// = 큐의 맨 뒤 숫자를 맨 앞으로 땡겨서 다시 테두기 돌기
        que.offerFirst(que.pollLast());
        
		// 시작위치 초기화
        r=x1; c=y1;
        while(c!=y2) { //(1)
            map[r][c] = que.pollFirst();
            c++;
        }
        while(r!=x2) { //(2)
            map[r][c] = que.pollFirst();
            r++;
        }
        while(c!=y1) { //(3)
            map[r][c] = que.pollFirst();
            c--;
        }
        while(r!=x1) { //(4)
            map[r][c] = que.pollFirst();
            r--;
        }
        
        return min;
    }

}


/*
정확성  테스트
	테스트 1 〉	통과 (0.21ms, 60.4MB)
	테스트 2 〉	통과 (0.18ms, 58.6MB)
	테스트 3 〉	통과 (41.93ms, 84MB)
	테스트 4 〉	통과 (30.78ms, 96MB)
	테스트 5 〉	통과 (35.81ms, 97.4MB)
	테스트 6 〉	통과 (37.48ms, 100MB)
	테스트 7 〉	통과 (38.19ms, 99.2MB)
	테스트 8 〉	통과 (29.33ms, 94.2MB)
	테스트 9 〉	통과 (34.90ms, 97.7MB)
	테스트 10 〉	통과 (38.05ms, 95.5MB)
	테스트 11 〉	통과 (31.07ms, 80MB)

채점 결과
	정확성: 100.0
	합계: 100.0 / 100.0
*/

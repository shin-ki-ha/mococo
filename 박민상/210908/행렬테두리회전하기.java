import java.util.*;

class Solution {
    //행렬 저장용 2차원배열
    static int[][] board;
    //row, col 길이 저장용
    static int n,m;
    public int[] solution(int rows, int columns, int[][] queries) {
        n = rows; m = columns;
        board = new int[n][m];

        // init board
        // 맨 처음 행렬에 값을 채웁니다
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                board[i][j]=i*m+j+1;
            }
        }

        int[] answer = new int[queries.length];

        //queries에 담긴 순서대로 turn 메소드를 호출하여 회전시킵니다.
        //turn의 반환형은 int이고 회전시킨 숫자중 최솟값을 반환합니다.
        for(int k=0; k<queries.length; k++){
            answer[k] = turn(queries[k][0]-1, queries[k][1]-1, queries[k][2]-1, queries[k][3]-1);
        }


        return answer;
    }

    static int turn(int y1, int x1, int y2, int x2){
        //반환할 값을 저장할 ret 변수입니다.
        //행렬에 존재하는 제일 큰 수는 n*m이므로, n*m으로 초기화합니다.
        int ret=n*m;
        //회전시키기 전 각 모서리에 있는 값을 따로 저장합니다.
        int ul=board[y1][x1], ur=board[y1][x2], dl=board[y2][x1], dr=board[y2][x2];
        //각 모서리에 있는 값과 ret을 먼저 비교합니다.
        ret = Math.min(ret, ul);
        ret = Math.min(ret, ur);
        ret = Math.min(ret, dl);
        ret = Math.min(ret, dr);

        //사각형의 윗부분을 옮깁니다. 옮기는 순서에 유의해야합니다.
        for(int i=x2-1; i>=x1; i--){
            board[y1][i+1]=board[y1][i];
            ret = Math.min(ret, board[y1][i+1]);
        }
        //사각형의 오른쪽 부분
        for(int i=y2-1; i>=y1; i--){
            board[i+1][x2]=board[i][x2];
            ret = Math.min(ret, board[i+1][x2]);
        }
        //사각형의 아랫쪽 부분
        for(int i=x1+1; i<=x2; i++){
            board[y2][i-1]=board[y2][i];
            ret = Math.min(ret, board[y2][i-1]);
        }
        //사각형의 왼쪽 부분
        for(int i=y1+1; i<=y2; i++){
            board[i-1][x1]=board[i][x1];
            ret = Math.min(ret, board[i-1][x1]);
        }

        //각 모서리에 있던 값을 올바른 위치에 담습니다.
        board[y1][x1+1]=ul;
        board[y1+1][x2]=ur;
        board[y2][x2-1]=dr;
        board[y2-1][x1]=dl;

        return ret;
    }

}
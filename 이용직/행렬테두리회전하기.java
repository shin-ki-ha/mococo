class Solution {
    int[][] map;
    int r,c;
    
    class Response{
        int[][] map;
        int min;
    }
    
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        map = new int[rows+1][columns+1];
        int cnt=1;
        r = rows;
        c = columns;
        
        for(int i=1; i<=rows;i++){
            for(int j=1; j<=columns; j++){
                map[i][j] = cnt++;
            }
        }
        cnt = 0;
        for(int[] arr : queries){

            Response r = rotate(arr[0],arr[1],arr[2],arr[3],map);
            
            map = r.map;
            answer[cnt++] = r.min;

        }
            
        
        
        
        return answer;
    }
    
    Response rotate(int startx, int starty, int endx, int endy, int[][] m){
    	int[][] ret = new int[r+1][c+1];
        
        int min = Integer.MAX_VALUE;
        
        for(int i=1; i<= r;i++){
            for(int j=1; j<=c; j++){
                ret[i][j] = m[i][j];
            }
        }
        int lenx = endx - startx;
        int leny = endy - starty;
        
        //  윗 줄 돌리기
        for(int j = starty; j < starty + leny; j++){
            ret[startx][j+1] = m[startx][j];
            if(m[startx][j] < min){
                min = m[startx][j];
            }
        }
        
        // 아랫 줄 돌리기
        for(int j = endy; j>endy-leny; j--){
            ret[endx][j-1] = m[endx][j];
            if(m[endx][j] < min){
                min = m[endx][j];
            }            
        }
        
        // 우측 돌리기
        for(int i = startx; i < startx+lenx; i++){
            ret[i+1][endy] = m[i][endy];
            if(m[i][endy] < min){
                min = m[i][endy];
            }
        }
        
        // 좌측 돌리기
    	for(int i = endx; i>endx-lenx; i--){
            ret[i-1][starty] = m[i][starty];
            if(m[i][starty] < min){
                min = m[i][starty];
            }
        }
        Response r = new Response();
        r.min = min;
        r.map = ret;
        
        return r;
    }
    
    void print(int[][] m) {
        for(int i = 1; i<m.length; i++){
            for(int j=1; j<m[0].length;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
    
}
테스트 1 〉	통과 (0.28ms, 69.6MB)
테스트 2 〉	통과 (0.17ms, 61MB)
테스트 3 〉	통과 (172.29ms, 215MB)
테스트 4 〉	통과 (57.85ms, 105MB)
테스트 5 〉	통과 (159.64ms, 190MB)
테스트 6 〉	통과 (69.55ms, 121MB)
테스트 7 〉	통과 (109.52ms, 147MB)
테스트 8 〉	통과 (67.07ms, 120MB)
테스트 9 〉	통과 (88.75ms, 110MB)
테스트 10 〉	통과 (108.40ms, 102MB)
테스트 11 〉	통과 (81.19ms, 119MB)
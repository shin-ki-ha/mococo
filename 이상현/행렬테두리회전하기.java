class Solution {
    // 회전 함수, 회전시킨 데이터 중 가장 작은 값 반환
    public int rotate(int[][] map, int sr, int sc, int er, int ec) {
        int min = map[sr][sc];
        int x = map[sr][sc];

        for(int i = sr; i < er; i++) {
            map[i][sc] = map[i + 1][sc];
            min = min < map[i + 1][sc] ? min : map[i + 1][sc];
        }
        for(int i = sc; i < ec; i++) {
            map[er][i] = map[er][i + 1];
            min = min < map[er][i + 1] ? min : map[er][i + 1];
        }
        for(int i = er; i > sr; i--) {
            map[i][ec] = map[i - 1][ec];
            min = min < map[i - 1][ec] ? min : map[i - 1][ec];
        }
        for(int i = ec; i > sc; i--) {
            map[sr][i] = map[sr][i - 1];
            min = min < map[sr][i - 1] ? min : map[sr][i - 1];
        }
        map[sr][sc + 1] = x;
        return min;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows][columns];
        int cnt = 1;
        
        // 회전시킬 행렬 생성
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                map[i][j] = cnt++;
            }
        }
        
        // 회전시키면서 가장 작은 데이터를 받아 저장
        for(int i = 0; i < queries.length; i++) {
            answer[i] = rotate(map, queries[i][0] - 1,
                                    queries[i][1] - 1,
                                    queries[i][2] - 1,
                                    queries[i][3] - 1);
        }
        
        return answer;
    }
}

정확성  테스트
테스트 1 〉	통과 (0.04ms, 69.6MB)
테스트 2 〉	통과 (0.03ms, 57.7MB)
테스트 3 〉	통과 (8.70ms, 64MB)
테스트 4 〉	통과 (5.48ms, 64.7MB)
테스트 5 〉	통과 (4.83ms, 76.9MB)
테스트 6 〉	통과 (5.52ms, 64.8MB)
테스트 7 〉	통과 (8.69ms, 66.1MB)
테스트 8 〉	통과 (6.61ms, 80.5MB)
테스트 9 〉	통과 (4.73ms, 79.3MB)
테스트 10 〉	통과 (4.14ms, 78.8MB)
테스트 11 〉	통과 (3.96ms, 63.4MB)
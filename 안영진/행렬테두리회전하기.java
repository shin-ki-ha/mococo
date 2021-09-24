class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        
        
        // 테스트 1 〉	통과 (0.24ms, 73.3MB)
        // 테스트 2 〉	통과 (0.21ms, 76.8MB)
        // 테스트 3 〉	통과 (34.21ms, 99.7MB)
        // 테스트 4 〉	통과 (28.05ms, 96.8MB)
        // 테스트 5 〉	통과 (25.43ms, 93.8MB)
        // 테스트 6 〉	통과 (39.35ms, 97.1MB)
        // 테스트 7 〉	통과 (39.25ms, 107MB)
        // 테스트 8 〉	통과 (25.80ms, 98.1MB)
        // 테스트 9 〉	통과 (37.69ms, 106MB)
        // 테스트 10 〉	통과 (32.14ms, 95.3MB)
        // 테스트 11 〉	통과 (40.77ms, 110MB)
            
            
        //달팽이 돌리기 생각났음
        
        int queries_length = queries.length;
        int[] answer = new int [queries_length];
        int[][] arr = new int [rows][columns];
        
        int count =1;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++) {
                   arr[i][j] = count++;
            }
        }
        
        // for (int i = 0; i < rows; i++){
        //     for (int j = 0; j < columns; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.print("\n");
        // }
       
        
        int x1,y1,x2,y2;
        
        for (int i=0; i< queries_length; i++){
            x1 = queries[i][0] - 1;
            y1 = queries[i][1] - 1;
            x2 = queries[i][2] - 1;
            y2 = queries[i][3] - 1;
            
            int temp = arr[x1][y1];
            int min = temp;
            
            //위로 돌리기
            for (int j = x1; j < x2; j++){
                arr[j][y1] = arr[j+1][y1];
                min = Math.min(min,arr[j][y1]);
            }
            
            //왼쪽으로 돌리기
            for (int j = y1; j < y2; j++){
                arr[x2][j] = arr[x2][j+1];
                min = Math.min(min,arr[x2][j]);
            }
            //아래로 돌리기
            for (int j = x2; j > x1; j--){
                arr[j][y2] = arr[j-1][y2];
                min = Math.min(min,arr[j][y2]);
            }
            //오른쪽으로 돌리기
            for (int j = y2; j > y1; j--){
                arr[x1][j] = arr[x1][j-1];
                min = Math.min(min,arr[x1][j]);
            }
            
            arr[x1][y1+1] = temp;
            answer[i] = min;
            
            System.out.println(min);
        }
        
        
        
        
        
        return answer;
    }
}
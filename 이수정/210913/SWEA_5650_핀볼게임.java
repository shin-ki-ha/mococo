// package day0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5650_핀볼게임 {

	/*
	 * 상/우/하/좌 로 가서 블록을 만났을 때 바뀌는 방향
	 * 1: 하 좌 우 상
	 * 2: 우 좌 상 하
	 * 3: 좌 하 상 우
	 * 4: 하 상 좌 우
	 * 5: 하 좌 상 우
	 */
	static int answer, N, map[][];
	static String[] block = {null, "2310", "1302", "3201", "2031", "2301"};
	static int[] dr= {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; //상우하좌
	static HashMap<Integer, int[][]> hash;
	static List<int[]> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.valueOf(br.readLine());
			map = new int[N][N];
			answer = 0;
			
			hash = new HashMap<>();
			list = new ArrayList<>();
			
			for (int n = 0; n < N; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int m = 0; m < N; m++) { 
					map[n][m] = Integer.valueOf(st.nextToken()); 
					if(map[n][m]==0) { list.add(new int[] {n,m}); }
					if(map[n][m]>=6 && map[n][m]<=10) {
						if(!hash.containsKey(map[n][m])) hash.put(map[n][m], new int[][] {{n, m}});
						else {
							int[][] xys = hash.get(map[n][m]);
							hash.put(map[n][m], new int[][] {xys[0], {n,m}});
						}
					}
				}
			}
			for (int[] start : list) {
				for (int d = 0; d < 4; d++) {
					play(start[0], start[1], d, 0);
				}
			}
			System.out.printf("#%d %d\n", t, answer);
		}
		br.close();
	}
	
	public static void play(int r, int c, int d, int score) {
		int nr=r+dr[d]; int nc=c+dc[d];
		while(true) {
			if(!isSafe(nr, nc)) {
				d=(d+2)%4; 
				score+=1;
			} else if (map[nr][nc]>=1 && map[nr][nc]<=5) {
				d = block[map[nr][nc]].charAt(d) - '0';
				score += 1;
			} else if (map[nr][nc]>=6) {
				int[][] directs = hash.get(map[nr][nc]);
				if(directs[0][0]==nr && directs[0][1]==nc) {
					nr=directs[1][0]; nc=directs[1][1];
				} else {
					nr=directs[0][0]; nc=directs[0][1];
				}
			}
			if((nr==r && nc==c) || (isSafe(nr, nc) && map[nr][nc]==-1)) {
				if(answer<score) answer = score;
				return;
			}
			nr+=dr[d]; nc+=dc[d];
		}
	}

	public static boolean isSafe(int r, int c) {
		if(r<0 || r>=N || c<0 || c>=N) return false;
		return true;
	}
}

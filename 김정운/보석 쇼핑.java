import java.util.*;


/***
 * 
 *첫 번째 풀이는 단순하게 2중 for문으로 탐색-> 시간초과 
 *두 번쨰 풀이는 투포인터 사용하여 문제 해결
 */
class Solution {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		// ArrayList<String> list=new ArrayList<>();
		HashSet<String> set = new HashSet<>();

		// 맵을 이용하여 보석 갯수 확인
		Map<String, Integer> map = new HashMap<>();
		// 셋으로 중복 보석 거르기
		for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}
		// 최소길이
		int min = Integer.MAX_VALUE;

		// 투포인터 시작 지점
		int start = 0;
		// 투포인터 끝 지점
		int end = 0;
		// 보석 갯수
		int cnt = 0;
		// 일단 하나 넣고 시작
		map.put(gems[0], 1);

		while (start <= end && end < gems.length) {
			// 비교하다가 보석을 다 찾으면
			if (map.size() == set.size()) {
				// 일단 최솟값 갱신
				if (min > end - start) {
					min = end - start;
					answer[0] = start + 1;
					answer[1] = end + 1;
				}
				// 시작 지점에 있는 보석 갯수 1개 줄이기
				map.put(gems[start], map.get(gems[start]) - 1);
				// 만약 시작 인덱스에 있는 보석이 맵에 0개 있다면
				if (map.get(gems[start]) == 0) {
					// 시작 인덱스에 있는 보석을 맵에서 삭제
					map.remove(gems[start]);
				}
				// 시작 인덱스 ->로
				start++;

				// 맵의 크기가 셋보다 작으면-> 보석을 다 못모은것
			} else if (map.size() < set.size()) {
				// 보석을 더 탐색하기 위해서 끝 지점을 -> 옮긴다.
				end++;
				if (end >= gems.length)
					break;// 만약 끝에 다다르면 브레이크
				try {
					// 일단 끝 인덱스에 있는 보석이 map에 있는지 검사
					// 없으면 캐치문으로 갈것이다 왜?-> 맵에는 없는 보석을 탐색해서 예외발생
					// 만약 있으면 그 보석의 갯수 구해서
					int tt = map.get(gems[end]);
					// 1 더한다.
					map.put(gems[end], tt + 1);
				} catch (Exception e) {
					// 캐치문으로 넘어왔다는거는 start~end 사이에 보석이 없다는것
					// 그래서 end 인덱스에 있는 보석을 넣어준다.
					map.put(gems[end], 1);

				}

			}

		}
		
		

		return answer;
	}
}


/***
 * 테스트 1 〉	통과 (0.17ms, 72.1MB)
테스트 2 〉	통과 (0.40ms, 58.6MB)
테스트 3 〉	통과 (0.66ms, 61.1MB)
테스트 4 〉	통과 (3.34ms, 73.1MB)
테스트 5 〉	통과 (0.11ms, 59.5MB)
테스트 6 〉	통과 (0.12ms, 59.9MB)
테스트 7 〉	통과 (0.23ms, 70.5MB)
테스트 8 〉	통과 (1.14ms, 73MB)
테스트 9 〉	통과 (1.04ms, 78MB)
테스트 10 〉	통과 (3.49ms, 76.8MB)
테스트 11 〉	통과 (5.88ms, 75.5MB)
테스트 12 〉	통과 (2.81ms, 67.9MB)
테스트 13 〉	통과 (2.42ms, 72.8MB)
테스트 14 〉	통과 (4.03ms, 62.4MB)
테스트 15 〉	통과 (3.10ms, 60MB)
효율성  테스트
테스트 1 〉	통과 (7.17ms, 54.3MB)
테스트 2 〉	통과 (16.62ms, 59.3MB)
테스트 3 〉	통과 (19.37ms, 58.6MB)
테스트 4 〉	통과 (52.94ms, 65.2MB)
테스트 5 〉	통과 (28.44ms, 62.2MB)
테스트 6 〉	통과 (34.36ms, 65.3MB)
테스트 7 〉	통과 (40.51ms, 68.5MB)
테스트 8 〉	통과 (37.92ms, 68.9MB)
테스트 9 〉	통과 (41.45ms, 70.3MB)
테스트 10 〉	통과 (37.86ms, 74.3MB)
테스트 11 〉	통과 (60.54ms, 79MB)
테스트 12 〉	통과 (128.40ms, 78.8MB)
테스트 13 〉	통과 (134.19ms, 80.6MB)
테스트 14 〉	통과 (62.66ms, 80.4MB)
테스트 15 〉	통과 (89.04ms, 81.4MB)
채점 결과
정확성: 33.3
효율성: 66.7
합계: 100.0 / 100.0
 */
***/
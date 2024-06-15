
/*
 * 백트래킹
 * 
 * 백트래킹으로 풀어야 할 것 같긴 한데 가지치기를 하지 않고 브루트포스로 돌면 확인하는 경우의 수가 최대 9^81 개다.
 * 백트래킹으로 이 중 충분한 수의 경우들을 가지치기해서 문제에 주어진 2초만에 푼다는 걸 어떻게 확신할 수 있지..?
 * -> 일단 아래 코드로 81개의 모든 칸이 0인 보드를 입력했을 때 충분히 빠른 시간 안에 출력이 되므로 시간 초과는 나지 않을 것이라 생각한다.
 * 
 * 9개의 정사각형 (ij로 표현)
 * 
 * 	00	01	02		03	04	05		06	07	08
 * 	10	11	12		13	14	15		16	17	18
 *  20	21	22		23	24	25		26	27	28
 * 		
 * 	30	31	32		33	34	35		36	37	38
 * 	40	41	42		43	44	45		46	47	48
 * 	50	51	52		53	54	55		56	57	58
 * 		
 * 	60	61	62		63	64	65		66	67	68
 * 	70	71	72		73	74	75		76	77	78
 * 	80	81	82		83	84	85		86	87	88
 * 
 * 왼쪽 상단부터 행 우선으로 0, 1, 2, 3, 4, 5, 6, 7, 8 번째 int 비트마스킹으로 표현할 정사각형
 * 인덱스 표현 : i / 3 * 3 + j / 3
 * 
 * 각 정사각형에서 왼쪽 상단으로부터 행 우선으로 0, 1, 2, 3, 4, 5, 6, 7, 8 번째 비트에 설정된다.
 * 비트 표현 : i % 3 * 3 + j % 3
 * 이건 필요없네
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int[][] board = new int[9][9];
	// 1~9번째 행, 열과 9개의 정사각형에 포함된 수를 비트마스킹으로 나타내는 변수들.
	private static int[] firstDim = new int[9], secondDim = new int[9], square = new int[9];
	// 비어있는 칸만 연산하기 위해 별도로 저장.
	private static List<int[]> coords = new ArrayList<>();

	private static boolean solve(int index) {
		int x, y, radix;
		boolean found = false;

		if (index == coords.size())
			return true;
		x = coords.get(index)[0];
		y = coords.get(index)[1];
		for (int value = 1; value <= 9 && !found; value++) {
			radix = 1 << value;
			if ((firstDim[x] & radix) == 0 && (secondDim[y] & radix) == 0 && (square[x / 3 * 3 + y / 3] & radix) == 0) {
				board[x][y] = value;
				firstDim[x] |= radix;
				secondDim[y] |= radix;
				square[x / 3 * 3 + y / 3] |= radix;
				if (!(found = solve(index + 1))) {
					board[x][y] = 0;
					firstDim[x] ^= radix;
					secondDim[y] ^= radix;
					square[x / 3 * 3 + y / 3] ^= radix;
				}
			}
		}
		return found;
	}

	public static void main(String[] args) throws IOException {
		int value;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				value = System.in.read() & 15;
				board[i][j] = value;
				firstDim[i] |= 1 << value;
				secondDim[j] |= 1 << value;
				square[i / 3 * 3 + j / 3] |= 1 << value;
				if (value == 0)
					coords.add(new int[] { i, j });
			}
			// '\n'
			System.in.read();
		}
		solve(0);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				sb.append(board[i][j]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
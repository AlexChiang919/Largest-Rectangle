import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MethodOne {

	private static final String PROBLEM = "grid";
	private static final String EXT = ".dat";

	private static int R = 0;
	private static int C = 0;

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int times = Integer.parseInt(scan.nextLine());
		int set = 1;
		while (times-- > 0) {
			String[] split = scan.nextLine().split(" ");
			R = Integer.parseInt(split[0]);
			C = Integer.parseInt(split[1]);
			char[][] grid = new char[R][C];
			for (int i = 0; i < R; i++)
				grid[i] = scan.nextLine().toCharArray();
			int[][] x = new int[R][C];
			int[][] o = new int[R][C];
			messAround(grid, x, 'x');
			messAround(grid, o, 'o');
			printLine("Grid " + set++ + ": " + maxRectangle(x) + " " + maxRectangle(o));
			printArray(x);
		}
		scan.close();
	}
	
	public static void messAround(char[][] cha, int[][] array, char ch) {
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				if (cha[r][c] == ch)
					array[r][c] = 1;
				else
					array[r][c] = 0;
			}
		}
	}

	public static int maxHist(int[] row) {
		Stack<Integer> result = new Stack<>();
		int top = 0;
		int max = 0;
		int area = 0;
		int i = 0;
		while (i < C) {
			if (result.empty() || row[result.peek()] <= row[i]) {
				result.push(i++);
			} else {
				top = row[result.pop()];
				area = top * i;

				if (!result.empty())
					area = top * (i - result.peek() - 1);
				max = Math.max(area, max);
			}
		}
		while (!result.empty()) {
			top = row[result.pop()];
			area = top * i;
			if (!result.empty())
				area = top * (i - result.peek() - 1);
			max = Math.max(area, max);
		}
		return max;
	}

	public static int maxRectangle(int[][] grid) {
		int result = maxHist(grid[0]);
		for (int i = 1; i < R; i++) {

			for (int j = 0; j < C; j++)

				// if A[i][j] is 1 then add A[i -1][j]
				if (grid[i][j] == 1)
					grid[i][j] += grid[i - 1][j];

			// Update result if area with current row (as last row)
			// of rectangle) is more
			result = Math.max(result, maxHist(grid[i]));
		}
		return result;
	}

	public static void printArray(char[][] obj) {
		for (char[] ob : obj) {
			for (char o : ob)
				print(o);
			printLine();
		}
	}
	
	public static void printArray(int[][] obj) {
		for (int[] ob : obj) {
			for (int o : ob)
				print(o);
			printLine();
		}
	}

	public static void print(Object... o) {
		for (Object obj : o) {
			System.out.print(obj);
		}
	}

	public static void printLine(Object... o) {
		if (o.length <= 0) {
			System.out.println();
			return;
		}
		for (Object obj : o) {
			System.out.println(obj);
		}
	}

	public static void printF(boolean newLine, String format, Object... o) {
		System.out.printf(format + ((newLine) ? "\n" : ""), o);
	}

}

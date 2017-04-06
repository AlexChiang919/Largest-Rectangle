import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MethodOne {

	private static final String PROBLEM = "grid";
	private static final String EXT = ".dat";

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int times = Integer.parseInt(scan.nextLine());
		while (times-- > 0) {
			String[] split = scan.nextLine().split(" ");
			int rsiz = Integer.parseInt(split[0]);
			int csiz = Integer.parseInt(split[1]);
			char[][] grid = new char[rsiz][csiz];
			for (int i = 0; i < rsiz; i++)
				grid[i] = scan.nextLine().toCharArray();
			int[] start = getUpmostPosition(grid, '.');
			int width = (start[0] != -1 || start[1] != -1) ? 1 : 0;
			int length = (start[0] != -1 || start[1] != -1) ? 1 : 0;
			int largest = 0;
			Queue<Integer> q = new LinkedList<>();
			q.add(start[0]);
			q.add(start[1]);
			boolean down = false;
			boolean right = false;
			while (!q.isEmpty()) {
				int r = q.poll();
				int c = q.poll();
				int rr = r + 1;
				int cc = c + 1;
				if (!inBounds(grid, r, cc))
					continue;
				if (grid[r][cc] == '.' && !down) {
					width++;
					q.add(r);
					q.add(cc);
				} else if (inBounds(grid, rr, c)) {
					down = true;
					if (grid[rr][c] == '.' && grid[rr][start[1] + width - 1] == '.') {
						length++;
						q.add(rr);
						q.add(c);
					} else {
						if (width * length > largest) {
							largest = width * length;
						}
					}
				}
			}
			start = getLeftmostPosition(grid, '.');
			q.add(start[0]);
			q.add(start[1]);
			width = length = 0;
			while (!q.isEmpty()) {
				int r = q.poll();
				int c = q.poll();
				int rr = r + 1;
				int cc = c + 1;
				if (!inBounds(grid, rr, c))
					continue;
				if (grid[rr][c] == '.' && !right) {
					length++;
					q.add(rr);
					q.add(c);
				} else if (inBounds(grid, r, cc)) {
					right = true;
					if (grid[r][cc] == '.' && grid[start[0] + length - 1][cc] == '.') {
						width++;
						q.add(r);
						q.add(cc);
					} else {
						if (width * length > largest) {
							largest = width * length;
						}
					}
				}
			}
			printLine(width + " " + length);
			if (largest == 0) {
				printLine("No rectangle.");
			} else {
				printLine("Largest rectangle: " + largest);
			}
		}
		scan.close();
	}
	
	public static int[] getUpmostPosition(char[][] array, char ch) {
		for (int r = 0; r < array.length; r++)
			for (int c = 0; c < array[r].length; c++)
				if (array[r][c] == ch)
					return new int[] {r, c};
		return new int[] {-1, -1};
	}
	
	public static int[] getLeftmostPosition(char[][] array, char ch) {
		for (int c = 0; c < array[0].length; c++)
			for (int r = 0; r < array.length; r++)
				if (array[r][c] == ch)
					return new int[] {r, c};
		return new int[] {-1, -1};
	}
	
	public static boolean inBounds(char[][] array, int r, int c) {
		return (r >= 0 && r < array.length) && (c >= 0 && c < array[r].length);
	}
	
	public static void printArray(char[][] obj) {
		for (char[] ob : obj) {
			for (char o : ob)
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

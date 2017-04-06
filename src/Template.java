import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Template {

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
		}
		scan.close();
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

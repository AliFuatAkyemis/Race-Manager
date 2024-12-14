import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class RaceTimer {
	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(System.in);
			Date date = new Date();
			String path = "C:\\Users\\alifu\\Desktop\\Race\\";
			BufferedWriter writer = null;
			String[][] laps = new String[60][6];
			int n = 0;
			while (true) {
				System.out.print("Number: ");
				String in = "";
				do in = input.nextLine();
				while (!hasDigit(in));
				n = isolInt(in);
				if (n <= 0) break;
				date = new Date();
				addToArray(laps[n-1], date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "\n");
			}
			for (int i = 0;i < laps.length;i++) {
				writer = new BufferedWriter(new FileWriter(path + Integer.toString(i+1) + ".txt"));
				for (int j = 0;j < laps[0].length;j++) {
					if (laps[i][j] != null) writer.write(Integer.toString(j+1) + " - " + laps[i][j]);
				}
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addToArray(String[] arr, String str) {
		for (int i = 0;i < arr.length;i++) {
			if (arr[i] == null) {
				arr[i] = str;
				return;
			}
		}
	}
	
	public static int isolInt(String str) {
		char[] arr = str.toCharArray();
		String res = "";
		for (int i = 0;i < arr.length;i++) if (Character.isDigit(arr[i])) res += arr[i];
		return Integer.valueOf(res);
	}
	
	public static boolean hasDigit(String str) {
		char[] arr = str.toCharArray();
		for (int i = 0;i < arr.length;i++) if (Character.isDigit(arr[i])) return true;
		return false;
	}
}

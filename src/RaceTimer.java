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
			String path = "logs/";
			BufferedWriter writer = null;
			String[][] laps = new String[60][6];
			String start = "01:15:00";
			int n = 0;
			while (true) {
				System.out.print("Number: ");
				String in = "";
				do in = input.nextLine();
				while (!hasDigit(in));
				n = isolInt(in);
				if (n <= 0) break;
				date = new Date();
				addToArray(laps[n-1], date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
			}
			for (int i = 0;i < laps.length;i++) {
				writer = new BufferedWriter(new FileWriter(path + (i+1) + ".txt"));
				for (int j = 0;j < laps[0].length;j++) {
					if (laps[i][j] != null) writer.write((j+1) + " - " + laps[i][j] + "\n");
				}
				writer.close();
			}
			writer = new BufferedWriter(new FileWriter(path + "Results.txt"));
			for (int i = 0;i < laps.length;i++) {
				double result = totalTimeOfLaps(laps[i]);
				if (result != -1) {
					writer.write((i+1) + " -> " + toDate(result) + " -> " + toDate(result - toTime(start)) + "\n");
				}
			}
			writer.close();
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
	
	public static double totalTimeOfLaps(String[] arr) {
		String str = "";
		for (int i = 0;i < arr.length;i++) {
			if (arr[i] == null) return -1;
			if (arr[i+1] == null || i == arr.length) {
				str = arr[i];
				break;
			}
		}
		return toTime(str);
	}
	
	public static double toTime(String str) {
		int j = 0;
		double sum = 0, factor = 3600;
		char[] charr = str.toCharArray();
		for (int i = 0;i < charr.length;i++) {
			if (factor == 1 || charr[i] == ':') {
				if (factor == 1) i = str.length();
				sum += (factor * Integer.valueOf(str.substring(j, i)));
				j = i+1;
				factor /= 60;
			}
		}
		return sum;
	}
	
	public static String toDate(double time) {
		int hour = 0, min = 0, sec = 0;
		sec = (int) time%60;
		time /= (int) 60;
		min = (int) time%60;
		time /= (int) 60;
		hour = (int) time%60;
		return hour + ":" + min + ":" + sec;
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

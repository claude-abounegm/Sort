import java.util.Scanner;

/*
 * Claude Abou Negm
 * CS 122[A]
 * Dr. Leap
 * #4 - Sorter Project
 * Main Program
 */

public class Program {

	public static void main(String[] args) {
		long startTime = 0;
		long endTime = 0;
		boolean continueLoop = true;

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Sorter Project by Claude Abou Negm");
			System.out.println("   1. Selection Sort");
			System.out.println("   2. Bubble Sort");
			System.out.println("   3. Ping Pong (Cocktail) Sort");
			System.out.println("   4. Recursive Merge Sort");
			System.out.println("   5. Threaded Merge Sort");
			int selection = GetInputInt(scanner,
					"Please input a number between 1 and 5: ", 1, 5);
			int arrayLength = GetInputInt(
					scanner,
					"Please input the number of items in the array (0 - 100,000): ",
					0, 100000);

			int[] randomArray = randomizeArray(arrayLength);
			int[] sortedArray = new int[0];

			switch (selection) {
			case 1:
				startTime = System.currentTimeMillis();
				sortedArray = Sorter.selectionSort(randomArray);
				endTime = System.currentTimeMillis();
				break;
			case 2:
				startTime = System.currentTimeMillis();
				sortedArray = Sorter.bubbleSort(randomArray);
				endTime = System.currentTimeMillis();
				break;
			case 3:
				startTime = System.currentTimeMillis();
				sortedArray = Sorter.pingPongSort(randomArray);
				endTime = System.currentTimeMillis();
				break;
			case 4:
				startTime = System.currentTimeMillis();
				sortedArray = Sorter.mergeSort(randomArray);
				endTime = System.currentTimeMillis();
				break;
			case 5:
				int threads = GetInputInt(scanner,
						"Please input the number of threads (1 - 4): ", 1, 4);
				startTime = System.currentTimeMillis();
				sortedArray = Sorter.mergeSort(randomArray, threads);
				endTime = System.currentTimeMillis();
				break;
			}

			printArray(sortedArray, 0, 10);
			if (sortedArray.length > 10)
				printArray(sortedArray, sortedArray.length - 10,
						sortedArray.length);
			System.out.println("Sorting took: " + (endTime - startTime)
					+ " ms.");

			continueLoop = (GetInputInt(scanner,
					"Do you want to try other sorts? [0: No | 1: Yes] ", 0, 1) == 1);
		} while (continueLoop);
		System.out.println("Thank you for using Sorter Project!");
	}

	/**
	 * Creates a new array and fills it with random values.
	 * 
	 * @param count
	 *            The amount of items in the array.
	 * @return A new randomized array.
	 */
	public static int[] randomizeArray(int count) {
		int[] list = new int[count];
		for (int i = 0; i < list.length; ++i)
			list[i] = (int) (Math.random() * list.length);
		return list;
	}

	public static void printArray(int[] list, int startIndex, int endIndex) {
		for (; startIndex < endIndex; ++startIndex)
			System.out.println(startIndex + ": " + list[startIndex]);
		System.out.println();
	}

	private static int GetInputInt(Scanner scanner, String message, int min,
			int max) {
		String str;
		Integer n = null;
		do {
			System.out.print(message);
			str = scanner.nextLine();
		} while ((n = TryParse(str)) == null || n < min || n > max);

		return n;
	}

	private static Integer TryParse(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception ex) {
			return null;
		}
	}
}

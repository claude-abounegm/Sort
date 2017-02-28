import java.util.Arrays;

public final class Sorter {

	// Make the constructor private since Java does not have static classes.
	private Sorter() {
	}

	public static final int MAX_THREADS = 4;

	/**
	 * Returns a new sorted array using a Selection Sort method.
	 * 
	 * @param array
	 *            The array that contains the values to be sorted.
	 */
	public static int[] selectionSort(int[] array) {
		if (array == null || array.length == 0)
			return new int[0];

		int temp;
		int minValueIndex = 0;

		// Create a copy of the original array to modify and return.
		int[] wipArray = Arrays.copyOf(array, array.length);
		for (int currentIndex = 0; currentIndex < (wipArray.length - 1); ++currentIndex) {
			minValueIndex = currentIndex;

			for (int i = currentIndex + 1; i < wipArray.length; ++i)
				if (wipArray[i] < wipArray[minValueIndex])
					minValueIndex = i;

			temp = wipArray[currentIndex];
			wipArray[currentIndex] = wipArray[minValueIndex];
			wipArray[minValueIndex] = temp;
		}
		return wipArray;
	}

	/**
	 * Returns a new sorted array using a Bubble Sort method.
	 * 
	 * @param array
	 *            The array that contains the values to be sorted.
	 */
	public static int[] bubbleSort(int[] array) {
		if (array == null || array.length == 0)
			return new int[0];

		int i, j, temp;
		boolean inOrder = false;
		int[] wipArray = Arrays.copyOf(array, array.length);

		for (i = 1; i < wipArray.length && !inOrder; ++i) {
			inOrder = true;
			for (j = 0; j < (wipArray.length - i); ++j) {
				if (wipArray[j] > wipArray[j + 1]) {
					temp = wipArray[j];
					wipArray[j] = wipArray[j + 1];
					wipArray[j + 1] = temp;
					inOrder = false;
				}
			}
		}
		return wipArray;
	}

	/**
	 * Returns a new sorted array using a PingPong (Cocktail) Sort method.
	 * 
	 * @param array
	 *            The array that contains the values to be sorted.
	 */
	public static int[] pingPongSort(int[] array) {
		if (array == null || array.length == 0)
			return new int[0];

		int temp;
		boolean inOrder = false;
		int[] wipArray = Arrays.copyOf(array, array.length);

		for (int i = 1; (i < wipArray.length && !inOrder); ++i) {
			inOrder = true;

			// Pass Downwards
			for (int j = 0; j < (wipArray.length - i); ++j) {
				if (wipArray[j] > wipArray[j + 1]) {
					temp = wipArray[j];
					wipArray[j] = wipArray[j + 1];
					wipArray[j + 1] = temp;
					inOrder = false;
				}
			}
			// Pass Upwards
			for (int j = (wipArray.length - i); j > 1; --j)
				if (wipArray[j] < wipArray[j - 1]) {
					temp = wipArray[j];
					wipArray[j] = wipArray[j - 1];
					wipArray[j - 1] = temp;
					inOrder = false;
				}
		}
		return wipArray;
	}

	/**
	 * Returns a new sorted array using a Merge Sort method.
	 * 
	 * @param array
	 *            The array that contains the values to be sorted.
	 */
	public static int[] mergeSort(int[] array) {
		return _mergeSort(array, 0);
	}

	/**
	 * Returns a new sorted array using a Merge Sort method (Threaded if
	 * threadsCount > 0).
	 * 
	 * @param array
	 *            The array that contains the values to be sorted.
	 * @param threadsCount
	 *            The number of threads needed to be used.
	 * @return
	 */
	public static int[] mergeSort(int[] array, int threadsCount) {
		return _mergeSort(array, (threadsCount % 2 == 0) ? threadsCount / 2
				: (threadsCount + 1) / 2);
	}

	private static int[] _mergeSort(int[] array, int threadsCount) {
		if (array == null || array.length == 0)
			return new int[0];

		if (threadsCount > MAX_THREADS)
			threadsCount = MAX_THREADS;

		int[] array1 = new int[array.length / 2];
		int[] array2 = new int[array.length - array1.length];

		System.arraycopy(array, 0, array1, 0, array1.length);
		System.arraycopy(array, array1.length, array2, 0, array2.length);

		if (threadsCount <= 0) {
			if (array1.length > 1)
				array1 = mergeSort(array1, 0);
			if (array2.length > 1)
				array2 = mergeSort(array2, 0);
		} else {
			ThreadMerge threadA = new ThreadMerge(array1, threadsCount);
			ThreadMerge threadB = new ThreadMerge(array2, threadsCount);
			threadA.start();
			threadB.start();
			try {
				threadA.join();
			} catch (InterruptedException e1) {
			}
			try {
				threadB.join();
			} catch (InterruptedException e) {
			}
			array1 = threadA.getArray();
			array2 = threadB.getArray();
		}

		return merge(array1, array2);
	}

	/**
	 * Merges the 2 arrays together in ascending order.
	 * 
	 * @param array1
	 *            The first array to be ordered and merged.
	 * @param array2
	 *            The second array to be ordered and merged.
	 * @return A new array that contains the 2 arrays ordered in ascending
	 *         order.
	 */
	private static int[] merge(int[] array1, int[] array2) {
		int[] sortedArray = new int[array1.length + array2.length];
		int i = 0, j = 0, k = 0;

		// compare the 2 arrays until one of them is exhausted
		while (i < array1.length && j < array2.length) {
			if (array1[i] <= array2[j])
				sortedArray[k++] = array1[i++];
			else
				sortedArray[k++] = array2[j++];
		}

		// copies over any remaining numbers in array a
		while (i < array1.length)
			sortedArray[k++] = array1[i++];
		// copies over any remaining numbers in array b
		while (j < array2.length)
			sortedArray[k++] = array2[j++];
		return sortedArray;
	}
}

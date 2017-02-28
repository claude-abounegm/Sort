public final class Search {
	private Search() {
	}

	/**
	 * Searches for the specified value in the array using a linear search.
	 * 
	 * @param array
	 *            The array that contains the value desired to be found.
	 * @param value
	 *            The value to be found in the array.
	 * @return Return the index that contains the value if found, otherwise
	 *         returns -1.
	 */
	public static int linearSearch(int[] array, int value) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == value)
				return i;
		return -1;
	}

	/**
	 * Searches for the specified value in the array using a binary search.
	 * 
	 * @param array
	 *            The array that contains the value desired to be found.
	 * @param value
	 *            The value to be found in the array.
	 * @return Return the index that contains the value if found, otherwise
	 *         returns -1.
	 */
	public static int binarySearch(int[] array, int value) {
		int hi = array.length - 1;
		int lo = 0;
		int mid;

		while (lo <= hi) {
			mid = (hi + lo) / 2;
			if (array[mid] == value)
				return mid;
			if (array[mid] < value)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return -1;
	}
}

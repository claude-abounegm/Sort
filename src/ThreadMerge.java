public class ThreadMerge extends Thread {
	private int[] _array;
	private int _count;

	public ThreadMerge(int[] array, int count) {
		_array = array;
		_count = count;
	}

	public void run() {
		_array = Sorter.mergeSort(_array, _count - 1);
	}

	public int[] getArray() {
		return _array;
	}
}
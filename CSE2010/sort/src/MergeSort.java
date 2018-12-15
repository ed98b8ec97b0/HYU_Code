
public class MergeSort {
	private int[] array;
	private int[] helper;

	private int size;

	public void sort(int[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
			return;
	    }
	    array = inputArr;
	    size = inputArr.length;
	    helper = new int[size];
	    mergesort(0, size - 1);
	}

	private void mergesort(int low, int high) {
	  	if (low < high) {
	  		int middle = ((low + high) / 2);
			mergesort(low, middle);
			mergesort(middle + 1, high);
			merge(low, middle, high);
	  	}
	}

	private void merge(int low, int middle, int high) {
		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}

		int arrayHead = low, leftHead = low, rightHead = middle + 1;

		while ((leftHead < middle) && (rightHead < high)) {
			if (helper[leftHead] < helper[rightHead]) {array[arrayHead++] = helper[leftHead++];}
			else {array[arrayHead++] = helper[rightHead++];}
		}
		if (leftHead == middle) {
			while ((rightHead <= high) && (helper[leftHead] > helper[rightHead])) {array[arrayHead++] = helper[rightHead++];}
			array[arrayHead++] = helper[leftHead];
			while (rightHead <= high) {array[arrayHead++] = helper[rightHead++];}
		}
		else {
			while ((leftHead <= middle) && (helper[leftHead] < helper[rightHead])) {array[arrayHead++] = helper[leftHead++];}
			array[arrayHead++] = helper[rightHead];
			while (leftHead <= middle) {array[arrayHead++] = helper[leftHead++];}	
		}
	}
}

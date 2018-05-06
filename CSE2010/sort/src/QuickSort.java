
public class QuickSort {
	private int[] array;
    private int length;
 
    public void sort(int[] inputArr) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }
 
    private void quickSort(int low, int high) {
        if (low >= high) {return;}

        int pivot = high, leftHead = low, rightHead = high - 1;

        while (leftHead <= rightHead) {
            while ((leftHead <= rightHead) && (array[leftHead] < array[pivot])) {leftHead++;}
            while ((leftHead <= rightHead) && (array[rightHead] > array[pivot])) {rightHead--;}
            if (leftHead <= rightHead) {
                swap(leftHead, rightHead);
                leftHead++;
                rightHead--;
            }
            
        }
        swap(leftHead, pivot);
        quickSort(low, leftHead - 1);
        quickSort(leftHead + 1, high);
        // System.out.printf("low = %d, high = %d\nleftHead = %d, pivot = %d, rightHead = %d\n", low, high, leftHead, pivot, rightHead);
        // printArray();
    }
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }    

    private void printArray() {
        for (int i = 0; i < length; i++) {System.out.printf("%d ", array[i]);}
        System.out.println();
    }
}

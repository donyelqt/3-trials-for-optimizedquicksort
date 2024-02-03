import java.util.Arrays;

public class OptimizedQuickSort2 {

    private static final int INSERTION_SORT_THRESHOLD = 10;  // Experiment with different threshold values

    // Add a counter for the number of comparisons
    private static long comparisonsCount;

    public static void main(String[] args) {
        
        int[] array1000To1 = generateDescendingArray(1000);
        
        

        System.out.println("\nTrial 2: Input 1000 down to 1");
        runAndMeasureSorting(array1000To1);

        
    }

    public static void quickSort(int[] arr) {
        comparisonsCount = 0;  // Reset the comparisons count
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            if (high - low <= INSERTION_SORT_THRESHOLD) {
                insertionSort(arr, low, high);
            } else {
                int partitionIndex = partition(arr, low, high);

                quickSortHelper(arr, low, partitionIndex - 1);
                quickSortHelper(arr, partitionIndex + 1, high);
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotIndex = choosePivot(low, high);
        int pivot = arr[pivotIndex];

        // Move pivot to the end
        swap(arr, pivotIndex, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisonsCount++;  // Counting the number of comparisons
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // Move pivot to its correct position
        swap(arr, i + 1, high);

        return i + 1;
    }

    private static int choosePivot(int low, int high) {
        // Use the middle element as the pivot
        return low + (high - low) / 2;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void runAndMeasureSorting(int[] arr) {
        comparisonsCount = 0;  // Reset the comparisons count
        long startTime = System.nanoTime();  // Measure in nanoseconds
        quickSort(arr);
        long endTime = System.nanoTime();

        System.out.println("Sorted Array: " + Arrays.toString(arr));
        System.out.println("Number of operations in QuickSort: " + comparisonsCount);
        System.out.println("Elapsed time: " + (endTime - startTime) + " nanoseconds");
    }

    public static int[] generateAscendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static int[] generateDescendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size) + 1;
        }
        return array;
    }
}

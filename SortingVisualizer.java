import java.util.Arrays;
import java.util.Random;

public class SortingVisualizer {

    // Utility function to swap elements in an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        System.out.println("Bubble Sort:");
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        System.out.println("Selection Sort:");
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
            System.out.println(Arrays.toString(arr));
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        System.out.println("Insertion Sort:");
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            System.out.println(Arrays.toString(arr));
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge sorted halves
            merge(arr, left, mid, right);
            System.out.println(Arrays.toString(arr));
        }
    }

    // Merge function for Merge Sort
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
            System.out.println(Arrays.toString(arr));
        }
    }

    // Partition function for Quick Sort
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Main function to demonstrate sorting algorithms
    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = rand.ints(10, 0, 100).toArray(); // Random array of 10 elements between 0 and 100
        int[] arrCopy;

        System.out.println("Original Array: " + Arrays.toString(arr));

        // Bubble Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        bubbleSort(arrCopy);

        // Selection Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        selectionSort(arrCopy);

        // Insertion Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        insertionSort(arrCopy);

        // Merge Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        System.out.println("Merge Sort:");
        mergeSort(arrCopy, 0, arrCopy.length - 1);

        // Quick Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        System.out.println("Quick Sort:");
        quickSort(arrCopy, 0, arrCopy.length - 1);
    }
}

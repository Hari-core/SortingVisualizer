import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SortingVisualizer extends JFrame {
    private static final long serialVersionUID = 1L;
    private int[] array;
    private JPanel panel;
    private JComboBox<String> algorithmSelector;
    private JButton startButton, resetButton;

    public SortingVisualizer() {
        // Set up the JFrame
        setTitle("Sorting Algorithm Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Generate a random array
        array = generateRandomArray(50);

        // Set up the panel to display sorting visuals
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawArray(g);
            }
        };
        panel.setPreferredSize(new Dimension(800, 500));
        panel.setBackground(Color.white);

        // Sorting algorithms dropdown
        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"};
        algorithmSelector = new JComboBox<>(algorithms);

        // Button to start sorting
        startButton = new JButton("Start Sorting");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    String selectedAlgorithm = (String) algorithmSelector.getSelectedItem();
                    if (selectedAlgorithm != null) {
                        sortArray(selectedAlgorithm);
                    }
                }).start();
            }
        });

        // Reset button to generate a new random array
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                array = generateRandomArray(50); // Generate a new random array
                panel.repaint(); // Refresh the panel to show the new array
            }
        });

        // Control panel for selecting sorting algorithm, starting and resetting the process
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Algorithm:"));
        controlPanel.add(algorithmSelector);
        controlPanel.add(startButton);
        controlPanel.add(resetButton);

        // Add control panel and sorting panel to the JFrame
        add(controlPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    // Generate a random array
    private int[] generateRandomArray(int size) {
        Random rand = new Random();
        return rand.ints(size, 0, 400).toArray();
    }

    // Draw the array as vertical bars
    private void drawArray(Graphics g) {
        int barWidth = panel.getWidth() / array.length;
        for (int i = 0; i < array.length; i++) {
            int height = array[i];
            g.setColor(Color.blue);
            g.fillRect(i * barWidth, panel.getHeight() - height, barWidth - 2, height);
        }
    }

    // Refresh the panel after each step of sorting
    private void updateDisplay() {
        panel.repaint();
        try {
            Thread.sleep(50); // Pause to visualize sorting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Select and apply the chosen sorting algorithm
    private void sortArray(String algorithm) {
        switch (algorithm) {
            case "Bubble Sort":
                bubbleSort(array);
                break;
            case "Selection Sort":
                selectionSort(array);
                break;
            case "Insertion Sort":
                insertionSort(array);
                break;
            case "Merge Sort":
                mergeSort(array, 0, array.length - 1);
                break;
            case "Quick Sort":
                quickSort(array, 0, array.length - 1);
                break;
            default:
                System.out.println("Unknown sorting algorithm selected.");
        }
    }

    // Bubble Sort
    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
                updateDisplay();
            }
        }
    }

    // Selection Sort
    private void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
            updateDisplay();
        }
    }

    // Insertion Sort
    private void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                updateDisplay();
            }
            arr[j + 1] = key;
            updateDisplay();
        }
    }

    // Merge Sort
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
            updateDisplay();
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
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
            updateDisplay();
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            updateDisplay();
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            updateDisplay();
        }
    }

    // Quick Sort
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
            updateDisplay();
        }
    }

    private int partition(int[] arr, int low, int high) {
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

    // Utility function to swap elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingVisualizer visualizer = new SortingVisualizer();
            visualizer.setVisible(true);
        });
    }
}

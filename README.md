# SortingVisualizer

A Java-based GUI application to visualize the execution of different sorting algorithms. This project is designed to help users understand the workings of popular sorting algorithms through real-time visualizations. The application uses Java Swing for its graphical user interface (GUI) and allows users to select from various sorting algorithms and reset the array for multiple trials.

## Features

- **Sorting Algorithms Visualized**: 
  - Bubble Sort
  - Selection Sort
  - Insertion Sort
  - Merge Sort
  - Quick Sort
- **Real-time Visualization**: See the sorting algorithm in action as it sorts a random array.
- **Reset Functionality**: Click a button to reset and generate a new random array for re-sorting.
- **Sorting Algorithm Selection**: Dropdown menu to select the sorting algorithm.
- **User-friendly GUI**: Simple and intuitive interface using Java Swing.
- Background music that plays during sorting.
- Colorful and interactive UI with a yellow background and red bars representing the array elements.
- Random array generation for diverse sorting experiences.
- Simple controls to start sorting and reset the array.
  
## Expected Features

- Smooth visualization of sorting algorithms.
- Ability to switch between sorting algorithms easily.
- Error handling for any unexpected behavior during sorting.

## Problems Faced

1. **Thread Blocking and UI Freezing**:
   - While implementing the sorting visualizations, the UI would freeze when sorting large arrays due to thread blocking. The main GUI thread was performing both the sorting operation and the rendering, causing delays and unresponsiveness.

2. **Repainting the UI**:
   - The visual rendering of the sorted array was not updated smoothly because the Java Swing `repaint()` method was not being called frequently enough during sorting operations.

## Tech Stack

- **Java**: Used for implementing the sorting logic and main application structure. Java's object-oriented nature allowed for efficient code reuse and modularity.
- **Swing**: A part of Java's standard library, Swing provides a flexible and easy-to-use framework for building GUI applications. It was chosen because of its native support for event-driven programming and simple integration with Java logic.
- **Multithreading**: Sorting algorithms were run on separate threads to prevent the UI from freezing and to ensure smooth real-time visualizations.

### Why These Tools Were Chosen

- **Java** was chosen for its robustness and ease of managing object-oriented design, which makes it ideal for projects that involve multiple components like sorting algorithms and GUI management.
- **Swing** was selected because it's part of Java's standard library, making it convenient to use without requiring external dependencies. Swing also provides sufficient tools for building simple, event-driven interfaces suitable for this project.
- **Multithreading** was used to handle sorting operations separately from the UI rendering process, ensuring that the application remained responsive during long sorting operations.

## Troubleshooting

1. **UI Freezing**:
   - To resolve the issue of UI freezing, the sorting operations were moved to separate threads. This allowed the GUI thread to handle rendering independently, resulting in a smoother experience.
   - Solution: Used `SwingWorker` to ensure sorting operations didn’t block the main thread.

2. **Visual Delays**:
   - The lack of frequent UI updates during sorting was addressed by forcing the GUI to repaint during key moments in the sorting process.
   - Solution: Called `repaint()` within each significant step of the sorting algorithm to ensure that the array visual was updated during the sort.

## Future Features

- **Additional Sorting Algorithms**: Extend the program to support other algorithms such as Heap Sort, Radix Sort, and Shell Sort.
- **Speed Control**: Add a slider to allow users to adjust the speed of the sorting visualization.
- **Detailed Algorithm Explanations**: Display a description of the algorithm’s logic and time complexity alongside the visualization.
- **Algorithm Comparison**: Allow users to compare multiple algorithms side by side, showcasing their performance on the same array.
- **Customization Options**: Allow users to adjust the size of the array and choose the initial order (random, sorted, reverse-sorted).


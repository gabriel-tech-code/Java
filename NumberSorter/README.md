# SortingApp

SortingApp.java is a Java-based desktop application that allows users to input a list of numbers (as double values) either manually or by loading a text file. The application then sorts the numbers using multiple algorithms and compares their performance.

This project demonstrates differences in algorithm efficiency, execution time, and implementation style.

## Features

- **Input Methods**:
    - Accepts user input as space-separated numbers
    - Supports loading input from a `.txt` file
- **Sort number in**:
    - Ascending (smallest → largest)
    - Descending (largest → smallest)
- **Displays**:
    - Sorted results
    - Algorithm used
    - Execution time for each algorithm
- **Sorting Algorithms**:
    - Lambda (λ) - Built-in Java streams
    - Do-While Loop - O(n²)
    - Merge Sort - O(n log n)
    - Selection Sort - O(n²)
    - Bozo Sort - O((n+1)!)
- **Graphical User Interface (GUI) built with Java Swing**

## Sorting Algorithms Implemented

1. Lambda Sort (λ)
    - Uses Java’s built-in sorting (Collections.sort)
    - Based on TimSort
    - Time Complexity: O(n log n)
    - Fast and optimized for real-world data
---
2. Do-While Loop Sort (Bubble Sort)
    - Repeatedly swaps adjacent elements until sorted
    - Time Complexity: O(n²)
    - Simple but inefficient for large datasets
---
3. Merge Sort
    - Divide-and-conquer algorithm
    - Splits list into halves and merges them in order
    - Time Complexity: O(n log n)
    - Efficient and stable
---
4. Selection Sort
    - Repeatedly selects the minimum (or maximum) element
    - Time Complexity: O(n²)
    - Easy to understand but slow
---
5. Bozo Sort
    - Randomly swaps elements until the list is sorted
    - Time Complexity: O((n+1)!)
    - Extremely inefficient and used for demonstration purposes only
6. Quick Sort
    - Divide-and-conquer algorithm
    - Selects a pivot and partitions the list into smaller and larger elements
    - Time Complexity: O(n log n) average, O(n²) worst case
    - Very fast in practice and widely used
---
7. Heap Sort
    - Uses a binary heap data structure
    - Builds a heap and repeatedly extracts the maximum (or minimum) element
    - Time Complexity: O(n log n)
    - Consistent performance but slightly slower than Quick Sort in practice


## Algorithm Complexity

| Algorithm | Time Complexity |
|-----------|-----------------|
| Lambda | O(n log n) |
| Do-While | O(n²) |
| Merge Sort | O(n log n) |
| Selection Sort | O(n²) |
| Quick Sort | O(n log n) |
| Heap Sort | O(n log n) |
| Bozo Sort | O((n+1)!) |

## Performance Measurement

- Each algorithm is timed using:`System.nanoTime()`
- Execution time is displayed in a formatted output, allowing users to compare algorithm efficiency.

## Input Format
- Numbers must be separated by spaces
    - Example:  90 11 2 1 0
- File input should follow the same format

## Example Output
```
        largest to smallest
90 11 2 1 0

            Algorithms

do-while loop           time spent 00:00:00:004
lambda sort             time spent 00:00:00:001
merge sort              time spent 00:00:00:002
selection sort          time spent 00:00:00:003
quick sort              time spent 00:00:00:002
heap sort               time spent 00:00:00:003
bozo sort               time spent 00:00:10:532
```
## Notes
- Bozo sort is intentionally inefficient and may take a long time for large inputs
- It is recommended to use small datasets (≤ 10 values) when running Bozo sort
- Invalid input will be detected and reported

## Purpose

This project is designed to:
- Demonstrate algorithm performance differences
- Provide hands-on experience with sorting techniques
- Practice Java GUI development and file handling
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class SortingApp extends JFrame {

    @SuppressWarnings("FieldMayBeFinal")
    private JTextField inputField;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextArea outputArea;
    @SuppressWarnings("unused")
    private JLabel timeLabel;

    public SortingApp() {
        setTitle("Java Sorting App");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        add(mainPanel, BorderLayout.CENTER);

        // Input Area
        JPanel inputPanel = new JPanel(new BorderLayout());

        JLabel inputLabel = new JLabel("\tInput list of numbers: ");
        inputField = new JTextField();

        inputField.setToolTipText("Enter numbers separated by spaces");
        inputPanel.add(inputLabel, BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.NORTH);

        mainPanel.add(new JSeparator());  // Separator between input and output  
        // Buttons Area
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton ascButton = new JButton("Sort Ascending");
        JButton descButton = new JButton("Sort Descending");
        JButton fileButton = new JButton("Load File");

        buttonPanel.add(ascButton);
        buttonPanel.add(descButton);
        buttonPanel.add(fileButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(new JSeparator());  // Separator between input and output  
        
        // Output Area
        JPanel outputPanel = new JPanel(new BorderLayout());

        JLabel outputLabel = new JLabel("Your sorted list:");
        outputArea = new JTextArea(3, 40);
        outputArea.setEditable(false);

        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        mainPanel.add(outputPanel);
        
        mainPanel.add(new JSeparator());
        
        // Button Actions
        ascButton.addActionListener(e -> sortNumbers(true));
        descButton.addActionListener(e -> sortNumbers(false));
        fileButton.addActionListener(e -> loadFile());

        setVisible(true);
    }

    private List<Double> parseInput(String text) {
        List<Double> numbers = new ArrayList<>();
        String[] parts = text.trim().split("\\s+");

        for (String p : parts) {
            try {
                numbers.add(Double.valueOf(p));
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid input detected: " + p);
                return new ArrayList<>();
            }
        }
        return numbers;
    }

    private void sortNumbers(boolean ascending) {
        List<Double> numbers = parseInput(inputField.getText());

        if (numbers.isEmpty()) {
            outputArea.setText("No valid numbers.");
            return;
        }

        // Copy lists for different methods
        List<Double> list1 = new ArrayList<>(numbers);
        List<Double> list2 = new ArrayList<>(numbers);
        List<Double> list3 = new ArrayList<>(numbers);
        List<Double> list4 = new ArrayList<>(numbers);
        List<Double> list5 = new ArrayList<>(numbers);
        List<Double> list6 = new ArrayList<>(numbers);

        // ---- DO-WHILE SORT ----
        long start1 = System.nanoTime();

        doWhileSort(list1, ascending);

        long end1 = System.nanoTime();

        // ---- LAMBDA SORT ----
        long start2 = System.nanoTime();

        if (ascending) {
            list2.sort((a, b) -> Double.compare(a, b));
        } else {
            list2.sort((a, b) -> Double.compare(b, a));
        }

        long end2 = System.nanoTime();

        // ---- MERGE SORT ----
        long start3 = System.nanoTime();
        mergeSort(list3, ascending);
        long end3 = System.nanoTime();

        // ---- SELECTION SORT ----
        long start4 = System.nanoTime();
        selectionSort(list4, ascending);
        long end4 = System.nanoTime();

        // ---- BOZO SORT (for fun) ----
        
        long start5 = System.nanoTime();
        boolean bozoSuccess = bozoSort(list5, ascending);
        long end5 = System.nanoTime();

        // ---- QUICK SORT (not displayed in results, but available for testing) ----
        long start6 = System.nanoTime();
        quickSort(list6, ascending);
        long end6 = System.nanoTime();

        // ---- HEAP SORT (not displayed in results, but available for testing) ----
        long start7 = System.nanoTime();
        heapSort(list6, ascending);
        long end7 = System.nanoTime();



        // ---- Display Results ----
        String type = ascending ? "\t\tSmallest to Largest" : "\t\tLargest to Smallest";

        outputArea.setText(type.toLowerCase()  + "\n\n" +  formatList(list1) +
            "\n\n\t\t Algorithms" + 
            "\nlambda (λ)\t\t\tTime spent: " + formatTime(end2 - start2) +
            "\ndo-while loop (O(n^2))\t\tTime spent: " + formatTime(end1 - start1) +
            "\nmerge sort (O(n log n))\t\tTime spent: " + formatTime(end3 - start3) +
            "\nselection sort (O(n^2))\t\tTime spent: " + formatTime(end4 - start4) +
            "\nquick sort (O(n log n))\t\tTime spent: " + formatTime(end6 - start6) +
            "\nheap sort (O(n log n))\t\tTime spent: " + formatTime(end7 - start7) +
            (bozoSuccess ? "\nbozo sort (O((n+1)!))\t\tTime spent: " + formatTime(end5 - start5) : 
            "\nbozo sort (O((n+1)!))\t\tTime spent: Failed after 1 million attempts")
            );
    }

    private String formatList(List<Double> list) {
        StringBuilder sb = new StringBuilder();
        for (double num : list) {
            if (num == (int) num) {
                sb.append((int) num).append(" ");
            } else {
                sb.append(num).append(" ");
            }
        }
        return sb.toString().trim();
    }
    // Simple Bubble Sort using do-while
    private void doWhileSort(List<Double> list, boolean ascending) {
        boolean swapped;
        int n = list.size();

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if ((ascending && list.get(i) > list.get(i + 1)) ||
                    (!ascending && list.get(i) < list.get(i + 1))) {

                    double temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    private String formatTime(long nanos) {
        long hours = nanos / 3_600_000_000_000L;
        long minutes = (nanos / 60_000_000_000L) % 60;
        long seconds = (nanos / 1_000_000_000L) % 60;
        long milliseconds = (nanos / 1_000_000L) % 1000;
        long nanoseconds = nanos % 1_000_000; // remaining nano part

        return String.format("%02d:%02d:%02d:%03d:%06d",
        hours, minutes, seconds, milliseconds, nanoseconds);
    }

    private void loadFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line).append(" ");
                }

                inputField.setText(sb.toString().trim());

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file.");
            }
        }
    }
    // Merge Sort Implementation
    private void mergeSort(List<Double> list, boolean ascending) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<Double> left = new ArrayList<>(list.subList(0, mid));
        List<Double> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left, ascending);
        mergeSort(right, ascending);

        merge(list, left, right, ascending);
    }

    private void merge(List<Double> list, List<Double> left, List<Double> right, boolean ascending) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if ((ascending && left.get(i) <= right.get(j)) ||
                (!ascending && left.get(i) >= right.get(j))) {

                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }
    // Selection Sort Implementation
    private void selectionSort(List<Double> list, boolean ascending) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            int index = i;

            for (int j = i + 1; j < n; j++) {
                if ((ascending && list.get(j) < list.get(index)) ||
                    (!ascending && list.get(j) > list.get(index))) {
                    index = j;
                }
            }

            double temp = list.get(i);
            list.set(i, list.get(index));
            list.set(index, temp);
        }
    }
    // Bozo Sort Implementation (for fun, not efficient)
    private boolean  bozoSort(List<Double> list, boolean ascending) {
        Random rand = new Random();
        int attempts = 0;
        int maxAttempts = 1000000; // safety cap

        while (!isSorted(list, ascending) && attempts < maxAttempts) {
            int i = rand.nextInt(list.size());
            int j = rand.nextInt(list.size());

            double temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);

            attempts++;
        }
        return attempts < maxAttempts;
    }
    private boolean isSorted(List<Double> list, boolean ascending) {
        for (int i = 0; i < list.size() - 1; i++) {
            if ((ascending && list.get(i) > list.get(i + 1)) ||
                (!ascending && list.get(i) < list.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
    // Quick Sort Implementation (not used in main, but available for testing)
    public static void quickSort(List<Double> list, boolean ascending) {
        quickSortHelper(list, 0, list.size() - 1, ascending);
    }

    private static void quickSortHelper(List<Double> list, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(list, low, high, ascending);

            quickSortHelper(list, low, pi - 1, ascending);
            quickSortHelper(list, pi + 1, high, ascending);
        }
    }

    private static int partition(List<Double> list, int low, int high, boolean ascending) {
        double pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && list.get(j) <= pivot) ||
                (!ascending && list.get(j) >= pivot)) {

                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }
    // Heap sort and other algorithms can be added similarly if needed
    public static void heapSort(List<Double> list, boolean ascending) {
        int n = list.size();

        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i, ascending);
        }

        // Extract elements
        for (int i = n - 1; i > 0; i--) {
            swap(list, 0, i);
            heapify(list, i, 0, ascending);
        }
    }

    private static void heapify(List<Double> list, int n, int i, boolean ascending) {
        int extreme = i; // largest or smallest
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (ascending) {
            // Max heap
            if (left < n && list.get(left) > list.get(extreme)) {
                extreme = left;
            }
            if (right < n && list.get(right) > list.get(extreme)) {
                extreme = right;
            }
        } else {
            // Min heap
            if (left < n && list.get(left) < list.get(extreme)) {
                extreme = left;
            }
            if (right < n && list.get(right) < list.get(extreme)) {
                extreme = right;
            }
        }

        if (extreme != i) {
            swap(list, i, extreme);
            heapify(list, n, extreme, ascending);
        }
    }
    private static void swap(List<Double> list, int i, int j) {
        Double temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortingApp::new);
    }

}
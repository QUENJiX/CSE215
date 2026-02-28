/**
 * ArraysDeepDiveDemo.java — Arrays: 1D, 2D, Utility Methods, Patterns & Algorithms
 * ===================================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: An array is a fixed-size container of elements stored in
 *    contiguous memory. Once created, its SIZE cannot change.
 *    Need dynamic sizing? Use ArrayList (see p05_Collections/ArrayListDemo.java).
 *
 * Topics covered:
 *   1. Array declaration, initialization, and access
 *   2. Arrays utility class (sort, search, fill, copy, equals)
 *   3. Multi-dimensional arrays (2D — matrices)
 *   4. Array copying (shallow vs deep implications)
 *   5. Common array patterns & algorithms
 *
 * 🔗 SEE ALSO: p05_Collections/ArrayListDemo.java, p01_Basics/MethodsAndRecursionDemo.java
 */
package p01_Basics;

import java.util.Arrays;

public class ArraysDeepDiveDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   ARRAYS DEEP DIVE DEMO                     ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =====================================================================
        // SECTION 1: DECLARATION & INITIALIZATION
        // =====================================================================
        System.out.println("=== 1. DECLARATION & INITIALIZATION ===");

        // Style 1: Declare then allocate
        int[] numbers = new int[5]; // All elements initialized to 0
        numbers[0] = 10;
        numbers[1] = 20;

        // Style 2: Inline initialization (size inferred)
        int[] primes = { 2, 3, 5, 7, 11, 13 };

        // Style 3: Anonymous array (useful for passing to methods)
        printArray("Anonymous", new int[] { 100, 200, 300 });

        /**
         * 📌 RULE — Default values for arrays:
         * int/long/short/byte → 0
         * float/double → 0.0
         * boolean → false
         * char → '\u0000' (null char)
         * Object references → null
         */
        System.out.println("Default int array: " + Arrays.toString(new int[3]));
        System.out.println("Default boolean:   " + Arrays.toString(new boolean[3]));
        System.out.println("Default String:    " + Arrays.toString(new String[3]));

        // =====================================================================
        // SECTION 2: ARRAYS UTILITY CLASS
        // =====================================================================
        System.out.println("\n=== 2. java.util.Arrays UTILITY CLASS ===");

        int[] arr = { 64, 25, 12, 22, 11, 90, 42 };
        System.out.println("Original: " + Arrays.toString(arr));

        // sort — modifies the array in place
        Arrays.sort(arr);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        // Sort a range only
        int[] partial = { 5, 3, 8, 1, 9, 2, 7 };
        Arrays.sort(partial, 1, 5); // Sort indices [1, 5) only
        System.out.println("Partial sort [1,5): " + Arrays.toString(partial));

        // binarySearch — array MUST be sorted first!
        int idx = Arrays.binarySearch(arr, 42);
        System.out.println("binarySearch(42): index " + idx);

        // fill — set all elements to a value
        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("Filled with 7: " + Arrays.toString(filled));

        // equals — compare two arrays element-by-element
        int[] a1 = { 1, 2, 3 };
        int[] a2 = { 1, 2, 3 };
        int[] a3 = { 1, 2, 4 };
        System.out.println("a1.equals(a2) [WRONG]: " + a1.equals(a2)); // false! (reference comparison)
        System.out.println("Arrays.equals(a1, a2): " + Arrays.equals(a1, a2)); // true ✅
        System.out.println("Arrays.equals(a1, a3): " + Arrays.equals(a1, a3)); // false

        // copyOf — create a new array (can resize)
        int[] original = { 1, 2, 3, 4, 5 };
        int[] longer = Arrays.copyOf(original, 8); // Pad with zeros
        int[] shorter = Arrays.copyOf(original, 3); // Truncate
        System.out.println("copyOf(8):  " + Arrays.toString(longer));
        System.out.println("copyOf(3):  " + Arrays.toString(shorter));

        // copyOfRange
        int[] slice = Arrays.copyOfRange(original, 1, 4); // [1, 4) → indices 1, 2, 3
        System.out.println("copyOfRange(1,4): " + Arrays.toString(slice));

        // =====================================================================
        // SECTION 3: 2D ARRAYS (Matrices)
        // =====================================================================
        System.out.println("\n=== 3. 2D ARRAYS (MATRICES) ===");

        /**
         * 💡 INTUITION — A 2D array is an "array of arrays".
         * int[][] grid = new int[3][4]; → 3 rows, 4 columns
         *
         * Visualize:
         * grid[0] → [_, _, _, _]
         * grid[1] → [_, _, _, _]
         * grid[2] → [_, _, _, _]
         *
         * grid[row][col] to access an element.
         */

        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        System.out.println("--- Print Matrix ---");
        printMatrix(matrix);

        System.out.println("Element at [1][2]: " + matrix[1][2]); // 6
        System.out.println("Rows: " + matrix.length);
        System.out.println("Cols in row 0: " + matrix[0].length);

        // Jagged arrays — rows can have DIFFERENT lengths!
        System.out.println("\n--- Jagged Array (different row lengths) ---");
        int[][] jagged = {
                { 1, 2 },
                { 3, 4, 5, 6 },
                { 7 }
        };
        for (int i = 0; i < jagged.length; i++) {
            System.out.println("Row " + i + " (length " + jagged[i].length + "): "
                    + Arrays.toString(jagged[i]));
        }

        // Matrix operations
        System.out.println("\n--- Matrix Transpose ---");
        int[][] transposed = transpose(matrix);
        printMatrix(transposed);

        // =====================================================================
        // SECTION 4: COPYING ARRAYS
        // =====================================================================
        System.out.println("\n=== 4. ARRAY COPYING ===");

        /**
         * ⚠️ GOTCHA — Assignment does NOT copy an array!
         * int[] b = a; ← Both 'a' and 'b' point to the SAME array in memory.
         * Changing b[0] will change a[0] too!
         *
         * To make an independent copy, use:
         * 1. Arrays.copyOf()
         * 2. System.arraycopy()
         * 3. clone()
         */

        int[] orig = { 1, 2, 3, 4, 5 };
        int[] ref = orig; // NOT a copy — same reference!
        int[] copy = Arrays.copyOf(orig, orig.length); // True copy

        ref[0] = 999;
        System.out.println("After ref[0] = 999:");
        System.out.println("  orig: " + Arrays.toString(orig)); // [999, 2, 3, 4, 5] — changed!
        System.out.println("  copy: " + Arrays.toString(copy)); // [1, 2, 3, 4, 5] — independent

        // System.arraycopy — fastest way (native method)
        int[] src = { 10, 20, 30, 40, 50 };
        int[] dest = new int[5];
        System.arraycopy(src, 1, dest, 0, 3); // Copy 3 elements from src[1] to dest[0]
        System.out.println("System.arraycopy: " + Arrays.toString(dest)); // [20, 30, 40, 0, 0]

        // =====================================================================
        // SECTION 5: COMMON ARRAY PATTERNS & ALGORITHMS
        // =====================================================================
        System.out.println("\n=== 5. COMMON ARRAY PATTERNS ===");

        int[] data = { 4, 2, 9, 1, 7, 5, 3, 8, 6 };

        // --- Find min and max ---
        System.out.println("--- Min / Max ---");
        int min = data[0], max = data[0];
        for (int val : data) {
            if (val < min)
                min = val;
            if (val > max)
                max = val;
        }
        System.out.println("Array: " + Arrays.toString(data));
        System.out.println("Min: " + min + ", Max: " + max);

        // --- Sum / Average ---
        System.out.println("\n--- Sum / Average ---");
        int sum = 0;
        for (int val : data)
            sum += val;
        double avg = (double) sum / data.length;
        System.out.println("Sum: " + sum + ", Average: " + String.format("%.2f", avg));

        // --- Reverse array in place ---
        System.out.println("\n--- Reverse in Place ---");
        int[] toReverse = { 1, 2, 3, 4, 5 };
        System.out.println("Before: " + Arrays.toString(toReverse));
        reverseInPlace(toReverse);
        System.out.println("After:  " + Arrays.toString(toReverse));

        // --- Remove duplicates (from sorted) ---
        System.out.println("\n--- Remove Duplicates (sorted array) ---");
        int[] withDups = { 1, 1, 2, 2, 2, 3, 4, 4, 5 };
        int[] unique = removeDuplicates(withDups);
        System.out.println("Input:  " + Arrays.toString(withDups));
        System.out.println("Unique: " + Arrays.toString(unique));

        // --- Two Sum problem (classic interview!) ---
        System.out.println("\n--- Two Sum Problem ---");
        int[] twoSumArr = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = twoSum(twoSumArr, target);
        System.out.println("Array: " + Arrays.toString(twoSumArr) + ", Target: " + target);
        System.out.println("Indices: " + Arrays.toString(result));

        // --- Bubble Sort (educational — understand how sorting works) ---
        System.out.println("\n--- Bubble Sort (educational) ---");
        int[] unsorted = { 64, 34, 25, 12, 22, 11, 90 };
        System.out.println("Before: " + Arrays.toString(unsorted));
        bubbleSort(unsorted);
        System.out.println("After:  " + Arrays.toString(unsorted));

        System.out.println("\n✅ All demos completed successfully!");
    }

    // =========================================================================
    // HELPER METHODS
    // =========================================================================

    public static void printArray(String label, int[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    /** Transpose: rows become columns, columns become rows */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    /** Reverse array in-place using two pointers */
    public static void reverseInPlace(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            // Swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /** Remove duplicates from a SORTED array — returns new array */
    public static int[] removeDuplicates(int[] sorted) {
        if (sorted.length == 0)
            return sorted;

        int[] temp = new int[sorted.length];
        temp[0] = sorted[0];
        int uniqueCount = 1;

        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                temp[uniqueCount++] = sorted[i];
            }
        }
        return Arrays.copyOf(temp, uniqueCount);
    }

    /** Two Sum: find indices of two numbers that add up to target */
    public static int[] twoSum(int[] nums, int target) {
        // Brute force O(n²) — for demonstration; a HashMap approach is O(n)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 }; // Not found
    }

    /**
     * Bubble Sort — O(n²) — educational only, not for production!
     *
     * 💡 INTUITION: Like bubbles rising in water.
     * Repeatedly compare adjacent elements and swap if they're in wrong order.
     * After each pass, the largest unsorted element "bubbles up" to its correct
     * position.
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break; // Optimization: already sorted
        }
    }
}

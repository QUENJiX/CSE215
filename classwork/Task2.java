package classwork;
public class Task2 {
    public static void main(String[] args) {
        double[] arr = { 1.2, 3.4, 0.5, 2.1, 4.6, 0.9, 3.3, 2.8, 1.5, 4.0 };

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.print("Sorted Array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
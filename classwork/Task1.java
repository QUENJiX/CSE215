package classwork;
public class Task1 {
    public static void main(String[] args) {
        int[] numbers = { 12, 45, 7, 23, 56, 90, 11, 34, 2, 67 };
        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum = sum + numbers[i];
        }
        int max = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println("Sum: " + sum);
        System.out.println("Maximum: " + max);
    }
}
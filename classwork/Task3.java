package classwork;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = new int[10];
        int sum = 0;

        System.out.println("Enter 10 integers:");
        for (int i = 0; i < 10; i++) {
            arr[i] = input.nextInt();
            sum += arr[i];
        }
        double average = (double) sum / 10;
        System.out.println("Average: " + average);

        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (arr[i] > average) {
                count++;
            }
        }
        double percentage = (count * 100.0) / 10;
        System.out.println("Percentage above average: " + percentage + "%");
        input.close();
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String message = input.nextLine();

        System.out.println("Hi, " + message + "!");
        input.close();
    }
}
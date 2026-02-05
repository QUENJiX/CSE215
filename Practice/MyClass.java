import java.util.Scanner;

public class MyClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");

        int x = input.nextInt();

        if ((x >= 3) && (x <= 30))
            System.out.println("You've entered " + x);
        else
            System.out.println("Out of range");
        System.out.println("Good Bye!");

        input.close();
    }
}
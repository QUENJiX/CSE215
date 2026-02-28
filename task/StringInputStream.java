import java.util.Scanner;

public class StringInputStream {
    public static void main(String[] args) {
        Scanner inSS = null;
        String userInfo;
        String firstName;
        String lastName;
        int userAge;

        userInfo = "Amy Smith 19";

        // Init scanner object with string
        inSS = new Scanner(userInfo);

        // Parse name and age values from string
        firstName = inSS.next(); // "Amy"
        lastName = inSS.next(); // "Smith"
        userAge = inSS.nextInt(); // 19

        // Output parsed values
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Age: " + userAge);
        inSS.close();
    }
}
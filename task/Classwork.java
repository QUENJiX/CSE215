import java.util.Scanner;

public class Classwork {
    public static void main(String[] args) {
        // 1. Try-with-resources safely handles closing the Scanner
        try (Scanner input = new Scanner(System.in)) {
            Footballer p1 = new Footballer();
            Footballer p2 = new Footballer();

            p1.setName("Messi");
            p1.setTeam("Inter Miami");
            p1.setPosition("Attacking Midfielder");

            p2.setName("Neymar");
            p2.setTeam("Al-Hilal");
            p2.setPosition("Winger");

            System.out.println("--- Enter information for 3 matches ---");
            for (int i = 1; i <= 3; i++) {
                System.out.println("\nMatch " + i);

                // Player 1 Input
                p1.increaseMatches();
                System.out.print(p1.getName() + " Goals: ");
                p1.increaseGoals(input.nextInt());
                System.out.print(p1.getName() + " Assists: ");
                p1.increaseAssists(input.nextInt());

                // Player 2 Input
                p2.increaseMatches();
                System.out.print(p2.getName() + " Goals: ");
                p2.increaseGoals(input.nextInt());
                System.out.print(p2.getName() + " Assists: ");
                p2.increaseAssists(input.nextInt());
            }

            System.out.println("\n--- Comparison Result ---");
            p1.comparePlayer(p2);
        }
    }
}
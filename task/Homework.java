import java.util.Scanner;

public class Homework {
    // 1. Added constants so it's easy to change the assignment size later
    private static final int NUM_PLAYERS = 15;
    private static final int NUM_MATCHES = 10;

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Footballer[] players = new Footballer[NUM_PLAYERS];

            System.out.println("--- Enter Information for " + NUM_PLAYERS + " Players ---");
            for (int i = 0; i < players.length; i++) {
                players[i] = new Footballer();
                System.out.println("\nPlayer " + (i + 1) + ":");
                System.out.print("Name: ");
                players[i].setName(input.nextLine());
                System.out.print("Team: ");
                players[i].setTeam(input.nextLine());
                System.out.print("Position: ");
                players[i].setPosition(input.nextLine());
            }

            System.out.println("\n--- Match Information Entry ---");
            // 2. Replaced standard for-loop with enhanced for-loop (for-each) for
            // readability
            for (Footballer player : players) {
                System.out.println("\nEntering match information for: " + player.getName());

                for (int matchNum = 1; matchNum <= NUM_MATCHES; matchNum++) {
                    player.increaseMatches();
                    System.out.print("Match " + matchNum + " Goals: ");
                    player.increaseGoals(input.nextInt());
                    System.out.print("Match " + matchNum + " Assists: ");
                    player.increaseAssists(input.nextInt());
                }
                // Consume the leftover newline from nextInt()
                input.nextLine();
            }

            System.out.println("\n--- Player Statistics Report ---");
            System.out.printf("%-15s | %-13s | %-11s | %-13s | %-24s | %-18s%n",
                    "Player", "Total Matches", "Goals/Match",
                    "Assists/Match", "Goal Contributions/Match", "Goals/Assist Ratio");
            // Added a divider line for the table
            System.out.println("-".repeat(105));

            for (Footballer player : players) {
                System.out.printf("%-15s | %-13d | %-11.2f | %-13.2f | %-24.2f | %-18.2f%n",
                        player.getName(),
                        player.getMatchesCount(),
                        player.getAvgGoalsPerMatch(),
                        player.getAvgAssistsPerMatch(),
                        player.getAvgGoalContributions(),
                        player.getGoalsToAssistRatio());
            }
        }
    }
}
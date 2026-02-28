public class Footballer {
    // 1. Encapsulated fields to private
    private String name;
    private String team;
    private String position;
    private int matches;
    private int goals;
    private int assists;

    public Footballer() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Stat Increments
    public void increaseMatches() {
        this.matches += 1;
    }

    public void increaseGoals(int extraGoals) {
        this.goals += extraGoals;
    }

    public void increaseAssists(int extraAssists) {
        this.assists += extraAssists;
    }

    // Logic Methods
    public void comparePlayer(Footballer other) {
        if (this.goals > other.goals && this.assists > other.assists) {
            System.out.println(this.name + " has more goals and assists than " + other.name);
        } else if (this.goals > other.goals && this.assists <= other.assists) {
            System.out.println(this.name + " has more goals but fewer or equal assists than " + other.name);
        } else if (this.assists > other.assists && this.goals <= other.goals) {
            System.out.println(this.name + " has more assists but fewer or equal goals than " + other.name);
        } else {
            System.out.println(this.name + " has fewer goals and assists than " + other.name);
        }
    }

    public int getMatchesCount() {
        return this.matches;
    }

    // 2. Optimized rounding logic without converting to String
    private double formatValue(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public double getAvgGoalsPerMatch() {
        return matches == 0 ? 0.0 : formatValue((double) goals / matches);
    }

    public double getAvgAssistsPerMatch() {
        return matches == 0 ? 0.0 : formatValue((double) assists / matches);
    }

    public double getAvgGoalContributions() {
        if (matches == 0)
            return 0.0;
        double contributions = goals + assists;
        return formatValue(contributions / matches);
    }

    public double getGoalsToAssistRatio() {
        return assists == 0 ? goals : formatValue((double) goals / assists);
    }
}
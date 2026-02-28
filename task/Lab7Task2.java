class Square {
    private double side;

    // Default Constructor
    public Square() {
        this.side = 1.0;
    }

    // Parameterized Constructor
    public Square(double side) {
        this.side = side;
    }

    // Getter and Setter
    public double getSide() {
        return this.side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    // Logic Methods
    public double getArea() {
        return this.side * this.side;
    }

    public double getPerimeter() {
        return 4 * this.side;
    }

    public double getDiagonal() {
        return this.side * Math.sqrt(2);
    }
}

public class Lab7Task2 {
    public static void main(String[] args) {
        Square pizzaBox = new Square();
        Square biriyaniBox = new Square(5.5);

        System.out.println("--- Square Properties ---");
        System.out.println("Pizza box area: " + pizzaBox.getArea());
        System.out.println("Biriyani box perimeter: " + biriyaniBox.getPerimeter());
        // Used %n instead of \n for platform-independent line breaks
        System.out.printf("Biriyani box diagonal: %.2f%n", biriyaniBox.getDiagonal());

        System.out.println("\n--- Updating Pizza Box ---");
        pizzaBox.setSide(2.0);
        System.out.println("Pizza box new side: " + pizzaBox.getSide());
    }
}
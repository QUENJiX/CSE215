class Square {
    private double side;
    public Square() {
        this.side = 1.0; 
    }public Square(double s) {
        this.side = s;
    }public void setSide(double s) {
        this.side = s;
    }public double getSide() {
        return this.side;
    }public double getArea() {
        return this.side * this.side;
    }public double getPerimeter() {
        return 4 * this.side;
    }public double getDiagonal() {
        return this.side * Math.sqrt(2);
    }
}

public class Lab7Task2 {
    public static void main(String[] args) {
        Square pizzaBox = new Square();
        Square biriyaniBox = new Square(5.5);

        System.out.println("Pizza box area: " + pizzaBox.getArea());
        System.out.println("Biriyani box perimeter: " + biriyaniBox.getPerimeter());
        System.out.printf("Biriyani box diagonal: %.2f\n", biriyaniBox.getDiagonal());
        pizzaBox.setSide(2.0);
        System.out.println("Pizza box new side: " + pizzaBox.getSide());
    }
}
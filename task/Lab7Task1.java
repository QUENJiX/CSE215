class Student {
    // 1. Encapsulated fields to private
    private String fullName;
    private String nsuId;
    private double currentGpa;

    // Default Constructor
    public Student() {
        this.fullName = "Unknown";
        this.nsuId = "0000";
        this.currentGpa = 0.0;
    }

    // Parameterized Constructor
    public Student(String givenName, String givenId, double givenGpa) {
        this.fullName = givenName;
        this.nsuId = givenId;
        this.currentGpa = givenGpa;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNsuId() {
        return nsuId;
    }

    public void setNsuId(String nsuId) {
        this.nsuId = nsuId;
    }

    public double getCurrentGpa() {
        return currentGpa;
    }

    public void setCurrentGpa(double currentGpa) {
        this.currentGpa = currentGpa;
    }

    // Logic Methods
    public void displayStudentInfo() {
        System.out.println("Student Name: " + this.fullName);
        System.out.println("Student ID: " + this.nsuId);
        System.out.println("Student CGPA: " + this.currentGpa);
    }
}

public class Lab7Task1 {
    public static void main(String[] args) {
        Student ghostStudent = new Student();
        Student zamilBhai = new Student("Zamil", "2110123442", 3.9);

        // 2. Used setter instead of direct field access
        ghostStudent.setCurrentGpa(3.5);

        System.out.println("--- First Object ---");
        ghostStudent.displayStudentInfo();

        System.out.println("\n--- Second Object ---");
        zamilBhai.displayStudentInfo();
    }
}
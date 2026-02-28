class Student {
    String fullName;
    String nsuId;
    double currentGpa;
    Student() {
        this.fullName = "Unknown"; 
        this.nsuId = "0000"; 
        this.currentGpa = 0.0; 
    }Student(String givenName, String givenId, double givenGpa) {
        this.fullName = givenName;
        this.nsuId = givenId;
        this.currentGpa = givenGpa;
    }void displayStudentInfo() {
        System.out.println("Student Name: " + this.fullName); 
        System.out.println("Student ID: " + this.nsuId); 
        System.out.println("Student CGPA: " + this.currentGpa); 
    }
}

public class Lab7Task1 {
    public static void main(String[] args) {
        Student ghostStudent = new Student();
        Student zamilBhai = new Student("Zamil", "2110123442", 3.9);
        ghostStudent.currentGpa = 3.5;
        
        System.out.println("First Object");
        ghostStudent.displayStudentInfo();
        System.out.println("\nSecond Object");
        zamilBhai.displayStudentInfo();
    }
}
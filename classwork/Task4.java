package classwork;
public class Task4 {
    public static void main(String[] args) {
        int[][] matrix = {
                { 4, 2, 7, 6 },
                { 3, 7, 9, -5 },
                { 2, 12, 3, 2 },
                { -1, 4, -3, 9 }
        };

        for (int i = 0; i < 4; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < 4; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            System.out.println("Sum of Row " + (i + 1) + ": " + rowSum);
            System.out.println("Sum of Column " + (i + 1) + ": " + colSum);
        }

        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < 4; i++) {
            diag1 += matrix[i][i];
            diag2 += matrix[i][3 - i];
        }
        System.out.println("Main Diagonal Sum: " + diag1);
        System.out.println("Secondary Diagonal Sum: " + diag2);
    }
}
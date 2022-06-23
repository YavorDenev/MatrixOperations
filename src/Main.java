import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void printDeterminant(double[][] SquareMat) {
        System.out.println("\nThe determinant is :  " + getDeterminant(SquareMat));
    }

    public static void printInverseMatrix(double[][] SquareMat) {
        if (getDeterminant(SquareMat) == 0) {
            System.out.println("\nThis matrix has no inverse.");
        } else {
            System.out.println("\nThe inverse matrix is : ");
            printMatrix(getInverseMatrix(SquareMat));
        }
    }

    public static void checkForIdentity(double[][] SquareMat) {
        double det = getDeterminant(SquareMat);
        String s = (det == 0) ? "NOT " : "";
        System.out.println("\nThis matrix can " + s + "be converted to identity matrix (I)");
    }

    public static double[][] getInverseMatrix(double[][] mat) {       // using the adjoint matrix
        double det = getDeterminant(mat);
        double[][] invMatrix = new double[mat.length][mat.length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                invMatrix[i][j] = Math.pow(-1, (i + j)) * getDeterminant(getMinorMatrix(mat, j, i)) / det;
            }
        }

        return invMatrix;
    }

    public static double getDeterminant(double[][] mat) {   // by the Laplace expansion with Adjusted minors
        double det = 0;

        if (mat.length == 1) {
            det = mat[0][0];
        } else {
            for (int i = 0; i < mat[0].length; i++) {
                det += mat[0][i] * Math.pow(-1, i) * getDeterminant(getMinorMatrix(mat, 0, i));
            }
        }

        return det;
    }

    public static double[][] getMinorMatrix(double[][] mat, int row, int column) {   // returns minor matrix of mat for i=row and j=column
        double[][] minor = new double[mat.length - 1][mat.length - 1];

        for (int i = 0; i < mat.length - 1; i++) {
            int x = (2 * i + 1) / (i + row + 1);           // similar to:  x = (i<row) ? 0 : 1;
            for (int j = 0; j < mat.length - 1; j++) {
                int y = (2 * j + 1) / (j + column + 1);    // similar to:  y = (j<column) ? 0 : 1;
                minor[i][j] = mat[i + x][j + y];
            }
        }

        return minor;
    }

    public static void enterChoiceForSquareMatrix(double[][] SquareMat) {
        System.out.print("Enter 1, 2, 3, 4 or 5 ... ");
        String choice = scan.next();
        switch (choice) {
            case "1" -> {
                printDeterminant(SquareMat);
                showSquareMatrixMenu(SquareMat);
            }
            case "2" -> {
                printInverseMatrix(SquareMat);
                showSquareMatrixMenu(SquareMat);
            }
            case "3" -> {
                checkForIdentity(SquareMat);
                showSquareMatrixMenu(SquareMat);
            }
            case "4" -> inputSquareMatrix();
            case "5" -> showMainMenu();
            case "6" -> System.out.println("Good bye!");
            default -> {
                System.out.println("Invalid input. Try again!");
                enterChoiceForSquareMatrix(SquareMat);
            }
        }
    }

    public static void showSquareMatrixMenu(double[][] SquareMat) {
        System.out.println("""

                What do you want to do with the matrix?
                \t1. Calculation of the determinant;
                \t2. Finding the inverse matrix;
                \t3. Checking whether the matrix can be converted to identity matrix (I);
                \t4. Entering new square matrix;
                \t5. Return to main menu;
                \t6. Exit.""");
        enterChoiceForSquareMatrix(SquareMat);
    }

    public static void inputSquareMatrix() {
        System.out.println("\nEnter the number of rows and columns of the matrix: ");
        int num = inputPositiveInteger();
        System.out.println("Enter the matrix: ");
        double[][] SquareMat = inputMatrix(num, num);
        System.out.println("\nThe matrix is :  ");
        printMatrix(SquareMat);
        showSquareMatrixMenu(SquareMat);
    }

    public static void printProductOfMatrices() {
        System.out.println("\nEnter the number of rows of the first matrix: ");
        int rows1 = inputPositiveInteger();
        System.out.println("Enter the number of columns of the first matrix: ");
        int columns1 = inputPositiveInteger();
        System.out.println("Enter the first matrix: ");
        double[][] mat1 = inputMatrix(rows1, columns1);
        System.out.println("Enter the number of rows of the second matrix: ");
        int rows2 = inputPositiveInteger();
        while (columns1 != rows2) {
            System.out.println("The number of columns in the first matrix must be equal to the number of rows in the second!" +
                    "\nTry again!");
            rows2 = inputPositiveInteger();
        }
        System.out.println("Enter the number of columns of the second matrix: ");
        int columns2 = inputPositiveInteger();
        System.out.println("Enter the second matrix: ");
        double[][] mat2 = inputMatrix(rows2, columns2);
        System.out.println("\nThe first matrix is :  ");
        printMatrix(mat1);
        System.out.println("\nThe second matrix is :  ");
        printMatrix(mat2);
        System.out.println("\nThe product of the matrices is :  ");
        printMatrix(multiplyMatrices(mat1, mat2));
    }

    public static double[][] multiplyMatrices(double[][] mat1, double[][] mat2) {
        double[][] product = new double[mat1.length][mat2[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                product[i][j] = 0;
                for (int k = 0; k < mat2.length; k++) {
                    product[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }

        return product;
    }

    public static void printSumNDifferOfMatrices(char operation) {                // "operation" determines whether there
        System.out.println("\nEnter the number of rows of the two matrices: ");   //  is addition '+'  or subtraction '-'
        int rows = inputPositiveInteger();
        System.out.println("Enter the number of columns of the two matrices: ");
        int columns = inputPositiveInteger();
        System.out.println("Enter the first matrix: ");
        double[][] mat1 = inputMatrix(rows, columns);
        System.out.println("Enter the second matrix: ");
        double[][] mat2 = inputMatrix(rows, columns);
        System.out.println("\nThe first matrix is :  ");
        printMatrix(mat1);
        System.out.println("\nThe second matrix is :  ");
        printMatrix(mat2);
        switch (operation) {
            case '+' -> {
                System.out.println("\nThe sum of the matrices is :  ");
                printMatrix(sumMatrices(mat1, mat2));
            }
            case '-' -> {
                System.out.println("\nThe difference of the matrices is :  ");
                printMatrix(subtractMatrices(mat1, mat2));
            }
        }
    }

    public static double[][] sumMatrices(double[][] mat1, double[][] mat2) {
        double[][] sum = new double[mat1.length][mat2[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                sum[i][j] = mat1[i][j] + mat2[i][j];
            }
        }

        return sum;
    }

    public static double[][] subtractMatrices(double[][] mat1, double[][] mat2) {
        double[][] difference = new double[mat1.length][mat2[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                difference[i][j] = mat1[i][j] - mat2[i][j];
            }
        }

        return difference;
    }

    public static void printMatrix(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    System.out.print("     0    ");
                } else if (mat[i][j] / (double) (int) mat[i][j] == 1) {
                    System.out.printf("%6.0f", mat[i][j]);
                    System.out.print("    ");
                } else {
                    System.out.printf("%10.3f", mat[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static double[][] inputMatrix(int row, int column) {
        double[][] matrix = new double[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.printf("row %2d, col %2d  :  ", (i + 1), (j + 1));
                matrix[i][j] = inputDouble();
            }
        }

        return matrix;
    }

    public static double inputDouble() {
        while (!(scan.hasNextDouble())) {
            System.out.println("Invalid input. Try again!");
            scan.next();
        }

        return scan.nextDouble();
    }

    public static int inputPositiveInteger() {
        while (!(scan.hasNextInt())) {
            System.out.println("Invalid input. Try again!");
            scan.next();
        }
        int num = scan.nextInt();
        while (num < 1) {
            System.out.println("Invalid input number. Try again!");
            num = inputPositiveInteger();
        }

        return num;
    }

    public static void enterChoice() {
        System.out.print("Enter 1, 2, 3, 4 or 5 ... ");
        String choice = scan.next();
        switch (choice) {
            case "1" -> {
                printSumNDifferOfMatrices('+');
                showMainMenu();
            }
            case "2" -> {
                printSumNDifferOfMatrices('-');
                showMainMenu();
            }
            case "3" -> {
                printProductOfMatrices();
                showMainMenu();
            }
            case "4" -> inputSquareMatrix();
            case "5" -> System.out.println("Good bye!");
            default -> {
                System.out.println("Invalid input. Try again!");
                enterChoice();
            }
        }
    }

    public static void showMainMenu() {
        System.out.println("""

                What is your choice?
                \t1. Sum of two matrices;
                \t2. Difference of two matrices;
                \t3. Product of two matrices;
                \t4. Square matrix operations;
                \t5. Exit.""");
        enterChoice();
    }

    public static void main(String[] args) {
        System.out.println("\nThis is a simple matrix calculator.");
        showMainMenu();
    }
}
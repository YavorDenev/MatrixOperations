import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static double[][] getInverseMatrix(double[][] mat) {       // using the adjoint matrix
        double det = getDeterminant(mat);
        double[][] invMatrix = new double[mat.length][mat.length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                invMatrix[i][j] = Math.pow(-1, (i + j)) * getDeterminant(getMinor(mat, j, i)) / det;
            }
        }

        return invMatrix;
    }

    public static double getDeterminant(double[][] mat) {   // by the Laplace expansion with Adjusted minors
        double det = 0;

        if (mat.length == 1) {
            det = mat[0][0];
        }
        else {
            for (int i = 0; i < mat[0].length; i++) {
                det += mat[0][i] * Math.pow(-1, i) * getDeterminant(getMinor(mat, 0, i));
            }
        }

        return det;
    }

    public static double[][] getMinor(double[][] mat, int row, int column) {   // returns minor matrix of mat for i=row and j=column
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

    public static void sumMatrices() {
        System.out.println("Enter the number of rows of the two matrices: ");
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
        System.out.println("\nThe sum of the matrices is :  ");
        printMatrix(sumMatrices(mat1, mat2));
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

    public static void subtractMatrices() {
        System.out.println("Enter the number of rows of the two matrices: ");
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
        System.out.println("\nThe difference of the matrices is :  ");
        printMatrix(subtractMatrices(mat1, mat2));
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
                matrix[i][j] = scan.nextDouble();
            }
        }

        return matrix;
    }

    public static int inputPositiveInteger() {
        while ( ! (scan.hasNextInt()) ) {
            System.out.println("Invalid input. Try again!");
            String s = scan.next();
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
                sumMatrices();
                showMainMenu();
            }
            case "2" -> {
                subtractMatrices();
                showMainMenu();
            }
            //case "3" -> {
                //multiplyMatrices();
                //showMainMenu();
            //}
            //case "4" -> {
                //showSquareMatrixMenu();
                //showMainMenu();
            //}
            case "5" -> System.out.println("Good bye!");
            default -> {
                System.out.println("Invalid input. Try again!");
                enterChoice();
            }
        }
    }

    public static void showMainMenu() {
        System.out.println("\nWhat is your choice? " +
                "\n\t1. Sum of two matrices;" +
                "\n\t2. Difference of two matrices;" +
                "\n\t3. Product of two matrices;" +
                "\n\t4. Square matrix operations;" +
                "\n\t5. Exit.");
        enterChoice();
    }

    public static void main(String[] args) {
        System.out.println("\nThis is a simple matrix calculator.");
        showMainMenu();


        //double[][] a = {
                //{ 5, 5, 0, 1, 0},           // det = -2040
                //{ 5, -1, 0, 3, 3},
                //{ 2, 2, 6, 0, 0},
                //{ 0, 7, 0, 9, 0},
                //{ 0, 0, 0, 0, 1},

                //{0, 0, 2,},                 //   det = -6
                //{2, 1, 4,},
                //{3, 0, 0,},

                //{0, 1, 2,},                 //   det = 12
                //{0, 0, 4,},
                //{3, 0, 0,},

                //{10, 15, 77, 5, 0, 72,},      //   det = -76953
                //{20, 57, 30, 6, 2, 3,},
                //{37, 56, 85, 3, 3, 4,},
                //{5, 3, 11, 2, 4, 5,},
                //{0, 1, 2, 3, 5, 6,},
                //{1, 2, 3, 4, 5, 7,},

                //{1, 2,},                   //   det = -2
                //{3, 4,},

                //{9}                          //   det = 9
        //};

        //double[][] x = {
                //{-99, -99.9, -99,},
                //{89, -3, 1,},
                //{44, -13, 12,},
                //{6, 8, 1,},
        //};

        //double[][] y = {
                //{99, 88.33, 3, 54, 5,},
                //{99, 6, 0, 53, 22,},
                //{99, 9, 8, -73, -2,},

                //{2, -6, -3,},
                //{12, -11, 0,},
                //{4, -13, 2,},
                //{7, 8, -5,},
        //};


        
        //double[][] resultMatrix = multiplyMatrices(x, y);

        //double[][] resultMatrix = sumMatrices (x, y);

        //double[][] resultMatrix = subtractMatrices (x, y);

        //double[][] resultMatrix = getInverseMatrix (a);

        //printMatrix(resultMatrix);

        //printMatrix(inputMatrix(3,2));

        //System.out.println("...............  " + getDeterminant (a));
    }
}
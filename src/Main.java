public class Main {

    static double[][] invertedMatrix(double[][] mat) {       // по Метода на адюнгираните количества
        double det = getDeterminant(mat);
        double[][] invMatrix = new double[mat.length][mat.length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                invMatrix[i][j] = Math.pow(-1, (i + j)) * getDeterminant(getMinor(mat, j, i)) / det;
            }
        }

        return invMatrix;
    }

    static double getDeterminant(double[][] mat) {   // по Правилото на Лаплас
        double det = 0;                              // с Адюнгирани минори  +  рекурсия
                                                     // предимства: простота; няма деление и загуба на точност;
        if (mat.length == 1) {                       // недостатък: при много големи матрици не е най-ефективния
            det = mat[0][0];
        }
        else {
            for (int i = 0; i < mat[0].length; i++) {
                det += mat[0][i] * Math.pow(-1, i) * getDeterminant(getMinor(mat, 0, i));
            }
        }

        return det;
    }

    static double[][] getMinor(double[][] mat, int row, int column) {     // връща минорна матрица на mat
        double[][] minor = new double[mat.length - 1][mat.length - 1];    // за i=row и j=column

        for (int i = 0; i < mat.length - 1; i++) {
            int x = (2 * i + 1) / (i + row + 1);            // аналогично на: x = (i<row) ? 0 : 1;
            for (int j = 0; j < mat.length - 1; j++) {
                int y = (2 * j + 1) / (j + column + 1);     // аналогично на: y = (j<column) ? 0 : 1;
                minor[i][j] = mat[i + x][j + y];
            }
        }

        return minor;
    }

    static double[][] multiplyMatrices(double[][] mat1, double[][] mat2) {
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

    static double[][] sumMatrices(double[][] mat1, double[][] mat2) {
        double[][] sum = new double[mat1.length][mat2[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                sum[i][j] = mat1[i][j] + mat2[i][j];
            }
        }

        return sum;
    }


    static double[][] subtractMatrices(double[][] mat1, double[][] mat2) {
        double[][] difference = new double[mat1.length][mat2[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                difference[i][j] = mat1[i][j] - mat2[i][j];
            }
        }

        return difference;
    }

    static void printMatrix(double[][] mat) {
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

    public static void main(String[] args) {
        double[][] a = {
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

                {10, 15, 77, 5, 0, 72,},      //   det = -76953
                {20, 57, 30, 6, 2, 3,},
                {37, 56, 85, 3, 3, 4,},
                {5, 3, 11, 2, 4, 5,},
                {0, 1, 2, 3, 5, 6,},
                {1, 2, 3, 4, 5, 7,},

                //{1, 2,},                   //   det = -2
                //{3, 4,},

                //{9}                          //   det = 9
        };

        double[][] x = {
                {-99, -99.9, -99,},
                {89, -3, 1,},
                {44, -13, 12,},
                {6, 8, 1,},
        };

        double[][] y = {
                {99, 88.33, 3, 54, 5,},
                {99, 6, 0, 53, 22,},
                {99, 9, 8, -73, -2,},

                //{2, -6, -3,},
                //{12, -11, 0,},
                //{4, -13, 2,},
                //{7, 8, -5,},
        };

        double[][] resultMatrix = multiplyMatrices(x, y);

        //double[][] resultMatrix = sumMatrices (x, y);

        //double[][] resultMatrix = subtractMatrices (x, y);

        //double[][] resultMatrix = invertedMatrix (a);

        printMatrix(resultMatrix);

        //System.out.println("...............  " + getDeterminant (a));
    }
}
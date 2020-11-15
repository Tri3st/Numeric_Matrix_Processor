/**
 * Numeric Matrix Processor 
 * Written for Hyperskill challenge 
 * by 
 * @author Martin van Diest
 * 
 */

package processor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        double[][] mx1;
        double[][] mx2;
        double[][] mx3;
        Boolean quit = false;
        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice : ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    mx1 = inputMatrix(scanner);
                    mx2 = inputMatrix(scanner);
                    if (mx1.length != mx2.length || mx1[0].length != mx2[0].length ) {
                        System.out.println("you cant add those 2 matrices!!");
                        System.out.printf("%d columns from matrix 1 and %d rows from matrix 2 .. NOPE",mx1[0].length,mx2.length);
                    } else {
                        mx3 = addMatrices(mx1,mx2);
                        System.out.println("Result :");
                        printMatrix(mx3);
                    }
                    break;
                case "2" :
                    mx1 = inputMatrix(scanner);
                    System.out.println("Multiply with what constant ?");
                    int con = scanner.nextInt();
                    mx2 = multiplyConstantMatrix(mx1, con);
                    printMatrix(mx2);
                    scanner.nextLine();
                    break;
                case "3" :
                    mx1 = inputMatrix(scanner);
                    mx2 = inputMatrix(scanner);
                    if (mx1[0].length != mx2.length ) {
                        System.out.println("you cant multiply those 2 matrices!!");
                        System.out.printf("%d columns from matrix 1 and %d rows from matrix 2 .. NOPE",mx1[0].length,mx2.length);
                    } else {
                        mx3 = multiplyMatrices(mx1,mx2);
                        System.out.println("Result :");
                        printMatrix(mx3);
                    }
                    break;
                case "4" :
                    String inp = "";
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    while (true) {
                        System.out.print("Your choice :");
                        inp = scanner.nextLine();
                        switch (inp) {
                            case "1" :
                                mx1 = inputMatrix(scanner);
                                mx2 = mainDiagonalTranspose(mx1);
                                System.out.println("The result is:");
                                printMatrix(mx2);
                                break;
                            case "2" :
                                mx1 = inputMatrix(scanner);
                                mx2 = sideDiagonalTranspose(mx1);
                                System.out.println("The result is:");
                                printMatrix(mx2);
                                break;
                            case "3" :
                                mx1 = inputMatrix(scanner);
                                mx2 = verticalLineTranspose(mx1);
                                System.out.println("The result is:");
                                printMatrix(mx2);
                                break;
                            case "4" :
                                mx1 = inputMatrix(scanner);
                                mx2 = horizontalLineTranspose(mx1);
                                System.out.println("The result is:");
                                printMatrix(mx2);
                                break;
                            default :
                                break;
                        }
                        break;
                    }

                    break;
                case "5":
                    mx1 = inputMatrix(scanner);
                    System.out.println("The result is:");
                    System.out.println(getD(mx1));
                    break;
                case "6":
                    mx1 = inputMatrix(scanner);
                    System.out.println("The result is:");
                    mx2 = inverseMatrix(mx1);
                    printMatrix(mx2);
                    break;
                case "0":
                    quit = true;
                    break;
                default:
                    System.out.println("Unrecognized command. Try again.");
                    break;
            }

            if (quit) break;
        }
    }

    public static double[][] inputMatrix (Scanner scanner) {
        System.out.println("Give dimensions of matrix (Rows Columns) : ");
        String inp = scanner.nextLine();
        String[] inpDim = inp.split(" ");
        int[] dimensions = new int[2];
        dimensions[0] = Integer.parseInt(inpDim[0]);
        dimensions[1] = Integer.parseInt(inpDim[1]);
        double[][] matrix = new double[dimensions[0]][dimensions[1]];
       for (int i = 0; i < dimensions[0]; i++) {
           // System.out.printf("Enter row %d : %n", i + 1);
            String row = scanner.nextLine();
            String[] rows = row.split(" ");
            for (int j = 0; j < dimensions[1]; j++) {
                matrix[i][j] = Double.parseDouble(rows[j]);
            }
        }
     //   printMatrix(matrix);
        return matrix;
    }

    public static void printMatrix(double[][] m) {
        int row = m.length;
        int col = m[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == col - 1) {
                    System.out.printf("%6.2f%n",m[i][j]);
                } else {
                    System.out.printf("%6.2f ",m[i][j]);
                }
            }
        }
    }

    public static double[][] multiplyConstantMatrix(double[][] m1, double constante){
        double[][] m2 = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++){
            for (int j = 0; j < m1[0].length; j++){
                m2[i][j] = m1[i][j] * constante;
            }
        }
        return m2;
    }

    public static double[][] addMatrices(double[][] m1, double[][] m2){
        int row = m1.length;
        int col = m1[0].length;
        double[][] m3 = new double[row][col];
        for (int i = 0;i < row; i++) {
            for (int j = 0; j < col; j++){
                m3[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return m3;
    }

    public static double[][] multiplyMatrices(double[][] m1, double[][] m2) {
        int row = m1.length;
        int col = m2[0].length;
        double[][] m3 = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++ ) {
                for (int k = 0; k < m2.length; k++) {
                    m3[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return m3;

    }

    public static double[][] mainDiagonalTranspose(double[][] mx) {
        double[][] mx2 = new double[mx[0].length][mx.length];
        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[0].length; j++) {
                mx2[j][i] = mx[i][j];
            }
        }
        return mx2;
    }

    public static double[][] sideDiagonalTranspose(double[][] mx) {
        double[][] mx2 = new double[mx[0].length][mx.length];
        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[0].length; j++) {
                mx2[mx[0].length - j - 1][mx.length - i - 1] = mx[i][j];
            }
        }
        return mx2;
    }

    public static double[][] verticalLineTranspose(double[][] mx) {
        double[][] mx2 = new double[mx.length][mx[0].length];
        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[0].length; j++) {
                mx2[i][mx[0].length - j - 1] = mx[i][j];
            }
        }
        return mx2;
    }

    public static double[][] horizontalLineTranspose(double[][] mx) {
        double[][] mx2 = new double[mx.length][mx[0].length];
        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[0].length; j++) {
                mx2[mx.length - i -1][j] = mx[i][j];
            }
        }
        return mx2;
    }

 /*   // Function to get cofactor of
    // mat[p][q] in temp[][]. n is
    // current dimension of mat[][]
    public static double[][] getCofactor(double[][] mat)
    {
        int i = 0, j = 0;
        int p = mat.length;
        int q = mat[0].length;
        int n = p;
        double[][] temp = new double[p][q];

        // Looping for each element of
        // the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {

                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];

                    // Row is filled, so increase
                    // row index and reset col
                    //index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return temp;
    }
    */

    public static double[][] getMinor(double[][] matrix, int m, int n) {
        int size = matrix.length;
        int x =0;
        int y = 0;
        double[][] minor = new double[size-1][size-1];
        for (int i = 0; i < size; i++) {
            if (i != n) {
                for (int j = 0; j < size; j++) {
                    if (j != m) {
                        minor[x][y] = matrix[i][j];
                        y++;
                    }
                }
                x++;
                y = 0;
            }
        }
        return minor;
    }

    public static double[][] getCofarctorMatrix(double[][] mx){
        double[][] mx2 = new double[mx.length][mx.length];
        for (int i = 0; i < mx.length; i++){
            for (int j = 0; j < mx.length; j++){
                mx2[i][j] = Math.pow(-1,(i + j)) * getD(getMinor(mx, i, j));
            }
        }
        return mx2;
    }

    public static double getD(double[][] m) {
        double det = 0;
        if (m.length == 2) {
            return m[0][0] * m[1][1] - m[0][1] * m[1][0];
        }
        for (int i = 0; i < m.length; i++) {
            double[][] temp = stripMatrix(m, i);
            det += i % 2 == 0 ? m[0][i] * getD(temp) : -(m[0][i] * getD(temp));
        }
        return det;
    }

    private static double[][] stripMatrix(double[][] m, int num) {
        double[][] res = new double[m.length - 1][m.length - 1];
        for (int i = 1; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (j < num) {
                    res[i - 1][j] = m[i][j];
                }
                if (j > num) {
                    res[i - 1][j - 1] = m[i][j];
                }
            }
        }
        return res;
    }

    private static double[][] inverseMatrix(double[][] m) {
        double[][] temp;
        double[][] temp2;
        if (getD(m) == 0) {
            System.out.println("THERE IS NO INVERSE OF THIS MATRIX");
            return m;
        }
        temp = getCofarctorMatrix(m);
      //  temp2 = mainDiagonalTranspose(temp);
        double cons = 1/getD(m);
        return multiplyConstantMatrix(temp, cons);
    }

}

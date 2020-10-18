package Algorithm.Metrix;

import java.util.Arrays;
import java.util.Scanner;

public class MetrixAlgorithm {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double a[][] = new double[n][n];
        double result[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
            }
        }
        double[] ei = new double[n];
        ei[0] = 1;
        double[] temp = solve_metrix(a, ei);
        add(result, temp, 0);
        for (int i = 1; i < n; i++) {
            ei[i] = 1;
            ei[i - 1] = 0;
            temp = solve_metrix(a, ei);
            add(result, temp, i);
        }
        printMetrix(result);
        System.out.println();
        printMetrix(mertrixMul(result, a));
    }

    private static void add(double[][] a, double b[], int i) {
        for (int j = 0; j < b.length; j++) {
            a[j][i] = b[j];
        }
    }

    //a m*k  b k*n
    public static double[][] mertrixMul(double[][] a, double[][] b) {
        int m = a.length;
        int k = b.length;
        int n = b[0].length;
        double[][] result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < k; l++) {
                    result[i][j] += a[i][l] * b[l][j];
                }
            }
        }
        return result;
    }

    public static int[][] getReverse(int a[][]) {
        if (a == null) return null;
        if (a.length == 0) return null;
        if (a[0].length == 0) return null;
        int result[][] = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                result[j][i] = a[i][j];
            }
        }
        return result;
    }

    public static void printMetrix(double a[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[] solve_metrix(double A[][], double[] b) {
        int n = b.length;
        double L[][] = new double[n][n];
        double U[][] = new double[n][n];
        int p[] = new int[n];
        for (int i = 0; i < n; i++) {
            L[i][i] = 1;
        }
        LUP_decomposition(A, L, U, p);
        return LUP_solve(L, U, p, b);
    }

    public static void LUP_decomposition(double t[][], double L[][], double U[][], int p[]) {

        int n = p.length;
        double A[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            A[i] = Arrays.copyOf(t[i], n);

        }
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }

        for (int i = 0; i < n; i++) {
            double max = 0;
            int k = 0;
            for (int j = i; j < n; j++) {
                if ((A[j][i] >= 0 ? A[j][i] : -A[j][i]) > max) {
                    max = A[j][i];
                    k = j;
                }
            }
            double dif = 1e-8;
            if ((max - 0) < dif) throw new RuntimeException("非法矩阵");
            int temp = p[i];
            p[i] = p[k];
            p[k] = temp;
            //交换第i行和第k行
            for (int j = 0; j < n; j++) {
                double temp2 = A[i][j];
                A[i][j] = A[k][j];
                A[k][j] = temp2;
            }
            for (int j = i + 1; j < n; j++) {
                A[j][i] /= A[i][i];
                for (int l = i + 1; l < n; l++) {
                    A[j][l] -= A[j][i] * A[i][l];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) L[i][j] = A[i][j];
                else U[i][j] = A[i][j];
            }
        }
    }

    //L为下三角单位矩阵，U为上三角矩阵，P为置换矩阵，b为结果
    public static double[] LUP_solve(double[][] L, double[][] U, int[] p, double[] b) {
        int n = b.length;
        double y[] = new double[n];//Ly=Pb
        for (int i = 0; i < n; i++) {
            y[i] = b[p[i]];
            for (int j = 0; j < i; j++) {
                y[i] -= L[i][j] * y[j];
            }
        }
        double x[] = new double[n];//Ux=y
        for (int i = n - 1; i >= 0; i--) {
            x[i] = y[i];
            for (int j = n - 1; j > i; j--) {
                x[i] -= U[i][j] * x[j];
            }
            x[i] /= U[i][i];
        }
        return x;
    }
}

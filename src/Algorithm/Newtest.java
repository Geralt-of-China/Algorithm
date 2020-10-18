package Algorithm;

import java.util.Scanner;
import java.util.function.Function;

public class Newtest {
    static Scanner scanner;

    public static void main(String args[]) {
        System.out.println(Boolean.valueOf(""));
    }

    //求b的n次方对m取模的最快算法
    public static int modFast(int b, int n, int m) {
        int result = 1;
        int power = b % m;
        for (; n != 0; n >>= 1) {
            if ((1 & n) == 1)
                result = result * power % m;
            power = power * power % m;
        }
        return result;
    }

    static class newton implements Function<Double, Double> {
        @Override
        public Double apply(Double n) {
            double x = 1;
            for (double abs = x * x - n; (abs > 0 ? abs : -abs) > 1e-9; ) {
                x = (x * x + n) / (2 * x);
                abs = x * x - n;
            }
            return x;
        }
    }

    static class newton2 implements Function<Double, Double> {

        @Override
        public Double apply(Double aDouble) {
            double from = 0, to = aDouble;
            double pos = (from + to) / 2;
            for (; Math.abs(pos * pos - aDouble) > 1e-9; ) {
                pos = (from + to) / 2;
                double temp = pos * pos - aDouble;
                if (temp > 0) to = pos;
                else from = pos;
            }
            return pos;
        }
    }

    public static void testDouble(Function<Double, Double> f1, Function<Double, Double> f2, double n) {
        long time1 = System.currentTimeMillis();
        System.out.printf("result is %f\n", f1.apply(n).doubleValue());
        System.out.println(System.currentTimeMillis() - time1);

        long time2 = System.currentTimeMillis();
        System.out.printf("result is %f\n", f2.apply(n).doubleValue());
        System.out.println(System.currentTimeMillis() - time2);

    }


}
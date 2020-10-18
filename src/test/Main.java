package test;

import java.lang.reflect.Field;

public class Main {

    public static void main(String args[]) {
        try {
            Field field = A.class.getDeclaredField("i");
            field.setAccessible(true);
            A a = new A();
            field.set(a, 2);
            System.out.println(field.get(a));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static int check(String x, long d, long thresh) {
        //将x解释为d进制与thresh比较
        //已经知道d<thresh
        long result = 0;
        for (int i = 0; i < x.length(); i++) {
            long temp = result;
            result *= d;
            if (result / d != temp) return 1;
            result += x.charAt(i) - '0';
            if (result < 0) return 1;
            if (result > thresh) return 1;
        }
        if (result == thresh) return 0;
        return -1;
    }
}

package JavaLa;

import java.io.IOException;

public class test implements Cloneable {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader a = test.class.getClassLoader();
        //Class classA=a.loadClass("JavaLa.A");
        Class.forName("JavaLa.A", true, a);
    }


    //计算f(n)
    public static long getMax(long n) {
        long floor;
        long ceil;
        long max = 1;
        for (long i = 2; ; i++) {
            double result = Math.pow(n, (double) 1 / i);
            floor = (long) Math.floor(result);
            ceil = (long) Math.ceil(result);
            if (floor == 1) return max;
            if ((long) Math.pow(floor, i) == n) max = i;
            if ((long) Math.pow(ceil, i) == n) max = i;
        }
    }
}

class A implements Cloneable {
    int i = 3;

    static {
        System.out.println("a is loaded");
    }

    A() throws IOException {
        System.out.println("a constructor called");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class B extends A {
    static int j = 4;

    static {
        System.out.println("b static called");
    }

    B() throws Exception {
        Class a = test.class;
        System.out.println("b constructor called");
    }
}
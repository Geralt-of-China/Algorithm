package test;

import java.util.*;

public class Main2 {
    public static void main(String args[]) {
        for (String s : System.getProperty("java.class.path").split(";")) {
            System.out.println(s);
        }

    }
}

class A {
    void hello() {
        System.out.println("hello in A");
    }
}

class B extends A {
    void hello() {
        System.out.println("hello in B");
    }

    void test() {
        super.hello();
    }
}

class C extends B {
    void test() {
        super.hello();

    }
}
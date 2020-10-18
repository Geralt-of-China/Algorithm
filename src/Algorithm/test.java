package Algorithm;

import Algorithm.Geometry.GeometryAlgo;
import Algorithm.Geometry.IntPoint;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        for (int y = 0; y < 100; y++) {
            Random random = new Random();
            int a[] = new int[1000000];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(a.length);
            }
            long current = System.currentTimeMillis();
            Algorithm.mergesortOnSame(a);
            long temp = System.currentTimeMillis();
            //System.out.println(Arrays.toString(a));
            System.out.print("原址归并排序time spend: " + (temp - current));

            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(a.length);
            }
            current = System.currentTimeMillis();
            Algorithm.quicksort2(a);
            temp = System.currentTimeMillis();
            //System.out.println(Arrays.toString(a));
            System.out.printf("| 快速排序time spend:%d", temp - current);

            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(a.length);
            }
            current = System.currentTimeMillis();
            temp = System.currentTimeMillis();

            System.out.printf("| 计数排序time spend:%d\n", temp - current);
            //System.out.println(Arrays.toString(a2));
        }
    }
}
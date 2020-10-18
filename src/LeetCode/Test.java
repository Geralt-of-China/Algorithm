package LeetCode;

import java.util.HashMap;
import java.util.Scanner;

public class Test {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[][] = new int[n + 1][n + 1];
        while (!sc.hasNext("0")) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            a[first][second] = 1;
            a[second][first] = 1;
        }
        int result[] = new int[n + 1];
        test(a, result);
        for (int i = 1; i < result.length; i++) {
            System.out.println(i + " : " + result[i]);
        }
    }

    public static ListNode generate(int[] a) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i :
                a) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void test(int g[][], int a[]) {
        for (int i = 1; i < g.length; i++) {
            if (a[i] == 0) a[i] = i;
            for (int j = 1; j < g[i].length; j++) {
                if (g[i][j] > 0) a[j] = a[i];
            }
        }
    }
}

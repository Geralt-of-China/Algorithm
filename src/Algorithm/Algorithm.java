package Algorithm;

import java.util.*;

public class Algorithm {
    public static void main(String args[]) {
        System.out.println(3 % -2);
    }

    public static void bubbleSort(int a[]) {
        for (int i = 0; i < a.length; i++) {//i等于已经排好序的个数
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    a[j] = a[j] + a[j + 1];
                    a[j + 1] = a[j] - a[j + 1];
                    a[j] = a[j] - a[j + 1];
                }
            }
        }
    }

    public static void insertSort(int a[]) {
        for (int j = 1; j < a.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (a[i] > a[j]) {
                    int temp = a[j];
                    for (int k = j; k > i; k--) a[k] = a[k - 1];
                    a[i] = temp;
                    break;
                }
            }
        }
    }

    public static double poly(int a[], double x) {
        assert a.length > 0;
        int n = a.length - 1;
        double result = a[n];
        for (int i = n - 1; i >= 0; i--) {
            result = result * x + a[i];
        }
        return result;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int target1 = totalLength / 2, target2 = (totalLength - 1) / 2;
        int from1 = 0, to1 = nums1.length, from2 = 0, to2 = nums2.length;
        int result1 = 0, result2 = 0;
        boolean ifresult1 = false, ifresult2 = false;
        for (; !ifresult1 || !ifresult2; ) {
            if (from1 < to1) {
                int p1 = (from1 + to1) / 2;
                int p2 = binarySerach(nums2, nums1[p1]);
                if (p2 < from2) p2 = from2;
                else if (p2 >= to2) p2 = to2;
                int pTotal = p2 + p1;
                if (pTotal == target1) {
                    ifresult1 = true;
                    result1 = nums1[p1];
                } else if (pTotal > target1) {
                    to1 = p1;
                    to2 = p2;
                } else {
                    from1 = p1 + 1;
                    from2 = p2;
                }

                if (pTotal == target2) {
                    ifresult2 = true;
                    result2 = nums1[p1];
                } else if (pTotal > target2) {
                    to1 = p1;
                    to2 = p2;
                } else {
                    from1 = p1 + 1;
                    from2 = p2;
                }
            }

            if (from2 < to2) {
                int p2 = (from2 + to2) / 2;
                int p1 = binarySerach(nums1, nums2[p2]);
                if (p1 < from1) p1 = from1;
                else if (p1 > to1) p1 = to1;
                int pTotal = p2 + p1;

                if (pTotal == target1) {
                    ifresult1 = true;
                    result1 = nums2[p2];
                } else if (pTotal > target1) {
                    to1 = p1;
                    to2 = p2;
                } else {
                    from1 = p1;
                    from2 = p2 + 1;
                }

                if (pTotal == target2) {
                    ifresult2 = true;
                    result2 = nums2[p2];
                } else if (pTotal > target2) {
                    to1 = p1;
                    to2 = p2;
                } else {
                    from1 = p1;
                    from2 = p2 + 1;
                }
            }

        }
        return (double) (result1 + result2) / 2;
    }

    public static int binarySerach(int[] a, int n) {
        int from = 0, to = a.length;
        int mid = (from + to) / 2;
        for (; from < to; mid = (from + to) / 2) {
            if (a[mid] == n) return mid;
            else if (a[mid] < n) from = mid + 1;
            else to = mid;
        }
        return mid;
    }

    static int memoryUsed = 0;

    public static int[] mergesort(int[] a) {
        return mergesortHelper2(a, 0, a.length);
    }

    private static int[] mergesortHelper2(int[] a, int s, int e) {
        if (s > e) throw new IllegalArgumentException("非法输入");
        int result[] = new int[e - s];
        if (s == e) return result;
        else if (e - s == 1) {
            result[0] = a[s];
        } else {
            int mid = (s + e) / 2;
            int[] temp1 = mergesortHelper2(a, s, mid);
            int[] temp2 = mergesortHelper2(a, mid, e);
            memoryUsed += result.length;
            merge(result, temp1, temp2);
        }
        return result;

    }

    private static void merge(int result[], int[] a1, int[] a2) {
        memoryUsed += a1.length + a2.length;
        int p1 = 0, p2 = 0, i = 0;
        for (; p1 < a1.length && p2 < a2.length; i++) {
            if (a1[p1] < a2[p2]) {
                result[i] = a1[p1];
                p1++;
            } else {
                result[i] = a2[p2];
                p2++;
            }
        }
        if (p1 < a1.length) {
            while (p1 < a1.length) {
                result[i] = a1[p1];
                i++;
                p1++;
            }
        } else {
            while (p2 < a2.length) {
                result[i] = a2[p2];
                i++;
                p2++;
            }
        }
    }

    //根据前序遍历和后续遍历重建一个二叉树
    public static Node rebuildNode(int[] preOrder, int[] postOrder, int start1, int start2, int length) {
        if (length <= 0) return null;
        Node node = new Node();
        node.value = preOrder[start1];
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (postOrder[start2 + i] == node.value) {
                index = i;
                break;
            }
        }
        if (index == -1) throw new IllegalArgumentException();
        node.left = rebuildNode(preOrder, postOrder, start1 + 1, start2, index);
        node.right = rebuildNode(preOrder, postOrder, start1 + 1 + index, start2 + index + 1, length - index - 1);
        return node;
    }

    public static int fibonacci(int i) {
        if (i < 2) return i;
        int n1 = 1;
        int n2 = 0;
        int temp;
        for (int j = 2; j <= i; j++) {
            temp = n1;
            n1 = n1 + n2;
            n2 = temp;
        }
        return n1;
    }

    //快速排序
    public static void quickSort(int a[], int start, int end) {
        if (start < end) {
            int i = partition(a, start, end);
            quickSort(a, start, i - 1);
            quickSort(a, i + 1, end);
        }
    }

    //获取第index个顺序统计量
    public static int select(int a[], int start, int end, int index) {
        if (a == null || start < 0 || end >= a.length || start > end || index > end - start + 1 || index < 1)
            throw new IllegalArgumentException();
        return select_recur(a, start, end, index);
    }

    private static int select_recur(int a[], int start, int end, int index) {
        if (start == end) return a[start];
        int i = partition(a, start, end);
        int number = i - start + 1;//获取a[start,i]有多少个数字
        if (number == index) return a[i];
        else if (number < index) return select_recur(a, i + 1, end, index - number);
        else return select_recur(a, start, i - 1, index);
    }

    //对[start,end]进行随机划分，返回划分的index，即a[start,index-1]小于等于a[index],a[index+1,end]的大于等于a[index]
    private static int partition(int a[], int start, int end) {
        //获取一个随机下标,作为划分的值
        int pivotIndex = new Random().nextInt(end - start + 1) + start;
        swap(a, pivotIndex, end);
        int pivot = a[end];
        int i = start - 1;
        //不变式： a[stat,i] < pivot <= a[i+1,j-1]
        for (int j = start; j < end; j++) {
            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, end);
        return i + 1;
    }

    private static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //旋转数组的最小数字
    public static int min(int a[]) {
        if (a.length == 0) throw new IllegalArgumentException("数组不能为空");
        int start = 0;
        int end = a.length - 1;
        int middle;
        while (end > start + 1) {
            middle = (start + end) / 2;
            if (a[middle] < a[end]) end = middle;
            else start = middle;
        }
        return a[end];
    }

    public static int maxProductAfterCutting(int length) {
        int a[] = new int[length + 1];
        a[1] = 1;
        for (int i = 2; i <= length; i++) {
            for (int j = 0; j <= i / 2; j++) {
                int temp;
                if (j == 0) temp = i;
                else temp = a[j] * a[i - j];
                if (a[i] < temp) a[i] = temp;
            }
        }
        return a[length];
    }

    //计算base的exp次方
    public static double power(double base, int exp) {
        //if (base==0)return 0;
        double x = base;
        int flag = Integer.MIN_VALUE;
        boolean isNeg = false;
        if (exp < 0) {
            exp = -exp;
            isNeg = true;
        }
        while ((flag & exp) == 0)
            flag >>>= 1;
        if (flag == 0) return 1;
        for (; flag != 1; ) {
            flag >>>= 1;
            x = x * x;
            if ((flag & exp) == 1) x *= base;
        }
        return isNeg ? 1 / x : x;
    }

    public static void changeOddAndEven(int a[]) {
        int start = 0;
        int end = a.length - 1;
        do {
            while (start < a.length && a[start] % 2 == 1) start++;
            while (end >= 0 && a[end] % 2 == 0) end--;
            if (start < end) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            } else break;
        } while (true);

    }

    //逆转链表
    public static void reverse(List list) {
        Node last = null;
        Node current = list.head;
        while (current != null) {
            list.head = current;
            Node next = current.next;
            current.next = last;
            last = current;
            current = next;
        }
    }

    public static Node merge(Node node1, Node node2) {
        Node dummy = new Node();
        Node current = dummy;
        while (node1 != null && node2 != null) {
            if (node1.value < node2.value) {
                current.next = node1;
                node1 = node1.next;
                current = current.next;
            } else {
                current.next = node2;
                node2 = node2.next;
                current = current.next;
            }
        }
        if (node1 == null) current.next = node2;
        else current.next = node1;
        return dummy.next;
    }

    public static void printMetrix(int a[][], int row, int column, int start) {
        if (row <= 0 || column <= 0) return;
        for (int i = 0; i < column; i++) {
            System.out.print(a[start][start + i] + " ");
        }
        for (int i = 1; i < row; i++) {
            System.out.print(a[start + i][start + column - 1] + " ");
        }
        for (int i = 1; i < column && row > 1; i++) {
            System.out.print(a[start + row - 1][start + column - 1 - i] + " ");
        }
        for (int i = 1; i < row - 1 && column > 1; i++) {
            System.out.print(a[start + row - 1 - i][start] + " ");
        }
        printMetrix(a, row - 2, column - 2, start + 1);
    }

    //判断一个数组是不是二叉搜索树的后序遍历
    public static boolean isPostOrder(int a[], int start, int end) {
        if (start > end) return true;
        int index = start;
        while (a[index] < a[end]) index++;
        int middle = index;//记录分割点
        if (index != end) {
            for (; index < end; index++) {
                if (a[index] < a[end]) return false;
            }
        }
        return isPostOrder(a, start, middle - 1) && isPostOrder(a, middle, end - 1);
    }

    //给定一个sum，输出所有路径上节点和为sum的路径
    public static LinkedList<Integer>[] printPath(Node root, int sum) {
        if (root == null) {
            if (sum == 0) {
                LinkedList[] temp = new LinkedList[1];
                temp[0] = new LinkedList<>();
                return temp;
            } else return new LinkedList[0];
        }
        if (root.value > sum) return new LinkedList[0];
        if (root.left == null && root.right == null && root.value == sum) {
            LinkedList<Integer> temp = new LinkedList<>();
            temp.addFirst(root.value);
            LinkedList[] a = new LinkedList[1];
            a[0] = temp;
            return a;
        }
        LinkedList[] path1 = printPath(root.left, sum - root.value);
        LinkedList[] path2 = printPath(root.right, sum - root.value);
        LinkedList[] totalPath = new LinkedList[path1.length + path2.length];
        int index = 0;
        for (int i = 0; i < path1.length; i++) {
            path1[i].addFirst(root.value);
            totalPath[index++] = path1[i];
        }
        for (int i = 0; i < path2.length; i++) {
            path2[i].addFirst(root.value);
            totalPath[index++] = path2[i];
        }
        return totalPath;
    }

    //给定一棵树，转换成一个双向链表
    public static void changeTreeToLinkList(Node node) {
        if (node == null) return;
        changeTreeToLinkList(node.left);
        changeTreeToLinkList(node.right);
        Node temp = node.left;
        Node pre = null;
        while (temp != null) {
            pre = temp;
            temp = temp.right;
        }
        if (pre != null) {
            pre.right = node;
            node.left = pre;
        }
        temp = node.right;
        Node post = null;
        while (temp != null) {
            post = temp;
            temp = temp.left;
        }
        if (post != null) {
            post.left = node;
            node.right = post;
        }
    }

    //获取子数组的最大和
    public static int getMaxSubsequenceSum(int a[], int start, int end) {
        if (start > end) throw new IllegalArgumentException();
        if (start == end) return a[start];
        int middle = (start + end) / 2;//middle<end,middle+1<=end
        int left = getMaxSubsequenceSum(a, start, middle);
        int right = getMaxSubsequenceSum(a, middle + 1, end);
        int rightMax = 0;
        int sum = 0;
        for (int i = middle + 1; i <= end; i++) {
            sum += a[i];
            if (sum > rightMax) rightMax = sum;
        }
        int leftMax = 0;
        sum = 0;
        for (int i = middle; i >= 0; i--) {
            sum += a[i];
            if (sum > leftMax) leftMax = sum;
        }
        int middleSum = leftMax + rightMax;
        int max = left;
        if (left < right) max = right;
        if (max < middleSum) max = middleSum;
        return max;
    }

    public static int getMaxSubsequenceSum(int a[]) {
        int max = 0;
        int currentSum = 0;
        for (int i :
                a) {
            if (i >= 0) {
                currentSum += i;
            } else {
                if (currentSum > max) max = currentSum;
                currentSum += i;
                if (currentSum < 0) currentSum = 0;
            }
        }
        if (currentSum > max) max = currentSum;
        return max;
    }

    public static String getMaxSubstringWithoutDuplication(String str) {
        String maxStr = "";
        StringBuilder currentStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            String a = Character.toString(str.charAt(i));
            int indexOfA = currentStr.indexOf(a);
            if (indexOfA >= 0) {
                if (currentStr.length() > maxStr.length()) maxStr = currentStr.toString();
                currentStr = currentStr.delete(0, indexOfA + 1);
            }
            currentStr.append(a);
        }
        if (currentStr.length() > maxStr.length()) maxStr = currentStr.toString();
        return maxStr;
    }

    //获取i在一个排序数组a中的出现次数
    public static int getTimes(int a[], int i) {
        int start = 0;
        int end = a.length - 1;
        int first = 0;
        int last = first - 1;
        //找first
        while (start <= end) {
            int middle = (start + end) / 2;
            if (a[middle] == i) {
                if (middle == 0 || a[middle - 1] < i) {
                    first = middle;
                    break;
                } else end = middle - 1;
            } else if (a[middle] < i) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        start = 0;
        end = a.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (a[middle] == i) {
                if (middle == a.length - 1 || a[middle + 1] > i) {
                    last = middle;
                    break;
                } else start = middle + 1;
            } else if (a[middle] < i) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return last - first + 1;
    }

    //获得一个二叉搜索树的第k个节点
    public static int getKthNode(Node root, int k) {
        Algorithm.k = k;
        Node res = InorderTraverse(root);
        if (res == null) throw new IllegalArgumentException();
        return res.value;
    }

    static int k;

    private static Node InorderTraverse(Node node) {
        if (node == null) return null;
        Node result = InorderTraverse(node.left);
        if (result != null) return result;
        if (k == 1) return node;
        k--;
        return InorderTraverse(node.right);
    }

    public static int getMaxProfit(int a[]) {
        if (a == null) throw new IllegalArgumentException();
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) min = a[i];
            else if (a[i] - min > max) max = a[i] - min;
        }
        return max;
    }

    //先序遍历的迭代
    public static void preorderByIteration(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {//current为空且stack为空时停止循环
            if (current != null) {
                visit(current);
                if (current.right != null) stack.push(current.right);
                current = current.left;
            } else {
                current = stack.pop();
            }
        }
    }

    public static void preorderByIteration2(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            visit(node);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    //中序遍历的迭代
    public static void inorderByIteration(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            Node temp = stack.pop();
            visit(temp);
            current = temp.right;
        }
    }

    //后续遍历的迭代
    public static void postorderByIteration(Node root) {
        Stack<Node> stack = new Stack<>();
        Node pre = null;//前一个访问的节点
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            if (node.left == null && node.right == null ||
                    pre != null && (pre == node.left || pre == node.right)) {
                visit(node);
                pre = node;
                stack.pop();
            } else {
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
    }

    public static void visit(Node node) {
        System.out.print(node.value + " ");
    }

    //最小编辑问题，将a变为b的最小编辑次数
    public static int change(String a, String b) {
        int[][] m = new int[a.length() + 1][b.length() + 1];//mij表示把a的前i个字符转换为b的前j个字符需要的最少次数
        for (int i = 1; i < a.length() + 1; i++) {
            m[i][0] = i;
        }
        for (int i = 1; i < b.length() + 1; i++) {
            m[0][i] = i;
        }
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) m[i + 1][j + 1] = m[i][j];
                else {
                    int min = m[i][j];
                    if (m[i + 1][j] < min) min = m[i + 1][j];
                    if (m[i][j + 1] < min) min = m[i][j + 1];
                    m[i + 1][j + 1] = min + 1;
                }
            }
        }
        return m[a.length()][b.length()];
    }

    public static void getExpectation() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[n];
        int max = a[a.length - 1];//最大的xi值
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            if (max < a[i]) max = a[i];
        }
        double pro = 0;//最大值小于i的概率
        double e = 0;
        for (int i = 1; i <= max; i++) {
            double pro2 = 1;//小于等于i的概率
            for (int j = 0; j < a.length; j++) {
                if (a[j] > i) {
                    pro2 *= (double) i / a[j];
                }
            }
            e += (pro2 - pro) * i;
            pro = pro2;
        }
        System.out.printf("%.2f", e);
    }


    //原址版版本的mergesort,空间复杂度为O(n)
    public static void mergesortOnSame(int a[]) {
        if (a == null) return;
        int temp[] = new int[a.length];
        mergesortHelper(a, temp, 0, a.length - 1);
    }

    //将a[s]到a[e]排序,temp为辅助数组
    private static void mergesortHelper(int a[], int temp[], int s, int e) {//[s  e]
        assert (s <= e);
        if (s == e) return;
        //保证s<e
        int m = (s + e) / 2;//s<e -> s<=m<e
        mergesortHelper(a, temp, s, m);
        mergesortHelper(a, temp, m + 1, e);
        for (int i = s; i <= m; i++)
            temp[i] = a[i];
        for (int j = 0; j < e - m; j++) {
            temp[e - j] = a[m + 1 + j];
        }
        for (int i = s, l = s, h = e; i < e; i++) {
            if (temp[l] < temp[h]) a[i] = temp[l++];
            else a[i] = temp[h--];
        }
    }


    public static void quicksort2(int a[]) {
        quickSort2Helper(a, 0, a.length - 1);
    }

    private static void quickSort2Helper(int a[], int s, int e) {
        if (s >= e) return;
        //若需要随机化把下列代码解封
        //int k=new Random().nextInt(e-s+1)+s;
        //swap(a,k,e);
        int i = partition2(a, s, e);
        quickSort2Helper(a, s, i - 1);
        quickSort2Helper(a, i + 1, e);
    }

    //新的划分函数,将[s,e]依据value=a[e]进行划分
    //返回k满足[s,k-1]小于value,[k+1,e]大于等于value,a[k]等于value
    private static int partition2(int a[], int s, int e) {
        assert (s <= e);
        int l = s - 1;
        int h = e;
        int value = a[e];
        //开始时l<h,且a[h]>=value
        do {
            while (a[++l] < value) ;//循环结束时可以保证l<=h
            while (l < h && a[--h] >= value) ;//若l==h则直接结束,结束时也保证l<=h
            swap(a, l, h);
        } while (l < h);//结束时l==h
        swap(a, l, e);
        return l;
    }

    //输入一个数组，数组的值介于[0,n)之间
    public static void countSort(int a[], int result[], int n) {
        int temp[] = new int[n];
        for (int i :
                a) {
            temp[i]++;
        }
        int index = 0;
        for (int i = 1; i < temp.length; i++) {
            temp[i] = temp[i] + temp[i - 1];
        }
        //此时temp[i]表示i在结果数组中应该放的位置
        for (int i = 0; i < a.length; i++) {
            result[--temp[a[i]]] = a[i];
        }

    }

    //k表示a里的数字最多少位，可以理解为a里每个数位a(k-1)....a(1)a(0)
    private static void radixSort(int a[], int result[], int k) {
        int radix = 10;
        int count[] = new int[radix];//默认以10位底

        for (int i = 0, base = 1; i < k; i++, base *= radix) {//从0位到k-1位
            for (int j = 0; j < count.length; j++) {
                count[j] = 0;
            }
            for (int j = 0; j < a.length; j++) {
                count[(a[j] / base) % radix]++;
            }
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }
            for (int j = a.length - 1; j >= 0; j--) {
                result[--count[(a[j] / base) % radix]] = a[j];
            }
            for (int j = 0; j < a.length; j++) {
                a[j] = result[j];
            }
        }
    }

    //[0]m+[1]n==[2],[2]为最大公约数
    public static int[] extendEuclid(int m, int n) {
        int ac = 1, bc = 0, ad = 0, bd = 1, c = m, d = n;//q为商，r为余数
        //满足ac*m+bc*n==c;
        //ad*m+bd*n=d
        for (int q = c / d, r = c % d; r != 0; q = c / d, r = c % d) {
            c = d;
            d = r;
            int temp = ac;
            ac = ad;
            ad = temp - q * ad;
            temp = bc;
            bc = bd;
            bd = temp - q * bd;
        }
        int result[] = new int[3];
        result[0] = ad;
        result[1] = bd;
        result[2] = d;
        return result;
    }
}

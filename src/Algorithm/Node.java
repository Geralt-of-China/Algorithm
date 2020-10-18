package Algorithm;

public class Node implements Comparable<Node> {
    final String a = "why123";

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int value;
    public Node next;
    public Node left;
    public Node right;

    @Override
    public int compareTo(Node o) {

        return this.value - o.value;
    }
}
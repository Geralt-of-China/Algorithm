package LeetCode;

//leet code problem 25: Reverse Nodes in k-Group
public class P25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) return head;
        ListNode[] a = new ListNode[k];
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int i = 0; ; i++) {
            if (head == null) {
                if (i == 0)
                    current.next = null;
                else current.next = a[0];
                break;
            }
            a[i] = head;
            head = head.next;
            if (i == k - 1) {
                for (int j = k - 1; j >= 0; j--) {
                    current.next = a[j];
                    current = current.next;
                }
                i = -1;
            }
        }
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

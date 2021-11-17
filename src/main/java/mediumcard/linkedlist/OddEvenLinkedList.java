package mediumcard.linkedlist;


public class OddEvenLinkedList {
    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Accepted in one go
    //70 / 70 test cases passed.
    //Status: Accepted
    //Runtime: 0 ms
    //Memory Usage: 38.2 MB
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;
        ListNode l1 = head;
        ListNode l2 = l1.next;

        ListNode l5 = head;
        int count = 1;
        while (l5.next != null) {
            l5 = l5.next;
            count++;
        }
        int p = count / 2;
        int z = 0;
        while (p != z) {
            l1.next = l2.next;
            l5.next = l2;
            l1 = l1.next;
            l2 = l1.next;
            l5 = l5.next;
            l5.next = null;
            z++;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l4 = new ListNode(1);
        l4.next = new ListNode(2);
        l4.next.next = new ListNode(3);
        l4.next.next.next = new ListNode(4);
        l4.next.next.next.next = new ListNode(5);

        ListNode l9 = oddEvenList(l4);
        while (l9 != null) {
            System.out.println(l9.val);
        }
    }
}

package easycard.linkedlist;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LinkedListOperations {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int k = 0;
        ListNode h1 = head;
        ListNode h2 = head;
        while (h1 != null) {
            h1 = h1.next;
            k++;
        }
        if (k == 1) {
            head = null;
            return head;
        }
        int j = 0;
        while (h2 != null) {
            if (j == (k - n))
                break;
            if (n == 1 && j == (k - n - 1))
                break;
            h2 = h2.next;
            j++;
        }
        if (n == 1) {
            h2.next = null;
        } else {
            h2.val = h2.next.val;
            h2.next = h2.next.next;
        }
        return head;
    }

    public static ListNode reverseList(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode n1 = new ListNode(head.val);
        n1.next = null;
        ListNode second = head.next;
        while (second != null) {
            ListNode n2 = new ListNode(second.val);
            n2.next = n1;
            n1 = n2;
            second = second.next;
        }
        return n1;
    }

    public static ListNode reverseListRecursive(ListNode head) {
        ListNode n1 = new ListNode(head.val);
        n1.next = null;
        return reverseListRecursiveR(head, n1);
    }

    public static ListNode reverseListRecursiveR(ListNode head, ListNode n3) {
        if (head == null || head.next == null) {
            return n3;
        }
        ListNode n2 = new ListNode(head.next.val);
        n2.next = n3;
        head = head.next;
        return reverseListRecursiveR(head, n2);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return l1;
        }
        if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;

        ListNode n1 = new ListNode(0);
        ListNode head = n1;
        if (l1.val <= l2.val) {
            n1.val = l1.val;
            l1 = l1.next;

        } else {
            n1.val = l2.val;
            l2 = l2.next;
        }
        while (l1 != null && l2 != null) {
            int value;
            if (l1.val <= l2.val) {
                value = l1.val;
                l1 = l1.next;
            } else {
                value = l2.val;
                l2 = l2.next;
            }
            ListNode n2 = new ListNode(value);
            n1.next = n2;
            n1 = n2;
        }
        if (l1 != null) {
            while (l1 != null) {
                ListNode n2 = new ListNode(l1.val);
                n1.next = n2;
                n1 = n2;
                l1 = l1.next;
            }
        }
        if (l2 != null) {
            while (l2 != null) {
                ListNode n2 = new ListNode(l2.val);
                n1.next = n2;
                n1 = n2;
                l2 = l2.next;
            }
        }
        return head;
    }

    public static ListNode mergeTwoListsR(ListNode l1, ListNode l2) {
        ListNode l3 = null;
        ListNode l4 = null;
        return mergeR(l1, l2, l3, l4);
        //return l4;
    }

    public static ListNode mergeR(ListNode l1, ListNode l2, ListNode l3, ListNode l4) {
        ListNode t = null;
        if (l1 == null && l2 == null) {
            return l4;
        } else if (l1 == null && l2 != null) {
            t = new ListNode(l2.val);
            l2 = l2.next;
        } else if (l2 == null && l1 != null) {
            t = new ListNode(l1.val);
            l1 = l1.next;
        } else if (l1.val <= l2.val) {
            t = new ListNode(l1.val);
            l1 = l1.next;
        } else if (l1.val > l2.val) {
            t = new ListNode(l2.val);
            l2 = l2.next;
        }
        if (l3 == null) {
            l3 = t;
            l4 = l3;
        } else {
            l3.next = t;
            l3 = l3.next;
        }
        return mergeR(l1, l2, l3, l4);

    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode h1 = head;
        ListNode h2 = head;
        ListNode h3 = head;
        // Count number of nodes
        int c = 0;
        while (h1 != null) {
            h1 = h1.next;
            c++;
        }
        // Reverse second Half of it.
        int mid = c % 2 == 0 ? c / 2 : c / 2 + 1;
        int k = 0;
        while (k < mid) {
            h2 = h2.next;
            k++;
        }
        ListNode n1 = new ListNode(h2.val);
        n1.next = null;
        ListNode second = h2.next;
        while (second != null) {
            ListNode n2 = new ListNode(second.val);
            n2.next = n1;
            n1 = n2;
            second = second.next;
        }
        // Compare each of node
        // Reverse Second half of it again
        int p = 0;
        while (p < mid && n1 != null && h3 != null) {
            if (n1.val != h3.val)
                return false;
            n1 = n1.next;
            h3 = h3.next;
            p++;
        }
        return true;
    }

    public static ListNode swapPairs(ListNode head) {
        return swapPairsR(head, head);
    }

    public static ListNode swapPairsR(ListNode realHead, ListNode head) {
        if (head == null || head.next == null)
            return realHead;
        ListNode n1 = new ListNode(head.val);
        ListNode n2 = new ListNode(head.next.val);
        head.val = n2.val;
        head.next.val = n1.val;
        head = head.next.next;
        return swapPairsR(realHead, head);
    }

    public static void main(String[] args) {
        ListNode l4 = new ListNode(1);
        l4.next = new ListNode(2);
        l4.next.next = new ListNode(3);
        l4.next.next.next = new ListNode(4);

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
//        l1.next.next.next = new ListNode(-1);
//        l1.next.next.next.next = new ListNode(4);
//        l1.next.next.next.next.next = new ListNode(1);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
//        l2.next.next.next = new ListNode(9);
//        l2.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next = new ListNode(9);
        /*ListNode l3 = removeNthFromEnd(l1, 4);*/
        /*ListNode l3 = reverseList(l1);*/
        //System.out.println(isPalindrome(l4));
        ListNode l3 = mergeTwoListsR(l1, l2);
        //reverseListRecursive(l4);
        while (l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }
    }
}

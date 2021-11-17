package mediumcard.linkedlist;

public class AddTwoNumbers {


    //Definition for singly-linked list.
    public class ListNode {
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


    // Solved in 1 Go
    // 1568 / 1568 test cases passed.
    //Status: Accepted
    //Runtime: 2 ms
    //Memory Usage: 38.9 MB
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;
        int val = l1.val + l2.val;
        int carry = val >= 10 ? val / 10 : 0;
        int finalval = val >= 10 ? val % 10 : val;
        ListNode l3 = new ListNode(finalval);
        l1 = l1.next;
        l2 = l2.next;
        ListNode l4 = l3;
        while (l1 != null && l2 != null) {
            val = l1.val + l2.val + carry;
            carry = val >= 10 ? val / 10 : 0;
            finalval = val >= 10 ? val % 10 : val;
            l4.next = new ListNode(finalval);
            l4 = l4.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                val = l1.val + carry;
                carry = val >= 10 ? val / 10 : 0;
                finalval = val >= 10 ? val % 10 : val;
                l4.next = new ListNode(finalval);
                l4 = l4.next;
                l1 = l1.next;
            }
        }
        if (l2 != null) {
            while (l2 != null) {
                val = l2.val + carry;
                carry = val >= 10 ? val / 10 : 0;
                finalval = val >= 10 ? val % 10 : val;
                l4.next = new ListNode(finalval);
                l4 = l4.next;
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            l4.next = new ListNode(carry);
        }
        return l3;
    }
}

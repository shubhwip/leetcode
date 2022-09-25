package medium.design;


// Accepted in one go without using IDE
// Pat on my back
class CircularQueue {

    class CircularLinkedList {
        CircularLinkedList next;
        int value;

        CircularLinkedList(int value) {
            this.value = value;
        }
    }

    CircularLinkedList head;
    CircularLinkedList tail;
    int size;
    int currSize;

    public CircularQueue(int k) {
        this.size = k;
        this.currSize = 0;
    }

    public boolean enQueue(int value) {
        if(currSize + 1 > size)
            return false;
        if(currSize == 0) {
            this.head = new CircularLinkedList(value);
            this.tail = this.head;
            this.head.next = this.tail;
            this.tail.next = this.head;
        } else {
            CircularLinkedList node = new CircularLinkedList(value);
            this.tail.next = node;
            node.next = head;
            this.tail = node;
        }
        currSize++;
        return true;
    }

    public boolean deQueue() {
        if(this.isEmpty())
            return false;
        CircularLinkedList temp = head;
        head = temp.next;
        tail.next = head;
        temp = null;
        currSize--;
        return true;

    }

    public int Front() {
        if(this.isEmpty())
            return -1;
        return head.value;

    }

    public int Rear() {
        if(this.isEmpty())
            return -1;
        return tail.value;
    }

    public boolean isEmpty() {
        return currSize == 0 ? true: false;
    }

    public boolean isFull() {
        return currSize == size ? true: false;
    }

    public int size() {
        return currSize;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

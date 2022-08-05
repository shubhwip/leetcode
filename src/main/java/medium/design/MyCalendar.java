import java.util.List;
import java.util.TreeMap;

class MyCalendar {
    static class TreeNode {

        public int start, end;
        public TreeNode left, right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    TreeNode root;
    boolean inserted;

    public MyCalendar() {
        this.root = null;
    }

    public boolean book(int start, int end) {
        this.inserted = false;
        this.root = insert(this.root, start, end);
        return this.inserted;
    }

    public TreeNode insert(TreeNode curr, int start, int end) {
        if (curr == null) {
            this.inserted = true;
            return new TreeNode(start, end);
        }

        if (start >= curr.end)
            curr.right = insert(curr.right, start, end);
        else if (end <= curr.start)
            curr.left = insert(curr.left, start, end);
        return curr;
    }
}

class MyCalendar1 {

    TreeMap<Integer, Integer> map;
    public MyCalendar1() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer low = map.lowerKey(end);

        if(low == null || map.get(low) <= start) {
            map.put(start, end);
            return true;
        }
        return false;
    }

    List<Booking> bookings;

    // public MyCalendar1() {
    //     bookings = new ArrayList<>(); 
    // }

    public boolean book1(int start, int end) {
        if(bookings.isEmpty()) {
            bookings.add(new Booking(start, end));
            return true;
        }
        for(Booking b : bookings) {
            if((b.start < start && start < b.end) || (b.start < end && end < b.end)) {
                return false;
            }
            if((start <= b.start && end >= b.end) || (end == b.end)) {
                return false;
            }
        }
        bookings.add(new Booking(start, end));
        return true;
    }

    static class Booking {
        public int start;
        public int end;

        public Booking(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
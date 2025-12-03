class MyLinkedList {

    class LinkedList {
        int val;
        LinkedList next;

        public LinkedList() {
        }

        public LinkedList(int val) {
            this.val = val;
        }
    }

    LinkedList head;
    LinkedList tail;

    public MyLinkedList() {
        head = new LinkedList(); // head(empty) -> nodes -> tail(empty)
        tail = new LinkedList();
        head.next = tail;
    }

    public int get(int index) {
        LinkedList dummyHead = head.next;
        for (int i = 0; i < index; i++) {
            if (dummyHead == null) {
                return -1;
            }
            dummyHead = dummyHead.next;
        }
        if (dummyHead == null || dummyHead == tail) {
            return -1;
        }
        return dummyHead.val;
    }

    public void addAtHead(int val) {
        LinkedList dummyHead = new LinkedList(val);
        LinkedList temp = head.next;
        head.next = dummyHead;
        dummyHead.next = temp;
    }

    public void addAtTail(int val) {
        tail.val = val;
        tail.next = new LinkedList();
        tail = tail.next;
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        LinkedList dummyHead = head.next;

        for (int i = 0; i < index - 1; i++) {

            if (dummyHead.next == null || dummyHead.next == tail) {
                return;
            }
            dummyHead = dummyHead.next;
        }
        if (dummyHead == null) {
            return;
        }
        LinkedList temp = dummyHead.next;
        dummyHead.next = new LinkedList(val);
        dummyHead.next.next = temp;
    }

    public void deleteAtIndex(int index) {
        if (index == 0 && head.next != tail) {
            head.next = head.next.next;
            return;
        }

        LinkedList dummyHead = head;
        for (int i = 0; i < index; i++) {
            if (dummyHead.next == null || dummyHead.next == tail) {
                return;
            }
            dummyHead = dummyHead.next;
        }
        if (dummyHead == null || dummyHead.next == tail) {
            return;
        }
        dummyHead.next = dummyHead.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
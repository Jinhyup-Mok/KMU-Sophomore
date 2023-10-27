package ds_hw1.list;

public class DoublyLinkedList<E>  implements List<E>{

    public DoublyLinkedList<E> head;
    public DoublyLinkedList<E> tail;
    int size;
    private E item;
    private DoublyLinkedList<E> next;
    private DoublyLinkedList<E> prev;
    public DoublyLinkedList(E item, DoublyLinkedList<E> prev, DoublyLinkedList<E> next) {
        this.item = item; this.prev = prev; this.next = next;
    }
    public DoublyLinkedList() {
        head = new DoublyLinkedList<E>(null, null, tail);
        tail = new DoublyLinkedList<E>(null, head, null);
        size = 0;
    }
    DoublyLinkedList<E> next() {return next;}
    DoublyLinkedList<E> setNext(DoublyLinkedList<E> next) {return this.next = next;}
    DoublyLinkedList<E> prev() {return prev;}
    DoublyLinkedList<E> setPrev(DoublyLinkedList<E> prev) {return this.prev = prev;}
    E item() {return item;}
    E setItem(E item) {return this.item = item;}

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void insert(int pos, E item) {
        DoublyLinkedList<E> curr = head;
        for(int i=0; i<pos; i++) {
            curr = curr.next();
        }
        curr.setNext(new DoublyLinkedList<E>(item, curr, curr.next()));
        curr.next().next().setPrev(curr.next());
        size++;
    }

    @Override
    public void append(E item) {
        DoublyLinkedList<E> n = new DoublyLinkedList<E>(item, tail.prev, tail);
        tail.prev.next = n;
        tail.prev = n;
        size++;
    }

    @Override
    public void update(int pos, E item) {
        DoublyLinkedList<E> curr = head;
        for(int i=0; i<pos; i++) {
            curr = curr.next;
        }
        curr.next.item = item;
    }

    @Override
    public E getValue(int pos) {
        DoublyLinkedList<E> curr = head;
        for(int i=0; i<pos; i++) {
            curr = curr.next;
        }
        return curr.next.item;
    }

    public E remove(int pos) {
        DoublyLinkedList<E> curr = head;
        for(int i=0; i<pos; i++) {
            curr = curr.next();
        }
        E ret = curr.next().item();
        curr.next().next().setPrev(curr);
        curr.setNext(curr.next().next());
        size--;
        return ret;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }
    class LinkedListIterator implements ListIterator<E> {
        DoublyLinkedList curr = head;

        public boolean hasNext() {
            return curr.next != tail;
        }
        public E next() {
            curr = curr.next;
            return (E) curr.item;
        }
        public boolean hasPrevious() {
            return curr != head;
        }
        public E previous() {
            curr = curr.prev;
            return (E) curr.next.item;
        }
    }
    public void printall(DoublyLinkedList<E> myList) {
        System.out.print("Print myList : ");
        for(int j=0; j<myList.length(); j++) {
            System.out.print(myList.getValue(j) + " ");
        } System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> myList = new DoublyLinkedList<>();
        myList.append(1);
        System.out.println("Append Item : " + myList.getValue(0) + " ");
        myList.append(3);
        System.out.println("Append Item : " + myList.getValue(1) + " ");
        myList.insert(1, 2);
        System.out.print("After Append and Insert : ");
        myList.printall(myList);
        myList.append(10);
        myList.append(9);
        myList.append(5);
        myList.printall(myList);
        myList.update(3, -1);
        System.out.print("After Update pos_3 to -1 : ");
        myList.printall(myList);
        for(int i=6; i<30; i++) {
            myList.append(i);
        }
        myList.printall(myList);
        System.out.println("Before Remove_Length : " + myList.length());
        myList.remove(4);
        myList.remove(10);
        myList.printall(myList);
        System.out.println("After Remove_Length : " + myList.length());
        myList.insert(18, -1);
        System.out.print("After Insert -1 in pos_18 : ");
        myList.printall(myList);
        myList.clear();
        System.out.println("Clear Result : " + myList.length());
        myList.printall(myList);
    }
}


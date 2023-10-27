package ds_hw1.list;

public class LinkedList<E> implements List<E>{

    public Link<E> head, tail;
    int size;

    public LinkedList(){
        tail = head = new Link<>(null, null); // LinkedList 이용
//        tail = head = Link.get(null, null); // freelist 이용 코드
        size = 0;
    }

    public void clear() {
        head.next = null;
        tail = head;
        size = 0;
    }
    public void insert(int pos, E item) {
        Link<E> curr = head;
        for (int i = 0; i < pos; i++)
            curr = curr.next;
        Link<E> n = new Link<>(item, curr.next);
//        Link<E> n = Link.get(item, curr.next); // freelist 이용 코드
        curr.next = n;
//      -> curr.next = new Link(item, curr.next);

        if (curr == tail) tail = curr.next;

        size++;
    }

    public void append(E item) {
        Link<E> n = new Link<>(item, null);
//        Link<E> n = Link.get(item, null); // freelist 이용 코드
        tail.next = n;
        tail = n;
        size++;
    }

    public void update(int pos, E item) {
        Link<E> curr = head;
        for (int i = 0; i < pos; i++)
            curr = curr.next;
        curr.next.item = item;
    }

    public E getValue(int pos) {
        Link<E> curr = head;
        for (int i = 0; i < pos; i++)
            curr = curr.next;
        return curr.next.item;
    }

    public E remove(int pos) {
        Link<E> curr = head;
        for (int i = 0; i < pos; i++)
            curr = curr.next;

        E ret = curr.next.item;

        if (curr.next == tail)
            tail = curr;

//        Link<E> tmp = curr.next; // freelist 이용 코드
        curr.next = curr.next.next;
//        tmp.release(); // freelist 이용 코드

        size--;

        return ret;
    }

    public int length() {
        return size;
    }

    public String toString() {
        Link<E> curr = head;
        String a = "";
        for(int i = 0; i < size; i++) {
            a += curr.next.item + ", ";
            curr = curr.next;
        }
        return a;
    }

    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            Link<E> curr = head;

            @Override
            public boolean hasNext() {
                return curr != tail;
            }

            @Override
            public E next() {
                curr = curr.next;
                return curr.item;
            }

            @Override
            public boolean hasPrevious() {
                return curr != head;
            }

            @Override
            public E previous() {
                Link<E> tmp = head;
                while (tmp.next != curr) {
                    tmp = tmp.next;
                }
                curr = tmp;
                return curr.next.item;
            }
        };
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Integer> myList = new LinkedList<>();

        myList.append(7);
        System.out.println(myList);
        myList.insert(0, 10);
        System.out.println(myList);
        myList.insert(0, 12);
        System.out.println(myList);
        myList.append(9);
        System.out.println(myList);
        myList.remove(2);
        System.out.println(myList);
        myList.append(20);
        System.out.println(myList);
        myList.insert(3, 17);
        System.out.println(myList);
        myList.append(117);
        System.out.println(myList);
        myList.remove(1);
        System.out.println(myList);
        myList.clear();
        System.out.println("[" +myList + "]");

        for(int i=0; i<200000; i++) {
            myList.append(i);
        }
        for(int i=0; i<myList.length(); i++) {
            myList.remove(i);
        }

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("코드 실행 시간: %20dms", endTime - startTime));
    }
}

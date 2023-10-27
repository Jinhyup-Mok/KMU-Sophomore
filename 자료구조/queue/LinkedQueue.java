package ds_hw2.queue;

public class LinkedQueue<E> implements Queue<E> {

    Link<E> front, rear;
    int size;

    public LinkedQueue() {
        front = rear = new Link<>(null, null);
        size = 0;
    }
    @Override
    public void clear() {
        front.next = null;
        rear = front;
        size = 0;
    }

    @Override
    public void enqueue(E it) {
        Link<E> n = new Link<>(it, null);
        rear.next = n;
        rear = n;
        size++;

    }

    @Override
    public E dequeue() {
        E ret = front.next.item;
        front.next = front.next.next;
        size--;
        return ret;
    }

    @Override
    public E frontValue() {
        return front.next.item;
    }

    @Override
    public int length() {
        return size;
    }

    public static void main(String[] args) {
        Queue<String> myLQueue = new LinkedQueue<>();

        myLQueue.enqueue("qFirst");
        myLQueue.enqueue("qSecond");
        System.out.println("After enqueue FrontValue : " + myLQueue.frontValue());
        System.out.println("length : " + myLQueue.length());
        System.out.println("Dequeue Item : " + myLQueue.dequeue());
        System.out.println("After dequeue FrontValue : " + myLQueue.frontValue());
        myLQueue.enqueue("qThird");
        myLQueue.enqueue("qFourth");
        myLQueue.enqueue("qFifth");
        myLQueue.enqueue("qSixth");
        myLQueue.enqueue("qSeventh");
        System.out.println("length : " + myLQueue.length());
        System.out.println("After enqueue FrontValue : " + myLQueue.frontValue());
        System.out.println("Dequeue Item : " + myLQueue.dequeue());
        System.out.println("Dequeue Item : " + myLQueue.dequeue());
        System.out.println("Dequeue Item : " + myLQueue.dequeue());
        System.out.println("length : " + myLQueue.length());
        System.out.println("Top FrontValue : " + myLQueue.frontValue());
        myLQueue.clear();
        System.out.println("Clear FrontValue : " + myLQueue.length());

    }
}

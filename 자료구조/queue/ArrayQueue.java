package ds_hw2.queue;

public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] arr_queue;    // 요소를 담을 배열
    private int size;    // 요소 개수

    private int front;
    private int rear;


    // 생성자1 (초기 용적 할당을 안할 경우)
    public ArrayQueue() {
        this.arr_queue = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = DEFAULT_CAPACITY;
    }

    // 생성자2 (초기 용적 할당을 할 경우)
    public ArrayQueue(int capacity) {
        this.arr_queue = (E[]) new Object[capacity];
        this.size = capacity;
    }
    @Override
    public void clear() {
        for(int i = 0; i < arr_queue.length; i++) {
            arr_queue[i] = null;
        }
        front = rear = size = 0;
    }

    @Override
    public void enqueue(E it) {
        arr_queue[rear++] = it;
        rear = rear % size;
    }

    @Override
    public E dequeue() {
        E dequeue_data = arr_queue[front];
        arr_queue[front++] = null;
        front = front % arr_queue.length;

        return dequeue_data;
    }

    @Override
    public E frontValue() {return arr_queue[front];}
    @Override
    public int length() {
        if(rear > front)
            return rear - front;
        return size - front + rear;
    }

    public static void main(String[] args) {
        Queue<String> myAQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue length : " + myAQueue.length());
        myAQueue.enqueue("ArrayQueue Start!!");
        System.out.println("After Enqueue FrontValue: " + myAQueue.frontValue());
        myAQueue.enqueue("Test Dequeue");
        System.out.println("Dequeue item : " + myAQueue.dequeue());
        System.out.println("After Dequeue Front : " + myAQueue.frontValue());
        myAQueue.enqueue("frist");
        myAQueue.enqueue("second");
        myAQueue.enqueue("third");
        System.out.println("After Enqueue Front : " + myAQueue.frontValue());
        System.out.println("--- Test Circular Queue ---");
        for(int i=100; i<116; i++) {
            String ch = Character.toString(i);
            myAQueue.enqueue(ch);
        }
        System.out.println("Deque item : " + myAQueue.dequeue());
        myAQueue.enqueue("Last enqueue");
        System.out.println("Front Value : " + myAQueue.frontValue());
    }
}

package ds_hw2.stack;

public class ArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_SIZE = 10;
    private int maxSize;
    private int top;
    private E[] arr_stack;

    public ArrayStack() {
        this(DEFAULT_SIZE);
    }

    public ArrayStack(int size) {
        maxSize = size;
        top = -1;
        arr_stack = (E[]) new Object[maxSize];
    }
    @Override
    public void clear() {
        top = -1;
    }

    @Override
    public void push(E it) {
        arr_stack[++top] = it;

    }

    @Override
    public E pop() {
        return arr_stack[top--];
    }

    @Override
    public E topValue() {
        return arr_stack[top];
    }

    @Override
    public int length() {
        return maxSize;
    }

    public static void main(String[] args) {
        Stack<String> myAStack = new ArrayStack<>(20);
        System.out.println(myAStack.length());
        myAStack.push("a");
        myAStack.push("b");
        myAStack.push("c");
        System.out.println("Before Pop Top : " + myAStack.topValue());
        System.out.println("Pop Item : " + myAStack.pop());
        System.out.println("After Pop Top : " + myAStack.topValue());
        System.out.println("--- Push d to s ---");
        for(int i=100; i<116; i++) { // 아스키코드를 이용하여 문자 집어넣기
            String ch = Character.toString(i);
            myAStack.push(ch);
        }
        System.out.println("After Push Top : " + myAStack.topValue());
        for(int j=0; j<3; j++) {
            System.out.println("Pop Item : " + myAStack.pop());
        }
        System.out.println("After Pop Top : " + myAStack.topValue());

        myAStack.clear();
        myAStack.push("Clear Top");
        System.out.println("After Clear Top : " + myAStack.topValue());
    }

}

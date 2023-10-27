package ds_hw2.stack;
import java.util.EmptyStackException;

public class LinkedStack<E> implements Stack<E> {
    private static class Link<E> {
        private E it;
        public Link<E> next;
        Link(E it, Link<E> next){
            this.it = it; this.next=next;
        }
        E it(){return it;}
    }
    private Link<E> top;
    private int size;

    public LinkedStack(){
        top=null;
        size=0;
    }
    public void clear(){
        top=null;
        size=0;
    }
    public void push(E it){
        Link<E> i = new Link(it,top);
        top = i;
        size++;
    }
    public E pop(){
        if(size==0){
            throw new EmptyStackException();
        }else{
            E ret = top.it();
            size--;
            return ret;
        }

    }
    public E topValue(){
        if(size==0){
            throw new EmptyStackException();
        }else{
            return top.it();
        }
    }
    public int length(){
        return size;
    }

    public static void main(String[] args) {
        Stack<String> myLStack = new LinkedStack<>();
        myLStack.push("first");
        System.out.println("After Push length : " + myLStack.length());
        myLStack.push("second");
        System.out.println("After Push length : " + myLStack.length());
        System.out.println("Print Top : " + myLStack.topValue());
        myLStack.push("third");
        System.out.println("After Push length : " + myLStack.length());
        System.out.println("Pop Item : " + myLStack.pop());
        System.out.println("After Pop length : " + myLStack.length());
        myLStack.push("fourth");
        System.out.println("After Push length : " + myLStack.length());
        System.out.println("Pop Item : " + myLStack.pop());
        System.out.println("After Pop length : " + myLStack.length());
        System.out.println("Pop Item : " + myLStack.pop());
        myLStack.clear();
        System.out.println("Clear : " + myLStack.length());
        myLStack.push("fifth");
        for(int i=0; i<10; i++) {
            myLStack.push("repeat");
        }
        System.out.println("Push Repeat Result : " + myLStack.length());
        System.out.println("Pop Item : " + myLStack.pop());
        System.out.println("Finish length : " + myLStack.length());
        myLStack.clear();
        System.out.println("Finish Clear : " + myLStack.length());
    }
}

package ds_hw1.list;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_SIZE = 10;
    private int listSize;
    private int size;
    private E[] data;

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    public ArrayList(int size) {
        listSize = 0;
        this.size = size;
        data = (E[]) new Object[size];
    }

    @Override
    public void clear() {
        listSize = 0;
    }

    @Override
    public void insert(int pos, E item) {
        if (listSize >= size) {
            E[] doubling = (E[]) new Object[size * 2];
            for(int i = 0; i < listSize; i++) {
                doubling[i] = data[i];
            }
            data = doubling;
        }
        for (int i = listSize; i > pos; i--) {
            data[i] = data[i - 1];
        }
        data[pos] = item;
        listSize++;
    }

    @Override
    public void append(E item) {
        if (listSize >= size) {
            E[] doubling = (E[]) new Object[size * 2];
            for(int i = 0; i < listSize; i++) {
                doubling[i] = data[i];
            }
            data = doubling;
        }
        data[listSize++] = item;
    }

    @Override
    public void update(int pos, E item) {
        data[pos] = item;
    }

    @Override
    public E getValue(int pos) {
        return data[pos];
    }

    @Override
    public E remove(int pos) {
        E ret = data[pos];
        listSize--;
        for (int i = pos; i < listSize; i++) {
            data[i] = data[i + 1];
        }
        return ret;
    }

    @Override
    public int length() {
        return listSize;
    }

    public ListIterator<E> listIterator(){
        return new ListIterator<E>(){
            int pos = 0;

            public boolean hasNext(){
                return pos < listSize;
            }
            public E next(){
                return data[pos++];
            }
            public boolean hasPrevious(){
                return pos > 0;

            }
            public E previous(){
                return data[--pos];
            }
        };
    }

    public void printall() {
        System.out.print("Print List : ");
        for(int i=0; i<size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>(20);
        ArrayList<Integer> intList = new ArrayList<>(20);
        myList.append("First");
        myList.printall();
        myList.append("Second");
        myList.printall();
        myList.insert(0, "Insert_First");
        myList.printall();
        myList.append("Third");
        myList.printall();
        myList.update(3, "Update_Third");
        myList.printall();
        System.out.println("Before Remove : " + myList.length());
        myList.remove(2);
        System.out.println("After Remove : " + myList.length());

        myList.append("Fourth");
        myList.printall();
        myList.append("Fifth");
        myList.printall();
        myList.insert(1, "Insert_Second");
        for (int i = 8; i <= 30; i++) {
            intList.append(i);
        }

        System.out.println("Print myList size : " + myList.length());
        System.out.println(Arrays.toString(myList.data));
        System.out.println("Print intList size : " + intList.length());
        System.out.println(Arrays.toString(intList.data));
        System.out.println();

        myList.clear();
        System.out.println("After Clear_myList : " + myList.length());
        System.out.println();
        intList.clear();
        System.out.println("After Clear_int : " + intList.length());
    }
}

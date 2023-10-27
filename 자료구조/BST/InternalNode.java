package ds_hw3.BST;

public class InternalNode<E> implements BinNode<E> {
    private E data;
    private BinNode<E> l, r;

    public InternalNode(E data, BinNode<E> l, BinNode<E> r) {
        this.data = data;
        this.l = l;
        this.r = r;
    }

    @Override
    public E element() {
        return data;
    }

    @Override
    public E setElement(E item) {
        return this.data = item;
    }

    @Override
    public BinNode<E> left() {
        return l;
    }

    @Override
    public BinNode<E> right() {
        return r;
    }

    @Override
    public boolean isLeaf() {
        return l == null && r == null;
    }

    @Override
    public void setLeft(BinNode<E> n) {
        this.l = n;
    }

    @Override
    public void setRight(BinNode<E> n) {
        this.r = n;
    }
}

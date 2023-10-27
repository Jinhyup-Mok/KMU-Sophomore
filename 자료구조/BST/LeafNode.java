package ds_hw3.BST;

public class LeafNode<E> implements BinNode<E> {
    public E data;

    public LeafNode(E data) {
        this.data = data;
    }

    @Override
    public E element() {
        return data;
    }

    @Override
    public E setElement(E item) {
        return data = item;
    }

    @Override
    public BinNode<E> left() {
        return null;
    }

    @Override
    public BinNode<E> right() {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public void setLeft(BinNode<E> n) {
    }

    @Override
    public void setRight(BinNode<E> n) {
    }
}

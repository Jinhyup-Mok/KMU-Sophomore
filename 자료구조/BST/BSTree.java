package ds_hw3.BST;

public class BSTree<K extends Comparable<K>, E> implements Dictionary<K, E> {
    class Entry {
        public K key;
        public E element;
        public Entry(K key, E element) {
            this.key = key;
            this.element = element;
        }
    }

    private BinNode<Entry> root;
    private int size = 0;

    public BSTree() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void insert(K k, E e) {
        root = insert_(k, e, root);
        size++;
    }

    public BinNode<Entry> insert_(K k, E e, BinNode<Entry> rt) {
        if (rt == null) {
            return new LeafNode<>(new Entry(k, e));
        }
        else if (rt.element().key.compareTo(k) == 0) rt.element().element = e;
        else if (rt.element().key.compareTo(k) < 0) {
            if (rt.isLeaf()) rt = new InternalNode<>(new Entry(rt.element().key, rt.element().element), null, null);
            rt.setRight(insert_(k, e, rt.right()));
        }
        else {
            if (rt.isLeaf()) rt = new InternalNode<>(new Entry(rt.element().key, rt.element().element), null, null);
            rt.setLeft(insert_(k, e, rt.left()));
        }
        return rt;
    }

    @Override
    public E remove(K k) {
        E ret = find_(k, root);
        if (ret != null) {
            root = remove_(k, root);
            size--;
        }
        return ret;
    }

    private BinNode<Entry> remove_(K k, BinNode<Entry> rt) {
        if (rt.element().key.compareTo(k) > 0) rt.setLeft(remove_(k, rt.left()));
        else if (rt.element().key.compareTo(k) < 0) rt.setRight(remove_(k, rt.right()));
        else {
            if (rt.left() == null) return rt.right();
            else if (rt.right() == null) return rt.left();
            else {
                Entry leftMost = getLeftMost(rt.right());
                rt.setElement(leftMost);
                rt.setRight(removeLeftMost(rt.right()));
            }
        }
        if (rt.isLeaf()) rt = new LeafNode<Entry>(new Entry(rt.element().key, rt.element().element));
        return rt;
    }

    private Entry getLeftMost(BinNode<Entry> rt) {
        BinNode<Entry> cur = rt;
        while (cur.left() != null) cur = cur.left();
        return cur.element();
    }

    private BinNode<Entry> removeLeftMost(BinNode<Entry> rt) {
        if (rt.left() == null) return rt.right();
        else {
            rt.setLeft(removeLeftMost(rt.left()));
            if (rt.isLeaf()) rt = new LeafNode<Entry>(new Entry(rt.element().key, rt.element().element));
            return rt;
        }
    }
    @Override
    public E removeAny() {
        return remove(root.element().key);
    }

    @Override
    public E find(K k) {
        return find_(k, root);
    }

    public E find_(K k, BinNode<Entry> rt) {
        if (rt == null) return null;
        else if (rt.element().key.compareTo(k) == 0) {
            return rt.element().element;
        }
        else if (rt.element().key.compareTo(k) < 0) return find_(k, rt.right());
        else return find_(k, rt.left());
    }
    @Override
    public int size() {
        return size;
    }
}

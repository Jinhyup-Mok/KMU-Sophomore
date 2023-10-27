package ds_hw1.list;

public class Link<E> {
    public E item;
    public Link<E> next;
    public Link(E item, Link<E> next) {
        this.item = item;
        this.next = next;
    }
    static Link<?> freelist = null;
    static <E> Link<E> get(E item, Link<E> next) {
        if (freelist == null) return new Link<>(item, next);

        Link<E> ret = (Link<E>) Link.freelist;
        freelist = freelist.next;

        ret.item = item;
        ret.next = next;

        return ret;
    }

    void release() {
        item = null;
        next = (Link<E>) Link.freelist;
        freelist = this;
    }
}


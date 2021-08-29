public class Queue2<T> {
    DLL2<T> list;

    public Queue2() {
        this.list = new DLL2<T>();
    }
    public Queue2(T content) {
        this();
        this.enqueue(content);
    }
    public void enqueue(T content) {
        list.add(content);
    }

    public T dequeue() {
        T returnObject = list.getFirst();
        list.remove(0);
        return returnObject;
    }
    public void clear() {
        list.clear();
    }
}

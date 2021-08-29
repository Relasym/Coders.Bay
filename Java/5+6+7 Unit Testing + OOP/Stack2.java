public class Stack2<T> {
    DLL2<T> list;

    public Stack2() {
        list= new DLL2<T>();
    }
    public Stack2(T content) {
        this();
        this.push(content);
    }

    public void push(T content) {
        list.add(content);
    }

    public T pop() {
        T returnObject= list.getLast();
        list.remove(list.indexOf(returnObject));
        return returnObject;
    }

    public Object peek() {
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.getLength()==0;
    }

    public void clear() {
        list.clear();
    }


}

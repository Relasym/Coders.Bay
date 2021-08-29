public class DLL2 <T> {
    private int length;
    private DLL2Element <T> firstElement;
    private DLL2Element <T> lastElement;

    public DLL2() {
        this.length = 0;
        firstElement = null;
        lastElement = null;
    }

    public DLL2(T content) {
        this.length = 1;
        firstElement = new DLL2Element<>(content, null);
        lastElement = firstElement;
    }

    public void add(T content) {
        DLL2Element<T> newElement = new DLL2Element<>(content, lastElement);
        if (length == 0) {
            firstElement = newElement;
            lastElement = newElement;
        } else {
            lastElement.next = newElement;
            lastElement = newElement;
        }
        length++;
    }

    public T get(int index) {
        if (index < 0 || index > length - 1) {
            return null;
        }
        DLL2Element <T> currentElement = firstElement;
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.next;
        }
        return currentElement.content;
    }

    public T getFirst() {
        return (firstElement == null) ? null : firstElement.content;
    }

    public T getLast() {
        return (lastElement == null) ? null : lastElement.content;
    }

    public void remove(int index) {
        if (index == 0) {
            //removing first element (can also be last element)
            firstElement = firstElement.next;
            if (firstElement != null) {
                firstElement.previous = null;
            } else {
                lastElement = null;
            }
        } else if (index == length - 1) {
//            removing last element (that is not the first element)
            lastElement=lastElement.previous;
            lastElement.next=null;

        } else {
            //removing element in the middle
            //find element
            DLL2Element<T> currentElement = firstElement;
            for(int i =0;i<index;i++) {
                currentElement=currentElement.next;
            }
            //cut it out
            currentElement.next.previous=currentElement.previous;
            currentElement.previous.next=currentElement.next;

        }

        length--;
    }

    public int getLength() {
        return length;
    }

    public boolean contains(T searchContent) {
        DLL2Element <T> currentElement = firstElement;
        while (currentElement != null) {
            if (currentElement.content.equals(searchContent)) {
                return true;
            }
            currentElement = currentElement.next;
        }
        return false;
    }

    public void reverse() {
        DLL2Element<T> currentElement=firstElement;
        DLL2Element<T> nextElement;
        DLL2Element<T> swap;
        while(currentElement!=null)
        {
            nextElement=currentElement.next;
            swap = currentElement.next;
            currentElement.next=currentElement.previous;
            currentElement.previous=swap;
            currentElement=nextElement;
        }
        firstElement=lastElement;

    }

    public int indexOf(T searchContent) {
        DLL2Element<T> currentElement = firstElement;
        int index = 0;
        while (currentElement != null) {
            if (currentElement.content.equals(searchContent)) {
                return index;
            }
            index++;
            currentElement = currentElement.next;
        }
        return -1;
    }

    public void clear() {
        while(length>0) {
            remove(0);
        }
    }

    private static class DLL2Element<T>{
        DLL2Element<T> previous;
        DLL2Element<T> next;
        T content;

        public DLL2Element(T content, DLL2Element<T> lastElement) {
            this.content = content;
            this.previous = lastElement;
            this.next = null;
        }
    }
}

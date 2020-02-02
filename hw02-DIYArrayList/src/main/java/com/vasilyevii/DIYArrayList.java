package com.vasilyevii;

import java.util.*;

public class DIYArrayList<T> implements List<T> {

    private Object[] elementData;
    private int size = 0;
    private static final int INITIAL_SIZE = 10;


    public DIYArrayList() {
        this.elementData = new Object[]{};
    }

    public DIYArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
            this.size = initialCapacity;
        } else if (initialCapacity == 0) {
            this.elementData = new Object[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    @Override
    public boolean add(T t) {
        if (size++ >= elementData.length) {
            grow();
        }
        elementData[size - 1] = t;
        return true;
    }

    private void grow() {
        int addLength = elementData.length >>> 1;
        if (addLength == 0) {
            addLength = INITIAL_SIZE;
        }
        elementData = Arrays.copyOf(elementData, elementData.length + addLength);
    }

    @Override
    public String toString() {

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(elementData[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr<T>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListItr<T>(index);
    }

    private class ListItr<T> implements ListIterator<T> {

        int cursor;

        public ListItr() {
            this(0);
        }

        public ListItr(int cursor) {
            this.cursor = cursor;
        }

        @Override
        public T next() {
            if (cursor >= size) throw new NoSuchElementException();
            return (T) elementData[cursor++];
        }

        @Override
        public void set(T t) {
            elementData[cursor - 1] = t;
        }

        // UnsupportedOperationException
        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) elementData, 0, size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }


    // UnsupportedOperationException
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

}

package com.bobocode.cs;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    public static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }

        public static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private Node<T> node(int index) {
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        Stream.of(elements).forEach(linkedList::add);
        return linkedList;
//        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (size == 0) {
            first = Node.valueOf(element);
            size++;
            return;
        }
        node(size - 1).next = Node.valueOf(element);
        size++;
        //     throw new ExerciseNotCompletedException(); // todo: implement this method
    }


    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Node<T> newNode = Node.valueOf(element);
        if (index == 0) {
            addAsHead(newNode);
        } else if (index == size) {
            addAsTail(newNode);
        } else {
            Node<T> node = findNodeByIndex(index - 1);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
        //   throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    private Node<T> findNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        if (index == size - 1) {
            return last;
        } else {
            return nodeAt(index);
        }
    }

    private Node<T> nodeAt(int index) {
        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private void addAsHead(Node<T> newNode) {
        newNode.next = first;
        first = newNode;
        if (first.next == null) {
            last = first;
        }
    }

    private void addAsTail(Node<T> newNode) {
        last.next = newNode;
        last = newNode;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        checkIndex(index);
        node(index).element = element;
        //     throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return node(index).element;
        //    throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    private void checkIndex(int index) {
        if (first == null || size == index) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        checkNonNull();
        return first.element;

        //     throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        checkNonNull();
        return last.element;
        //    throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    private void checkNonNull() {
        if (first == null) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        T deletedElement;
        if (index == 0 && !isEmpty()) {
            deletedElement = first.element;
            removeFirst();
        } else {
            Node<T> previousNode = findNodeByIndex(index - 1);
            deletedElement = previousNode.next.element;
            previousNode.next = previousNode.next.next;
            if (index == size - 1) {
                last = previousNode;
            }
        }
        size--;
        return deletedElement;
        //      throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    public void removeFirst() {
        first = first.next;
        if (first == null) {
            last = null;
        }
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> currentNode = first;
        while (currentNode != null) {
            if (currentNode.element.equals(element)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
        //  throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return first == null;
        //    throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
        //     throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = last = null;
        size = 0;
        //     throw new ExerciseNotCompletedException(); // todo: implement this method
    }
}
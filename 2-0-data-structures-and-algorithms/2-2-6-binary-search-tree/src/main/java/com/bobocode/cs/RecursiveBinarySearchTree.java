package com.bobocode.cs;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private static class Node<T> {
        T element;
        Node<T> leftNode;
        Node<T> rightNode;

        public Node(T element) {
            this.element = element;
        }

    }

    int size;
    private Node<T> root;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> recursiveBinarySearchTree = new RecursiveBinarySearchTree<>();
        Stream.of(elements).forEach(recursiveBinarySearchTree::insert);
        return recursiveBinarySearchTree;
    }

    /**
     * insert an element
     *
     * @return true if element did not exist in the tree and was inserted successfully
     */

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        } else {
            return insert(root, element);
        }
    }

    private boolean insert(Node<T> current, T element) {
        if (element.compareTo(current.element) < 0) {
            if (current.leftNode == null) {
                current.leftNode = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(current.leftNode, element);
            }
        } else if (element.compareTo(current.element) > 0) {
            if (current.rightNode == null) {
                current.rightNode = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(current.rightNode, element);
            }
        }
        return false;
    }

    /**
     * @return true if tree contains element
     */

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        return contains(root, element);
    }

    private boolean contains(Node<T> current, T element) {
        if (current == null) {
            return false;
        } else if (element.compareTo(current.element) < 0) {
            return contains(current.leftNode, element);
        } else if (element.compareTo(current.element) > 0) {
            return contains(current.rightNode, element);
        } else {
            return true;
        }
    }

    /**
     * @return number of elements in the tree
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * @return max. number of transition between root node and any other node; 0 - if tree is empty or contains 1 element
     */

    @Override
    public int depth() {
        return root != null ? depth(root) - 1 : 0;
    }

    public int depth(Node<T> current) {
        if (current == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(current.leftNode), depth(current.rightNode));
        }
    }

    /**
     * traverse the tree in element's natural order
     *
     * @param consumer accepts ref. to node during traversing
     */

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(consumer, root);
    }

    public void inOrderTraversal(Consumer<T> consumer, Node<T> current) {
        if (current != null) {
            inOrderTraversal(consumer, current.leftNode);
            consumer.accept(current.element);
            inOrderTraversal(consumer, current.rightNode);
        }
    }
}
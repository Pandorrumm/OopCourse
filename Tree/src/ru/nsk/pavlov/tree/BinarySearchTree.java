package ru.nsk.pavlov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private final Comparator<E> comparator;
    private int size;

    public BinarySearchTree() {
        comparator = null;
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }

        if (element1 == null && element2 == null) {
            return 0;
        }

        if (element1 == null) {
            return -1;
        }

        if (element2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<E>) element1).compareTo(element2);
    }

    public void add(E element) {
        if (root == null) {
            root = new TreeNode<>(element);

            size++;
            return;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> parent = null;

        int comparisonResult = 0;

        while (currentNode != null) {
            parent = currentNode;
            comparisonResult = compare(element, currentNode.getData());

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        if (comparisonResult < 0) {
            parent.setLeft(new TreeNode<>(element));
        } else {
            parent.setRight(new TreeNode<>(element));
        }

        size++;
    }

    public boolean contains(E element) {
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(element, currentNode.getData());

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else if (comparisonResult > 0) {
                currentNode = currentNode.getRight();
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean remove(E element) {
        if (root == null) {
            return false;
        }

        TreeNode<E> nodeToDelete = root;
        TreeNode<E> parent = null;

        while (nodeToDelete != null) {
            int comparisonResult = compare(element, nodeToDelete.getData());

            if (comparisonResult < 0) {
                parent = nodeToDelete;
                nodeToDelete = nodeToDelete.getLeft();
            } else if (comparisonResult > 0) {
                parent = nodeToDelete;
                nodeToDelete = nodeToDelete.getRight();
            } else {
                break;
            }
        }

        if (nodeToDelete == null) {
            return false;
        }

        if (nodeToDelete.getLeft() == null) {
            replaceChild(parent, nodeToDelete, nodeToDelete.getRight());
        } else if (nodeToDelete.getRight() == null) {
            replaceChild(parent, nodeToDelete, nodeToDelete.getLeft());
        } else {
            TreeNode<E> replacement = nodeToDelete.getRight();
            TreeNode<E> replacementParent = nodeToDelete;

            while (replacement.getLeft() != null) {
                replacementParent = replacement;
                replacement = replacement.getLeft();
            }

            if (replacement != nodeToDelete.getRight()) {
                replacementParent.setLeft(replacement.getRight());
                replacement.setRight(nodeToDelete.getRight());
            }

            replacement.setLeft(nodeToDelete.getLeft());

            replaceChild(parent, nodeToDelete, replacement);
        }

        size--;

        return true;
    }

    private void replaceChild(TreeNode<E> parent, TreeNode<E> oldChild, TreeNode<E> newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.getLeft() == oldChild) {
            parent.setLeft(newChild);
        } else {
            parent.setRight(newChild);
        }
    }

    public void depthFirstSearchPreOrderRecursive(Consumer<E> consumer) {
        Objects.requireNonNull(consumer, "Consumer cannot be null");

        depthFirstSearchPreOrderRecursive(root, consumer);
    }

    private void depthFirstSearchPreOrderRecursive(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        depthFirstSearchPreOrderRecursive(node.getLeft(), consumer);
        depthFirstSearchPreOrderRecursive(node.getRight(), consumer);
    }

    public void depthFirstSearchPreOrder(Consumer<E> consumer) {
        Objects.requireNonNull(consumer, "Consumer cannot be null");

        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.pop();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    public void breadthFirstSearch(Consumer<E> consumer) {
        Objects.requireNonNull(consumer, "Consumer cannot be null");

        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        depthFirstSearchPreOrder(element -> stringBuilder.append(element).append(", "));

        if (stringBuilder.length() > 1) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
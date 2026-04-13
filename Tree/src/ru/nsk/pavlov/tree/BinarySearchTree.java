package ru.nsk.pavlov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<E> comparator;

    private static class TreeNode<E> {
        private TreeNode<E> left;
        private TreeNode<E> right;
        private final E data;

        private TreeNode(E data) {
            this.data = data;
        }
    }

    public BinarySearchTree() {
        this.root = null;
        this.comparator = null;
        this.size = 0;
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.root = null;
        this.comparator = comparator;
        this.size = 0;
    }

    private int compare(E leftElement, E rightElement) {
        if (leftElement == null && rightElement == null) {
            return 0;
        }

        if (leftElement == null) {
            return -1;
        }

        if (rightElement == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(leftElement, rightElement);
        } else {
            //noinspection unchecked
            return ((Comparable<E>) leftElement).compareTo(rightElement);
        }
    }

    public void add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element");
        }

        if (root == null) {
            root = new TreeNode<>(element);

            size++;
            return;
        }

        TreeNode<E> current = root;
        TreeNode<E> parent = null;

        int comparisonResult = 0;

        while (current != null) {
            parent = current;
            comparisonResult = compare(element, current.data);

            if (comparisonResult < 0) {
                current = current.left;
            } else if (comparisonResult > 0) {
                current = current.right;
            }
        }

        if (comparisonResult < 0) {
            parent.left = new TreeNode<>(element);
        } else {
            parent.right = new TreeNode<>(element);
        }

        size++;
    }

    public boolean contains(Object element) {
        if (element == null) {
            return false;
        }

        @SuppressWarnings("unchecked")
        E e = (E) element;

        TreeNode<E> current = root;

        while (current != null) {
            int comparisonResult = compare(e, current.data);

            if (comparisonResult < 0) {
                current = current.left;
            } else if (comparisonResult > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean remove(E element) {
        if (element == null || root == null) {
            return false;
        }

        TreeNode<E> nodeToDelete = root;
        TreeNode<E> parent = null;

        int comparisonResult;

        while (nodeToDelete != null) {
            comparisonResult = compare(element, nodeToDelete.data);

            if (comparisonResult < 0) {
                parent = nodeToDelete;
                nodeToDelete = nodeToDelete.left;
            } else if (comparisonResult > 0) {
                parent = nodeToDelete;
                nodeToDelete = nodeToDelete.right;
            } else {
                break;
            }
        }

        if (nodeToDelete == null) {
            return false;
        }

        if (nodeToDelete.left == null) {
            replaceChild(parent, nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == null) {
            replaceChild(parent, nodeToDelete, nodeToDelete.left);
        } else {
            TreeNode<E> replacement = nodeToDelete.right;
            TreeNode<E> replacementParent = nodeToDelete;

            while (replacement.left != null) {
                replacementParent = replacement;
                replacement = replacement.left;
            }

            if (replacement != nodeToDelete.right) {
                replacementParent.left = replacement.right;
                replacement.right = nodeToDelete.right;
            }

            replacement.left = nodeToDelete.left;

            replaceChild(parent, nodeToDelete, replacement);
        }

        size--;

        return true;
    }

    public int getSize() {
        return size;
    }

    public void forEachPreOrder(Consumer<E> action) {
        forEachPreOrderRecursive(root, action);
    }

    private void forEachPreOrderRecursive(TreeNode<E> node, Consumer<E> action) {
        if (node == null) {
            return;
        }

        action.accept(node.data);

        forEachPreOrderRecursive(node.left, action);
        forEachPreOrderRecursive(node.right, action);
    }

    public void forEachPreOrderIterative(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            action.accept(current.data);

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public void forEachBFS(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.poll();
            action.accept(current.data);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    private void replaceChild(TreeNode<E> parent, TreeNode<E> oldChild, TreeNode<E> newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else {
            parent.right = newChild;
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean isFirst = true;

        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.poll();

            if (!isFirst) {
                sb.append(", ");
            }

            sb.append(current.data);

            isFirst = false;

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        sb.append(']');

        return sb.toString();
    }
}
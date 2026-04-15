package ru.nsk.pavlov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int compare(E element1, E element2) {
        if (element1 == null && element2 == null) {
            return 0;
        }

        if (element1 == null) {
            return -1;
        }

        if (element2 == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(element1, element2);
        }

        //noinspection unchecked
        return ((Comparable<E>) element1).compareTo(element2);
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
            comparisonResult = compare(element, current.getData());

            if (comparisonResult < 0) {
                current = current.getLeft();
            } else if (comparisonResult > 0) {
                current = current.getRight();
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
        if (element == null) {
            return false;
        }

        TreeNode<E> current = root;

        while (current != null) {
            int comparisonResult = compare(element, current.getData());

            if (comparisonResult < 0) {
                current = current.getLeft();
            } else if (comparisonResult > 0) {
                current = current.getRight();
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

    public int getSize() {
        return size;
    }

    public void preOrderTraversal(Consumer<E> action) {
        preOrderRecursiveTraversal(root, action);
    }

    private void preOrderRecursiveTraversal(TreeNode<E> node, Consumer<E> action) {
        if (node == null) {
            return;
        }

        action.accept(node.getData());

        preOrderRecursiveTraversal(node.getLeft(), action);
        preOrderRecursiveTraversal(node.getRight(), action);
    }

    public void preOrderIterativeTraversal(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            action.accept(current.getData());

            if (current.getRight() != null) {
                stack.push(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
    }

    public void breadthFirstSearch(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.remove();
            action.accept(current.getData());

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        List<E> elements = new ArrayList<>();

        preOrderRecursiveTraversal(root, elements::add);

        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(elements.get(i) != null ? elements.get(i).toString() : "null");
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
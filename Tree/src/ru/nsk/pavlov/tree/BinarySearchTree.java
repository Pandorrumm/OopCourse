package ru.nsk.pavlov.tree;

import ru.nsk.pavlov.tree_node.TreeNode;

public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;

    public void insert(E x) {
        if (root == null) {
            root = new TreeNode<>(x);
            return;
        }

        TreeNode<E> current = root;

        while (true) {
            if (x.compareTo(current.getData()) < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode<>(x));
                    return;
                } else {
                    current = current.getLeft();
                }
            } else {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode<>(x));
                    return;
                } else {
                    current = current.getRight();
                }
            }
        }
    }

    public TreeNode<E> findNode(E x) {
        TreeNode<E> current = root;

        while (current != null) {
            int comparisonResult = x.compareTo(current.getData());

            if (comparisonResult < 0) {
                current = current.getLeft();
            } else if (comparisonResult > 0) {
                current = current.getRight();
            } else {
                return current;
            }
        }

        return null;
    }

    public boolean delete(E x) {
        TreeNode<E> nodeToDelete = findNode(x);

        if (nodeToDelete == null) {
            return false;
        }

        TreeNode<E> parent = findParent(nodeToDelete);

        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (parent == null) {
                root = null;
            } else if (parent.getLeft() == nodeToDelete) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (nodeToDelete.getLeft() == null) {

        } else if (nodeToDelete.getRight() == null) {

        } else {

        }

        return true;
    }

    private TreeNode<E> findParent(TreeNode<E> target) {
        if (target == null || target == root) {
            return null;
        }

        TreeNode<E> current = root;

        while (current != null) {
            if (current.getLeft() == target || current.getRight() == target) {
                return current;
            }

            int comparisonResult = target.getData().compareTo(current.getData());

            if (comparisonResult < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return null;
    }

    public void printTree() {
        printTree(root);
    }

    public void printTree(TreeNode<E> node) {
        if (node != null) {
            printTree(node.getLeft());
            System.out.print(node.getData() + " ");
            printTree(node.getRight());
        }
    }
}

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

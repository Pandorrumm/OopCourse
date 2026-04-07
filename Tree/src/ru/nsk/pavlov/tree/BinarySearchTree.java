package ru.nsk.pavlov.tree;

import ru.nsk.pavlov.tree_node.TreeNode;

public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int nodeCount;

    public void insert(E x) {
        if (root == null) {
            root = new TreeNode<>(x);
            nodeCount++;
            return;
        }

        TreeNode<E> current = root;

        while (true) {
            if (x.compareTo(current.getData()) < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode<>(x));

                    nodeCount++;

                    return;
                } else {
                    current = current.getLeft();
                }
            } else {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode<>(x));

                    nodeCount++;

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
            replaceChild(parent, nodeToDelete, null);
        } else if (nodeToDelete.getLeft() == null) {
            replaceChild(parent, nodeToDelete, nodeToDelete.getRight());
        } else if (nodeToDelete.getRight() == null) {
            replaceChild(parent, nodeToDelete, nodeToDelete.getLeft());
        } else {
            TreeNode<E> minNode = findMinimumNode(nodeToDelete.getRight());
            TreeNode<E> minNodeParent = findParent(minNode);

            if (minNode != nodeToDelete.getRight()) {
                if (minNodeParent.getLeft() == minNode) {
                    minNodeParent.setLeft(minNode.getRight());
                } else {
                    minNodeParent.setRight(minNode.getRight());
                }

                minNode.setRight(nodeToDelete.getRight());
            }

            minNode.setLeft(nodeToDelete.getLeft());

            replaceChild(parent, nodeToDelete, minNode);
        }

        nodeCount--;

        return true;
    }

    public int getNodeCount() {
        return nodeCount;
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

    private TreeNode<E> findMinimumNode(TreeNode<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
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

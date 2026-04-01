package ru.nsk.pavlov.tree_main;

import ru.nsk.pavlov.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(10);
        tree.insert(50);
        tree.insert(20);
        tree.insert(40);
        tree.insert(15);
        tree.insert(5);
        tree.insert(100);

        tree.printTree();
    }
}

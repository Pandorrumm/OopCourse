package ru.nsk.pavlov.tree_main;

import ru.nsk.pavlov.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(50);
        tree.add(null);
        tree.add(20);
        tree.add(null);
        tree.add(15);
        tree.add(5);
        tree.add(100);
        tree.add(78);
        tree.add(25);

        System.out.println(tree);

        System.out.println();
        System.out.println("Node count: " + tree.getSize());

        System.out.println();
        System.out.println("Found node with the value 20: " + tree.contains(20));

        System.out.println();
        System.out.println("Delete node with the value 5: " + tree.remove(5));
        System.out.println("Node count: " + tree.getSize());
        System.out.println(tree);

        System.out.println();
        System.out.println("Preorder tree depth first search with recursion:");
        tree.preOrderSearch(value -> System.out.println(value + " "));

        System.out.println();
        System.out.println("Preorder tree depth first search:");
        tree.preOrderSearch(value -> System.out.println(value + " "));

        System.out.println();
        System.out.println("Breadth first search:");
        tree.breadthFirstSearch(value -> System.out.println(value + " "));
    }
}
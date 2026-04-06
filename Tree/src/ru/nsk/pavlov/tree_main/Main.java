package ru.nsk.pavlov.tree_main;

import ru.nsk.pavlov.tree.BinarySearchTree;
import ru.nsk.pavlov.tree_node.TreeNode;

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

        System.out.println();
        System.out.println("Find node with the value 20");

        TreeNode<Integer> findNode = tree.findNode(20);

        if (findNode != null){
            System.out.println("The node with the value 20 has been found!");
            System.out.println("Note data: " + findNode.getData());
        } else{
            System.out.println("Node not found");
        }
    }
}

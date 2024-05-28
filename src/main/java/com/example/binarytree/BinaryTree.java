package com.example.binarytree;
import com.example.person.*;

import javafx.scene.control.TextArea;

public class BinaryTree {

    private class Node {
        Person content;
        Node left, right;

        Node(Person content){
            this.content = content;
            this.left = this.right = null;
        }
    }

    public Node root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public void add(Person content) {
        Node newNode = new Node(content);

        if (isEmpty()) {
            root = newNode;
            return;
        } else {
            insert(root, newNode);
        }
    }

    public void printTree() {
        print(root);
    }

    public void printTreeToTextArea(TextArea outputArea){
        printToTextArea(root, outputArea);

    }

    public void printToTextArea(Node root, TextArea outputArea){
        if (root == null)
        return;
        printToTextArea(root.left, outputArea);
        outputArea.appendText(root.content + "\n");
        printToTextArea(root.right, outputArea);
    }

    public void print(Node root) {
        if (root == null) 
            return;
        print(root.left);
        System.out.println(root.content);
        print(root.right);
    }

    private void insert(Node currentNode, Node newNode) {
        if (currentNode.content.compareTo(newNode.content) > 0) {
            if (currentNode.left == null) {
                currentNode.left = newNode;
                return;
            } else {
                insert(currentNode.left, newNode);
            }
        } else if (currentNode.content.compareTo(newNode.content) < 0) {
            if (currentNode.right == null) {
                currentNode.right = newNode;
                return;
            } else {
                insert(currentNode.right, newNode);
            }
        }
    }

    public boolean search(Node root, String search) {
        if (root.content.getName().equals(search)) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return false;
        }

        if (root.content.getName().compareTo(search) > 0 && root.left != null) {
            return search(root.left, search);
        }

        return search(root.right, search);
    }

    public void remove(String content) {
        root = remove(root, content);
    }

    private Node remove(Node root, String content) {
        if (root == null) {
            return root;
        }

        if (content.compareTo(root.content.getName()) < 0) {
            root.left = remove(root.left, content);
        } else if (content.compareTo(root.content.getName()) > 0) {
            root.right = remove(root.right, content);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.content = findMin(root.right).content;

            root.right = remove(root.right, root.content.getName());
        }

        return root;
    }

    private Node findMin(Node root) { 
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

package Trees;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Task1 implements Runnable {

    public static void main(String[] args) throws IOException {
        new Thread(null, new Task1(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Tree tree = new Tree();
        try(BufferedWriter write = new BufferedWriter(new FileWriter("output.txt"))){
            tree.generate("input.txt");
            tree.leftDetour(tree.getRoot(), write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Tree {
    private Node root;
    public Node getRoot() {
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }

    public void generate(String filePath) throws IOException {
        Queue<Integer> data = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String temp = reader.readLine();
        while(temp != null){
            data.add(Integer.parseInt(temp));
            temp = reader.readLine();
        }
        root = new Node(data.remove());
        while(!data.isEmpty()){
            add(data.remove(), root);
        }
    }
    public Node add(Integer key, Node node){
        if(node == null)
            return new Node(key);
        if(key.compareTo(node.getValue()) > 0)
            node.setRightChild(add(key, node.getRightChild()));
        else if(key.compareTo(node.getValue()) < 0)
            node.setLeftChild(add(key, node.getLeftChild()));
        return node;
    }
    public void leftDetour(Node node, BufferedWriter writer) throws IOException {
       if(node != null){
          writer.write(node.getValue().toString() + "\n");
          leftDetour(node.getLeftChild(), writer);
           leftDetour(node.getRightChild(), writer);
       }
    }
    public Node delete(Node node, Integer key){
        if(node == null)
            return null;
        if(key.compareTo(node.getValue()) < 0){
            node.setLeftChild(delete(node.getLeftChild(), key));
            return node;
        }
        else if(key.compareTo(node.getValue()) > 0){
            node.setRightChild(delete(node.getRightChild(), key));
            return node;
        }
        if(node.getLeftChild() == null)
            return node.getRightChild();
        else if(node.getRightChild() == null)
            return node.getLeftChild();
        else{
            Integer min = findMin(node.getRightChild()).getValue();
            node.setValue(min);
            node.setRightChild(delete(node.getRightChild(), min));
            return node;
        }
    }
    public Node findMin(Node node){
        if(node.getLeftChild() != null)
            return findMin(node.getLeftChild());
        else
            return node;
    }
}

class Node {
    private Integer value;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node(Integer value) {
        this.value = value;
    }
    public Node(Integer value, Node parent) {
        this.value = value;
        this.parent = parent;
    }
    public Node() {
    }

    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public Node getLeftChild() {
        return leftChild;
    }
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    public Node getRightChild() {
        return rightChild;
    }
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
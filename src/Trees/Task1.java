package Trees;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Task1 implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Task1(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Tree tree = new Tree();
        try(BufferedWriter write = new BufferedWriter(new FileWriter("tst.out"))){
            tree.generate("tst.in");
            tree.lefrDetourReverce(tree.getRoot());
            tree.getRoot().setSRI(0);
            tree.getRoot().setLSRnUT(0);
            tree.getRoot().setLSRnUTc(1);
            tree.leftDetour(tree.getRoot());
            if(!tree.getFlag())
            tree.delete(tree.getRoot(), tree.getDel());
            tree.leftDetour(tree.getRoot(), write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Tree {
    private Integer LSR = 0;
    private Node root;
    private Integer del;
    private Boolean flag = true;
    public Node getRoot() {
        return root;
    }
    public Integer getDel() {
        return del;
    }
    public Boolean getFlag() {
        return flag;
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
    public void leftDetour(Node node) {
       if(node != null){
          functionSecondStep(node);
          leftDetour(node.getLeftChild());
          leftDetour(node.getRightChild());
       }
    }
    public void leftDetour(Node node, BufferedWriter writer) throws IOException {
        if(node != null){
            writer.write(node.getValue().toString() + "\n");
            leftDetour(node.getLeftChild(), writer);
            leftDetour(node.getRightChild(), writer);
        }
    }
    public void lefrDetourReverce(Node node){
        if(node != null){
            lefrDetourReverce(node.getLeftChild());
            lefrDetourReverce(node.getRightChild());
            functionFirstStep(node);
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

    public void functionFirstStep(Node node){
        if(node.getLeftChild() == null && node.getRightChild() == null){
            node.setHeight(0);
            node.setLeaflets(1);
        }
        if(node.getLeftChild() != null && node.getRightChild() == null){
            node.setHeight(node.getLeftChild().getHeight() + 1);
            node.setLeaflets(node.getLeftChild().getLeaflets());
            node.setLSR(node.getLeftChild().getHeight() + 1);
            if(node.getLSR().compareTo(LSR) > 0)
                LSR = node.getLSR();
        }
        if(node.getLeftChild() == null && node.getRightChild() != null){
            node.setHeight(node.getRightChild().getHeight() + 1);
            node.setLeaflets(node.getRightChild().getLeaflets());
            node.setLSR(node.getRightChild().getHeight() + 1);
            if(node.getLSR().compareTo(LSR) > 0)
                LSR = node.getLSR();
        }
        if(node.getLeftChild() != null && node.getRightChild() != null){
            node.setHeight(node.getRightChild().getHeight().compareTo(node.getLeftChild().getHeight()) >= 0 ? node.getRightChild().getHeight() + 1 : node.getLeftChild().getHeight() + 1);
            node.setLeaflets(node.getRightChild().getLeaflets().compareTo(node.getLeftChild().getLeaflets()) == 0 ? node.getRightChild().getLeaflets() + node.getLeftChild().getLeaflets() : node.getRightChild().getLeaflets().compareTo(node.getLeftChild().getLeaflets()) > 0 ? node.getRightChild().getLeaflets() : node.getLeftChild().getLeaflets());
            node.setLSR(node.getLeftChild().getHeight() + node.getRightChild().getHeight() + 2);
            if(node.getLSR().compareTo(LSR) > 0)
                LSR = node.getLSR();
        }
    }
    public void functionSecondStep(Node node){
        if(node.getLeftChild() == null && node.getRightChild() == null){
            node.setSRT(0);
        }
        if(node.getRightChild() == null && node.getLeftChild() != null){
            node.setSRT(node.getLSR().equals(LSR) ? node.getLeftChild().getLeaflets() : 0);
            node.getLeftChild().setLSRnUT(node.getLSRnUT() + 1);
            node.getLeftChild().setLSRnUTc(node.getLSRnUTc());
        }
        if(node.getRightChild() != null && node.getLeftChild() == null){
            node.setSRT(node.getLSR().equals(LSR) ? node.getRightChild().getLeaflets() : 0 );
            node.getRightChild().setLSRnUT(node.getLSRnUT() + 1);
            node.getRightChild().setLSRnUTc(node.getLSRnUTc());
        }
        if(node.getLeftChild() != null && node.getRightChild() != null){
            node.setSRT(node.getLSR().equals(LSR) ? node.getRightChild().getLeaflets() * node.getLeftChild().getLeaflets() : 0 );
            var(node.getLeftChild(), node.getRightChild(), node.getLSRnUTc(), node.getLSRnUT());
            var(node.getRightChild(), node.getLeftChild(),  node.getLSRnUTc(), node.getLSRnUT());
        }
        if(!node.equals(root) && LSR.equals(node.getLSRnUT() + node.getHeight()))
            node.setSRI(node.getLSRnUTc() * node.getLeaflets());
        if((node.getSRI() + node.getSRT()) % 2 == 0){
            if(flag){
                del = node.getValue();
                flag = false;
            }
            if(del.compareTo(node.getValue()) > 0)
                del = node.getValue();
        }

    }
    public void var(Node nodeL, Node nodeR, Integer LSRnUTc, Integer LSRnUT){
        if(LSRnUT.equals(nodeR.getHeight() + 1)){
            nodeL.setLSRnUT(nodeR.getHeight() + 2);
            nodeL.setLSRnUTc(LSRnUTc + nodeR.getLeaflets());
        }
        if(LSRnUT.compareTo(nodeR.getHeight() + 1) < 0){
            nodeL.setLSRnUT(nodeR.getHeight() + 2);
            nodeL.setLSRnUTc(nodeR.getLeaflets());
        }
        if(LSRnUT.compareTo(nodeR.getHeight() + 1) > 0){
            nodeL.setLSRnUT(LSRnUT + 1);
            nodeL.setLSRnUTc(LSRnUTc);
        }
    }
}

class Node {
    private Integer LSR = 0;

    private Integer LSRnUT; //длина наибольшего полупути из вершины в другую не через поддерево
    private Integer LSRnUTc; //количество таких различных полупутей

    private Integer SRT; //количество различных наибольших полупутей, для которых вершина - корень
    private Integer SRI; //количество различных наибольших полупутей, для которых вершина не корень, но они проходят через неё
    private Integer height;
    private Integer leaflets; //число листьев, лежащих на расстоянии height от вершины (длина наибольшего полупути в поддереве)

    private Integer value;
    private Node leftChild;
    private Node rightChild;

    public Node(Integer value) {
        this.value = value;
    }
    public Node() {}

    public Integer getValue() {
        return value;
    }
    public Node getLeftChild() {
        return leftChild;
    }
    public Integer getHeight() {
        return height;
    }
    public Integer getLeaflets() {
        return leaflets;
    }
    public Integer getLSR() {
        return LSR;
    }
    public Integer getSRT() {
        return SRT;
    }
    public Integer getSRI() {
        return SRI;
    }
    public Integer getLSRnUT() {
        return LSRnUT;
    }
    public Integer getLSRnUTc() {
        return LSRnUTc;
    }
    public Node getRightChild() {
        return rightChild;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    public void setLSRnUT(Integer LSRnUT) {
        this.LSRnUT = LSRnUT;
    }
    public void setLSRnUTc(Integer LSRnUTc) {
        this.LSRnUTc = LSRnUTc;
    }
    public void setSRT(Integer SRT) {
        this.SRT = SRT;
    }
    public void setSRI(Integer SRI) {
        this.SRI = SRI;
    }
    public void setLSR(Integer LSR) {
        this.LSR = LSR;
    }
    public void setLeaflets(Integer leaflets) {
        this.leaflets = leaflets;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
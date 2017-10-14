package com.company;

import java.util.ArrayList;


public class Node {
    ArrayList<Boolean> way;
    Character Letter;
    Integer cost;
    private Node left,right;

    Node(Character letter, Integer cost) {
        Letter = letter;
        this.cost = cost;
    }

    public Node(Character letter, Integer cost, Node left, Node right) {
        Letter = letter;
        this.cost = cost;
        this.left = left;
        this.right = right;
    }

    public Integer getCost() {
        return cost;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Boolean isFather(){
        return (left!=null)||(right!=null);
    }
}

package org.intelligentjava.machinelearning.decisiontree;

import java.util.ArrayList;
import java.util.List;

import org.intelligentjava.machinelearning.decisiontree.label.Label;

public class Node {
    
    private String name;
    
    private Label label;
    
    private List<Node> children = new ArrayList<>();
    
    private Node(String name) {
        this.name = name;
    }

    private Node(String name, Label label) {
        this.label = label;
        this.name = name;
    }

    public static Node newNode(String name) {
        return new Node(name);
    }

    public static Node newLeafNode(String name, Label label) {
        return new Node(name, label);
    }
    
    public void addChild(Node child) {
        children.add(child);
    }
    
    public boolean isLeaf() {
        return label != null;
    }

    public String getName() {
        return name;
    }
    
}

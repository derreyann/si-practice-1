package es.udc.intelligentsystems.example.entity;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.State;

import java.util.UUID;

public class Node {
    private Action action;
    private Node parent;
    private State state;
    private int depth=0;

    public Node() {

    }

    public Node(Action action, Node parent, State state) {
        this.action = action;
        this.parent = parent;
        this.state = state;
        if(parent != null) depth = parent.getDepth()+1;

    }

    public Node(State state) {
        this.action = null;
        this.parent = null;
        this.state = state;
        if(parent != null) depth = parent.getDepth()+1;
    }

    public int getDepth() { return depth; }

    public Action getAction() {
        return action;
    }

    public Node getParent() {
        return parent;
    }

    public State getState() {
        return state;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "\n" +
                "action=" + action +
                ", parent=" + parent +
                ", state=" + state +
                "}\n";
    }
}

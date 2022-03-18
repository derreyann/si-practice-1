package es.udc.intelligentsystems.giorgiodishvili_yanndere.entity;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.State;


public class Node implements Comparable<Node> {
    private Action action;
    private Node parent;
    private State state;
    private int depth = 0;
    private int cost = 0;
    private int f = 0;

    public Node(Action action, Node parent, State state, int cost) {
        this.action = action;
        this.parent = parent;
        this.state = state;
        this.cost = cost;
        this.f = cost;
        if (parent != null) depth = parent.getDepth() + 1;
    }

    public Node(Action action, Node parent, State state) {
        this.action = action;
        this.parent = parent;
        this.state = state;
        if (parent != null) depth = parent.getDepth() + 1;
    }

    public Node(State state) {
        this.action = null;
        this.parent = null;
        this.state = state;
    }

    public int getDepth() {
        return depth;
    }

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

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "\n" +
                "action=" + action +
                ", parent=" + parent +
                ", state=" + state +
                "}\n";
    }

    @Override
    public int compareTo(Node o) {
        return this.f - o.f;
    }
}

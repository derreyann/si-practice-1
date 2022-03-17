package es.udc.intelligentsystems.example.strategy;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstStrategy implements SearchStrategy {

    private Node[] successors(SearchProblem p, Node current) {

        int i=0;
        State s = current.getState();
        Action[] acc = p.actions(s);
        Node[] successors = new Node[acc.length];
        for(Action a : acc) {
            State st = p.result(s, a);
            successors[i] = new Node(a, current, st);
            i++;
        }
        return successors;
    }

    private Node[] reconstruct(Node current) {

        Node[] solution = new Node[current.getDepth()+1];
        while(current != null) {
            solution[current.getDepth()] = current;
            current = current.getParent();
        }
        return solution;
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception {

        int created=1, expanded=0;
        ArrayList<State> explored = new ArrayList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        State currentState = p.getInitialState();
        Node searching = new Node(currentState);
        frontier.add(searching);

        System.out.println(" - Starting search...");

        while(!frontier.isEmpty()) {
            searching = frontier.poll();
            currentState = searching.getState();
            expanded++;
            System.out.println(" - Current state changed to " + currentState);
            if(p.isGoal(currentState)) {
                System.out.println(" - END - " + currentState);
                System.out.println("\nNumber of nodes created: " + created);
                System.out.println("\nNumber of nodes expanded: " + expanded);
                return reconstruct(searching);
            }
            System.out.println(" - " + currentState + " is not a goal");
            explored.add(currentState);
            Node[] sons = successors(p, searching);
            created += sons.length;
            for(Node n : sons) {
                State sc = n.getState();
                System.out.println(" - RESULT(" + currentState + "," + n.getAction() + ")=" + sc);
                if(explored.contains(sc)) {
                    System.out.println(" - " + sc + " already explored");
                    continue;
                }
                System.out.println(" - " + sc + " NOT explored");
                if(!frontier.contains(n)) frontier.add(n);
            }
        }

        throw new Exception("No solution could be found");
    }

}

package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.*;

public class Strategy4 implements SearchStrategy {


    public Strategy4() {
    }

    public Node[] reconstruct_sol(Stack<Node> exploredNodes) {

        Node[] nodeList = new Node[exploredNodes.size()];
        int counter = 0;

        for (Node n : exploredNodes) {
            if(n.getParent() != null){
                nodeList[counter] = n;
            }
            counter++;
        }

        return nodeList;
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        ArrayList<State> explored = new ArrayList<State>();
        Stack<Node> exploredNodes = new Stack<>();
        State currentState = p.getInitialState();
        explored.add(currentState);
        exploredNodes.add(new Node(currentState));
        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);

        while (!p.isGoal(currentState)) {
            System.out.println((i++) + " - " + currentState + " is not a goal");
            Action[] availableActions = p.actions(currentState);
            boolean modified = false;
            for (Action acc : availableActions) {
                State sc = p.result(currentState, acc);
                System.out.println((i++) + " - RESULT(" + currentState + "," + acc + ")=" + sc);
                if (!explored.contains(sc)) {
                    exploredNodes.add(new Node(acc, exploredNodes.peek(), currentState));
                    currentState = sc;
                    System.out.println((i++) + " - " + sc + " NOT explored");
                    explored.add(currentState);
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + currentState);
                    break;
                } else {
                    System.out.println((i++) + " - " + sc + " already explored");
                }
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentState);
        return reconstruct_sol(exploredNodes);
    }
}

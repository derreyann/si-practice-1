package es.udc.intelligentsystems.example.strategy;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.*;

public class BreadthFirstStrategy implements SearchStrategy {
    public Node[] reconstruct_sol(Node solutionNode) {

        ArrayList<Node> nodeList = new ArrayList<>();

        while (solutionNode.getParent() != null) {
            nodeList.add(solutionNode.getParent());
            solutionNode = solutionNode.getParent();
        }

        return nodeList.toArray(new Node[0]);
    }


    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Node initialNode = new Node(p.getInitialState());

        if (p.isGoal(p.getInitialState())) return reconstruct_sol(initialNode);

        // empty explored list
        ArrayList<State> explored = new ArrayList<>();

        // frontier stack with first node
        Queue<Node> f = new LinkedList<>();
        f.add(initialNode);

        int i = 1;


        // check if frontier is empty
        while (!f.isEmpty()) {
            // last element of frontier
            Node node = f.poll();
            // last elements state
            State state = node.getState();
            // add this state to explored list
            explored.add(state);
            if (p.isGoal(state)) {
                return reconstruct_sol(node);
            }

            List<Node> availableActions = successors(node,p);
            for (Node n : availableActions){
                if (p.isGoal(n.getState())) {
                    return reconstruct_sol(node);
                }
                if (!explored.contains(n.getState())) {
                    boolean b = f.stream().noneMatch(c -> c.getState() == n.getState());
                    if (b) {
                        f.add(n);
                    }
                    System.out.println((i++) + " - " + n.getState() + " NOT explored");
                    System.out.println((i++) + " - Current state changed to " + n.getState());
                } else {
                    System.out.println((i++) + " - " + n.getState() + " already explored");
                }
            }
        }
        // if frontier is empty throw solution exception
        throw new IllegalStateException("No solution found");
    }

    private List<Node> successors(Node node, SearchProblem p) {
        List<Node> successors = new ArrayList<>();
        Action[] availableActions = p.actions(node.getState());
        for (Action acc : availableActions) {
            Node e = new Node(acc, node, p.result(node.getState(), acc));
            successors.add(e);
        }
        return successors;
    }
}

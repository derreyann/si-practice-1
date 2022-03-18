package es.udc.intelligentsystems.example.strategy;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.*;

import static es.udc.intelligentsystems.example.utils.SearchStrategyHelper.reconstruct_sol;
import static es.udc.intelligentsystems.example.utils.SearchStrategyHelper.successors;

public class DepthFirstStrategy implements SearchStrategy {

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Node initialNode = new Node(p.getInitialState());
        long created = 1;
        long expanded = 0;

        // empty explored list
        ArrayList<State> explored = new ArrayList<>();

        // frontier stack with first node
        Stack<Node> f = new Stack<>();
        f.push(initialNode);

        int i = 1;
        // check if frontier is empty
        while (!f.isEmpty()) {
            expanded++;
            // last element of frontier
            Node node = f.pop();
            // last elements state
            State state = node.getState();
            // goal check
            if (p.isGoal(state)) {
                System.out.println("\nNumber of nodes created: " + created);
                System.out.println("\nNumber of nodes expanded: " + expanded);
                return reconstruct_sol(node);
            }
            // add this state to explored list
            explored.add(state);
            // get all available checks
            List<Node> availableActions = successors(node, p);
            created += availableActions.size();
            for (Node n : availableActions) {
                // created node amount
                // state not already explored
                if (!explored.contains(n.getState())) {
                    // state not already in frontire
                    boolean b = f.stream().noneMatch(c -> c.getState() == n.getState());
                    if (b) {
                        //if it is not there add
                        f.push(n);
                    }
                    System.out.println((i++) + " - " + n.getState() + " NOT explored");
                    System.out.println((i++) + " - Current state changed to " + n.getState());
                } else {
                    System.out.println((i++) + " - " + n.getState() + " already explored");
                }
            }
        }
        System.out.println("\nNumber of nodes created: " + created);
        System.out.println("\nNumber of nodes expanded: " + expanded);
        // if frontier is empty throw solution exception
        throw new IllegalStateException("No solution found");
    }

}

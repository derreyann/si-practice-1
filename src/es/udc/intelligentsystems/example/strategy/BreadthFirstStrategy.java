package es.udc.intelligentsystems.example.strategy;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.*;

import static es.udc.intelligentsystems.example.utils.SearchStrategyHelper.reconstruct_sol;
import static es.udc.intelligentsystems.example.utils.SearchStrategyHelper.successors;

public class BreadthFirstStrategy implements SearchStrategy {

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Node initialNode = new Node(p.getInitialState());
        long created = 1;
        long expanded = 0;

        if (p.isGoal(p.getInitialState())) return reconstruct_sol(initialNode);

        // empty explored list
        ArrayList<State> explored = new ArrayList<>();

        // frontier stack with first node
        Queue<Node> f = new LinkedList<>();
        f.add(initialNode);

        int i = 1;

        // check if frontier is empty
        while (!f.isEmpty()) {
            expanded++;
            // last element of frontier
            Node node = f.poll();
            // last elements state
            State state = node.getState();
            // add this state to explored list
            explored.add(state);
            if (p.isGoal(state)) {
                System.out.println("\n Number of nodes created: " + created);
                System.out.println("\n Number of nodes expanded: " + expanded);
                return reconstruct_sol(node);
            }
            // get all available checks
            List<Node> availableActions = successors(node, p);
            created += availableActions.size();
            for (Node n : availableActions) {
                // goal check
                if (p.isGoal(n.getState())) {
                    System.out.println("\n Number of nodes created: " + created);
                    System.out.println("\n Number of nodes expanded: " + expanded);
                    return reconstruct_sol(node);
                }

                // state not already explored
                if (!explored.contains(n.getState())) {
                    // state not already in frontire
                    boolean b = f.stream().noneMatch(c -> c.getState() == n.getState());
                    if (b) {
                        //if it is not there add
                        f.add(n);
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

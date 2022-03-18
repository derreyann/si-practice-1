package es.udc.intelligentsystems.giorgiodishvili_yanndere.strategy;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.InformedSearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.entity.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import static es.udc.intelligentsystems.giorgiodishvili_yanndere.utils.SearchStrategyHelper.successors;

public class BestFirstStrategy implements InformedSearchStrategy {

    @Override
    public State solve(SearchProblem p, Heuristic h) throws Exception {

        int created = 1;
        int expanded = 0;

        ArrayList<State> explored = new ArrayList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(new Node(null, null, p.getInitialState(), (int) h.evaluate(p.getInitialState())));

        System.out.println(" - Starting search...");

        while (!frontier.isEmpty()) {
            Node searching = frontier.poll();
            State currentState = searching.getState();

            expanded++;
            System.out.println(" - Current state changed to " + currentState + " cost " + searching.getF());

            if (p.isGoal(currentState)) {
                System.out.println(" - END - " + currentState);
                System.out.println("\nNumber of nodes created: " + created);
                System.out.println("\nNumber of nodes expanded: " + expanded);
                return currentState;
            }


            System.out.println(" - " + currentState + " is not a goal");
            explored.add(currentState);
            // getting the successors
            List<Node> sons = successors(searching, p, h);
            created += sons.size();
            for (Node n : sons) {
                State sc = n.getState();
                n.setCost(searching.getCost() + 1);
                n.setF((int) (n.getCost() + h.evaluate(n.getState())));
                System.out.println(" - RESULT(" + currentState + "," + n.getAction() + ")=" + sc);
                // check explored
                if (!explored.contains(sc)) {
                    // check if state is not in frontier
                    boolean b = frontier.stream().noneMatch(c -> c.getState() == sc);
                    // if it is not add to frontier and continue onto the next node
                    if (b) {
                        frontier.add(n);
                        continue;
                    }
                    // if it is in the front get the node which is equal to it in whole
                    Optional<Node> nodeStream = frontier.stream().filter(n::equals).findFirst();
                    // check if there is any nodes equal
                    if (nodeStream.isPresent()) {
                        // check if f is less than the cost
                        if (n.getF() < nodeStream.get().getCost()) {
                            frontier.remove(n);
                            frontier.add(n);
                        }
                    }
                }
                System.out.println("STATE " + n.getState() + "  " + n.getF());
            }
        }
        throw new IllegalStateException("No Solution Found");
    }
}

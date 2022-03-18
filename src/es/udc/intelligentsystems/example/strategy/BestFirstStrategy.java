package es.udc.intelligentsystems.example.strategy;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.InformedSearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import static es.udc.intelligentsystems.example.utils.SearchStrategyHelper.successors;

public class BestFirstStrategy implements InformedSearchStrategy {

    @Override
    public State solve(SearchProblem p, Heuristic h) throws Exception {

        int created = 1;
        int expanded = 0;

        ArrayList<State> explored = new ArrayList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(new Node(p.getInitialState()));

        System.out.println(" - Starting search...");

        while (!frontier.isEmpty()) {
            Node searching = frontier.poll();
            State currentState = searching.getState();

            expanded++;
            System.out.println(" - Current state changed to " + currentState);

            if (p.isGoal(currentState)) {
                System.out.println(" - END - " + currentState);
                System.out.println("\nNumber of nodes created: " + created);
                System.out.println("\nNumber of nodes expanded: " + expanded);
                return currentState;
            }


            System.out.println(" - " + currentState + " is not a goal");
            explored.add(currentState);
            List<Node> sons = successors(searching, p, h);
            created += sons.size();
            for (Node n : sons) {
                State sc = n.getState();
                n.setCost(searching.getCost() + 1);
                n.setCost((int) (n.getCost() + h.evaluate(n.getState())));
                System.out.println(" - RESULT(" + currentState + "," + n.getAction() + ")=" + sc);
                if (!explored.contains(sc)) {
                    boolean b = frontier.stream().noneMatch(c -> c.getState() == sc);
                    if (!b) {
                        frontier.add(n);
                        continue;
                    }
                    Optional<Node> nodeStream = frontier.stream().filter(n::equals).findFirst();
                    if (nodeStream.isPresent()) {
                        if (n.getF() < nodeStream.get().getCost()) {
                            frontier.remove(n);
                            frontier.add(n);
                        }
                    }
                }
                System.out.println("" + searching.getState() + "  " + searching.getF());
            }
            explored.add(searching.getState());
        }
        throw new IllegalStateException("No Solution Found");
    }
}

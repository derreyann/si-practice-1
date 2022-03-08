package es.udc.intelligentsystems.example.strategy;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.ArrayList;
import java.util.Stack;

public class GraphSearchStrategy implements SearchStrategy {

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
        ArrayList<State> explored = new ArrayList<State>();
        Stack<Node> f = new Stack<>();
        explored.add(p.getInitialState());
        f.push(new Node(p.getInitialState()));
        int i = 1;

        System.out.println((i++) + " - Starting search at " + p.getInitialState());
        while (!f.empty()) {
            Node node = f.pop();
            if (!p.isGoal(node.getState())) {
                explored.add(node.getState());
                System.out.println((i++) + " - " + node.getState() + " is not a goal");
                Action[] availableActions = p.actions(node.getState());
                for (Action acc : availableActions) {
                    State sc = p.result(node.getState(), acc);
                    System.out.println((i++) + " - RESULT(" + node.getState() + "," + acc + ")=" + sc);
                    if (!explored.contains(sc)) {
                        boolean b = f.stream().noneMatch(c -> c.getState() == sc);
                        if (b) {
                            f.push(new Node(acc, node, sc));
                        }
                        System.out.println((i++) + " - " + sc + " NOT explored");
                        System.out.println((i++) + " - Current state changed to " + sc);
                        break;
                    } else {
                        System.out.println((i++) + " - " + sc + " already explored");
                    }
                }
            } else {
                System.out.println((i++) + " - END - " + node.getState());
                return reconstruct_sol(node);
            }
        }
        throw new IllegalStateException("No solution found");
    }
}



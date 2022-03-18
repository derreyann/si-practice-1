package es.udc.intelligentsystems.example.utils;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.example.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class SearchStrategyHelper {
    public static Node[] reconstruct_sol(Node solutionNode) {

        ArrayList<Node> nodeList = new ArrayList<>();

        while (solutionNode.getParent() != null) {
            nodeList.add(solutionNode.getParent());
            solutionNode = solutionNode.getParent();
        }

        return nodeList.toArray(new Node[0]);
    }

    public static List<Node> successors(Node node, SearchProblem p, Heuristic h) {
        List<Node> successors = new ArrayList<>();
        Action[] availableActions = p.actions(node.getState());
        for (Action acc : availableActions) {
            Node e = new Node(acc, node, p.result(node.getState(), acc), (int) h.evaluate(node.getState()));
            successors.add(e);
        }
        return successors;
    }

    public static List<Node> successors(Node node, SearchProblem p) {
        List<Node> successors = new ArrayList<>();
        Action[] availableActions = p.actions(node.getState());
        for (Action acc : availableActions) {
            Node e = new Node(acc, node, p.result(node.getState(), acc));
            successors.add(e);
        }
        return successors;
    }
}

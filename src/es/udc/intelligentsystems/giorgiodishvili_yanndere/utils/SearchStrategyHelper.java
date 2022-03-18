package es.udc.intelligentsystems.giorgiodishvili_yanndere.utils;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class SearchStrategyHelper {

    /**
     *  iterates over solution node parent
     * @param solutionNode which was found
     * @return parentList
     */
    public static Node[] reconstruct_sol(Node solutionNode) {

        ArrayList<Node> nodeList = new ArrayList<>();

        while (solutionNode.getParent() != null) {
            nodeList.add(solutionNode.getParent());
            solutionNode = solutionNode.getParent();
        }

        return nodeList.toArray(new Node[0]);
    }

    /**
     *
     * @param node cuurent node
     * @param p the problem which we trying to solve
     * @param h heuristic approach class, which calculates the cost
     * @return List of Nodes which have all the actions applied to them
     */
    public static List<Node> successors(Node node, SearchProblem p, Heuristic h) {
        List<Node> successors = new ArrayList<>();
        Action[] availableActions = p.actions(node.getState());
        for (Action acc : availableActions) {
            Node e = new Node(acc, node, p.result(node.getState(), acc), (int) h.evaluate(node.getState()));
            successors.add(e);
        }
        return successors;
    }

    /**
     *
     * @param node current node
     * @param p the problem which we trying to solve
     * @return List of Nodes which have all the actions applied to them
     */
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

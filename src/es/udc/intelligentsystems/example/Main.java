package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.example.entity.Board;
import es.udc.intelligentsystems.example.problems.MagicSquareProblem;
import es.udc.intelligentsystems.example.problems.VacuumCleanerProblem;
import es.udc.intelligentsystems.example.strategy.GraphSearchStrategy;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
//        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
//                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
//        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        int[][] ints = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(
                0, 0, new Board(ints)
        );
        SearchProblem aspiradora = new MagicSquareProblem(initialState);

        SearchStrategy buscador = new GraphSearchStrategy();
        System.out.println(Arrays.toString(buscador.solve(aspiradora)));
    }
}

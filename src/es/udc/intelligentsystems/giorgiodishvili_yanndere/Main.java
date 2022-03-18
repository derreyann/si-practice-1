package es.udc.intelligentsystems.giorgiodishvili_yanndere;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.InformedSearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.entity.Board;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.heuristics.MagicSquareHeuristics;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.problems.MagicSquareProblem;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.strategy.BestFirstStrategy;

public class Main {

    public static void main(String[] args) throws Exception {
//        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
//                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
//        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

//        int[][] ints = {{4, 9, 2}, {3, 5, 0}, {0, 1, 0}};
//        int[][] ints = {{4, 9, 2}, {3, 5, 6}, {0, 1, 0}};
//        int[][] ints = {{4, 9, 2}, {3, 5, 7}, {0, 1, 0}};
//        int[][] ints = {{4, 9, 2}, {3, 5, 8}, {0, 1, 0}};
//        int[][] ints = {{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//        int[][] ints = {{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//        int[][] ints = {{2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 1, 0, 0}};
        int[][] ints = {{2, 0, 0, 9}, {14, 0, 0, 3}, {11, 0, 0, 0}, {0, 1, 0, 0}};
        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(
                new Board(ints)
        );
        SearchProblem aspiradora = new MagicSquareProblem(initialState);
        Heuristic h = new MagicSquareHeuristics();
        InformedSearchStrategy buscador = new BestFirstStrategy();
        System.out.println(buscador.solve(aspiradora, h));
    }
}

//[[2, 9, 8], [7, 6, 5], [4, 3, 1]]
//[[2, 9, 4], [7, 5, 3], [6, 1, 8]]
//[[2, 1, 3], [4, 5, 6], [7, 8, 9]]
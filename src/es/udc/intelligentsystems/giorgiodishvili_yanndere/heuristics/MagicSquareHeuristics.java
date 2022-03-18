package es.udc.intelligentsystems.giorgiodishvili_yanndere.heuristics;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.problems.MagicSquareProblem;

public class MagicSquareHeuristics extends Heuristic {
    @Override
    public float evaluate(State e) {

        int row, distance=0, diagonal=0;
        int[][] matrix = ((MagicSquareProblem.MagicSquareState) e).getBoard().getBoard();
        int goal=(matrix.length * ((matrix.length * matrix.length) + 1)) / 2;

        for(int i=0; i<matrix.length; i++) {
            row = 0;
            for(int j : matrix[i]) row += j;
            distance += (goal - row);
            diagonal += matrix[i][matrix.length-(i+1)];
        }

        return distance+(goal-diagonal);
    }
}

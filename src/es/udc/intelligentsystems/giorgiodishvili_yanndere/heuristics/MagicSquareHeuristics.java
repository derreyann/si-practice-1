package es.udc.intelligentsystems.giorgiodishvili_yanndere.heuristics;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.entity.Board;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.problems.MagicSquareProblem;

public class MagicSquareHeuristics extends Heuristic {
    @Override
    public float evaluate(State e) {

        int row;
        Board board = ((MagicSquareProblem.MagicSquareState) e).getBoard();
        int[][] matrix = board.getBoard();
        long[] rowSum = new long[matrix.length];
        long[] colSum = new long[matrix.length];
        long diagonal = 0;
        long boardSum = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                rowSum[i] += matrix[i][j];
                colSum[i] += matrix[j][i];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    diagonal = matrix[i][j];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (rowSum[i] != board.getSum()) {
                boardSum += rowSum[i];
            }
            if (colSum[i] != board.getSum()) {
                boardSum += colSum[i];
            }
        }
        if (diagonal != board.getSum())
            boardSum += diagonal;

        return board.getSum() - boardSum;
    }
}

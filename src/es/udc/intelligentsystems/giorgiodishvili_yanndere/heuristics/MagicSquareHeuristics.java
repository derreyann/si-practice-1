package es.udc.intelligentsystems.giorgiodishvili_yanndere.heuristics;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.entity.Board;
import es.udc.intelligentsystems.giorgiodishvili_yanndere.problems.MagicSquareProblem;

public class MagicSquareHeuristics extends Heuristic {
    /**
     *
     * @param e current state
     * @return sums up all of the diagonals/rows/cols which are not equal to board sum
     */
    @Override
    public float evaluate(State e) {
        Board board = ((MagicSquareProblem.MagicSquareState) e).getBoard();
        int[][] matrix = board.getBoard();
        long[] rowSum = new long[matrix.length];
        long[] colSum = new long[matrix.length];
        long diagonal = 0;
        long reverseDiagonal = 0;
        long boardSum = 0;

        // row and column sum up
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                rowSum[i] += matrix[i][j];
                colSum[i] += matrix[j][i];
            }
        }
        // normal diagonal sum
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    diagonal = matrix[i][j];
                }
            }
        }

        // check if any of the row/column is not same as sum add it to boardSum
        for (int i = 0; i < matrix.length; i++) {
            if (rowSum[i] != board.getSum()) {
                boardSum += rowSum[i];
            }
            if (colSum[i] != board.getSum()) {
                boardSum += colSum[i];
            }
        }

        // reverse diagonal sum
        int column = matrix.length;
        for (int i = 0; i < board.getBoard().length; i++) {
            column--;
            reverseDiagonal += matrix[i][column];
        }

        // sum check for diagonal
        if (diagonal != board.getSum())
            boardSum += diagonal;

        // sum check for reverse diagonal
        if (reverseDiagonal != board.getSum())
            boardSum += reverseDiagonal;

        return boardSum;
    }
}

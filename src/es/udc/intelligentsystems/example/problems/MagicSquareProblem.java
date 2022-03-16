package es.udc.intelligentsystems.example.problems;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class MagicSquareProblem extends SearchProblem {
    public static class MagicSquareState extends State {

        Board board;

        public MagicSquareState(Board board) {
            this.board = board;
        }

        @Override
        public String toString() {
            return "{ " + Arrays.deepToString(board.getBoard()) + " }";

        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            MagicSquareProblem.MagicSquareState that = (MagicSquareProblem.MagicSquareState) obj;

            return (board == that.board);
        }

        @Override
        public int hashCode() {
            return board.hashCode() ;
        }
    }

    public static class MagicSquareAction extends Action {

        private final int x;
        private final int y;

        private final int number;

        public MagicSquareAction(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }

        @Override
        public String toString() {
            return "MagicSquareAction{" + "x=" + x + ", y=" + y + ", number=" + number + '}';
        }

        @Override
        public boolean isApplicable(State st) {
            MagicSquareProblem.MagicSquareState vcSt = (MagicSquareProblem.MagicSquareState) st;
            try {
                if (vcSt.board.getBoard()[y][x] == 0)
                    return true;
            } catch (IndexOutOfBoundsException x) {
                return false;
            }
            return false;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareProblem.MagicSquareState vcSt = (MagicSquareProblem.MagicSquareState) st;
            if (!isApplicable(vcSt)) {
                throw new IllegalStateException("Action is not applicable " + vcSt + "x= " + x + "y=" + y);
            }
            vcSt.board.getBoard()[y][x] = number;
            Board board = new Board(vcSt.board.getBoard());

//            int[][] newBoard = new int[vcSt.board.getBoard().length][vcSt.board.getBoard().length];
//            newBoard[y][x] = number;
//            for (int i = 0; i < newBoard.length; i++) {
//                for (int j = 0; j < newBoard.length; j++) {
//                    if (vcSt.board.getBoard()[i][j] != 0) {
//                        newBoard[i][j] = vcSt.board.getBoard()[i][j];
//                    }
//                }
//            }
//            Board board = new Board(newBoard);
            return new MagicSquareState(board);
        }
    }


    public MagicSquareProblem(State initialState) {
        super(initialState);
    }

    @Override
    public boolean isGoal(State st) {
        return isMagicSquare(((MagicSquareState) st).board.getBoard());
    }

    boolean isMagicSquare(int mat[][]) {

        // sumd1 and sumd2 are the sum of the two diagonals
        int sumd1 = 0, sumd2 = 0;
        for (int i = 0; i < mat.length; i++) {
            // (i, i) is the diagonal from top-left -> bottom-right
            // (i, N - i - 1) is the diagonal from top-right -> bottom-left
            sumd1 += mat[i][i];
            sumd2 += mat[i][mat.length - 1 - i];
        }
        // if the two diagonal sums are unequal then it is not a magic square
        if (sumd1 != sumd2)
            return false;

        // calculating sums of Rows and columns and checking if they are equal to each other,
        // as well as equal to diagonal sum or not
        for (int i = 0; i < mat.length; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < mat.length; j++) {
                rowSum += mat[i][j];
                colSum += mat[j][i];
            }
            if (rowSum != colSum || colSum != sumd1)
                return false;
        }
        return true;
    }

    @Override
    public Action[] actions(State st) {
        MagicSquareProblem.MagicSquareState vcSt = (MagicSquareProblem.MagicSquareState) st;
        int[][] board = vcSt.board.getBoard();
        ArrayList<Action> actions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    for (int available : vcSt.board.getPossibleValues()) {
                        actions.add(new MagicSquareAction(j, i, available));
                    }
                }
            }
        }

        return actions.toArray(new Action[0]);
    }
}


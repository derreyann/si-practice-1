package es.udc.intelligentsystems.giorgiodishvili_yanndere.entity;

import java.util.Stack;

public class Board {
    private final int[][] board;
    private final int boardLastNumber;
    private final Stack<Integer> possibleValues = new Stack<>();
    private final long sum;

    public Board(int[][] board) {
        this.board = board;
        this.boardLastNumber = board.length * board.length;
        this.sum =  ((long) board.length * (boardLastNumber + 1)) / 2;
        for (int i = 1; i <= boardLastNumber; i++) {
            possibleValues.add(i);
        }
        recalculatePossibleValues();
    }

    public int getBoardLastNumber() {
        return boardLastNumber;
    }

    public long getSum() {
        return sum;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public Stack<Integer> getPossibleValues() {
        return possibleValues;
    }

    /**
     * it just gets all the values which are possible to be in the array and is not in the matrix yet
     * @return Stack with all the possible values (List impl does not really matter)
     */
    public Stack<Integer> recalculatePossibleValues() {
        possibleValues.clear();
        for (int i = 1; i <= boardLastNumber; i++) {
            possibleValues.add(i);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    possibleValues.remove(new Integer(board[i][j]));
                }
            }
        }
        return possibleValues;
    }
}

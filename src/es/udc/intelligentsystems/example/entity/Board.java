package es.udc.intelligentsystems.example.entity;

import java.util.Stack;

public class Board {
    private int[][] board;
    private final int boardLastNumber;
    private final int sum;
    private final Stack<Integer> possibleValues = new Stack<>();


    public Board(int[][] board) {
        this.board = board;
        this.boardLastNumber = board.length * board.length;
        this.sum = board.length * ((board.length * board.length) + 1) / 2;
        for (int i = 1; i <= boardLastNumber; i++) {
            possibleValues.add(i);
        }
        recalculatePossibleValues();
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getNextNewValue() {
        Stack<Integer> possibleValues = recalculatePossibleValues();
        if (possibleValues.isEmpty()) {
            throw new IllegalStateException("No MORE MOVES");
        }
        return possibleValues.pop();
    }

    public void setNextNewValue(int value) {
        possibleValues.push(value);
    }

    public int getBoardLastNumber() {
        return boardLastNumber;
    }

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

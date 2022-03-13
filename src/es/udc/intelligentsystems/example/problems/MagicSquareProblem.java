package es.udc.intelligentsystems.example.problems;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.entity.Board;

import java.util.Arrays;

public class MagicSquareProblem extends SearchProblem {
    public static class MagicSquareState extends State {

        int currentX;
        int currentY;
        Board board;

        public MagicSquareState(int currentX, int currentY, Board board) {
            this.currentX = currentX;
            this.currentY = currentY;
            this.board = board;
        }

        @Override
        public String toString() {
            return "{ " + currentX + " " + currentY + " " + Arrays.deepToString(board.getBoard()) + " }";

        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            MagicSquareProblem.MagicSquareState that = (MagicSquareProblem.MagicSquareState) obj;

            return (currentX == that.currentX) && (currentY == that.currentY);
        }

        @Override
        public int hashCode() {
            int result = currentY;
            result = 31 * result + currentX;
            return result;
        }
    }

    public static class MagicSquareAction extends Action {
        public enum Type {UP, DOWN, LEFT, RIGHT}

        private Type type;

        public MagicSquareAction(Type type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type.name();
        }

        @Override
        public boolean isApplicable(State st) {
            return true;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareProblem.MagicSquareState vcSt = (MagicSquareProblem.MagicSquareState) st;
            int x = vcSt.currentX;
            int y = vcSt.currentY;
            switch (type) {
                case UP -> y--;
                case DOWN -> y++;
                case LEFT -> x--;
                case RIGHT -> x++;
            }
            vcSt.board.getBoard()[x][y] = vcSt.board.getNextNewValue();
            return new MagicSquareState(x, y, vcSt.board);
        }
    }


    public MagicSquareProblem(State initialState) {
        super(initialState);
    }

    @Override
    public boolean isGoal(State st) {
        MagicSquareState state = (MagicSquareState) st;
        for (int i = 0; i < ((MagicSquareState) st).board.getBoard().length; i++) {
            for (int j = 0; j < ((MagicSquareState) st).board.getBoard().length; j++) {
                if (state.board.getBoard()[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public Action[] actions(State st) {
        MagicSquareProblem.MagicSquareState vcSt = (MagicSquareProblem.MagicSquareState) st;
        int[][] board = vcSt.board.getBoard();
        int lastElemIndex = board.length - 1;
        Action[] actionList;
        //first row checks
        if (vcSt.currentY == 0) {
            // first row firs element
            if (vcSt.currentX == 0) {
                actionList = new Action[]{
                        new MagicSquareAction(MagicSquareAction.Type.DOWN),
                        new MagicSquareAction(MagicSquareAction.Type.RIGHT)
                };

                // first row last element
            } else if (vcSt.currentX == lastElemIndex) {
                actionList = new Action[]{
                        new MagicSquareAction(MagicSquareAction.Type.DOWN),
                        new MagicSquareAction(MagicSquareAction.Type.LEFT)
                };
                // first row any other option
            } else {
                actionList = new Action[]{
                        new MagicSquareAction(MagicSquareAction.Type.DOWN),
                        new MagicSquareAction(MagicSquareAction.Type.RIGHT),
                        new MagicSquareAction(MagicSquareAction.Type.LEFT)
                };
            }
            // last row check
        } else if (vcSt.currentY == lastElemIndex) {
            if (vcSt.currentX == 0) {
                actionList = new Action[]{
                        new MagicSquareAction(MagicSquareAction.Type.UP),
                        new MagicSquareAction(MagicSquareAction.Type.RIGHT)
                };
            } else if (vcSt.currentX == lastElemIndex) {
                actionList = new Action[]{
                        new MagicSquareAction(MagicSquareAction.Type.UP),
                        new MagicSquareAction(MagicSquareAction.Type.LEFT)
                };
            } else {
                actionList = new Action[]{
                        new MagicSquareAction(MagicSquareAction.Type.UP),
                        new MagicSquareAction(MagicSquareAction.Type.RIGHT),
                        new MagicSquareAction(MagicSquareAction.Type.LEFT)
                };
            }
            // left border
        } else if (vcSt.currentX == 0) {
            actionList = new Action[]{
                    new MagicSquareAction(MagicSquareAction.Type.UP),
                    new MagicSquareAction(MagicSquareAction.Type.RIGHT),
                    new MagicSquareAction(MagicSquareAction.Type.DOWN)
            };
            // right border
        } else if (vcSt.currentX == lastElemIndex) {
            actionList = new Action[]{
                    new MagicSquareAction(MagicSquareAction.Type.UP),
                    new MagicSquareAction(MagicSquareAction.Type.LEFT),
                    new MagicSquareAction(MagicSquareAction.Type.DOWN)
            };
            // any other situation
        } else {
            actionList = new Action[]{
                    new MagicSquareAction(MagicSquareAction.Type.UP),
                    new MagicSquareAction(MagicSquareAction.Type.LEFT),
                    new MagicSquareAction(MagicSquareAction.Type.DOWN),
                    new MagicSquareAction(MagicSquareAction.Type.RIGHT)
            };
        }
        return actionList;
    }
}

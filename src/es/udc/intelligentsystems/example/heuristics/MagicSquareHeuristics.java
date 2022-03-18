package es.udc.intelligentsystems.example.heuristics;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.example.problems.MagicSquareProblem;

public class MagicSquareHeuristics extends Heuristic {
    @Override
    public float evaluate(State e) {
        MagicSquareProblem.MagicSquareState stat = (MagicSquareProblem.MagicSquareState) e;
        long result = stat.getBoard().getSum();
        float h = 0;
        int amount = 0;
        int x = 0;
        //Fila
        for (int i = 0; i < stat.n * stat.n; i += stat.n) {
            for (int j = 0; j < stat.n; j++) {
                amount = amount + stat.a[j + i];
            }
            if (result != amount) {
                h += 1;
            }
            amount = 0;
        }
        //Columna
        for (int j = 0; j < stat.n; j++) {
            for (int i = 0; i < stat.n * stat.n; i += stat.n) {
                amount = amount + stat.a[i + j];
            }
            if (result != amount) {
                h += 1;
            }
            amount = 0;
        }
        //Diagonal
        for (int i = 0; i < stat.n * stat.n; i += stat.n) {
            amount = amount + stat.a[i + x];
            x++;
        }
        if (result != amount) {
            h += 1;
        }
        amount = 0;
        x--;
        for (int i = 0; i < stat.n * stat.n; i += stat.n) {
            amount = amount + stat.a[i + x];
            x--;
        }
        if (result != amount) {
            h += 1;
        }
        return h;
    }
}

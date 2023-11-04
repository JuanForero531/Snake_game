package snake;

import snake.ScoreManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class ScoresUI extends JFrame {
    private JTable scoresTable;
    private ScoreManager scoreManager;


    private class ScoresTableModel extends AbstractTableModel {
        private List<Score> scores;
        private String[] columnNames = {"Fecha y Hora", "Nombre", "Puntuación"};

        public ScoresTableModel(List<Score> scores) {
            this.scores = scores;
        }

        @Override
        public int getRowCount() {
            return scores.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Score score = scores.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return score.getDateTime();
                case 1:
                    return score.getPlayerName();
                case 2:
                    return score.getScore();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}

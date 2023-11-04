package snake;

import snake.Score;
import snake.SnakeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainMenu extends JFrame {

    private JTextField playerNameField;
    private JButton startGameButton;
    private JButton showScoresButton;
    private JLabel developerInfoLabel;
    private JPanel panelInfo;
    private ScoreManager scoreManager;

    public MainMenu() {
        setTitle("Menú del Juego de la Serpiente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);

        JPanel menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        playerNameField = new JTextField(20);
        startGameButton = new JButton("Iniciar Juego");
        showScoresButton = new JButton("Mostrar Puntuaciones");
        developerInfoLabel = new JLabel("Información del Desarrollador: Juan José Forero, 202127643, UPTC, Ingeniería de Sistemas, Programación III");

        menuPanel.setBackground(Color.YELLOW);
        playerNameField.setFont(new Font("Arial", Font.ITALIC, 20));
        startGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        showScoresButton.setFont(new Font("Arial", Font.BOLD, 20));
        developerInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(playerNameField, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        menuPanel.add(startGameButton, gbc);

        gbc.gridx = 1;
        menuPanel.add(showScoresButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(developerInfoLabel, gbc);

        ImageIcon iconoLogo = new ImageIcon("src/snake/uptc.jpg");
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        JLabel lblLogo = new JLabel(iconoEscalado);
        menuPanel.add(lblLogo);


        scoreManager = new ScoreManager("src/snake/scores.dat");

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText();
                if (!playerName.isEmpty()) {
                    SnakeFrame snake = new SnakeFrame();
                    snake.setVisible(true);
                    dispose();
                }
            }
        });

        showScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<Score> scores = scoreManager.getScores();
                showScoresDialog(scores);
            }
        });

        add(menuPanel);
        setLocationRelativeTo(null);
    }


    private void showScoresDialog(List<Score> scores) {
        StringBuilder scoreTable = new StringBuilder();
        scoreTable.append("Tabla de Puntuaciones:\n\n");
        scoreTable.append(String.format("%-20s %-10s\n", "Jugador", "Puntuación"));

        for (Score score : scores) {
            scoreTable.append(String.format("%-20s %-10s\n", score.getPlayerName(), score.getScore()));
        }

        JTextArea textArea = new JTextArea(scoreTable.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Tabla de Puntuaciones", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        ScoreManager scoreManager = new ScoreManager("scores.dat");
        Score score1 = new Score("Jugador1", 100);
        Score score2 = new Score("Jugador2", 150);
        Score score3 = new Score("Jugador3", 200);


        scoreManager.addScore(score1);
        scoreManager.addScore(score2);
        scoreManager.addScore(score3);


        scoreManager.saveScores();

    }
}

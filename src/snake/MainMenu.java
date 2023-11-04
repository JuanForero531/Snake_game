package snake;

import snake.SnakeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private JTextField playerNameField;
    private JButton startGameButton;
    private JButton showScoresButton;
    private JLabel developerInfoLabel;
    private JPanel panelInfo;

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

        // Cambiar el color de fondo y el tipo de letra de los componentes
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
        // Escala la imagen al tamaño deseado (por ejemplo, 100x100)
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        JLabel lblLogo = new JLabel(iconoEscalado);
        menuPanel.add(lblLogo);

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
                // Mostrar las puntuaciones
                // Llamar a un método para mostrar las puntuaciones
            }
        });

        add(menuPanel);
        setLocationRelativeTo(null);
    }
}
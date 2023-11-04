package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

public class GameField extends JPanel {

	public static final int PANEL_WIDTH = 700;
	public static final int PANEL_HEIGHT = 700;

	private List<Ellipse2D.Double> snakeParts;
	private Apple apple;


	public GameField() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.GREEN);
		initDefaults();
	}

	public void initDefaults() {
		apple = new Apple(100, 100);
		snakeParts = Collections
				.synchronizedList(new ArrayList<Ellipse2D.Double>());
		snakeParts.add(new Ellipse2D.Double(260, 260, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 280, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 300, 20, 20));
		snakeParts.add(new Ellipse2D.Double(260, 320, 20, 20));
	}

	public void setSnakeParts(List<Ellipse2D.Double> snakeParts) {
		this.snakeParts = snakeParts;
	}

	public void setApple(Apple apple) {
		this.apple = apple;
	}

	public Apple getApple() {
		return apple;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);


		g2.setPaint(Color.red);
		g2.fillOval((int) apple.getShape().getMinX() + 5, (int) apple.getShape()
				.getMinY() + 5, 10, 10);


		g2.setPaint(new Color(34, 136, 215));
		for (Ellipse2D e : snakeParts) {
			g2.fill(e);
		}


		g2.setPaint(Color.BLUE);
		g2.fill(snakeParts.get(0));
	}
}

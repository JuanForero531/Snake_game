package snake;

import java.awt.geom.Ellipse2D;

public class Game implements Runnable {

	public static final int DELAY = 150;
	private static int currentDelay = DELAY;


	private SnakeFrame frame;
	private boolean gameOver;
	private GameField gameField;
	private Snake snake;
	private Apple apple;
	private long lastFoodGenerationTime;

	private long lastObstacleGenerationalTime;

	private Obstacle obstacle;

	public Game(GameField gameField, Snake snake, SnakeFrame frame) {
		apple = new Apple(100, 100);
		obstacle = new Obstacle(200, 200);
		this.frame = frame;
		this.snake = snake;
		this.gameField = gameField;

		this.gameField.setSnakeParts(snake.getParts());
		this.gameField.setApple(apple);
		this.gameField.setObstacle(obstacle);
		lastFoodGenerationTime = System.currentTimeMillis();
		lastObstacleGenerationalTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		try {
			while (true) {
				snake.move();
				snake.check();
				manageObstacles();
				long currentTime = System.currentTimeMillis();

				if (currentTime - lastObstacleGenerationalTime >= obstacle.getObstacleExpiryInterval()) {
					obstacle.next(snake);
					lastObstacleGenerationalTime = currentTime;
				}

				if (currentTime - lastFoodGenerationTime >= apple.getFoodExpiryInterval()) {
					apple.next(snake);
					lastFoodGenerationTime = currentTime;
				}

				if (snake.isGameOver()) {
					Thread.currentThread().interrupt();
				}

				if (!Thread.currentThread().isInterrupted()) {
					gameField.repaint();
				}


				ScorePanel scorePanel = new ScorePanel();
				int newDelay = calculateNewDelay(Integer.parseInt(scorePanel.getScore()));
				if (newDelay != currentDelay) {
					currentDelay = newDelay;
					Thread.sleep(currentDelay);
				} else {
					Thread.sleep(DELAY);
				}
			}
		} catch (InterruptedException ex) {
			frame.gameOver();
		}
	}

	private int calculateNewDelay(int score) {

		if (score < 20) {
			return DELAY;
		} else if (score < 40) {
			return DELAY - 50;
		} else {
			return DELAY - 75;
		}
	}


	private void manageObstacles() {
		for (Ellipse2D.Double e : snake.getParts()) {
			if (e.getBounds2D().intersects(obstacle.getShape().getBounds2D())) {
				gameField.setCollisionWithObstacle(true);
				gameOver = true;

			}
		}

		if (gameField.isCollisionWithObstacle() && gameOver) {
			Thread.currentThread().interrupt();
		}


	}
}


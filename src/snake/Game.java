package snake;

public class Game implements Runnable {

	public static final int DELAY = 400;

	private SnakeFrame frame;
	private GameField gameField;
	private Snake snake;
	private Apple apple;
	private long lastFoodGenerationTime;

	public Game(GameField gameField, Snake snake, SnakeFrame frame) {
		apple = new Apple(100, 100);
		this.frame = frame;
		this.snake = snake;
		this.gameField = gameField;

		this.gameField.setSnakeParts(snake.getParts());
		this.gameField.setApple(apple);
		lastFoodGenerationTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		try {
			while (true) {
				snake.move();
				snake.check();

				long currentTime = System.currentTimeMillis();
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
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException ex) {
			frame.gameOver();
		}
	}
}


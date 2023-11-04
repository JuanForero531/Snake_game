package snake;

import snake.Snake;

import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Apple {

	public static final int XSIZE = 20;
	public static final int YSIZE = 20;

	private double x;
	private double y;
	private long creationTime;
	private long foodExpiryInterval;

	public Apple(double x, double y) {
		this.x = x;
		this.y = y;
		this.foodExpiryInterval = loadFoodExpiryIntervalFromConfig();
		this.creationTime = System.currentTimeMillis();
	}

	private long loadFoodExpiryIntervalFromConfig() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/snake/config.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("foodExpiryInterval=")) {
					return Long.parseLong(line.substring(18));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 5000;
	}

	private double getNew() {
		double d = 1111;
		while (d >= 400 || d % 20 != 0) {
			d = Math.random() * 1000;
			d = (int) d;
		}
		return d;
	}

	public Ellipse2D.Double getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}

	public long getFoodExpiryInterval() {
		return foodExpiryInterval;
	}

	public void next(Snake snake) {
		boolean foodExpired = hasExpired();

		for (Ellipse2D.Double e : snake.getParts()) {
			while ((x == e.getMinX() && y == e.getMinY()) || foodExpired) {
				x = getNew();
				y = getNew();
				creationTime = System.currentTimeMillis();
				foodExpired = false;
			}
		}
	}

	public boolean hasExpired() {
		return System.currentTimeMillis() - creationTime > foodExpiryInterval;
	}
}

package snake;

import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Obstacle {

    public static final int XSIZE = 20;
    public static final int YSIZE = 20;

    private double x;
    private double y;
    private long creationTime;
    private long obstacleExpiryInterval;

    public Obstacle(double x, double y) {
        this.x = x;
        this.y = y;
        this.obstacleExpiryInterval = loadObstacleExpiryIntervalFromConfig();
        this.creationTime = System.currentTimeMillis();
    }

    private long loadObstacleExpiryIntervalFromConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/snake/configObstacle.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("obstacleExpiryInterval=")) {
                    return Long.parseLong(line.substring(18));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 4000;
    }

    public Ellipse2D.Double getShape() {
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }

    public long getObstacleExpiryInterval() {
        return obstacleExpiryInterval;
    }

    public void next(Snake snake) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - creationTime >= obstacleExpiryInterval) {
            // El obstáculo ha expirado, genera una nueva posición
            generateNewPosition();
        }

        for (Ellipse2D.Double e : snake.getParts()) {
            if (e.getMinX() == x && e.getMinY() == y) {
                // El obstáculo está en la misma posición que la serpiente, genera una nueva posición
                generateNewPosition();
            }
        }
    }

    private void generateNewPosition() {
        x = getNew();
        y = getNew();
        creationTime = System.currentTimeMillis();
    }
    public boolean hasExpired() {
        return System.currentTimeMillis() - creationTime > obstacleExpiryInterval;
    }

    private double getNew() {
        double d = 1111;
        while (d >= 400 || d % 20 != 0) {
            d = Math.random() * 1000;
            d = (int) d;
        }
        return d;
    }
}

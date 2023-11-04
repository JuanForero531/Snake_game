package snake;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private List<Score> scores;
    private String scoresFileName;

    public ScoreManager(String scoresFileName) {
        this.scoresFileName = scoresFileName;
        scores = new ArrayList<>();
        loadScores();
    }

    public void addScore(Score score) {
        scores.add(score);
        saveScores();
    }

    public List<Score> getScores() {
        return scores;
    }

    private void loadScores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/snake/scores.dat"))) {
            scores = (List<Score>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void saveScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/snake/scores.dat"))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

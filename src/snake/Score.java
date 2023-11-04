package snake;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {
    private Date dateTime;
    private String playerName;
    private int score;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score(String playerName, int score) {
        this.dateTime = dateTime;
        this.playerName = playerName;
        this.score = score;
    }


}

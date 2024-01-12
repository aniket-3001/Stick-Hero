package com.iiitd.stickhero;

public class GameHistory implements Game {
    private int score;
    private int cherries;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }

    public GameHistory(int score, int cherries) {
        this.score = score;
        this.cherries = cherries;
    }
}
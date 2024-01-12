package com.iiitd.stickhero;

import java.util.ArrayList;

public interface Game {
    ArrayList<Pillar> pillars = new ArrayList<>();
    ArrayList<GameHistory> savedGames = new ArrayList<>();
    ArrayList<Integer> scores = new ArrayList<>(); //for high score --> used comparator
}

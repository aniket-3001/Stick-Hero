package com.iiitd.stickhero;

import java.util.Random;

/*
Factory Design Pattern has been implemented here for GameManager as it used for Pillar and Cherry
generation, encapsulates the creation logic for generating pillars and cherries, and enables
easy extension for adding new game elements in the future.
 */
public class GameManager implements Game{
    private static int number;
    private final Random random;

    public GameManager() {
        random = new Random();
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        GameManager.number = number;
    }

    public Pillar generateRandomPillar() {
        // Generate a pillar at a random gap from the current pillar
        int gap = (int) (Math.random() * 130 + 10); // Random gap between 10 and 140
        int width = (int) (Math.random() * 70 + 30); // Random width between 30 and 100
        Pillar pillar = new Pillar(width, 180);
        if (GameManager.number==0) pillar.setLayoutX(330 + gap);
        else pillar.setLayoutX(300+(pillars.get(number-1).getWidth()/2)+gap);
        pillar.setLayoutY(220);
        pillar.setGapDistance(gap);
        pillar.setWidth(width);
        pillars.add(pillar);
        number++;
        return pillar;
    }
    public int generateRandomCherry(){
        boolean cherryBool = random.nextBoolean();
        int minX = (int) (300+pillars.get(number-2).getWidth()/2);
        int maxX = (int) pillars.get(number-1).getLayoutX();
        if (maxX-minX<=35) return 0; //as cherry width is 30
        if (cherryBool){
            return random.nextInt((maxX - minX-32) + 1) + minX;
        }
        return 0;
    }
}

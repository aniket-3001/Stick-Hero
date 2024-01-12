package com.iiitd.stickhero;

import javafx.scene.image.ImageView;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

//Junit test class
public class JUnitTests1 {
    @After
    public void testingDone() {
        System.out.println("JUnit Testing done successfully - by Thread t1");
    }
    @Test
    public void testGenerateRandomPillarWidth() { //Tests whether the width of the generated pillar falls within the bounds of the scene
        GameManager gameManager = new GameManager();
        Pillar pillar = gameManager.generateRandomPillar();
        assertNotNull(pillar); //making sure pillar is generated
        assertTrue(pillar.getWidth() >= 30 && pillar.getWidth() <= 100);
    }
    @Test
    public void testGenerateRandomPillarHeight() { //Tests whether the height and initial layout Y of the generated pillar are as expected
        GameManager gameManager = new GameManager();
        Pillar pillar = gameManager.generateRandomPillar();
        assertEquals(180, (int)pillar.getHeight());
        assertEquals(220, (int)pillar.getLayoutY());
    }
    @Test
    public void testGenerateRandomPillarGap() {
        GameManager gameManager = new GameManager();
        Pillar pillar = gameManager.generateRandomPillar();
        assertTrue(pillar.getGapDistance() >= 10 && pillar.getGapDistance() <= 140);
    }

    @Test
    public void testGameScore() { //Tests whether the initial game score is non-negative
        int score = StickHero.getGameScore();
        assertTrue(score >= 0);
    }
}

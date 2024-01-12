package com.iiitd.stickhero;

import javafx.scene.image.ImageView;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class JUnitTests2 {
    @After
    public void testingDone() {
        System.out.println("JUnit Testing done successfully - by main thread");
    }
    @Test
    public void testingOneInstance() { //Tests whether only one instance of HighScoreComparator is created using the singleton pattern
        HighScoreComparator c1 = HighScoreComparator.getInstance();
        HighScoreComparator c2 = HighScoreComparator.getInstance();
        assertEquals(c1,c2);
    }
    @Test
    public void testToggleHeroFlip() {//Tests whether toggling the hero's flip changes the isHeroUpsideDown state in StickHero
        Hero hero1 = new Hero();
        ImageView heroImageView = new ImageView();
        boolean initialUpsideDown = StickHero.isIsHeroUpsideDown();
        hero1.toggleHero(heroImageView);
        assertNotEquals(initialUpsideDown, StickHero.isIsHeroUpsideDown());
    }
}

package com.iiitd.stickhero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/*
As Hero class extends StickHero, which is a collection of many items/objects of different types including a Hero "hero" object,
thus Hero class contains an object of its own type and is composed of several individual items - thus demonstrating
Composite Design Pattern
 */

public class Hero extends StickHero{

    public void stickRotate(Rectangle stick, ImageView hero_I) {
        double pivotX = stick.getX() + stick.getWidth() / 2; // X coordinate of the center of the base
        double pivotY = stick.getY() + stick.getHeight(); // Y coordinate of the bottom of the base

        Rotate rotate = new Rotate(0, pivotX, pivotY); // Initial rotation is 0 degrees
        stick.getTransforms().add(rotate);

        Timeline rotateTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rotate.angleProperty(), 90, Interpolator.LINEAR))
        );
//         Add an event handler to execute moveHero() at the end of the rotation
        rotateTimeline.setOnFinished(event -> moveHero(stick, hero_I));
        rotateTimeline.play();
    }

    public void moveHero(Rectangle stick, ImageView hero) {
        double stickLength = stick.getHeight(); // Adjust this based on your stick dimensions
        double moveDis;
        if (GameManager.getNumber() ==1) moveDis = 23;
        else moveDis = 0;

        // Create a timeline for continuous checking during the hero's movement
        Timeline checkIntersectionTimeline = new Timeline(
                new KeyFrame(Duration.millis(50), event -> {
                    if (!isCherryCollected() && isIsHeroUpsideDown() && getGameRoot().getChildren().contains(getCherryI())) {
                        if (hero.getBoundsInParent().intersects(getCherryI().getBoundsInParent())) {
                            // The hero is upside down, a cherry is present, and intersects with the hero
                            // Collect the cherry
                            setCherryCollected(true);
                            getCherryI().setVisible(false);

                        }
                    }
                    // Check if the hero is upside down and has reached just the next pillar
                    if (!isHasFallen() && isIsHeroUpsideDown() && hero.getBoundsInParent().intersects(getNextPillar().getBoundsInParent())) {
                        setHasFallen(true);
                        getCurrentStick().setVisible(false);
                        getCurrentPillar().setVisible(false);
                        getNextPillar().setVisible(false);
                        // Move the hero gradually with an interpolator
                        Timeline moveHeroBack = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(hero.translateXProperty(), hero.getTranslateX(), Interpolator.LINEAR)),
                                new KeyFrame(Duration.seconds(4.5), new KeyValue(hero.translateXProperty(), hero.getTranslateX() - stickLength-moveDis, Interpolator.EASE_BOTH))
                        );
                        moveHeroBack.play();
                        fall(stick, hero);
                    }
                }));
        checkIntersectionTimeline.setCycleCount(30);
        checkIntersectionTimeline.play();

        getGameScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.C) {
                toggleHeroFlip(hero);
            }
        });

        // Move the hero gradually with an interpolator
        Timeline moveHeroTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(hero.translateXProperty(), hero.getTranslateX(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(hero.translateXProperty(), hero.getTranslateX() + stickLength+moveDis, Interpolator.EASE_BOTH))
        );

//        moveHeroTimeline.setOnFinished(event -> {
//
//            else moveFurther(stick,hero);
//        });
        moveHeroTimeline.setOnFinished(event -> {
            if (stickLength< pillars.get(GameManager.getNumber() - 1).getGapDistance() || stickLength> pillars.get(GameManager.getNumber() - 1).getGapDistance() +pillars.get(GameManager.getNumber() -1).getWidth()) {
                setHasFallen(true);
                fall(stick, hero);
            }
            if (!isHasFallen() && isIsHeroUpsideDown()) {
                fall(stick, hero);
            } else if (!isHasFallen()) {
                moveFurther(stick, hero);
            }
        });

        if (!isHasFallen()) {
            moveHeroTimeline.play();
        }
//        if (!hasFallen) moveHeroTimeline.play();
    }
    public void toggleHeroFlip(ImageView hero) {
        double pivotX = hero.getX() + hero.getFitWidth() / 2;
        double pivotY = hero.getY() + hero.getFitHeight();
        hero.getTransforms().add(new Rotate(180, pivotX, pivotY));
        getMusicManager().flipMusic();
        setIsHeroUpsideDown(!isIsHeroUpsideDown());
    }

    public void moveFurther(Rectangle stick, ImageView hero) {
        getGameRoot().getChildren().remove(getCherryI());
        if (isCherryCollected()){
            setCherryScore(getCherryScore()+1);
            getCherryText().setText(String.valueOf(getCherryScore()));
        }
        setCherryCollected(false);
        setHasFallen(false);
        getMusicManager().nextMusic();
        setGameScore(getGameScore()+1);
        getScoreText().setText(String.valueOf(getGameScore()));
        getScorePaused().setText("Score : "+ getGameScore());
        double stickLength = stick.getHeight();

        // Move the hero gradually with an interpolator
        Timeline moveFurtherTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(hero.translateXProperty(), hero.getTranslateX(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(hero.translateXProperty(), hero.getTranslateX() + (pillars.get(GameManager.getNumber() - 1).getWidth() -(stickLength- pillars.get(GameManager.getNumber() - 1).getGapDistance())), Interpolator.EASE_BOTH))
        );
        moveFurtherTimeline.setOnFinished(event -> getGameController().sceneChange(stick,hero));
        moveFurtherTimeline.play();
        //move scene to left --> currentPillar, nextPillar, stick, hero by how much? gap+current/2+next/2; --> tp bring to centre
    }
    public void toggleHero(ImageView hero) {
        double pivotX = hero.getX() + hero.getFitWidth() / 2;
        double pivotY = hero.getY() + hero.getFitHeight();
        hero.getTransforms().add(new Rotate(180, pivotX, pivotY));
        setIsHeroUpsideDown(!isIsHeroUpsideDown());
    }
    public void fall(Rectangle stick, ImageView hero) {
        // Stop the existing timelines
        getGrowTimeline().stop();
        double fallDistance = 250;
        getMusicManager().fallMusic();

        // Fall the hero gradually with an interpolator
        Timeline fallTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(hero.translateYProperty(), hero.getTranslateY(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(hero.translateYProperty(), hero.getTranslateY() + fallDistance, Interpolator.EASE_BOTH))
        );

        fallTimeline.play();
        double pivotX = stick.getX() + stick.getWidth() / 2; // X coordinate of the center of the base
        double pivotY = stick.getY() + stick.getHeight(); // Y coordinate of the bottom of the base

        Rotate rotate = new Rotate(0, pivotX, pivotY); // Initial rotation is 0 degrees
        stick.getTransforms().add(rotate);

        Timeline rotateTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rotate.angleProperty(), 90, Interpolator.LINEAR))
        );
        rotateTimeline.setOnFinished(e-> getGameController().gameOver());
        rotateTimeline.play();
    }

}

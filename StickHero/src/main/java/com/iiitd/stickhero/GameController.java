package com.iiitd.stickhero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/*
Facade Design Pattern implementation --> The GameController class acts as a facade by providing a simplified interface to control
various aspects of the game. The methods such as togglePlayButton, saveGame, resetGame, sceneChange, nextIteration, gameOver
encapsulate complex interactions and operations involved in managing the game state, handling user input, and updating the game view.
The external components or the main game loop can interact with the GameController through these high-level methods without
needing to understand the internal details of how each operation is carried out, thus the facade pattern helps abstract away
the complexity of the game management operations. By providing a facade, we simplify the interface and make it more readable and user-friendly.
 */

public class GameController extends StickHero{

    public void reviveGame() {
        if (getCherryScore() < 3) {
            // don't have enough cherries, show a text prompt
            Text notEnoughCherriesText = new Text("You don't have enough cherries !");
            notEnoughCherriesText.setFont(Font.font("Arial", 20));
            notEnoughCherriesText.setFill(Color.RED);
            notEnoughCherriesText.setX((600 - notEnoughCherriesText.getLayoutBounds().getWidth()) / 2);
            notEnoughCherriesText.setY(90);

            getGameRoot().getChildren().add(notEnoughCherriesText);

            Timeline hideTextTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> notEnoughCherriesText.setVisible(false))
            );
            hideTextTimeline.play();
        } else {
            //have enough cherries, revive the game
            setCherryScore(getCherryScore()-3); // Deduct 3 cherries for revival
            pillars.clear();
            getScoreText().setText(String.valueOf(getGameScore()));
            getScorePaused().setText("Score : " + getGameScore());
            getCherryText().setText(String.valueOf(getCherryScore()));
            setIsHeroUpsideDown(false);
            setHasFallen(false);
            setCherryCollected(false);
            getCurrentStick().setVisible(true);
            getCurrentPillar().setVisible(true);
            getNextPillar().setVisible(true);
            GameManager.setNumber(0);
            setIsRevived(true);
            StickHero.showGameScene();
        }
    }

    public void loadGame() {
        // Display the loaded games in a green box with Text objects
        setLoadBox(new Rectangle(250, 200, Color.LIGHTGREEN));
        getLoadBox().setX((600 - getLoadBox().getWidth()) / 2);
        getLoadBox().setY((400 - getLoadBox().getHeight()) / 2);

        setLoadT(new Text("Saved Games (Click on the game):-"));
        getLoadT().setFont(Font.font("Arial", 15));
        getLoadT().setFill(Color.BLACK);
        getLoadT().setUnderline(true);
        getLoadT().setX(getLoadBox().getX() + 8);
        getLoadT().setY(getLoadBox().getY() + 25);

        getGameRoot().getChildren().addAll(getLoadBox(), getLoadT());
        getLoadBox().toFront();
        getLoadT().toFront();
        if (savedGames.isEmpty()){
            Text noSavedGames = new Text("You have no saved games !");
            noSavedGames.setFont(Font.font("Arial", 17));
            noSavedGames.setFill(Color.RED);
            noSavedGames.setX(getLoadBox().getX() + 19);
            noSavedGames.setY(getLoadT().getY()+70);
            getGameRoot().getChildren().add(noSavedGames);
            noSavedGames.toFront();
            getPlay_I().setVisible(false);
            getPause_I().setVisible(false);
            getSound_I().setVisible(false);
            getRedCross_I().setVisible(false);

            Timeline hideElementsTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1.5), event -> {
                        getLoadBox().setVisible(false);
                        getLoadT().setVisible(false);
                        noSavedGames.setVisible(false);
                    })
            );
            hideElementsTimeline.play();
        }

        for (int i = 0; i < savedGames.size(); i++) {
            GameHistory gameData = savedGames.get(i);
            setLoadText(new Text(String.format("%d. Game %d - Score: %d ; Cherry: %d", i + 1, i + 1, gameData.getScore(), gameData.getCherries())));
            getLoadText().setFont(Font.font("Arial", 15));
            getLoadText().setFill(Color.BLACK);
            getLoadText().setX(getLoadBox().getX() + 10);
            getLoadText().setY(getLoadT().getY() + 30 * (i + 1));

            // Event handler for starting the game when the text is clicked
            int finalI = i; // To make the variable effectively final for lambda expression
            getLoadText().setOnMouseClicked(event -> startLoadedGame(finalI));
            getGameRoot().getChildren().add(getLoadText());

        }
        getPlay_I().setVisible(false);
        getSound_I().setVisible(false);
        getRedCross_I().setVisible(false);

    }

    public void startLoadedGame(int finalI) {
        setGameScore(savedGames.get(finalI).getScore());
        setCherryScore(savedGames.get(finalI).getCherries());
        pillars.clear();
        getScoreText().setText(String.valueOf(getGameScore()));
        getScorePaused().setText("Score : " + getGameScore());
        getCherryText().setText(String.valueOf(getCherryScore()));
        GameManager.setNumber(0);
        setIsMute(false);
        setIsLoaded(true);
        setIsHeroUpsideDown(false);
        setHasFallen(false);
        setCherryCollected(false);
        getCurrentStick().setVisible(true);
        getCurrentPillar().setVisible(true);
        getNextPillar().setVisible(true);
        showGameScene();
    }

    public void togglePlayButton() {
        getPause_I().setVisible(!getPause_I().isVisible());
        getPlay_I().setVisible(!getPlay_I().isVisible());
        getPauseMenu().setVisible(!getPauseMenu().isVisible());
        getGamePaused().setVisible(!getGamePaused().isVisible());
        getScorePaused().setVisible(!getScorePaused().isVisible());
        getHighScorePaused().setVisible(!getHighScorePaused().isVisible());
        getRestart_I().setVisible(!getRestart_I().isVisible());
        getSave_I().setVisible(!getSave_I().isVisible());
        getLoad_I().setVisible(!getLoad_I().isVisible());
    }

    public void saveGame() {
        GameHistory gameHistory = new GameHistory(getGameScore(), getCherryScore());
        savedGames.add(0,gameHistory);
        if (savedGames.size()>5){
            savedGames.remove(5);
        }
        togglePlayButton();
        // Show the "Game Saved!" text
        getGameSavedText().toFront();
        getGameSavedText().setVisible(true);

        Timeline hideTextTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> getGameSavedText().setVisible(false))
        );
        hideTextTimeline.setOnFinished(e->togglePlayButton());
        hideTextTimeline.play();

    }

    public void resetGame() {
        // Reset game score
        setGameScore(0);
        setCherryScore(0);
        setIsHeroUpsideDown(false);
        setHasFallen(false);
        setCherryCollected(false);
        getCurrentStick().setVisible(true);
        getCurrentPillar().setVisible(true);
        getNextPillar().setVisible(true);
        pillars.clear();
        getScoreText().setText(String.valueOf(getGameScore()));
        getScorePaused().setText("Score : " + getGameScore());
        getCherryText().setText(String.valueOf(getCherryScore()));
        GameManager.setNumber(0);
    }
    public void sceneChange(Rectangle stick, ImageView hero) {

        double distanceToMove = pillars.get(GameManager.getNumber() - 1).getGapDistance() + (getCurrentPillar().getWidth() + getNextPillar().getWidth()) / 2;
        double durationInSeconds = 0.5;

        // Move the hero horizontally
        KeyValue heroKeyValue = new KeyValue(hero.translateXProperty(), hero.getTranslateX() - distanceToMove, Interpolator.LINEAR);
        KeyFrame heroKeyFrame = new KeyFrame(Duration.seconds(durationInSeconds), heroKeyValue);

        // Move the stick horizontally
        KeyValue stickKeyValue = new KeyValue(stick.translateXProperty(), stick.getTranslateX() - distanceToMove, Interpolator.LINEAR);
        KeyFrame stickKeyFrame = new KeyFrame(Duration.seconds(durationInSeconds), stickKeyValue);

        // Move the currentPillar horizontally
        KeyValue currentPillarKeyValue = new KeyValue(getCurrentPillar().translateXProperty(), getCurrentPillar().getTranslateX() - distanceToMove, Interpolator.LINEAR);
        KeyFrame currentPillarKeyFrame = new KeyFrame(Duration.seconds(durationInSeconds), currentPillarKeyValue);

        // Move the newPillar horizontally
        KeyValue newPillarKeyValue = new KeyValue(getNextPillar().translateXProperty(), getNextPillar().getTranslateX() - distanceToMove, Interpolator.LINEAR);
        KeyFrame newPillarKeyFrame = new KeyFrame(Duration.seconds(durationInSeconds), newPillarKeyValue);


        // Create a timeline and add keyframes
        Timeline timeline = new Timeline(heroKeyFrame, stickKeyFrame, currentPillarKeyFrame, newPillarKeyFrame);
        timeline.setOnFinished(event -> nextIteration());
        timeline.play();
    }

    public void nextIteration() {
        getCurrentPillar().setVisible(false);
        setCurrentPillar(getNextPillar());
        setNextPillar(getGameManager().generateRandomPillar());
        getGameRoot().getChildren().add(getNextPillar());
        getGameRoot().getChildren().remove(getCurrentStick());
        int nextCherry = getGameManager().generateRandomCherry();
        if (nextCherry!=0){
            getCherryI().setX(nextCherry);
            getCherryI().setY(220);
            getCherryI().setFitHeight(40);
            getCherryI().setFitWidth(30);
            getCherryI().setVisible(true);
            getGameRoot().getChildren().add(getCherryI());
        }
        setCurrentStick(new Rectangle(300 + (getCurrentPillar().getWidth() / 2), 220, 2, 0)); //initial height is 0 --> will increase on mouse pressed
        getCurrentStick().setFill(Color.BROWN);
        getGameRoot().getChildren().add(getCurrentStick());
    }
    public void gameOver() {
        //game over message - revive option & exit option
        scores.add(getGameScore());
        scores.sort(HighScoreComparator.getInstance());
        setHighScore(scores.get(0));
        getHighScorePaused().setText("High Score : "+ getHighScore());
        getPauseMenu().setVisible(true);
        getGameOverText().setVisible(true);
        getRestart_I().setVisible(true);
        getRevive_I().setVisible(true);
        getLoad_I().setVisible(true);
        getScorePaused().setVisible(true);
        getHighScorePaused().setVisible(true);
        getPlay_I().setVisible(false);
        getPause_I().setVisible(false);
        getSound_I().setVisible(false);
        getRedCross_I().setVisible(false);
        getPauseMenu().toFront();
        getGameOverText().toFront();
        getRestart_I().toFront();
        getRevive_I().toFront();
        getLoad_I().toFront();
        getScorePaused().toFront();
        getHighScorePaused().toFront();
    }

}

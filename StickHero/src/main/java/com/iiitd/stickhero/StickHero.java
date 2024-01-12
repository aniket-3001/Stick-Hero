package com.iiitd.stickhero;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.nio.file.Paths;

public class StickHero extends Application implements Game{
    private static Stage mainStage;
    private static Rectangle currentPillar;
    private static Rectangle nextPillar;
    private static Rectangle currentStick;
    private static GameManager gameManager;
    private static MusicController musicManager;
    private static GameController gameController;
    private static Hero hero;
    private static Group gameRoot;
    private static Scene gameScene;
    private static Timeline growTimeline;
    private static boolean isHeroUpsideDown = false;
    private static boolean isLoaded = false;
    private static boolean isRevived = false;
    private static boolean cherryCollected = false;
    private static boolean hasFallen = false;
    private static boolean isMute = false;
    private static Text scoreText;
    private static Text scorePaused;
    private static Text cherryText;
    private static Text gamePaused;
    private static Text gameOverText;
    private static Text gameSavedText;
    private static Text loadGameText;
    private static Text highScorePaused;
    private static Rectangle pauseMenu;
    private static ImageView pause_I;
    private static ImageView play_I;
    private static ImageView restart_I;
    private static ImageView save_I;
    private static ImageView revive_I;
    private static ImageView deduct_I;
    private static ImageView load_I;
    private static ImageView sound_I;
    private static ImageView redCross_I;
    private static ImageView cherryI;
    private static Rectangle loadBox;
    private static Text loadT;
    private static Text loadText;
    private static MediaPlayer bgPlayer;
    private static int gameScore = 0;
    private static int cherryScore = 0;
    private static int highScore = 0;

    public static Rectangle getCurrentPillar() {
        return currentPillar;
    }

    public static void setCurrentPillar(Rectangle currentPillar) {
        StickHero.currentPillar = currentPillar;
    }

    public static Rectangle getNextPillar() {
        return nextPillar;
    }

    public static void setNextPillar(Rectangle nextPillar) {
        StickHero.nextPillar = nextPillar;
    }

    public static Rectangle getCurrentStick() {
        return currentStick;
    }

    public static void setCurrentStick(Rectangle currentStick) {
        StickHero.currentStick = currentStick;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static void setGameManager(GameManager gameManager) {
        StickHero.gameManager = gameManager;
    }

    public static MusicController getMusicManager() {
        return musicManager;
    }

    public static void setMusicManager(MusicController musicManager) {
        StickHero.musicManager = musicManager;
    }

    public static GameController getGameController() {
        return gameController;
    }

    public static void setGameController(GameController gameController) {
        StickHero.gameController = gameController;
    }

    public static Hero getHero() {
        return hero;
    }

    public static void setHero(Hero hero) {
        StickHero.hero = hero;
    }

    public static Group getGameRoot() {
        return gameRoot;
    }

    public static void setGameRoot(Group gameRoot) {
        StickHero.gameRoot = gameRoot;
    }

    public static Scene getGameScene() {
        return gameScene;
    }

    public static void setGameScene(Scene gameScene) {
        StickHero.gameScene = gameScene;
    }

    public static Timeline getGrowTimeline() {
        return growTimeline;
    }

    public static void setGrowTimeline(Timeline growTimeline) {
        StickHero.growTimeline = growTimeline;
    }

    public static boolean isIsHeroUpsideDown() {
        return isHeroUpsideDown;
    }

    public static void setIsHeroUpsideDown(boolean isHeroUpsideDown) {
        StickHero.isHeroUpsideDown = isHeroUpsideDown;
    }

    public static boolean isIsLoaded() {
        return isLoaded;
    }

    public static void setIsLoaded(boolean isLoaded) {
        StickHero.isLoaded = isLoaded;
    }

    public static boolean isIsRevived() {
        return isRevived;
    }

    public static void setIsRevived(boolean isRevived) {
        StickHero.isRevived = isRevived;
    }

    public static boolean isCherryCollected() {
        return cherryCollected;
    }

    public static void setCherryCollected(boolean cherryCollected) {
        StickHero.cherryCollected = cherryCollected;
    }

    public static boolean isHasFallen() {
        return hasFallen;
    }

    public static void setHasFallen(boolean hasFallen) {
        StickHero.hasFallen = hasFallen;
    }

    public static boolean isIsMute() {
        return isMute;
    }

    public static void setIsMute(boolean isMute) {
        StickHero.isMute = isMute;
    }

    public static Text getScoreText() {
        return scoreText;
    }

    public static void setScoreText(Text scoreText) {
        StickHero.scoreText = scoreText;
    }

    public static Text getScorePaused() {
        return scorePaused;
    }

    public static void setScorePaused(Text scorePaused) {
        StickHero.scorePaused = scorePaused;
    }

    public static Text getCherryText() {
        return cherryText;
    }

    public static void setCherryText(Text cherryText) {
        StickHero.cherryText = cherryText;
    }

    public static Text getGamePaused() {
        return gamePaused;
    }

    public static void setGamePaused(Text gamePaused) {
        StickHero.gamePaused = gamePaused;
    }

    public static Text getGameOverText() {
        return gameOverText;
    }

    public static void setGameOverText(Text gameOverText) {
        StickHero.gameOverText = gameOverText;
    }

    public static Text getGameSavedText() {
        return gameSavedText;
    }

    public static void setGameSavedText(Text gameSavedText) {
        StickHero.gameSavedText = gameSavedText;
    }

    public static Text getLoadGameText() {
        return loadGameText;
    }

    public static void setLoadGameText(Text loadGameText) {
        StickHero.loadGameText = loadGameText;
    }

    public static Text getHighScorePaused() {
        return highScorePaused;
    }

    public static void setHighScorePaused(Text highScorePaused) {
        StickHero.highScorePaused = highScorePaused;
    }

    public static Rectangle getPauseMenu() {
        return pauseMenu;
    }

    public static void setPauseMenu(Rectangle pauseMenu) {
        StickHero.pauseMenu = pauseMenu;
    }

    public static ImageView getPause_I() {
        return pause_I;
    }

    public static void setPause_I(ImageView pause_I) {
        StickHero.pause_I = pause_I;
    }

    public static ImageView getPlay_I() {
        return play_I;
    }

    public static void setPlay_I(ImageView play_I) {
        StickHero.play_I = play_I;
    }

    public static ImageView getRestart_I() {
        return restart_I;
    }

    public static void setRestart_I(ImageView restart_I) {
        StickHero.restart_I = restart_I;
    }

    public static ImageView getSave_I() {
        return save_I;
    }

    public static void setSave_I(ImageView save_I) {
        StickHero.save_I = save_I;
    }

    public static ImageView getRevive_I() {
        return revive_I;
    }

    public static void setRevive_I(ImageView revive_I) {
        StickHero.revive_I = revive_I;
    }

    public static ImageView getDeduct_I() {
        return deduct_I;
    }

    public static void setDeduct_I(ImageView deduct_I) {
        StickHero.deduct_I = deduct_I;
    }

    public static ImageView getLoad_I() {
        return load_I;
    }

    public static void setLoad_I(ImageView load_I) {
        StickHero.load_I = load_I;
    }

    public static ImageView getSound_I() {
        return sound_I;
    }

    public static void setSound_I(ImageView sound_I) {
        StickHero.sound_I = sound_I;
    }

    public static ImageView getRedCross_I() {
        return redCross_I;
    }

    public static void setRedCross_I(ImageView redCross_I) {
        StickHero.redCross_I = redCross_I;
    }

    public static ImageView getCherryI() {
        return cherryI;
    }

    public static void setCherryI(ImageView cherryI) {
        StickHero.cherryI = cherryI;
    }

    public static Rectangle getLoadBox() {
        return loadBox;
    }

    public static void setLoadBox(Rectangle loadBox) {
        StickHero.loadBox = loadBox;
    }

    public static Text getLoadT() {
        return loadT;
    }

    public static void setLoadT(Text loadT) {
        StickHero.loadT = loadT;
    }

    public static Text getLoadText() {
        return loadText;
    }

    public static void setLoadText(Text loadText) {
        StickHero.loadText = loadText;
    }

    public static MediaPlayer getBgPlayer() {
        return bgPlayer;
    }

    public static void setBgPlayer(MediaPlayer bgPlayer) {
        StickHero.bgPlayer = bgPlayer;
    }

    public static int getGameScore() {
        return gameScore;
    }

    public static void setGameScore(int gameScore) {
        StickHero.gameScore = gameScore;
    }

    public static int getCherryScore() {
        return cherryScore;
    }

    public static void setCherryScore(int cherryScore) {
        StickHero.cherryScore = cherryScore;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int highScore) {
        StickHero.highScore = highScore;
    }

    @Override
    public void start(Stage stage) throws InterruptedException {
        mainStage = stage;
        mainStage.setResizable(false);
        Image icon = new Image("logo.png");
        mainStage.getIcons().add(icon);
        TestingThread t1 = new TestingThread();
        t1.start();
        Result result = JUnitCore.runClasses(JUnitTests2.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        pillars.clear();
        GameManager.setNumber(0);
        t1.join();
        /*The JUnit Testing is done in parallel for 2 classes of tests - one using thread t1
        created and another using the main thread. They both simultaneously do the testing and
        print their respective results, and the main thread waits for the thread t1 to finish
        its testing using join() & the program continues after the t1 has done its job.
        The output of results printed is non-deterministic as the threads are executed according
        to the scheduling policy by JVM.
         */
        isHeroUpsideDown = false;
        gameController = new GameController();
        hero = new Hero();
        String musicName = "src\\main\\resources\\gamebg.mp3";
        Media media = new Media(Paths.get(musicName).toUri().toString());
        bgPlayer = new MediaPlayer(media);
        musicManager = new MusicController();
        musicManager.bgMusic();
        showMainScene();
    }

    public void showMainScene() {
        Group root = new Group();
        mainStage.setTitle("Stick-Hero");

        Image bg = new Image("bg.jpg");
        ImageView bg_I = new ImageView(bg);
        bg_I.setFitWidth(600);
        bg_I.setFitHeight(400);

        Text startTitle = new Text("STICK HERO");
        startTitle.setFont(Font.font("Arial", 40));
        double textWidth = startTitle.getLayoutBounds().getWidth();
        double centerX = (600 - textWidth) / 2;
        startTitle.setX(centerX);
        startTitle.setY(100);

        Circle playButton = new Circle(70, Color.RED);
        playButton.setCenterX(300);
        playButton.setCenterY(200);

        Text playText = new Text("PLAY");
        playText.setFont(Font.font("Arial", 30));
        playText.setFill(Color.WHITE);
        playText.setX(playButton.getCenterX() - playText.getLayoutBounds().getWidth() / 2);
        playText.setY(playButton.getCenterY() + playText.getLayoutBounds().getHeight() / 4);

        root.getChildren().addAll(bg_I, startTitle, playButton, playText);

        EventHandler<MouseEvent> playButtonClickHandler = event -> showGameScene();
        playButton.setOnMouseClicked(playButtonClickHandler);
        playText.setOnMouseClicked(playButtonClickHandler);

        Scene scene = new Scene(root, 600, 400);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void showGameScene() {
        gameManager = new GameManager();
        gameRoot = new Group();
        Image gameBg = new Image("bg.jpg");
        ImageView gameBg_I = new ImageView(gameBg);
        gameBg_I.setFitWidth(600);
        gameBg_I.setFitHeight(400);

//        Image home = new Image("home.png");
//        ImageView home_I = new ImageView(home);
//        home_I.setX(234);
//        home_I.setY(25);
//        home_I.setFitHeight(36);
//        home_I.setFitWidth(36);

        Image sound = new Image("sound.png");
        sound_I = new ImageView(sound);
        sound_I.setX(265);
        sound_I.setY(22);
        sound_I.setFitHeight(42);
        sound_I.setFitWidth(45);

        Image redCross = new Image("cross.png");
        redCross_I = new ImageView(redCross);
        redCross_I.setX(267);
        redCross_I.setY(22);
        redCross_I.setFitHeight(42);
        redCross_I.setFitWidth(45);
        redCross_I.setVisible(false);

        EventHandler<MouseEvent> toggleRedCross = event -> {
            redCross_I.setVisible(!redCross_I.isVisible());
            isMute = !isMute;
            bgPlayer.setMute(redCross_I.isVisible());
        };
        sound_I.setOnMouseClicked(toggleRedCross);
        redCross_I.setOnMouseClicked(toggleRedCross);

        Image pause = new Image("play.png");
        pause_I = new ImageView(pause);
        pause_I.setX(331);
        pause_I.setY(17);
        pause_I.setFitHeight(58);
        pause_I.setFitWidth(65);

        Image play = new Image("pause.png");
        play_I = new ImageView(play);
        play_I.setX(331);
        play_I.setY(12);
        play_I.setFitHeight(67);
        play_I.setFitWidth(67);
        play_I.setVisible(false);

//        EventHandler<MouseEvent> goToMainSceneHandler = event -> showMainScene();
//        home_I.setOnMouseClicked(goToMainSceneHandler);

        pauseMenu = new Rectangle(200, 200, Color.DARKGREEN);
        pauseMenu.setX((600 - pauseMenu.getWidth()) / 2);
        pauseMenu.setY((400 - pauseMenu.getHeight()) / 2);
        pauseMenu.setVisible(false);

        gamePaused = new Text("Game Paused !");
        gamePaused.setFont(Font.font("Arial", 24));
        gamePaused.setFill(Color.YELLOW);
        gamePaused.setX(215);
        gamePaused.setY(140);
        gamePaused.setVisible(false);

        gameOverText = new Text("Game Over !");
        gameOverText.setFont(Font.font("Arial", 24));
        gameOverText.setFill(Color.YELLOW);
        gameOverText.setX(215);
        gameOverText.setY(140);
        gameOverText.setVisible(false);

        scorePaused = new Text("Score : "+ gameScore);
        scorePaused.setFont(Font.font("Arial", 24));
        scorePaused.setFill(Color.LIGHTSKYBLUE);
        scorePaused.setX(215);
        scorePaused.setY(180);
        scorePaused.setVisible(false);

        highScorePaused = new Text("High Score : "+highScore); //update using comparator
        highScorePaused.setFont(Font.font("Arial", 24));
        highScorePaused.setFill(Color.LIGHTSKYBLUE);
        highScorePaused.setX(215);
        highScorePaused.setY(220);
        highScorePaused.setVisible(false);

        Image restart = new Image("reload.png");
        restart_I = new ImageView(restart);
        restart_I.setX(215);
        restart_I.setY(245);
        restart_I.setFitHeight(36);
        restart_I.setFitWidth(37);
        restart_I.setVisible(false);

        // Event handler for restarting the game
        EventHandler<MouseEvent> restartGameHandler = event -> {
            gameController.resetGame();
            showGameScene(); //generate the first pillar and set up the scene
        };
        restart_I.setOnMouseClicked(restartGameHandler);

        Image save = new Image("save.png");
        save_I = new ImageView(save);
        save_I.setX(280);
        save_I.setY(245);
        save_I.setFitHeight(36);
        save_I.setFitWidth(37);
        save_I.setVisible(false);

        Image revive = new Image("revive.png");
        revive_I = new ImageView(revive);
        revive_I.setX(260);
        revive_I.setY(233);
        revive_I.setFitHeight(60);
        revive_I.setFitWidth(80);
        revive_I.setVisible(false);

        // Event handler for reviving the game
        EventHandler<MouseEvent> reviveGameHandler = event -> gameController.reviveGame();
        revive_I.setOnMouseClicked(reviveGameHandler);

        Image deduct = new Image("deduct.png");
        deduct_I = new ImageView(deduct);
        deduct_I.setX(260);
        deduct_I.setY(90);
        deduct_I.setFitHeight(100);
        deduct_I.setFitWidth(100);
        deduct_I.setVisible(false);

        gameSavedText = new Text("Game Saved!");
        gameSavedText.setFont(Font.font("Arial", 30));
        gameSavedText.setFill(Color.RED);
        gameSavedText.setX((600 - gameSavedText.getLayoutBounds().getWidth()) / 2);
        gameSavedText.setY(150);
        gameSavedText.setVisible(false);

        loadGameText = new Text("Game Loaded Successfully!");
        loadGameText.setFont(Font.font("Arial", 30));
        loadGameText.setFill(Color.RED);
        loadGameText.setX((600 - loadGameText.getLayoutBounds().getWidth()) / 2);
        loadGameText.setY(150);
        loadGameText.setVisible(false);

        if (isLoaded){
            loadGameText.toFront();
            loadGameText.setVisible(true);

            Timeline hideTextTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> loadGameText.setVisible(false))
            );
            hideTextTimeline.setOnFinished(e-> isLoaded = false);
            hideTextTimeline.play();
        }
        if (isRevived){
            deduct_I.toFront();
            deduct_I.setVisible(true);

            Timeline hideTextTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> deduct_I.setVisible(false))
            );
            hideTextTimeline.setOnFinished(e-> isRevived = false);
            hideTextTimeline.play();
        }

        Image load = new Image("load.png");
        load_I = new ImageView(load);
        load_I.setX(340);
        load_I.setY(240);
        load_I.setFitHeight(50);
        load_I.setFitWidth(51);
        load_I.setVisible(false);

        // Event handler for loading the game
        EventHandler<MouseEvent> loadGameHandler = event -> {
            gameController.loadGame();
//            showGameScene(); //generate the first pillar and set up the scene
        };
        load_I.setOnMouseClicked(loadGameHandler);

        EventHandler<MouseEvent> togglePlayButton = event -> {
            pause_I.setVisible(!pause_I.isVisible());
            play_I.setVisible(!play_I.isVisible());
            pauseMenu.setVisible(!pauseMenu.isVisible());
            gamePaused.setVisible(!gamePaused.isVisible());
            scorePaused.setVisible(!scorePaused.isVisible());
            highScorePaused.setVisible(!highScorePaused.isVisible());
            restart_I.setVisible(!restart_I.isVisible());
            save_I.setVisible(!save_I.isVisible());
            load_I.setVisible(!load_I.isVisible());
            // Bring elements to front
            pauseMenu.toFront();
            gamePaused.toFront();
            scorePaused.toFront();
            highScorePaused.toFront();
            restart_I.toFront();
            save_I.toFront();
            load_I.toFront();
        };

        pause_I.setOnMouseClicked(togglePlayButton);
        play_I.setOnMouseClicked(togglePlayButton);

        // Event handler for saving the game
        EventHandler<MouseEvent> saveGameHandler = event -> gameController.saveGame();
        save_I.setOnMouseClicked(saveGameHandler);

        Image hero1 = new Image("hero1.png");
        ImageView hero_I = new ImageView(hero1);
        hero_I.setX(280);
        hero_I.setY(186);
        hero_I.setFitHeight(36);
        hero_I.setFitWidth(37);

        Image cherry1 = new Image("cherry1.jpg");
        ImageView cherry_I1 = new ImageView(cherry1);
        cherry_I1.setX(520);
        cherry_I1.setY(10);
        cherry_I1.setFitHeight(70);
        cherry_I1.setFitWidth(60);

        Image cherry = new Image("cherrybg.png");
        cherryI = new ImageView(cherry);

        Text score = new Text("Score : ");
        score.setFont(Font.font("Arial", 35));
        score.setFill(Color.BLACK);
        score.setX(40);
        score.setY(55);

        scoreText = new Text(String.valueOf(gameScore));
        scoreText.setFont(Font.font("Arial", 50));
        scoreText.setFill(Color.RED);
        scoreText.setX(160);
        scoreText.setY(60);

        cherryText = new Text(String.valueOf(cherryScore));
        cherryText.setFont(Font.font("Arial", 50));
        cherryText.setFill(Color.BLACK);
        cherryText.setX(475);
        cherryText.setY(62);
        //update this cherry score when collect cherries

        currentPillar = new Rectangle(270,220,60,180);
        currentPillar.setFill(Color.BLACK);
        currentStick = new Rectangle(330,220,2,0); //initial height is 0 --> will increase on mouse pressed
        currentStick.setFill(Color.BROWN);

        // Mouse press event handler
        gameBg_I.setOnMousePressed(event -> {
            musicManager.stickGrowMusic();
            double growthSpeed = 1;
            double maxHeight = 300;
            growTimeline = new Timeline();
            growTimeline.getKeyFrames().clear();
            KeyFrame keyFrame = new KeyFrame(Duration.millis(10), e -> {
                if (currentStick.getHeight() < maxHeight) {
                    currentStick.setHeight(currentStick.getHeight() + growthSpeed);
                    currentStick.setY(220 - currentStick.getHeight());
                }
            });
            growTimeline.getKeyFrames().add(keyFrame);
            growTimeline.setCycleCount(Animation.INDEFINITE);
            growTimeline.play();
        });

        // Mouse release event handler
        gameBg_I.setOnMouseReleased(event -> {
            musicManager.getMediaPlayer1().stop();
            growTimeline.stop();
            hero.stickRotate(currentStick, hero_I);
        });

        gameRoot.getChildren().addAll(gameBg_I, sound_I, pause_I,play_I, redCross_I,currentPillar,hero_I,currentStick,score,scoreText,cherry_I1,cherryText,pauseMenu,gamePaused,scorePaused,highScorePaused,restart_I,save_I,revive_I,deduct_I,load_I,gameSavedText,loadGameText,gameOverText);
        nextPillar = gameManager.generateRandomPillar();
        gameRoot.getChildren().add(nextPillar);

        gameScene = new Scene(gameRoot, 600, 400);
        //window close request handler
        mainStage.setOnCloseRequest(event -> {
            event.consume();
            confirmExit(mainStage);
        });
        mainStage.setScene(gameScene);
    }

    private static void confirmExit(Stage primaryStage) { //private method because helper method
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Closing Game");
        alert.setHeaderText("Exit Stick Hero Game");
        alert.setContentText("Are you sure you want to exit?");

        alert.getButtonTypes().setAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                System.out.println("Exiting the Game");
                primaryStage.close();
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}

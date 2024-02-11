# Stick-Hero
This repository contains the source code for Stick Hero game (adaptation of the popular game with same name) implemented in Java with JavaFX libraries as part of the Advanced Programming course project. It also contains UML Class Diagrams, Use Case Diagrams & Screen Captures of the game.

AP Project (Section A) - Stick Hero Game

Name - Aniket Gupta (aniket22073@iiitd.ac.in)
Roll No. - 2022073

Project Description ->
Stick Hero is a Java-based game implemented using JavaFX library for the GUI. The game challenges players to extend a stick across platforms 
to help their hero traverse the gaps. Players need to carefully time the stick extension to bridge the gap between platforms. The game 
incorporates various Object-Oriented Programming (OOP) principles, design patterns, multithreading and JUnit tests. The code is organized 
into classes that encapsulate distinct entities and functionalities, fostering a clear and maintainable structure.

Object-Oriented Principles Applied ->

1. Abstraction: Classes are designed to encapsulate related functionalities. For example, the GameController class encapsulates game
management operations, and the Hero class encapsulates hero-related actions. The GameController class acts as a facade, providing a 
simplified interface for game control operations, abstracting away the complexity of internal details.

2. Inheritance: The game leverages inheritance, such as the Hero class extending the StickHero class. The Hero class is composed of 
various items, demonstrating the Composite Design Pattern.

3. Interfaces - In my code, The "Game" interface serves as a common interface for different components of the game, such as 
Controller, Manager, and Hero. By providing a shared set of data & methods, it enforces consistency in how these components are managed.

4. Relationships Between Classes: The program demonstrates the composition of objects, such as gameController holding pillars 
object and hero which being used in various parts of the program. The Game Interface has an ArrayList of various Class' objects type 
like GameHistory, Pillars, Scores, etc.

5. Encapsulation and Data Hiding: The code uses private instance variables at appropriate places and getter and setter methods to 
encapsulate data and hide it from direct access.

* The program follows all the good programming practices mentioned in the assignment document.

Design Patterns implemented -->
1. Singleton Design Pattern
The HighScoreComparator class utilizes the Singleton Design Pattern. It ensures that only one comparator object exists, saving memory by 
preventing the creation of multiple comparator instances.
2. Facade Design Pattern
The GameController class implements the Facade Design Pattern by providing simplified methods for managing game state, handling user input, 
and updating the view. External components interact with the GameController through high-level methods without needing to understand 
internal details. The facade pattern simplifies the interface, making it more readable and user-friendly.
3. Composite Design Pattern
The Hero class demonstrates the Composite Design Pattern. It extends the StickHero class, which is a collection of various items, and contains 
an object of its own type. The Hero class is composed of individual items, showcasing the composite structure.
4. Factory Design Pattern
The GameManager class implements the Factory Design Pattern. It encapsulates the creation logic for generating pillars and cherries, providing
an easy extension for adding new game elements in the future.

Implementation details of the classes -->

StickHero -- It is the main class of the game, which sets up the home and game screen using several text and images components.

GameController -- The GameController class manages game state, user input, and view updates. It includes methods for reviving 
the game, loading/saving games, toggling play button, saving the game, resetting the game, scene transitions, and handling game over scenarios.

Hero -- The Hero class extends StickHero and demonstrates the Composite Design Pattern. It handles hero actions, such as rotating the stick, 
moving the hero, and collecting cherries. The class also manages the hero's state, including upside-down and falling conditions.

GameManager --  The GameManager class implements the Factory Design Pattern, & serves as a factory for generating random pillars and 
cherries. It maintains the game level and provides methods to create new pillars and cherries, encapsulating the creation logic and 
providing a flexible way to extend the game with new elements in the future.

HighScoreComparator -- The HighScoreComparator class implements the Singleton Design Pattern, providing a comparator for high scores. It 
compares scores in descending order (higher to lower).

MusicController -- The MusicController class in the Stick Hero game manages various sound effects and background music during different 
game events. It utilizes JavaFX's MediaPlayer for playing audio files. Below are the audio files used:

Audio Files -- 
The audio files are stored in the "src/main/resources/" directory:
Background Music: "gamebg.mp3"
Stick Growth Sound Effect: "stick.mp3"
Falling Sound Effect: "falling.mp3"
Flip Sound Effect: "flip.mp3"
Next Level Sound Effect: "next.mp3"
Ensure that these audio files are present in the specified directory for the music controller to function correctly.

TestingThread -- The TestingThread class runs JUnit tests for the Stick Hero game. It is a separate thread to execute tests concurrently
with the main game. It executes the tests defined in the JUnitTests1 class.

JUnit Tests -- JUnit tests are divided into two classes, JUnitTests1 and JUnitTests2. They test functionalities such as generating 
random pillars, checking game scores, and toggling hero flips. The tests are organized to ensure the correctness of critical game mechanisms.
The tests include whether the width of the generated pillar falls within the expected bounds, the game score is non-negative, tests 
whether only one instance of HighScoreComparator is created using the singleton pattern, tests whether toggling the hero's flip 
changes the isHeroUpsideDown state in StickHero.


**How to Use (User Guide to Execute) ->** <br>
Source Code (Main class & other classes) is present inside the src folder inside the main folder <br>
The images used for the GUI and audio files are present inside the src folder inside the resources folder <br>
Pre-Requisites --> <br>
[IntelliJ IDEA](https://www.jetbrains.com/idea/) installed on your machine. (or any Java IDE) <br>
[Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) installed. <br>
Add JavaFX library to the project in Project Structure option. <br>
Add necessary dependencies like Media in pom.xml if you are using maven to run the code. (Already added in the pom.xml uploaded) <br>
Adjust the package line at the top of each Java file to match your IntelliJ directory structure. <br>
If you face any difficulty in running the files after following the above steps, create a new "JavaFX" project using your own IntelliJ, create the same java files and copy my code in those files. Ensure that the audio files mentioned above and the image files are present in the resources folder for the code to function correctly. <br>
Type this command to run Java file <br>
mvn clean javafx:run

Gameplay -->
1. Click and hold the mouse on the background to extend the stick.
2. Release the mouse to make the hero walk on the extended stick.
3. Collect cherries to increase your score by flipping using key "C".

Features -->
1. Dynamic pillar generation.
2. Background music and sound effects.
3. Pause and resume functionality.
4. Game saving and loading.
5. Simple and intuitive controls.
6. Reviving feature if you have 3 cherries.
7. JUnit testing incorporated.
8. Multithreading introduced to run the tests parallely.

Assumptions/Points to keep in mind for the Project ->
1. Note that valid input should be provided like when playing the game.
2. The hero moves automatically after the stick length is set from pressing of mouse on the background (not on any element like pillar). 
Avoid pressing the mouse button again during this movement to prevent unexpected behavior.
3. If a cherry is present on the screen between the pillars, the hero can collect it while moving using "C". Cherries can be used
for reviving. You are allowed to revive if you have atleast 3 cherries, which are deducted on revival.
4. Use the provided options to pause, restart, save and load games. Saved games can be selected from the list for 
continuation. Only 5 games can be saved. Games saved before that are removed.
5. Use the sound control options to mute or unmute the in-game sounds based on your preference.
6. Strive to achieve a high score and compete against your own best scores and ENJOY!!!

->Follow the steps outlined above to interact with the game application effectively. <br>
->The UML diagram was made during mid-submission (deadline 1) of the project, so it may differ a little from the final source code.

Conclusion ->
This Stick Hero game demonstrates the effective use of OOP principles, including interfaces, inheritance, encapsulation & design patterns to 
create a structured and maintainable codebase. It also has JUnit testing organized to ensure the correctness of critical game mechanisms.
The game also incorporates multithreading approach that allows the JUnit tests to run concurrently with the main game, providing a way 
to test the game's functionality independently and concurrently. It separates concerns into distinct classes, each responsible for 
specific functionalities, resulting in a well-organized and easy-to-maintain system.

📌 Important: Please make sure to follow the guidelines and policies outlined by the institution regarding the use of shared coursework materials. Use this repository responsibly and avoid any violations of academic integrity. Codes are provided for reference purposes only. It's recommended to understand the solutions and implement them independently.

# Number Guessing Game (JavaFX + Maven)

A simple Number Guessing Game built with Java, JavaFX, and Maven.  
The player tries to guess a randomly generated secret number within a limited number of attempts.

## Features

- GUI built with JavaFX (no console).
- Three difficulty levels:
    - Easy: 1–50, 8 attempts
    - Normal: 1–100, 10 attempts
    - Hard: 1–500, 12 attempts
- Visual feedback for:
    - Too high / too low guesses
    - Invalid input or out-of-range values
    - Game Over (out of attempts)
    - Success (correct guess)
- Simple stats (per session, in memory):
    - Total games played
    - Total wins
    - Best game (fewest attempts for a correct guess)

## Requirements

- JDK 25 (or compatible) installed and on `PATH`.
- Maven 3.8+ installed.
- JavaFX SDK 25.x downloaded separately (JavaFX is no longer bundled with the JDK).  
  You can download it from the official OpenJFX site: https://openjfx.io [web:4][web:24]

## Project Structure

number-guessing-game/
├── pom.xml
├── README.md
├── javafx-sdk-25.0.1/ # JavaFX SDK (not committed if you prefer)
├── src/
│ ├── main/
│ │ └── java/
│ │ └── com/
│ │ └── games/
│ │ └── NumberGuessingGameApp.java
│ └── test/
│ └── java/
│ └── com/
│ └── games/
│ └── NumberGuessingGameAppTest.java
└── target/


## Running the Game (Development)

The easiest way to run the game during development is via the JavaFX Maven plugin:

mvn javafx:run


This uses the configuration in `pom.xml` to launch the JavaFX application. [web:4][web:17]

## Building and Running the JAR

1. Build the project:

mvn clean package


This will generate `target/number-guessing-game-1.0-SNAPSHOT.jar`.

2. Ensure you have the JavaFX SDK extracted, for example in:

javafx-sdk-25.0.1/
└── lib/
├── javafx-controls.jar
├── javafx-fxml.jar
└── ...


3. Run the JAR with the JavaFX modules on the module path (Windows example):

java --module-path ".\javafx-sdk-25.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar .\target\number-guessing-game-1.0-SNAPSHOT.jar


Adjust the `javafx-sdk` path if you keep it elsewhere. [web:4][web:35]

## Notes

- The JavaFX SDK directory is typically not committed to version control.  
  You can:
    - Add it to `.gitignore`, and
    - Document the download location/path in this README (already done).
- You can tweak the difficulty ranges and attempt limits inside `NumberGuessingGameApp.java`.

## License

Choose a license you prefer (for example MIT, Apache-2.0, or GPL) and add a `LICENSE` file.  
Until then, this project can be considered “all rights reserved” by default. [web:7]
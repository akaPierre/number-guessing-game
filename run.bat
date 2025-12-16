@echo off
echo Starting Number Guessing Game...
java --module-path ".\javafx-sdk-25.0.1
\lib" --add-modules javafx.controls,javafx.fxml -jar target\number-guessing-game-1.0-SNAPSHOT.jar
pause
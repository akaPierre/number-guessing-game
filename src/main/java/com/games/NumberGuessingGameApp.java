package com.games;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.security.SecureRandom;

public class NumberGuessingGameApp extends Application {

    private final SecureRandom random = new SecureRandom();

    // Game state
    private int secretNumber;
    private int attempts;
    private int maxAttempts;
    private int minNumber;
    private int maxNumber;
    private boolean gameOver;

    // Stats
    private int totalGames = 0;
    private int wins = 0;
    private Integer bestAttempts = null; // null = no win yet

    // UI controls
    private Label titleLabel;
    private Label statusLabel;
    private TextField guessField;
    private Button guessButton;
    private Button newGameButton;
    private Label attemptsLabel;
    private Label rangeLabel;
    private Label statsLabel;
    private ComboBox<String> difficultyBox;

    @Override
    public void start(Stage primaryStage) {
        // Default difficulty: Normal
        setupDifficulty("Normal");
        attempts = 0;
        gameOver = false;

        createUI(primaryStage);
        startNewGame(); // initialize first game based on default difficulty
        primaryStage.show();
    }

    private void createUI(Stage stage) {
        // Title
        titleLabel = new Label("🔢 Number Guessing Game");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: #2c3e50;");

        // Difficulty selector
        Label difficultyLabel = new Label("Difficulty:");
        difficultyLabel.setFont(Font.font("Arial", 14));

        difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy (1-50)", "Normal (1-100)", "Hard (1-500)");
        difficultyBox.setValue("Normal (1-100)");
        difficultyBox.setOnAction(e -> {
            String selected = difficultyBox.getValue();
            if (selected.startsWith("Easy")) {
                setupDifficulty("Easy");
            } else if (selected.startsWith("Hard")) {
                setupDifficulty("Hard");
            } else {
                setupDifficulty("Normal");
            }
            startNewGame();
        });

        HBox difficultyRow = new HBox(10, difficultyLabel, difficultyBox);
        difficultyRow.setAlignment(Pos.CENTER);

        // Range info
        rangeLabel = new Label();
        rangeLabel.setFont(Font.font("Arial", 14));
        rangeLabel.setStyle("-fx-text-fill: #34495e;");

        // Status message
        statusLabel = new Label();
        statusLabel.setFont(Font.font("Arial", 16));
        statusLabel.setStyle("-fx-text-fill: #34495e;");
        statusLabel.setPadding(new Insets(10, 0, 10, 0));

        // Input field
        guessField = new TextField();
        guessField.setPromptText("Enter your guess...");
        guessField.setMaxWidth(200);
        guessField.setFont(Font.font("Arial", 14));

        // Guess button
        guessButton = new Button("Make Guess");
        guessButton.setFont(Font.font("Arial", 14));
        guessButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        guessButton.setOnAction(e -> handleGuess());

        // New Game button
        newGameButton = new Button("New Game");
        newGameButton.setFont(Font.font("Arial", 14));
        newGameButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        newGameButton.setOnAction(e -> startNewGame());

        HBox buttonsRow = new HBox(10, guessButton, newGameButton);
        buttonsRow.setAlignment(Pos.CENTER);

        // Attempts counter
        attemptsLabel = new Label("Attempts: 0 / 0");
        attemptsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        attemptsLabel.setStyle("-fx-text-fill: #e74c3c;");

        // Stats label
        statsLabel = new Label();
        statsLabel.setFont(Font.font("Arial", 13));
        statsLabel.setStyle("-fx-text-fill: #2c3e50;");
        updateStatsLabel();

        // Layout
        VBox root = new VBox(12);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.getChildren().addAll(
                titleLabel,
                difficultyRow,
                rangeLabel,
                statusLabel,
                guessField,
                buttonsRow,
                attemptsLabel,
                statsLabel
        );

        Scene scene = new Scene(root, 460, 380);
        stage.setTitle("Number Guessing Game");
        stage.setScene(scene);
        stage.setResizable(false);

        // Enter key support
        guessField.setOnAction(e -> handleGuess());
    }

    private void setupDifficulty(String level) {
        switch (level) {
            case "Easy":
                minNumber = 1;
                maxNumber = 50;
                maxAttempts = 8;
                break;
            case "Hard":
                minNumber = 1;
                maxNumber = 500;
                maxAttempts = 12;
                break;
            case "Normal":
            default:
                minNumber = 1;
                maxNumber = 100;
                maxAttempts = 10;
                break;
        }
        if (rangeLabel != null) {
            rangeLabel.setText("Range: " + minNumber + " to " + maxNumber + " | Max attempts: " + maxAttempts);
        }
    }

    private void startNewGame() {
        secretNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        attempts = 0;
        gameOver = false;
        guessButton.setDisable(false);
        statusLabel.setText("New game started! Guess a number between " + minNumber + " and " + maxNumber + ".");
        statusLabel.setStyle("-fx-text-fill: #34495e;");
        attemptsLabel.setText("Attempts: 0 / " + maxAttempts);
        guessField.clear();
        guessField.requestFocus();
        totalGames++;
        updateStatsLabel();
    }

    private void handleGuess() {
        if (gameOver) {
            showMessage("Game is over. Click 'New Game' to play again.", Color.ORANGE);
            return;
        }

        String text = guessField.getText().trim();
        if (text.isEmpty()) {
            showMessage("Please enter a number.", Color.ORANGE);
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid integer.", Color.ORANGE);
            guessField.clear();
            guessField.requestFocus();
            return;
        }

        if (guess < minNumber || guess > maxNumber) {
            showMessage("Your guess must be between " + minNumber + " and " + maxNumber + ".", Color.ORANGE);
            guessField.clear();
            guessField.requestFocus();
            return;
        }

        attempts++;
        updateAttemptsLabel();

        if (guess == secretNumber) {
            wins++;
            gameOver = true;
            guessButton.setDisable(true);
            showMessage("🎉 Correct! You guessed it in " + attempts + " attempts.", Color.GREEN);

            if (bestAttempts == null || attempts < bestAttempts) {
                bestAttempts = attempts;
            }
            updateStatsLabel();
        } else {
            if (attempts >= maxAttempts) {
                gameOver = true;
                guessButton.setDisable(true);
                showMessage("💀 Game Over! The number was " + secretNumber + ".", Color.RED);
                updateStatsLabel();
            } else if (guess < secretNumber) {
                showMessage("📈 Too low! Try higher.", Color.BLUE);
            } else {
                showMessage("📉 Too high! Try lower.", Color.RED);
            }
        }

        guessField.clear();
        guessField.requestFocus();
    }

    private void showMessage(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + toHexString(color) + ";");
    }

    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private void updateAttemptsLabel() {
        attemptsLabel.setText("Attempts: " + attempts + " / " + maxAttempts);
    }

    private void updateStatsLabel() {
        StringBuilder sb = new StringBuilder();
        sb.append("Games played: ").append(totalGames)
                .append(" | Wins: ").append(wins);
        if (bestAttempts != null) {
            sb.append(" | Best game: ").append(bestAttempts).append(" attempts");
        } else {
            sb.append(" | Best game: N/A");
        }
        statsLabel.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
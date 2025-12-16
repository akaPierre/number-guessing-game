# 🎯 Number Guessing Game (JavaFX + Maven)

![Java](https://img.shields.io/badge/Java-25+-orange?style=for-the-badge&logo=openjdk)
![JavaFX](https://img.shields.io/badge/JavaFX-25-blue?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![GitHub stars](https://img.shields.io/github/stars/akaPierre/number-guessing-game?style=for-the-badge)

A clean and beginner‑friendly **Number Guessing Game** built with **Java**, **JavaFX**, and **Maven**.

The goal is simple: the game randomly generates a secret number, and the player must guess it within a limited number of attempts. Feedback is provided after every guess, making it easy and fun to play.

This project is ideal for:
- Learning JavaFX basics
- Practicing GUI logic and input validation
- Understanding Maven project structure
- Showcasing a simple desktop app on GitHub

---

## ✨ Features

- Fully graphical interface (JavaFX — no console interaction)
- Three difficulty levels:
  - **Easy**: numbers from **1–50**, **8 attempts**
  - **Normal**: numbers from **1–100**, **10 attempts**
  - **Hard**: numbers from **1–500**, **12 attempts**
- Clear visual feedback for:
  - Guess is too high or too low
  - Invalid or out‑of‑range input
  - Game over (no attempts left)
  - Winning the game 🎉
- Simple session statistics (in memory):
  - Total games played
  - Total wins
  - Best game (fewest attempts to win)

---

## 🛠️ Tech Stack

- **Java** (JDK 21+ recommended, tested with JDK 25)
- **JavaFX** (SDK 25.x)
- **Maven** (3.8+)
- **JUnit** (basic testing structure included)

---

## 📋 Requirements

Before running the project, make sure you have:

- Java JDK installed and available on your `PATH`
- Maven installed (`mvn -v` should work)
- JavaFX SDK downloaded separately (JavaFX is no longer bundled with the JDK)

👉 Download JavaFX from: https://openjfx.io

---

## 📁 Project Structure

```
number-guessing-game/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/games/
│   │           └── NumberGuessingGameApp.java
│   └── test/
│       └── java/
│           └── com/games/
│               └── NumberGuessingGameAppTest.java
└── target/
```

> ⚠️ The JavaFX SDK folder is **not** committed to the repository and should be downloaded locally.

---

## ▶️ Running the Game (Development)

The easiest way to run the game during development is using the JavaFX Maven plugin:

```bash
mvn javafx:run
```

This uses the configuration defined in `pom.xml` to launch the application.

---

## 📦 Building and Running the JAR

### 1️⃣ Build the project

```bash
mvn clean package
```

This will generate the JAR file:

```
target/number-guessing-game-1.0-SNAPSHOT.jar
```

---

### 2️⃣ Run the JAR with JavaFX

Since JavaFX is external, you must provide its modules at runtime.

**Windows example:**

```bash
java --module-path ".\javafx-sdk-25.0.1\lib" \
     --add-modules javafx.controls,javafx.fxml \
     -jar .\target\number-guessing-game-1.0-SNAPSHOT.jar
```

Adjust the JavaFX SDK path if it is located elsewhere or if you are on Linux/macOS.

---

## 📝 Notes

- The JavaFX SDK directory should be added to `.gitignore`
- Difficulty ranges and attempt limits can be easily adjusted in:
  - `NumberGuessingGameApp.java`
- Statistics are stored **in memory only** and reset when the app closes

---

## 📄 License

MIT License

Copyright (c) 2025 Daniel Pierre Fachini de Toledo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

## 🚀 Future Improvements (Ideas)

- Persist statistics using a file or database
- Add sound effects or animations
- Add a timer‑based challenge mode

---

## 👤 Author

**Daniel Pierre Fachini de Toledo**  
GitHub: https://github.com/akaPierre

---

If you have suggestions or want to extend this project, feel free to fork it or open an issue 🙂


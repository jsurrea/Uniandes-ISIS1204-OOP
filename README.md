# Uniandes-ISIS1204-OOP

Collection of three Object-Oriented Programming course projects (ISIS1204, Universidad de los Andes), based on Cupi2 exercises.

![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Java%20Swing-1f6feb)
![HTML](https://img.shields.io/badge/Web-HTML5-e34f26?logo=html5&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green)

## Projects

### 1) Sudoku (`Sudoku/`)
Desktop Java Swing application with configurable Sudoku boards loaded from `.properties` files.

**Run:**
```bash
cd Sudoku
javac -encoding UTF-8 -d bin $(find source -name "*.java")
java -cp bin uniandes.cupi2.sudoku.interfaz.InterfazSudoku
```

<img src="https://github.com/user-attachments/assets/fdfe542f-48be-4dfe-8f4f-dc3216361568">

### 2) Guess the Code (`GuessTheCode/`)
Client-server Java Swing game where two players connect to a server and play code-guessing rounds.

- Server configuration is read from `GuessTheCode/data/servidor.properties`
- Uses embedded Apache Derby through `GuessTheCode/lib/derby.jar`

**Compile:**
```bash
cd GuessTheCode
javac -encoding UTF-8 -d bin $(find source -name "*.java")
```

**Run server + two clients (separate terminals):**
```bash
cd GuessTheCode
java -cp "bin:lib/derby.jar" uniandes.cupi2.cupiCode.servidor.interfaz.InterfazCupiCode
```

```bash
cd GuessTheCode
java -cp bin uniandes.cupi2.cupiCode.cliente.interfaz.InterfazJugador
```

```bash
cd GuessTheCode
java -cp bin uniandes.cupi2.cupiCode.cliente.interfaz.InterfazJugador
```

<img src="https://github.com/user-attachments/assets/6883fb66-4230-4242-b672-27017e53b9b3">

### 3) Lights Out (`LightsOut/`)
Static HTML puzzle page (`lucesapagadas.html`) with a 7x7 button grid and image assets.

**Open:**
```bash
cd LightsOut
# Open lucesapagadas.html in your browser
```

<img src="https://github.com/user-attachments/assets/9bfd989e-8e47-4b02-83d1-305117837d97">

## Project structure

```text
.
├── GuessTheCode/
│   ├── source/                       # Java client/server source
│   ├── test/source/                  # JUnit-style test class for Codigo
│   ├── data/servidor.properties      # Server + DB configuration
│   └── lib/derby.jar                 # Embedded Derby dependency
├── Sudoku/
│   ├── source/                       # Java Swing + game logic
│   └── data/                         # Board definitions and images
├── LightsOut/
│   ├── lucesapagadas.html            # Static page entry point
│   └── img/                          # Web images
└── README.md
```

## Tests

The repository contains `GuessTheCode/test/source/uniandes/cupi2/cupiCode/testCliente/CodigoTest.java` (JUnit 3 style). The JUnit dependency is not included in this repository, so tests require adding JUnit to the classpath before execution.

## License

This repository is licensed under the MIT License. See [LICENSE](LICENSE).

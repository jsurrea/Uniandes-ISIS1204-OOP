# Uniandes-ISIS1204-OOP

This is a collection of projects I worked on for the courses on Object Oriented Programming at Uniandes. All credits due to the [Proyecto Cupi2](https://cupi2.virtual.uniandes.edu.co) team.

The projects are meant to be opened with Eclipse, but you should be able to compile them with the following commands:

## Sudoku

```bash
cd Sudoku
javac -encoding UTF-8 -d bin $(find source -name "*.java")
java -cp bin uniandes.cupi2.sudoku.interfaz.InterfazSudoku
```

## Guess the Code

```bash
cd GuessTheCode
javac -encoding UTF-8 -d bin $(find source -name "*.java")
java -cp "bin:lib/derby.jar" uniandes.cupi2.cupiCode.servidor.interfaz.InterfazCupiCode &
java -cp bin uniandes.cupi2.cupiCode.cliente.interfaz.InterfazJugador &
java -cp bin uniandes.cupi2.cupiCode.cliente.interfaz.InterfazJugador &
```

## Lights Out

```bash
cd LightsOut
open lucesapagadas.html
```

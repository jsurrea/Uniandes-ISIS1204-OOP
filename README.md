# Object Oriented Programming Projects (ISIS1204 @ Uniandes)

This is a collection of projects I worked on for the courses on Object Oriented Programming at Uniandes. All credits due to the <a href="https://cupi2.virtual.uniandes.edu.co">Proyecto Cupi2</a> team.

The projects are meant to be opened with Eclipse, but you should be able to compile them with the following commands:

## Sudoku

```bash
cd Sudoku
javac -encoding UTF-8 -d bin $(find source -name "*.java")
java -cp bin uniandes.cupi2.sudoku.interfaz.InterfazSudoku
```

<img src="https://github.com/user-attachments/assets/fdfe542f-48be-4dfe-8f4f-dc3216361568">


## Guess the Code

```bash
cd GuessTheCode
javac -encoding UTF-8 -d bin $(find source -name "*.java")
java -cp "bin:lib/derby.jar" uniandes.cupi2.cupiCode.servidor.interfaz.InterfazCupiCode &
java -cp bin uniandes.cupi2.cupiCode.cliente.interfaz.InterfazJugador &
java -cp bin uniandes.cupi2.cupiCode.cliente.interfaz.InterfazJugador &
```

<img src="https://github.com/user-attachments/assets/6883fb66-4230-4242-b672-27017e53b9b3">


## Lights Out

```bash
cd LightsOut
open lucesapagadas.html
```


<img src="https://github.com/user-attachments/assets/9bfd989e-8e47-4b02-83d1-305117837d97">

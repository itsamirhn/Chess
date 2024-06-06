
# Chess
![Maven](https://github.com/itsamirhn/Chess/actions/workflows/maven.yml/badge.svg)
![Release](https://github.com/itsamirhn/Chess/actions/workflows/release.yml/badge.svg)

A Fully Object-Oriented Chess, written in pure Java


## Table of Contents
1. [Features](#features)
2. [Demo](#demo)
3. [Documentation](#documentation)
4. [Run Locally](#run-locally)
5. [Running Tests](#running-tests)
6. [Project Structure Diagrams](#project-structure-diagrams)
7. [License](#license)


## Features

- Support Custom [FEN](https://en.wikipedia.org/wiki/Forsyth–Edwards_Notation)
- Multiplayer
- Undo Move
- Plays sound
- Support Stalemate and Checkmate edge Cases
- Support [En Passant](https://en.wikipedia.org/wiki/En_passant) Move


## Demo

![Demo](demo.gif)


## Documentation
You can visit the online Documentation on here: [Documentation](https://itsamirhn.github.io/Chess)
Also the project structure diagrams are available in the [Project Structure Diagrams](#project-structure-diagrams) section

## Run Locally

1. Install JRE 21 or higher
2. Download the latest `Chess.jar` file from [releases page](https://github.com/itsamirhn/Chess/releases)
3. Run the following command:
```bash
  java -jar Chess.jar
```

## Running Tests

The project use [Maven](https://maven.apache.org/) as build automation tool. To run tests, run the following command:

```bash
  mvn test
```

## Project Structure Diagrams

### Logic
- Game Classes:

<img src="docs/diagrams/game.png" alt="Game" width="500"/>

- Pieces Classes:

<img src="docs/diagrams/pieces.png" alt="Pieces" width="500"/>

- Moves Classes:

<img src="docs/diagrams/moves.png" alt="Pieces" width="500"/>

### GUI
This project follows the [Model-View-Controller](https://en.wikipedia.org/wiki/Model–view–controller) design pattern. The GUI part of the project is implemented using Java Swing.
- GUI Classes:

<img src="docs/diagrams/gui.png" alt="GUI" width="500"/>

## License
![MIT](https://img.shields.io/badge/license-MIT-blue)

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details





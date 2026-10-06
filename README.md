# 2048 Java SWE Challenge

A deliberately incomplete implementation of the 2048 game intended for a software-engineering/OOP challenge.

## Goal

Complete the existing logic, implement the provided stubs, and add any new methods/classes you believe are necessary while keeping the design clean and maintainable.

This is **not** intended to be solved by filling every TODO mechanically. The candidate is expected to review the existing design and improve it when appropriate.

## What is already provided

- Maven project structure
- Java 17 baseline
- Grid and Tile domain classes
- Game state and direction abstractions
- Move service skeleton
- Tile generator abstraction
- Game facade
- Happy-path unit tests

## Candidate tasks

### A. Complete missing logic

Examples include:

- Grid access and mutation
- Empty/full checks
- Tile merge behavior
- Score calculation
- Game lifecycle
- Game-over detection

### B. Implement stub methods

Examples include:

- Movement in all four directions
- Tile shifting
- Tile merging
- Random tile generation

### C. Create new methods/classes where needed

You are explicitly allowed to introduce new abstractions if they improve the design. Do not feel constrained by the starter method list.

## Functional requirements

1. A new game starts with exactly two tiles.
2. A standard game uses a 4x4 grid, but the core grid should not depend on a hard-coded 4.
3. Tiles can move UP, DOWN, LEFT and RIGHT.
4. Tiles slide through empty cells.
5. Two adjacent tiles with the same value merge into one tile with double the value.
6. A tile must not merge more than once during a single move.
7. A new tile is generated only after a successful move.
8. Generated tiles are 2 or 4.
9. The score increases by the value created by each merge.
10. A game is over when the board is full and no legal move remains.
11. Restarting a game creates a fresh board and resets the score/state.
12. Moving in a direction that changes nothing must not generate a new tile or change the score.

## OOP / clean-code expectations

The solution should demonstrate:

- Single Responsibility Principle
- Encapsulation
- Composition over unnecessary inheritance
- Meaningful abstractions
- Small, focused methods
- No duplicated movement algorithms where a reusable abstraction is appropriate
- No magic numbers for core game rules
- No global mutable state
- Appropriate visibility for fields and methods
- Clear naming
- Separation of game orchestration from board mechanics
- Testable components

You may add interfaces, value objects, strategies, helpers, or other classes when justified. Avoid adding abstractions merely for the sake of abstraction.

## Unit tests

The project contains happy-path tests for the expected behavior. The starter implementation is intentionally incomplete, so some tests are expected to fail before the candidate completes the challenge.

Run:

```bash
mvn test
```

The candidate should make the provided tests pass and should add additional tests for edge cases discovered during implementation.

## Suggested engineering process

1. Read the existing code and tests before changing anything.
2. Run the tests and classify failures.
3. Identify responsibilities that are in the wrong class.
4. Implement the simplest correct solution.
5. Refactor duplicated or unclear code.
6. Add tests for edge cases.
7. Keep public APIs small and intentional.

## Evaluation focus

Correctness is necessary but not sufficient. Reviewers should also inspect:

- design decisions,
- maintainability,
- test quality,
- duplication,
- naming,
- error handling,
- extensibility,
- and whether the candidate improved the starter design where appropriate.

## Run the application

```bash
mvn exec:java -Dexec.mainClass=com.example.game2048.Main
```

Main class:

```text
com.example.game2048.Main
```

The console entry point is intentionally lightweight. The challenge implementation remains incomplete; use it to manually exercise the game while implementing the TODOs.

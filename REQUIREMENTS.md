# 2048 Challenge Requirements

## Goal
Complete the partially implemented Java 2048 game while preserving the public behavior described below.

## Functional requirements
1. A new game starts in `RUNNING` state with exactly two generated tiles.
2. Tiles may move UP, DOWN, LEFT, and RIGHT.
3. Tiles slide through empty cells in the requested direction.
4. Equal adjacent tiles merge once per move.
5. A merged tile has the sum of the two source values.
6. A successful move increases the score by each merge value produced by that move.
7. A new tile is generated after a successful move.
8. A move that does not change the board must not create a new tile.
9. The game becomes over when the board is full and no legal merge/move remains.
10. Restart creates a fresh running game with score zero and two tiles.
11. Grid size must not be hard-coded to 4 inside movement logic.

## Engineering requirements
- Apply encapsulation and single responsibility.
- Avoid duplicated movement algorithms where a clean abstraction can be used.
- Keep methods small and focused.
- Avoid magic numbers.
- Do not introduce global/static mutable state.
- Create additional classes/methods/interfaces when they improve the design.
- Do not modify the acceptance runner to make scenarios pass.
- Do not remove or weaken the supplied unit tests.

## Candidate freedom
The skeleton is intentionally not the final architecture. You may refactor it while preserving the externally observable requirements.

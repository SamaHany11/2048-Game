# Running Cases

## Acceptance runner

Run:

```bash
mvn test
mvn -q exec:java -Dexec.mainClass=com.example.game2048.Main
```

The runner is deterministic for the board-state scenarios. It compares actual values/states with expected values and prints PASS only when the comparison succeeds.

## Cases

| Case | Expected |
|---|---|
| Game starts | RUNNING + exactly 2 tiles |
| Merge left | `2,2,0,0` becomes `4,0,0,0` |
| Move right | `2,4,0,0` becomes `0,0,2,4` |
| Score | Merge of two 2s gives score 4 |
| Restart | RUNNING + score 0 + exactly 2 tiles |

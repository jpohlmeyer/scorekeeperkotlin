# ScoreKeeper

Android app to keep score while playing a board game.

## Features

- Create a list of players and reorder or remove them.
- Randomly pick a starting player by touching the screen with your fingers.
- Track scores for a simple game, entering points per player.
- End-of-game summary that ranks players by score.

## Requirements

- JDK 21
- Android SDK with API 37

## Building

```bash
./gradlew assembleDebug      # build the debug APK
```

## Project structure

```
app/src/main/java/com/github/jpohlmeyer/scorekeeperkotlin/
├── GameService.kt          # Singleton holding the current game
├── MainActivity.kt         # Single-activity host
├── model/                  # Game, Player and game-mode models
└── screens/                # One package per screen (start, add players, game, end)
app/src/main/res/           # Layouts, drawables, navigation graph and themes
design/                     # Source artwork for the app icon
```

## License

See [LICENSE](LICENSE).

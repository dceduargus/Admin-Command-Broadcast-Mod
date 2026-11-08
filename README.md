# Admin Command Broadcast (Fabric, MC 26.3)

Server-side Fabric mod that broadcasts executed admin commands (OP level 2+ / Gamemaster) and their output/feedback to server chat.

## Features

- **Execution broadcast**: Announces admin commands when executed via `CommandManagerMixin`.
- **Output broadcast**: Announces command execution results/feedback via `ServerCommandSourceMixin`.
- **Purely server-side**: No client-side installation required.

## Requirements

- Minecraft 26.3
- Fabric Loader >= 0.19.5
- Fabric API >= 0.165.0+26.3
- Java 25

## Configuration

Edit [`AdminBroadcastConfig.java`](file:///C:/Users/Eduardo/Documents/Proyectos/adminbroadcast/src/main/java/com/eduardo/adminbroadcast/AdminBroadcastConfig.java):

- `MIN_PERMISSION`: Minimum permission required to trigger broadcasts (default: `Permissions.COMMANDS_GAMEMASTER`).
- `INCLUDE_CONSOLE`: Whether commands executed from server console should be broadcast (default: `false`).
- `COMMAND_PREFIX`: Chat prefix for executed commands (`§c[ADMIN] §f`).
- `OUTPUT_PREFIX`: Chat prefix for command output (`§7[Result] §f`).

## Building

```bash
./gradlew build
```

The compiled mod JAR will be placed in `build/libs/admincommandbroadcast-1.0.0.jar`.

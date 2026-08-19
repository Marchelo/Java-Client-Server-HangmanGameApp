# Hangman game - Multiplayer

A client-server multiplayer Hangman game in Java (Swing GUI + sockets).
The server picks a random word at startup, and up to two players connect
and take turns guessing letters against that same word. Every player's
game state updates live on all connected screens as guesses come in.

## Project structure

- `comm` — protocol classes (Sender, Receiver) — raw objects (a single
  letter as a `String`, or a `GameState`) are sent directly, no
  Request/Response/Operation wrapper is used in this project
- `domm` — domain model (`GameState`: revealed word so far, used
  letters, remaining attempts, player name, winner, game-over flag)
- `server` — server-side networking (`ServerThread`, `ClientThread`) and
  server GUI (`ServerForm`, `ClientTableModel`)
- `client` — client GUI (`Main`) and networking (`CliController`)

## How it works

- The server picks one random word from a small built-in word list
  (`avion`, `kamin`, `flasa`) when it starts.
- Each connecting client gets its own `ClientThread` and its own
  `GameState` (each player has independent remaining attempts and used
  letters, but they're all guessing the **same** word).
- The server only accepts **2** simultaneous clients; a third connection
  is rejected and closed immediately.
- A client sends a single letter; the server checks it against the
  hidden word, updates that player's `GameState`, and sends it back.
- If a player fully reveals the word, the server broadcasts that
  player's winning `GameState` to **every** connected client and the
  game ends for everyone. If a player runs out of attempts, only that
  player's game ends — the others keep playing.
- Unlike a typical request/response setup, the client keeps a dedicated
  background thread (`CliController.listenForServer`) that continuously
  listens for `GameState` updates from the server and pushes them into
  the GUI as they arrive — no manual refresh needed.
- The server GUI shows a live table of connected clients, refreshed on
  every connect and every guess.

## Running the app

1. Run `server.ServerForm` and click **Start server**. Check the console
   for the word that was picked (useful for testing/debugging).
2. Run `client.Main` twice (two separate players). A third instance
   will be rejected while the first two are still connected.
3. In each client window, type a single letter into the guess field and
   click **Pogodi**. The five letter boxes fill in as letters are
   guessed correctly, and the labels below show used letters and
   remaining attempts.
4. When someone reveals the full word, a popup announces the winner and
   the game closes for both players. If a player runs out of attempts
   first, only their window shows "game over" — the other player
   continues.
  in-memory, session-based game; nothing is saved between runs.
- The max-clients limit (2) is hardcoded in `ServerThread`, not read
  from a config file.

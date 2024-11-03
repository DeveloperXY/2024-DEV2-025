# 2024-DEV2-025

I started by implementing the core game logic using a TDD approach, making no assumptions about how
the it will be hooked to eventual UI. I tried to cover as much ground as I could, before I started 
prepping to build the UI. At this state, I had 1 class (GameState) that not only held game state as the
name suggests, but also game logic as well. I needed to make a clear separation between the two,
in addition to make the game state Compose friendly, because the game state at this point was not 
observable by compose UI.

Before building the actual UI, I moved the game logic to separate use case classes to distinguish which 
actions can be performed within the scope of the game. There is no database or network work involved, 
so a ViewModel was set in place that holds on to the state of the game and utilizes the use case
classes to manage that state.

Finally, I built the compose UI and hooked the ViewModel to it.
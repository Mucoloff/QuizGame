# Quiz Game Plugin - Documentation

## Introduction

The "Quiz Game" plugin allows players to participate in an exciting quiz within the Minecraft world. The plugin features
multiple categories of questions, and players can select a category to start the game. During the quiz, players must
answer the questions correctly to earn points. The plugin also supports saving player scores to a MariaDB database,
allowing you to track their progress over time.

## Installation

To install the "Quiz Game" plugin, follow these steps:

1. Ensure you have a Paper, Spigot or Bukkit Minecraft server running on your machine or a dedicated server.

2. Download the plugin's JAR.

3. Place the JAR file into the "plugins" folder of your Minecraft server.

4. Load the plugin.

## Configuration

The "Quiz Game" plugin comes with a configuration file that allows you to customize various aspects of the game. Follow
the steps below to configure the plugin:

1. Start the Minecraft server and wait for the "Quiz Game" plugin to load.

2. Locate the "config.yml" file in the "QuizGame" folder within the "plugins" directory.

3. Open the "config.yml" file with a text editor.

4. Make the desired changes to the configuration options. Below are the available options:

### Database Configuration

```yaml
# Database Configuration
database:
  host: "localhost"
  port: 3306
  name: "quiz_game"
  username: "root"
  password: "password"
```

- `host`: The hostname or IP address of your MariaDB server.
- `port`: The port number of the MariaDB server.
- `name`: The name of the database where player scores will be stored.
- `username`: The username to connect to the MariaDB server.
- `password`: The password to authenticate the database connection.

### Messages Configuration

```yaml
# Messages Configuration
messages:
notAPlayer: "&4You are not a player!"
format: "&7[&6%0%&7] &6%1%:&f\n"
hover: "&7[&6Click for &6&lanswer&6!&7] %0% %1% %2% %3% %4% %5%"
startGame: "Start a game first!"
invalidPlayer: "Please use this command as a player"
invalidCategory: "Invalid category."
invalidAnswer: "Invalid answer."
points: "Congratulations! You earned %0% points"
showPoint: "&6%0%'s score: %1% points"
separator: "\n"
```

- These are the various messages sent to players during gameplay and command usage. You can modify the messages to suit
  your preferred language or style.

### Categories Configuration

```yaml
# Categories Configuration
categories:
  geography:
    category: "Geography"
    questions:
      capital:
        question: "What is the capital of France?"
        answers:
          paris:
            answer: "&6Paris"
            points: 1.0
          london:
            answer: "&aLondon"
            points: 0.0
          rome:
            answer: "&aRome"
            points: 0.0
          list:
            - paris
            - london
            - rome
      river:
        question: "Which river is the longest in the world?"
        answers:
          nile:
            answer: "&6Nile River"
            points: 1.0
          amazon:
            answer: "&aAmazon River"
            points: 0.0
          yangtze:
            answer: "&aYangtze River"
            points: 0.0
          list:
            - nile
            - amazon
            - yangtze
      list:
        - "capital"
        - "river"
  movies:
    category: "Movies"
    questions:
      oscar:
        question: "Which movie won the Best Picture Oscar in 2020?"
        answers:
          parasite:
            answer: "Parasite"
            points: 1.0
          joker:
            answer: "Joker"
            points: 0.0
          1917:
            answer: "1917"
            points: 0.0
          list:
            - parasite
            - joker
            - 1917
      actor:
        question: "Who played the role of Iron Man in the Marvel movies?"
        answers:
          robert_downey_jr:
            answer: "Robert Downey Jr."
            points: 1.0
          chris_evans:
            answer: "Chris Evans"
            points: 0.0
          tom_holland:
            answer: "Tom Holland"
            points: 0.0
          list:
            - robert_downey_jr
            - chris_evans
            - tom_holland
      list:
        - "oscar"
        - "actor"
  list:
    - "geography"
    - "movies"
```

- This section defines the quiz categories and the associated questions and answers. You can add or modify categories,
questions, and answers to tailor the quiz to your preferences.

## Commands

The "Quiz Game" plugin comes with the following commands:

- `/play <category>`: Start a quiz game with the selected category. For example `/startquiz geography`.
- `/score`: View your score.
- `/qreload`: Reload the plugin's configuration.
- `/answer <category> <question> <answer>`: To answer a question during the quiz, either type the answer in chat or use the /answer command followed by the category, question, and your chosen answer. For example `/answer geography capital paris`.

## Gameplay

To participate in the quiz game, players need to use the `/quiz` command followed by the desired quiz category. Once the game starts, players will receive questions and must answer them by typing their response in the chat or using the `/answer` command.

### How to Answer Questions
Players can answer questions in two ways:

1. <b>In Chat</b>: To answer a question, click your response directly in the chat. The plugin will automatically evaluate your answer and award points accordingly.
2. <b>Using /answer Command</b>: Alternatively, you can use the /answer command to submit your response.

Players' scores will be saved in the MariaDB database, allowing you to track and display their progress later.

## Conclusion

Feel free to customize the plugin further, add more categories and questions, and enhance the gameplay according to your preferences. If you encounter any issues or have questions, don't hesitate to refer to the documentation or ask me support.

Have fun and happy quizzing in your Minecraft server!

### Contacts:
- Discord: `mksweety`
- Telegram: `@mucoloff`
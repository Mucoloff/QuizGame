# Quiz Game Plugin - Documentation

## Introduction

The "Quiz Game" plugin allows players to participate in an exciting quiz. The plugin features multiple categories of
questions, and players can select a category to start the game. During the quiz, players must answer the questions
correctly to earn points. The plugin also supports saving player scores to a MySQL or MariaDB database, allowing you to
track their progress over time.

## Installation

To install the "Quiz Game" plugin, follow these steps:

1. Ensure you have a Paper, Spigot or Bukkit Minecraft server running on your machine or a dedicated server.

2. Download the plugin's JAR.

3. Place the JAR file into the "plugins" folder of your Minecraft server.

4. Load the plugin.

5. Make sure you have MySQL or MariaDB installed, that you have given the right permissions to your user and that you
   have created the database

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
  url: "?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true"
  username: "root"
  password: "password"
```

- `host`: The hostname or IP address of your MySQL / MariaDB database.
- `port`: The port number of the database.
- `name`: The name of the database where player scores will be stored.
- `username`: The username to connect to the database.
- `password`: The password to authenticate the database connection.

### Messages Configuration

```yaml
# Messages Configuration
messages:
  notAPlayer: "&4You are not a player!"
  format: "&7[%0%&7] %1%&f\n"
  hover: "&e&lClick &6for answer&6: %5%&6!"
  startGame: "&4Start a game first!"
  invalidPlayer: "&4Please use this command as a player"
  invalidCategory: "&4Invalid category. Available categories:\n%0%"
  invalidAnswer: "&4Invalid answer."
  points: "&6Congratulations! You &e&learned &f%0% &6points"
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
    category: "&9Geography"
    questions:
      capital:
        question: "&6What is the capital of France?"
        answers:
          paris:
            answer: "&aParis"
            points: 1.0
          london:
            answer: "&aLondon"
            points: 0.0
          rome:
            answer: "&aRome"
            points: 0.0
          list:
            - "paris"
            - "london"
            - "rome"
      river:
        question: "&bWhich river is the longest in the world?"
        answers:
          nile:
            answer: "&aNile River"
            points: 1.0
          amazon:
            answer: "&aAmazon River"
            points: 0.0
          yangtze:
            answer: "&aYangtze River"
            points: 0.0
          list:
            - "nile"
            - "amazon"
            - "yangtze"
      list:
        - "capital"
        - "river"
  movies:
    category: "&4Movies"
    questions:
      oscar:
        question: "&6Which movie won the Best Picture Oscar in 2020?"
        answers:
          parasite:
            answer: "&aParasite"
            points: 1.0
          joker:
            answer: "&aJoker"
            points: 0.0
          1917:
            answer: "&a1917"
            points: 0.0
          list:
            - "parasite"
            - "joker"
            - "1917"
      actor:
        question: "&6Who played the role of Iron Man in the Marvel movies?"
        answers:
          robert_downey_jr:
            answer: "&aRobert Downey Jr."
            points: 1.0
          chris_evans:
            answer: "&aChris Evans"
            points: 0.0
          tom_holland:
            answer: "&aTom Holland"
            points: 0.0
          list:
            - "robert_downey_jr"
            - "chris_evans"
            - "tom_holland"
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
- `/answer <category> <question> <answer>`: To answer a question during the quiz, either click the answer in chat or use
  the /answer command followed by the category, question, and your chosen answer. For
  example `/answer geography capital paris`.

## Gameplay

To participate in the quiz game, players need to use the `/quiz` command followed by the desired quiz category. Once the
game starts, players will receive questions and must answer them clicking their response in the chat or using
the `/answer` command.

### How to Answer Questions

Players can answer questions in two ways:

1. <b>In Chat</b>: To answer a question, click your response directly in the chat. 
2. <b>Using /answer Command</b>: Alternatively, you can use the /answer command to submit your response.

- The plugin will automatically evaluate your answer and award points accordingly.
- Players' scores will be saved in the database, allowing you to track and display their progress later.

## Conclusion

Feel free to customize the plugin further, add more categories and questions, and enhance the gameplay according to your
preferences. If you encounter any issues or have questions, don't hesitate to refer to the documentation or ask me
support.

Have fun and happy quizzing in your Minecraft server!

### Contacts:

- Discord: `mksweety`
- Telegram: `@mucoloff`
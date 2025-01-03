# A.S.T.E.R.O.I.D.S

## Introduction

A.S.T.E.R.O.I.D.S is an Atari-inspired shooter game where the player needs to destroy as many rocks and aliens as possible, based on the popular 1979 video game [_Asteroids_](https://en.wikipedia.org/wiki/Asteroids_(video_game)). This project's guidelines were set by [Dr. Isaac Wang](https://www.isaacdwang.com) from James Madison University for CS159 (Advanced Programming) in the Spring semester of 2023. This project was designed to gain experience with object-oriented programming, Java libraries, JUnit testing, and GUI implementation.

## Running A.S.T.E.R.O.I.D.S.

To run the game, ensure the following Java version is Java 17 by running the following command in the repository:

```bash
java -version
```
If you need to update the Java version, go to the [Updating Java](#updating-java) section.

You can start the game by running the following command:

```bash
cd bin
java GameDriver
```

A GUI window should pop up:
![ASTEROIDS Startup Screen](/screenshot.png)

## Gameplay

The game immediately starts when the program starts running. The player is in control of a spaceship (the triangle) in the center of the screen. The goal is to shoot as many asteroids (circles) and UFOs (rectangles) before the player runs of out all 3 of their lives by hitting the flying objects. The player (and the spaceship's projectiles) can go off the screen and will re-enter from the opposite side, maintaining its momentum and direction. 

### Controls

- **Move Forwards:** (Up Arrow Key)
- **Move Backwards:** (Down Arrow Key)
- **Turn Right:** (Right Arrow Key)
- **Turn Left:** (Left Arrow Key)
- **Shoot Projectile:** (Spacebar)

## Updating Java

Step 1: Update the package list

```bash
sudo apt-get update
```

Step 2: Install OpenJDK 17

```bash
sudo apt-get install openjdk-17-jdk
```

Step 3: Verify the installation

```bash
java -version
```

Step 4: Ensure the $JAVA_HOME environment variable points to the Java 17 installation

```bash
readlink -f $(which java) | sed "s:bin/java::"
```

Step 5: Add the following lines to your ~/.bashrc or ~/.zshrc file if not there already:

```bash
export JAVA_HOME=<path-to-java-17>
export PATH=$JAVA_HOME/bin:$PATH
```
Replace <path-to-java-17> with the path found in Step 4.

Step 6: Apply changes

```bash
source ~/.bashrc
```

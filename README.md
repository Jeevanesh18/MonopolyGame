🧩 Monopoly Game – Java Swing Edition
This is a basic Monopoly-style board game built entirely from scratch using pure Java and Swing GUI components. It was created as part of my Fundamentals of Programming course and represents my first full programming project done independently, without the use of LLMs, frameworks, or advanced libraries.

🎯 Project Overview
The game simulates a simplified version of Monopoly with a focus on turn-based movement, player interaction, and property mechanics. It includes a graphical user interface, player control buttons, a basic dice roll system, and customizable game tiles.

🧠 Learning Purpose
This project was created to:

Practice Java syntax and logic building

Understand GUI programming with Swing

Get familiar with event handling in Java

Learn code structuring and state management in games

🛠️ Features Implemented
✅ Graphical Board

Game board designed using JPanel, JLabel, and absolute positioning

Simulated square tiles like real Monopoly board

✅ Two Players

Supports two player turns with toggled moves

Player positions are updated and visualized on the board

✅ Dice System

Dice roll using Java’s Random class

Simulates movement across the board

✅ Turn-Based Logic

Alternating turns for each player

Win condition / board lap logic (if implemented)

✅ Buttons & Interaction

Functional buttons for rolling the dice

Dynamic label updates for player moves and positions

✅ Basic Game State Tracking

Keeps track of each player’s current tile/position

Highlights and messages based on game actions

🧱 Technologies Used
Language: Java

GUI Toolkit: Java Swing (JFrame, JPanel, JButton, JLabel, ImageIcon)

Utilities: Random, conditional logic, loops, arrays


📌 Code Highlights
JFrame was used as the main game window.

Absolute positioning (setLayout(null)) was used for full control over game elements.

Multiple JLabels were used to represent players and tiles.

Event-driven programming with ActionListener to handle user input.

Basic randomized logic using Java’s built-in Random class.

🚀 Areas for Improvement
Here are potential upgrades for future versions:

🧹 Code Organization
Refactor into multiple classes:

Player class

Tile class

GameBoard class

Main class for game logic

Use OOP principles: encapsulation, abstraction, etc.

🎨 GUI Design
Replace null layout with layout managers (GridLayout, BorderLayout) for scalability.

Add game assets (property images, themed icons, tile names, etc.)

🎲 Game Logic
Add property buying system

Add money system (income, rent, etc.)

Include chance/community chest events

Add jail logic, special tiles, etc.

💾 Save Game State
Add the ability to save and load games with file I/O.

🧠 What I Learned
How to build a complete Java project from scratch

How to design a GUI and handle events in Swing

Managing game logic and turn-based systems

How to organize and plan code in stages

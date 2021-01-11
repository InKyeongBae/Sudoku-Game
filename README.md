# Sudoku-Game : Team Project for JAVA GUI Game
>__* All screen capture for each function of the SW system and more detail in__ *" team2_project4_sudokureport.pdf "*
>__& SW Simulation in__ *" team2_project4_sudoku_simulation.mp4 "*


>2020-2 Object-Oriented-Programming class in CAU Team2


## Team Member
|Name|Role|
|---|---|
|김승아|Make MiniGames for HINT|
|배인경|'Generate Sudoku Game' Algorithm|
|이주연|GUI connect & Action Listner|

## Brief project description
1) Sudoku game
- The cells that make up Sudoku are subdivided into 81 cells (3x3) in total. It implemented rules that should be followed in Sudoku and added life and hint function.


2) Mini Game for HINT
- To get a hint from the Sudoku game, you have to play mini games. Minigames are offered randomly among rock-paper-scissors, oddeven, and timing. Every time you try to get a hint, your chances decrease.

## How to Compile
1. Prepare the Eclipse IDE for Java Developers or IntelliJ IDEA
2. Open the Project file that you want to execute.
3. Eclipse IDE works compilation and execution at once when pressing run (ctrl + F11)


## Function
__1) Sudoku game : Sudoku consists of 81 cells in total and is subdivided into 9cells (3 X 3).__
* 1-1) Rules to be observed are as follows
  - Each row and column contains 1 to 9 without any overlap. 
  - In 3x3 boxes, 1 to 9 are included one by one without overlapping.

* 1-2) Additional features
  - Life: Life decreases if the value is wrong
  - Hint: Get a hint for one cell. You have to pass the mini-game.

__2) Controller : Controller update the game data according to the user's input and informed the view of the changes again.__

__3) Model : Model handle the status and logic of game data.__

__4) View : View shows the data of the model to the user and operates by passing the user's input to the controller.__

__5) Mini Game : Mini games are given randomly among the [ Rock,Paper,Schissors / OddEven / Timing Game ]__



## UML Modeling for system design
![image](https://user-images.githubusercontent.com/65646971/104201588-43c3b580-546d-11eb-9c5c-2bb30fa9d487.png)


## Object-Oriented-Programming Concepts for our project
1) Method Overloading
2) Using MVC Pattern
3) Inheritance
4) Abstraction


## Screen Capture
#### SUDOKU GUI
![image](https://user-images.githubusercontent.com/65646971/104201844-85ecf700-546d-11eb-8b9a-3857983cf7c1.png)
#### MiniGame GUI
![image](https://user-images.githubusercontent.com/65646971/104201968-a9b03d00-546d-11eb-8df5-56b000475090.png)




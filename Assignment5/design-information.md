# Word Game UML Class Diagram Description
This is the description of the UML Class Diagram for word game. 

## 1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.  
Main application UI is made along with three sub UI's that show (1)Play a word game, (2) View statistics, and (3) Adjust the game settings. Each sub UI's have its set of interfaces as shown in the diagram.

## 2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, (2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and (3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1.
When Adjust the game settings is chosen, the user can make several settings as shown in the settings class. Number of minutes, size of the square board, and weights are all attributes. The ranges and default values are listed in the settings class.

## 3. When choosing to play a word game, a player will: 
## a. Be shown a 'board' of randomly generated letters. 
When Play a Word Game is chosen, it moves on the the WordGame class and will populate with the board as seen in the board class. BoardSize, letters, letterWeight, numberOfVowels, numberOfConsonants are all attributes. Operations are generateRandom letters.

## b. Be shown a timer counting down the number of minutes available for the game, as set in the settings.
In the Word Game class, a utility timer will be displayed. 

## c. Start with 0 points, which is not required to be displayed during the game.
Score will be kept but not displayed in the game. Visibility is '-' for score. 

## d. Until the game timer counts to zero and the game ends:
###   i. Enter a unique word made up of two or more letters on the board.  The word must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice.  The word does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).
The system has to recognize a unique word but we will not be using a dictionary. We will make a Word class that specifies what qualifies as a 'word.'

###   ii. Choose to re-roll the board at a cost of 5 points.  The board will be re-created in the same way it is generated at the start of each game, replacing each letter.  The timer will not be reset or paused.  The player’s score may go into negative values.
Board can be regenerated with 5 points. Visible button of reroll is shown as a booleon. Since this is booleon, when it is true, I can have the timer not pause and keep going. I also made operation of subtractFivePoints() if rerollButton is true.

###   iii. Choose to exit the game early.
There is exit() operation.

## e. At the end of the game, when the timer has run out or the player chooses to exit, the final score for the game will be displayed.
After exiting or timer, statistics with the final score will appear. Operation viewScore(). Statistics class has finalScore. 

## f. Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted.
keepScore() operation in the WordGame class keeps track of when it recognizes a "Word". Word class specifies what a word is. subtractFivePoints() then subtracts when BOOL rerollButton is true.

## g. After the player views the score, they will continue back to the main menu.
This is denoted by having operation returnToMainMenu().

## 4. Whenever the board is generated, or re-generated it will meet the following criteria:
## a. The board will consist of a square full of letters.  The square should have the number of letters, both horizontally and vertically, indicated by the size of the square board from the game settings (4x4, 5x5, 6x6, 7x7, or 8x8).  
The board class has several classes that makes up a board. Square is inside the board class. In the square class, the attribute is the boardSize which is an integer and can be 4 from 8. xTile and yTile indicate the size of the squares.

## b. 

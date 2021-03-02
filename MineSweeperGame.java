


/**
 * Write a description of class MineSweeperGame here.
 *This game has a 5x5 two-dimensional array that has three values.
 *0 = not selected
 *1 = no mine
 *9 = mine
 *The user is presented with a game grid at the beginning showing no cells selected (0)
 *The grid will show an X (1) if there is no mine, or a bomb emoji (9) if there is a mine.
 *The user is prompted to select a cell using [row][column] coordinates.
 *If the user hits a cell that contains a mine 3 times(9), he/she dies and the game ends.
 *If the user hits a cell that does not contain a mine, he/she is prompted to continue 
 *with the game until he/she hits a mine 3 times and dies or completes the entire grid without hitting a mine, 
 *in which case he/she wins and the game ends.
 * @author (Tinh Tran)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class MineSweeperGame
{
    static void printStarline(int arraySize) {
        for (int i = 0; i < arraySize + 2; i++) {
                System.out.print("*");
            }
            System.out.println("");
    }
    static int getNumberOfMines(int[][] intArray, int arraySize, int x, int y) {
        int numberOfMines = 0;
        for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < arraySize; j++) { 
                    
                    if (j == y-1)
                    {
                        if (i == x-1 || i == x || i == x+1)
                        {
                            if (intArray[i][j] == 9)
                            {
                                numberOfMines++;
                            }
                        }
                    }
                    else if (j == y)
                    {
                        if (i == x-1 || i == x+1)
                        {
                            if (intArray[i][j] == 9)
                            {
                                numberOfMines++;
                            }
                        }
                    }
                    else if (j == y+1)
                    {
                        if (i == x-1 || i == x || i == x+1)
                        {
                            if (intArray[i][j] == 9)
                            {
                                numberOfMines++;
                            }
                        }
                    }                   
                }
        }
        return numberOfMines;
    }
    
    static void printGameBoard(int[][] intArray, int arraySize) {
        System.out.println("Here is the minesweeper grid map.");
        printStarline(arraySize);
        for (int x = 0; x < arraySize; x++) {
                for (int y = 0; y < arraySize; y++) { 
                    if (y == 0)
                    {
                        System.out.print("*");
                    }
                    
                    int myInt = intArray[x][y];
                    
                    if (myInt == 9){
                        System.out.print("\uD83D\uDCA3");
                    }
                    else 
                    {
                        System.out.print(myInt);
                    }
                    
                    if (y == arraySize -1)
                    {
                        System.out.print("*");
                    }
                }
                System.out.println("");
                
        }
        printStarline(arraySize);
    }
    
    static void printData(int[][] intArray, int arraySize) {
        System.out.println("Here is the array map.");        
        for (int x = 0; x < arraySize; x++) {
                for (int y = 0; y < arraySize; y++) { 
                    
                    int myInt = intArray[x][y];
                    System.out.print(myInt);
                }
                System.out.println("");
        }
    }
    
    static boolean isGridFilled(int[][] intArray, int arraySize){
          for (int x = 0; x < arraySize; x++) {
                for (int y = 0; y < arraySize; y++) { 
                    
                    int myInt = intArray[x][y];
                    
                    if (myInt == 0){
                        //0 means not filled
                        return false;
                    }
                }
          }
          return true;
        }
    
    static boolean didPlayerHitMine(int[][] userArray, int row, int column, int[][] solutionArray, int[][] testArray)
    {
        boolean hitAMine = false;
        if (userArray[row][column] > 0)
            {
                System.out.println("You already selected this entry, try a different coordinate.");
            }
            else
            {
                userArray[row][column] = solutionArray[row][column];  
                testArray[row][column] = solutionArray[row][column];           
                
                // If coordinate is a mine.
                if (userArray[row][column] == 9)
                {
                    System.out.println("You hit a mine.");
                    hitAMine = true;
                }
                else
                {
                    hitAMine = false;
                }
            }
            return hitAMine;
    }
        
    public static void main(String[] args){
        System.out.print("\u000C");
        
        //This array holds the data for where all the mines are located.
        //0 means not selected
        //1 means no mine
        //9 means mine
        int[][] mySolutionArray = { {1,9,1,1,9},
                                    {1,9,1,9,1},
                                    {9,1,1,1,1},
                                    {1,1,9,1,1},
                                    {9,1,1,1,9},
                                   };
 
        //This array will hold the data for what cell the user selects.
        int[][] myUserArray = { {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                               };
                               
        //This array is used to keep track of all the cells the user selects that do not contain a mine.
        //When the user selects a cell the test array will be populated with a non-zero entry.
        //If all non-mine entries are greater than zero, then the game is done.                      
        int[][] myTestArray = { {0,9,0,0,0},
                                {0,0,0,9,0},
                                {9,0,0,0,0},
                                {0,0,9,0,0},
                                {0,0,0,0,9},
                               };
  
        Scanner myScanner = new Scanner(System.in);
        boolean gameOver = false;
        printGameBoard(myUserArray, 5);
        int mineTracker1 = 0;
        int mineTracker2 = 0;
        do 
        {        
            System.out.println("Player 1: Enter Row space Column starting at upper left corner as 0,0.");
            int row = myScanner.nextInt();
            int column = myScanner.nextInt();
            boolean hitAMine = didPlayerHitMine(myUserArray, row, column, mySolutionArray, myTestArray);
            if (hitAMine)
            {
                mineTracker1 = mineTracker1 + 1;
                printGameBoard(myUserArray, 5);
                if (mineTracker1 == 3)
                {
                    System.out.println("Player 1, you hit three mines! You lost!");
                    gameOver = true;
                }
                else if (isGridFilled(myTestArray, 5))
                {
                     System.out.println("Congratulations Player 1! You just beat the minesweeper game! \uD83D\uDE00");
                     printGameBoard(mySolutionArray, 5);
                     gameOver = true;
                }
            }
            else
            {
                printGameBoard(myUserArray, 5);
            }
            
            if (gameOver == false)
            {
                System.out.println("Player 2: Enter Row space Column starting at upper left corner as 0,0.");
                row = myScanner.nextInt();
                column = myScanner.nextInt();
                hitAMine = didPlayerHitMine(myUserArray, row, column, mySolutionArray, myTestArray);
                if (hitAMine)
                {
                    mineTracker2 = mineTracker2 + 1;
                    printGameBoard(myUserArray, 5);
                    if (mineTracker2 == 3)
                    {
                        System.out.println("Player 2, you hit three mines! You lost!");
                        gameOver = true;
                    }
                    else if (isGridFilled(myTestArray, 5))
                    {
                        System.out.println("Congratulations Player 2! You just beat the minesweeper game! \uD83D\uDE00");
                        printGameBoard(mySolutionArray, 5);
                        gameOver = true;
                    }
                }
                else
                {
                    printGameBoard(myUserArray, 5);
                }
            }        
        }
        while (gameOver == false);
    }   
}




/**
 * Write a description of class MineSweeperGame here.
 *This game has a 5x5 two-dimensional array that has three values.
 *0 = not selected
 *1 = no mine
 *2 = mine
 *The user is presented with a game grid at the beginning showing no cells selected (0)
 *The grid will show an X (1) if there is no mine, or an M (2) if there is a mine.
 *The user is prompted to select a cell using [row][column] coordinates.
 *If the user hits a cell that contains a mine (2), he/she dies and the game ends.
 *If the user hits a cell that does not contain a mine, he/she is prompted to continue 
 *with the game until he/she hits a mine and dies or completes the entire grid without hitting a mine, 
 *in which case he/she wins and the game ends.
 * @author (Tinh Tran)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class MineSweeperGame
{
    static void printGameBoard(int[][] intArray, int arraySize) {
        System.out.println("Here is the minesweeper grid map.");        
        for (int x = 0; x < arraySize; x++) {
                for (int y = 0; y < arraySize; y++) { 
                    
                    int myInt = intArray[x][y];
                    
                    if (myInt == 0){
                        //0 means not selected
                        System.out.print("O");
                    }
                    else if (myInt ==1)
                    {
                        //1 means no mine
                        System.out.print("X");
                    }
                    else 
                    {
                        //2 and anything else means mine
                        System.out.print("M");
                    }
                }
                System.out.println("");
        }
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
    
    public static void main(String[] args){
        System.out.print("\u000C");
        
        //This array holds the data for where all the mines are located.
        //0 means not selected
        //1 means no mine
        //2 means mine
        int[][] mySolutionArray = { {1,2,1,1,1},
                                    {1,1,1,2,1},
                                    {2,1,1,1,1},
                                    {1,1,2,1,1},
                                    {1,1,1,1,2},
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
        int[][] myTestArray = { {0,2,0,0,0},
                                {0,0,0,2,0},
                                {2,0,0,0,0},
                                {0,0,2,0,0},
                                {0,0,0,0,2},
                               };
  
        Scanner myScanner = new Scanner(System.in);
        boolean gameOver = false;
        do 
        {        
            printGameBoard(myUserArray, 5);
            System.out.println("Enter Row space Column starting at upper left corner as 0,0.");
            int row = myScanner.nextInt();
            int column = myScanner.nextInt();
            if (myUserArray[row][column] > 0)
            {
                System.out.println("You already selected this entry, try a different coordinate.");
            }
            else
            {
                myUserArray[row][column] = mySolutionArray[row][column];  
                myTestArray[row][column] = mySolutionArray[row][column];           
                
                if (myUserArray[row][column] == 2)
                {
                    System.out.println("You hit a mine, game over!");
                    printGameBoard(mySolutionArray, 5);
                    gameOver = true;
                }
                else
                {
                    if (isGridFilled(myTestArray, 5))
                    {
                        System.out.println("Congratulations! You just beat the minesweeper game!");
                        printGameBoard(mySolutionArray, 5);
                        gameOver = true;
                    }
                }
            }
        }
        while (gameOver == false);
    }   
}

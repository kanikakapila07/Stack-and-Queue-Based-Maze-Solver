

import java.io.IOException;
import java.util.Scanner;

class assignment2
 {  
//   initialize a 2d array to store the maze 
   private static char[][] maze;
//   adt doublylinkedstack 
   doublyLinkedStack<Integer> s = new doublyLinkedStack<Integer>();
// reader to read maze from the txt file 
   static reader myReader = new reader();
//    call the readMaze method and return a 2d array  
static char[][] readMaze(String s ) throws IOException
{
           return  maze = myReader.readMaze(s);
}
//  main method here 
    public static void main(String[] args) throws IOException
     {       
            // take user input for file name and what kind of search they want 
            Scanner scanner = new Scanner (System.in);
            System.out.println("Enter the name of the txt file");
            String fileName = scanner.nextLine();
            System.out.println("Enter 1 to use depth first search , Enter 2 to use breadth first search");
            int num = scanner.nextInt();
            // read the txt file , change the name of the file to read from different txt files
            readMaze(fileName);
            // convert the 2d char array to a 2d cell array using cellMaze
            cellMaze cells = new cellMaze(maze);
            if(num == 1){
            // dfSearch() is the depth-first Seach , using a dll stack structure
             cells.dfSearch();}
            // bfSearch() is the breadth-first Search , using a dll circular queue structure
             
            else if (num==2){cells.bfSearch();}
            scanner.close();
         }
      }


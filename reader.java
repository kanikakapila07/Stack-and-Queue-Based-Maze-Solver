
// this is the reader created to read a maze from a txt file and 
// store it in a char 2d array using java buffer reader class 
// This part is implemented base on https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class reader {
public char[][] readMaze(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int rows = 0;
        int cols = 0;

        // Read the number of rows and columns of the maze 
        while ((line = reader.readLine()) != null) {
            rows++;
            cols = line.length();
        }
        reader.close();
      
        char[][] maze = new char[rows][cols];
        reader = new BufferedReader(new FileReader(filePath));

        // Read the maze content
        int row = 0;
        while ((line = reader.readLine()) != null) {
            for (int col = 0; col < line.length(); col++) {
                maze[row][col] = line.charAt(col);
            }
            row++;
        }
        reader.close();
        
        return maze;

        
    }
    // method to print out the 2d char array maze 
    public void printMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}





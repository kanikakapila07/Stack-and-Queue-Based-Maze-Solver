
// class cellMaze 
public class cellMaze {
    private static cell[][] cells;
    private static int numRows;
    private static int numCols;
    private cell mouse;
    private static cell cheese;
    private cell wall;
    private cell space;
    // dll stack 
    static doublyLinkedStack<String> stack = new doublyLinkedStack<String>();
    // linked queue trailQueue to record the trial of the mouse 
    // used the linkedQueue from the code example , created by professor Kawash
    LinkedQueue<String> trailQueue = new  LinkedQueue<String>();
    // dll queue 
    static doublyLinkedQueue<String> queue = new doublyLinkedQueue<String>();
    // convert the char maze to a cell maze
    public cellMaze(char[][] maze) {
    //    get the rows and cols from the char maze 
        numRows = maze.length;
        numCols = maze[0].length;
        // create a new cell maze called cells 
        cells = new cell[numRows][numCols];
        // Initialize cells
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // for each cell in the cells 2d array , make i the row , 
                // j the col , and the char at that index to be the type
                cells[i][j] = new cell(i, j, maze[i][j]);
                // type is mouse if m 
                if (maze[i][j] == 'm') {
                    mouse = cells[i][j];
                    // type is cheese if c 
                } else if (maze[i][j] == 'c') {
                    cheese = cells[i][j];
                }
                // type is wall if 1 
                  else if (maze[i][j] =='1')
                  {
                    wall = cells[i][j];
                  }
                //   type is space if 0
                  else if (maze[i][j] == '0')
                  {
                    space = cells[i][j];
                  }
            }
        }
    }
// convert the cell's location to a string since the doublyLinkedStack and queue takes generic type 
static String convert(cell c)
{
   String  location;
   location = c.row+","+c.col;
   return location;

} 
// convert the location back to the cell 
static cell convertCell(String s)
{
    String[] str = s. split(",");
    int i = Integer.parseInt(str[0]);
    int j =  Integer.parseInt(str[1]);
    cell c = cells[i][j];
    return c ; 
}
// mark the the cell at the location s visited
static void markVisited(String s)
{
    cell c =convertCell(s);

    c.visited=true;
}
// check if location s is the cheese's location 
static boolean checkPosition(String s)
{    
    cell c = convertCell(s);
    if(c.row == cheese.row & c.col == cheese.col)
    {
      return true;
    }
    return false;
}
// check if the left of a cell is a wall 
// then do If (k is not marked visited) structure.add(k)
static void checkLeft(String s)
{   
    cell c = convertCell(s);
    int row = c.row;
    int col = c.col;
    if (row > 0 && !cells[row - 1][col].visited && cells[row - 1][col].type != '1') 
    {
         String k = convert(cells[row - 1][col]);
         stack.push(k);
         queue.enqueue(k);
         cells[row][col].type='^';

    }
    
}
// check if the top of a cell is a wall 
// then do If (k is not marked visited) structure.add(k)
static void checkTop (String s)
{
    cell c = convertCell(s);
    int row = c.row;
    int col = c.col;
    if (col > 0 && !cells[row][col - 1].visited && cells[row][col - 1].type != '1') 
    { 
        String k =convert(cells[row][col - 1]);
    
        
    stack.push(k);
    queue.enqueue(k);
    cells[row][col].type = '<';}
    
}
// check if the bot of a cell is a wall 
// then do If (k is not marked visited) structure.add(k)
static void checkBot(String s)
{
    cell c = convertCell(s);
    int row = c.row;
    int col = c.col;
    if (col < numCols - 1 && !cells[row][col + 1].visited && cells[row][col + 1].type != '1') 
    {
        String k = convert(cells[row][col + 1]);
        stack.push(k);
        queue.enqueue(k);
        cells[row][col ].type = '>';
     }
}
// check if the right of a cell is a wall 
// then do If (k is not marked visited) structure.add(k)
static void checkRight(String s)
{
    cell c = convertCell(s);
    int row = c.row;
    int col = c.col;
    if (row < numRows - 1 && !cells[row + 1][col].visited && cells[row + 1][col].type != '1') 
    {
       String k = convert(cells[row + 1][col]);
       stack.push(k);
       queue.enqueue(k);
       cells[row ][col].type='v';
    }
    
}
// search methods , implemented base on the pseudo code given in the assignment
// depth first search , use a dll stack to store the locations
public void dfSearch()
{   
    // mouse's position 
   String s=convert(mouse);
   stack.push(s);
   boolean moreToSearch = true;
   while (!stack.isEmpty() && moreToSearch)
 {  
    //print out the maze every iteration 
     printCells();
     String j = stack.pop();
     markVisited(j);
    //  add the location to the trailQueue 
     trailQueue.enqueue("("+j+")");
    //  printout the trailQueue every iteration
     trailQueue.printQueue();
    //  check if location j is the cheese's location 
     if(checkPosition(j))
     {  System.out.println("cheese is found");
    //  stop searching 
        moreToSearch = false;
     }
    //  For (every non-wall cell k that is a neighbour of j) do
   // If (k is not marked visited) then
   // structure.add(k) push() or enqueue(
     else{
        
        checkTop(j);
        checkBot(j);
        checkLeft(j);
        checkRight(j);
       
    }
     }
    //  get the cheese location , it should be the rear of the trailQueue
   String c = trailQueue.getRear();
//    print out the maze 
   printCells();
   System.out.println("Depth First Search result:");
   System.out.println("Mouse started at cell " +s);
   System.out.println("Cheese is found at cell "+c);
   System.out.println("The mouse took this route:");
   trailQueue.printQueue();
   System.out.println("mouse has traveled "+trailQueue.size+" times") ;


} 
// breadth first search , basically same method but used a dll queue
// instead of a dll stack to store the locations 
public void bfSearch()
{
   String s=convert(mouse);
   queue.enqueue(s);
   boolean moreToSearch = true;
   while (!queue.isEmpty() && moreToSearch)
   { printCells();
     String j = queue.dequeue();
     markVisited(j);
     trailQueue.enqueue("("+j+")");
     trailQueue.printQueue();
     if(checkPosition(j))
     {  System.out.println("cheese is found");
        moreToSearch = false;
     }
     else{
        checkTop(j);
        checkBot(j);
        checkLeft(j);
        checkRight(j);
       
    }
     }
   
   String c = trailQueue.getRear();
   printCells();
   System.out.println("Breadth First Search result:");
   System.out.println("Mouse started at cell " +s);
   System.out.println("Cheese is found at cell "+c);
   System.out.println("The mouse took this route:");
   trailQueue.printQueue();
   System.out.println("mouse has traveled "+trailQueue.size+" times") ;


}

//print out the cell maze 
  public void printCells()
  {  
    for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
            cell cell = cells[i][j];
           System.out.print(cell.type); }
           System.out.println();}
        
  }


}



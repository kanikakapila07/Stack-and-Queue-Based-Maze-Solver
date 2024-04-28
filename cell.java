
// class object cell 
// it has a row index , a column index , a type in char , and a boolean visisted
public class cell {
    int row ;
    int col ; 
    char type;
    boolean visited;
 

public cell(int row,int col ,char type)
{
  this.row=row ;
  this.col=col;
  this.type=type;
  // set the default to not visited
  this.visited=false;
  
}

}



public class doublyLinkedStack<T extends Comparable> implements StackInterface<T>
{
    //to implement a dll stack , we need to have two nodes , prev and next  
    private class Node<T extends Comparable> 
    {
        private T value;
        private Node<T> next;
        private Node<T> prev;
    }
    
    //top node points to the top of the stack 
    private Node<T> top;
    //size of the stack just for convenience 
    public int size;

    /**
     * Constructor for objects of class doublyLinkedStack 
     */
    public doublyLinkedStack()
     {   
        //in a empty stack , the top index is null, and the size is zero
        top = null;
        size = 0;
        
    }

    /**
     * PreCondition: None
     * PostCondition: returns true if stack is empty
     */
    public boolean isEmpty()
    {  
         // returns true if stack is empty (var top is null)
        return (top==null);
    }

    /**
     * PreCondition: None
     * PostCondition: returns false 
     */
    public boolean isFull()
    {
        // stacks can't get full therefore always return false 
        return false;
    }

    /**
     * PreCondition: None 
     * Postcondition: Adds a new element to stack  
     */

    // push method : put the new item on the top of the stack 
    public void push (T item) 
     {   
        // create a new node and make store the value of the item in there
        Node<T> newNode = new Node<T>();
        newNode.value = item;

        if(isEmpty())
        {  
            // when the stack is empty , it means that both prev and next
            // of the new node is null , and the newNode becomes the top
            newNode.prev = null;
            newNode.next=null;
            top = newNode;
            
        }  
          else
        {
            //  otherwise , make newNode the new top 
            newNode.next=top;
            top.prev = newNode ;
            newNode.prev =null ; 
            top = newNode;
        }
         //increments size after pushing 
        size++;
    }

    /**
     * PreCondition: None 
     * PostCondition:removes top item from stack else throws UnderflowException (if stack is empty) 
     */

    // pop method , delete and return the top of the stack 
    public T pop() throws UnderflowException
    {
        if(!isEmpty())
        {     
            // when there is only one element in the stack 
            if(top.next == null && top.prev == null)
            { // value of the top in temp 
              T temp = top.value;
              // make top null 
              top=null;
              //decrements size of stack 
              size--;
              // return temp 
              return temp;
            }

             else
            {
                //value of top in temp 
               T temp = top.value;
               // make the next node top 
               top = top.next ; 
               // previous top becomes null 
               top.prev=null;
               //decrements size of stack 
               size--;
               return temp;

            }
        }
         else throw new UnderflowException("Cannot pop from an empty stack");
    }

    /**
     * PreCondition: None
     * PostCondition:returns top element in stack else throw UnderflowException (if stack is empty) 
     */

    // peek returns the top element in the stack 
    public T peek() throws UnderflowException
    {
        if (!isEmpty())
            return top.value;
        else throw new UnderflowException("Cannot peek to an empty stack");
                
    } 
    
    /**
     * PreCondition: None
     * PostCondition: prints the contents of stack 
     */

    //  method to print out the stack , implement based on the code example 
    // linkedStack created by professor kawash
    public void printstack() {
        
        System.out.print("top-> ");
        Node<T> tmp = top;
        
        while (tmp != null)
        {
             System.out.print(tmp.value);
             if (tmp.next != null)System.out.print(", ");
             tmp = tmp.next;
        }
        System.out.println();
        }
      }

      
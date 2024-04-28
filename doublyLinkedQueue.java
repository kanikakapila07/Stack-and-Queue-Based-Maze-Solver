

public class doublyLinkedQueue<T extends Comparable> implements QueueInterface<T>
{  
    //to implement dll circular queue, we need to have two nodes: prev and next 
    private class Node<T extends Comparable> 
    {
        private T value; 
        private Node<T> next; 
        private Node<T> prev;

    }

    //front node points to front of circular queue
    private Node<T> front ;
    //rear node points to rear of circular queue 
    private Node<T> rear ;
    //size of queue 
    public int size ;

    /** 
    * Constructor for objects of class doublyLinkedQueue
    */
    public doublyLinkedQueue(){ 
        //in an empty circular queue, the front and rear is null and size is zero 
        front = null; 
        rear = null; 
        size = 0; 
     
    }

    /** 
    *PreCondition: None
    *PostCondition: returns true if circular queue is empty 
    */
    public boolean isEmpty(){
        //returns true if circular queue is empty (front of queue is null)
        return (front==null);   
    }

    /**
     * PreCondition: None
     * PostCondition: returns false 
     */
    public boolean isFull(){     
        //returns false if circular queue is full
        return false;   
    
    }

    /**
     * PreCondition: Circular queue is not full
     * PostCondition: adds a new element to the rear of circular queue 
     */
    public void enqueue(T item){   
        //creates a new node and stores value of item in it
        Node<T> newNode = new Node<T>();
        newNode.value = item;
        
        if(isEmpty()){ 
        //when the circular queue is empty, both front and raer is newNode
            front=newNode;
            rear = newNode;
            
        }
        //runs when circular queue is not empty
        //sets the rear of circular queue as newNode
        else{
            newNode.prev=rear;
            rear.next=newNode;
            rear = newNode ; 
        
        }
        //increments size of circular queue by 1
        size++ ; 
    }

    /**
     * PreCondition: Circular queue is not empty 
     * PostCondition: removes element from front of circular queue 
     */
    public T dequeue() throws UnderflowException{     
        //runs when circular queue is not empty 
        if(!isEmpty()){ 
            T temp = front.value ; 
            //runs when there's only one element in circular queue 

            if(size ==1){
                //sets front and rear of circular queue as null
                front = null;
                rear = null; 
                //decrements size of circular queue by 1 
                size--;
                
            }
        //runs when circular queue size is not 1
        else{
            //makes next node front
            front = front.next;
            //previous front becomes null
            front.prev=null;
            //decrements size of circular queue by 1
            size--;
        }
        return temp; 
    }
    //throws UndeflowException if the circular queue is empty 
    else{
        throw new UnderflowException("Cannot dequeue from an empty queue.");
    }
            
} 
/**
 * PreCondition: None
 * PostCondition: prints the contents of circular queue 
 */

//  method to print out the circular queue, implement based on the code example 
// LinkedQueue created by professor kawash
   public void printQueue()
    {
       //System.out.print("r->"+rear);
       Node<T> i = front;
       if (front == rear && front != null) System.out.print(i.value); // singleton queue
       else 
           while (i != null) 
       {
            System.out.print(i.value);
            if (i.next != null) System.out.print(",");
            i = i.next;
       }
       System.out.println("<-f");
       }


}
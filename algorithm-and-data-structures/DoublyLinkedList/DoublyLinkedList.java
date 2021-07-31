import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
	/*
	private int size;
	
    public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void repeatForwards(DLLNode currentNode)
    {
    		currentNode = currentNode.next;
    }
    public void repeatBackwards(DLLNode currentNode)
    {
    		currentNode = currentNode.prev;
    }
    public final int putToTail = Integer.MAX_VALUE;
	/*


	/**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail, temp; // allows the addition of a temp node

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1)
     * 
     *
     * Justification: The method checks if the head and tail are null
     * it checks in Theta(1) asymptotic time - a constant operation
     *  TODO
     */
    public boolean isEmpty()
    {
    	return head == null;  // returns true if the list is empty
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification: There is a while loop which opperates in a constant runtime operation of Theta(1)
     * since there is N elemetns its N*Theta(1) which equals Theta(N)
     *  TODO
     */

    public void insertBefore( int pos, T data ) 
    {
		int i = 0;
       	DLLNode insert = new DLLNode(data,null,head);
       	if(pos<=0 || isEmpty()){
    		insert.next = head;
    		insert.prev = null;
    		if(isEmpty()){
    			tail = insert;
        		head = insert;
    		}else{
        		head.prev = insert;
        		head = insert;
    		}
    		
    	}
       	else if(pos>=size()-1)
    	{
       		insert.prev = tail;
       		insert.next = null;
    		tail.next = insert;
    		tail = insert;
    	}else{
    		insert.next = head;
    		DLLNode tempPointer = new DLLNode(null,head,head);
    		while(i!=pos && pos<=size())
    		{
    				tempPointer.prev = tempPointer.next;
    				tempPointer.next = tempPointer.next.next;
    				i++;
    		}
    		if(i==pos)
    		{
    			insert.prev = tempPointer.prev;
    			insert.next = tempPointer.next;
    			insert.prev.next = insert;
    			insert.next.prev = insert;
    		}
    	}
      return;
}

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: 2N + 1
     *
     * Justification:It iterates through every node which has a cost of N and has 2 function calls 
     * which has cost N and 1
     *  TODO
     *
     */
    public T get(int pos) { 
        DLLNode getter = new DLLNode(null, null, head);
        T returnItem = null;
        int size = size();
        if(pos>=size) {
        	return null;
        }
        else if(isEmpty()) {
        	return null;
        }
        
        for(int i = 0; i<size; i++)
        {
      	  if(i==pos)
      	  {
      		 returnItem = getter.next.data;
      	  }else{
      		  getter = getter.next;
      	  }
        }
        return returnItem;
}

	 
    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(n)
     *
     * Justification:All operations in the while loop run in constant time - Theta(1)
     * So every iteration of the while loop will have a cost of theta(1)
     * In the worst case, the node gets deleted at the end of the list.
     * If the list contains N elements then the while loop will iterate
     * over the N elements. The total cost in worst case then will be
     * N*Theta(1) which equals Theta(N)
     *
     * 
     *  TODO
     */
    public boolean deleteAt(int pos)
    {
		int nodePosition = 0;
    	if (isEmpty() || pos < 0) {
			return false;
		
		}
    	else
		{
			DLLNode node = head;
			boolean elemRemoved = false;
			while (nodePosition <= pos && node != null) {
				if (nodePosition == pos) {
					if (node == head) {
						if (node.next != null) {
							node.next.prev = null;
							head = node.next;
						} else { 
							head = null;
							tail = null;
						}
					} else if (node == tail) {
						node.prev.next = null;
						tail = node.prev;
					} else {
						node.prev.next = node.next;
						node.next.prev = node.prev;
					}
					elemRemoved = true;
					return elemRemoved;

				} else {
					node = node.next;
					nodePosition++;
				}
			}
			return elemRemoved;
		}
        
    }
    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:While loops run in constant time(Theta(1))
     * The list has N elements so the total cost will be N*Theta(1) which equals Theta(N)
     *  TODO
     */
    public void reverse()
    {
    	if (isEmpty() || head == tail)
			return;
		else {
			DLLNode node = head;
			boolean exit = false;
			while (!exit) {
				DLLNode tempNode = new DLLNode(node.data, node.prev, node.next);
				if (node == tail) {
					node.next = tempNode.prev;
					node.prev = null;
					tail = head;
					head = node;
					exit = true;
				} else if (node == head) {
					node.prev = tempNode.next;
					node.next = null;
					node = tempNode.next;
				}  else {
					node.prev = tempNode.next;
					node.next = tempNode.prev;
					node = tempNode.next;
				}
			}
		}
		return;
      
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: N^2
     *
     * Justification:for loops have a cost of N and the makeUnique has 2 for loops so its N^2
     *  TODO
     */
    public void makeUnique()
    {
    	for(int i = 0; i <= size(); i++) {
    		T elem1 = get(i);
    		for(int k = 0; k <= size(); k++) {
    			T elem2 = get(k);
    			if(elem2 == elem1) {
    				deleteAt(k);
    			//	k--;
    			}
    		}
    	}
        //TODO

    }
    	 


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:It inserts the item at the head so it has a runtime of Theta(1)
     * 
     *  TODO
     */
    public void push(T item) 
    {
    	insertBefore(0, item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:the get function has a constant run time since it doesnt iterate
     * and it is the same for deleteAt function
     *  TODO
     */
    public T pop() 
    {
    	T item = get(0);
    	deleteAt(0);
    	return item;
      //TODO
     // return null;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:It inserts the item at the head so it has a runtime of Theta(1)
     *  TODO
     */
    public void enqueue(T item) 
    {
    	insertBefore(0, item); // same as push method
      //TODO
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *enqueueAndDequeue
     * Justification:It must go to the end of the list(N elements)
     *  TODO
     */
    public T dequeue() 
    {
     // pop from position size
      T item = get(size()-1);
      deleteAt(size()-1);
       return item;
    }
    
    private int size() {
    	int i=0;
		if(head!=null && tail!=null)
		{
			DLLNode temp = head;
			i++;
			while(temp.next!=null)
			{
				temp = temp.next;
				i++;
			}
		}
		return i;
	}
    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}
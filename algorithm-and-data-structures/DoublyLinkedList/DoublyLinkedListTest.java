import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }
    @Test
    
    public void testGet()
    {
    	DoublyLinkedList<Integer> queueTest = new DoublyLinkedList<Integer>();
    	queueTest.enqueue(2);
    	assertEquals("testing single element get", 2, (int) queueTest.get(0));
    	queueTest.enqueue(3);
    	queueTest.enqueue(4);
    	assertEquals("test for greater than size", null, queueTest.get(12));
    	queueTest.dequeue();
    	queueTest.dequeue();
    	assertEquals("out of bounds testing", null, queueTest.get(1));
    	queueTest.enqueue(5);
    	queueTest.enqueue(6);
    	queueTest.enqueue(7);
    	assertEquals("test get for more than 1 element list", 4, (int)queueTest.get(3));
    }
    
    @Test
    public void testDeleteAt()
    {
    	
    	
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>(); // test empty list    	
    	assertEquals("testing deleting in an empty list", false,testDLL.deleteAt(0));
        assertEquals("deleting a negative element", false, testDLL.deleteAt(-12));
        
        testDLL.insertBefore(0,1); // deleting test with single element
        testDLL.deleteAt(0);
        assertEquals("testing deleting in an empty list", "",testDLL.toString());
        testDLL.insertBefore(0,1);

        assertEquals("testing delete with a greater value than size", false ,testDLL.deleteAt(1));
        testDLL.deleteAt(0);
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.deleteAt(0);
        assertEquals("testing deleting first element", "1,2,3", testDLL.toString()); // test first element
        testDLL.deleteAt(2);
        assertEquals("testing deleting last element", "1,2", testDLL.toString()); //Test last element
        testDLL.insertBefore(2,3);
        testDLL.deleteAt(1);
        assertEquals("testing deleting middle element", "1,3", testDLL.toString()); // test middle element
        assertEquals("testing deleting element(1) in 2 element list", true, testDLL.deleteAt(1));
    }
    
    
    @Test
    public void testPushPop() /// testing the reverse, push and pop function
    {
    	DoublyLinkedList<Integer> testPushPop = new DoublyLinkedList<Integer>();
    	testPushPop.reverse();
    	assertEquals("pop off empty stack", null, testPushPop.pop());
    	testPushPop.pop();
    	testPushPop.push(-1);
    	testPushPop.pop();
    	testPushPop.push(2);
    	testPushPop.push(3);
    	testPushPop.push(4);
    	
    	assertEquals("push to stack", "4,3,2", testPushPop.toString());
    	
    	assertEquals("pop off stack", 4, (int)testPushPop.pop());
    	assertEquals("pop off stack", "3,2", testPushPop.toString());	
    	
    	testPushPop.reverse();
    	assertEquals("reverse", "2,3", testPushPop.toString());
    	testPushPop.reverse(); 
    	
    	testPushPop.push(4);
    	testPushPop.reverse();
    	assertEquals("reverse", "2,3,4", testPushPop.toString());
    }
    
    @Test
   public void testMakeUnique()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList();
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 1);
    	testDLL.makeUnique();/*
    	assertEquals("Checking deleteAt to a list containnig 3 elements", "1,2", testDLL.toString());
    	testDLL = new DoublyLinkedList();
    	
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 1);
    	testDLL.insertBefore(3, 4);
    	testDLL.makeUnique();
    	assertEquals("Checking deleteAt to a list containnig 3 elements", "1,2,4", testDLL.toString());
    	DoublyLinkedList<Integer> testDLLChar = new DoublyLinkedList();
    
  
    	testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 2);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 2);
    	testDLL.insertBefore(3, 1);
    	testDLL.insertBefore(4, 1);
    	testDLL.insertBefore(5, 1);
    	testDLL.insertBefore(6, 1);
    	testDLL.insertBefore(7, 1);
    	testDLL.makeUnique();
    	assertEquals("Checking makeUnique on a list containing 7 elements", "2,1", testDLL.toString());
*/
    }
    
    @Test
    public void testenqueueAndDequeue()
    {
    	DoublyLinkedList<Integer> enqueueAndDequeue = new DoublyLinkedList<Integer>();
    	assertEquals("pop off empty stack", null, enqueueAndDequeue.dequeue());
    	
    	enqueueAndDequeue.enqueue(2);
    	assertEquals("get element", 2, (int) enqueueAndDequeue.dequeue());
    	enqueueAndDequeue.enqueue(2);
    	
    	enqueueAndDequeue.enqueue(3);
    	enqueueAndDequeue.enqueue(4);
    	
    	assertEquals("add to queue", "4,3,2", enqueueAndDequeue.toString());
    	
    	assertEquals("dequeue", 2, (int) enqueueAndDequeue.dequeue());
    	assertEquals("dequeue", "4,3", enqueueAndDequeue.toString());	
    }
    

    

}
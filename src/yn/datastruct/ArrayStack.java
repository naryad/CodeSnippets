package yn.datastruct;

//ArrayStack class
//
//CONSTRUCTION: with no initializer
//
//******************PUBLIC OPERATIONS*********************
//void push( x )         --> Insert x
//void pop( )            --> Remove most recently inserted item
//Object top( )          --> Return most recently inserted item
//Object topAndPop( )    --> Return and remove most recent item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//******************ERRORS********************************
//top, pop, or topAndPop on empty stack

/**
 * Array-based implementation of the stack.
 * 
 * @author Mark Allen Weiss
 */
public class ArrayStack implements Stack {
	/**
	 * Construct the stack.
	 */
	public ArrayStack() {
		theArray = new Object[DEFAULT_CAPACITY];
		topOfStack = -1;
	}

	/**
	 * Test if the stack is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return topOfStack == -1;
	}

	/**
	 * Make the stack logically empty.
	 */
	public void makeEmpty() {
		topOfStack = -1;
	}

	/**
	 * Get the most recently inserted item in the stack. Does not alter the
	 * stack.
	 * 
	 * @return the most recently inserted item in the stack.
	 * @throws UnderflowException
	 *             if the stack is empty.
	 */
	public Object top() {
		if (isEmpty())
			throw new UnderflowException("ArrayStack top");
		return theArray[topOfStack];
	}

	/**
	 * Remove the most recently inserted item from the stack.
	 * 
	 * @throws UnderflowException
	 *             if the stack is empty.
	 */
	public void pop() {
		if (isEmpty())
			throw new UnderflowException("ArrayStack pop");
		topOfStack--;
	}

	/**
	 * Return and remove the most recently inserted item from the stack.
	 * 
	 * @return the most recently inserted item in the stack.
	 * @throws Underflow
	 *             if the stack is empty.
	 */
	public Object topAndPop() {
		if (isEmpty())
			throw new UnderflowException("ArrayStack topAndPop");
		return theArray[topOfStack--];
	}

	/**
	 * Insert a new item into the stack.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void push(Object x) {
		if (topOfStack + 1 == theArray.length)
			doubleArray();
		theArray[++topOfStack] = x;
	}

	/**
	 * Internal method to extend theArray.
	 */
	private void doubleArray() {
		Object[] newArray;

		newArray = new Object[theArray.length * 2];
		for (int i = 0; i < theArray.length; i++)
			newArray[i] = theArray[i];
		theArray = newArray;
	}

	private Object[] theArray;
	private int topOfStack;

	private static final int DEFAULT_CAPACITY = 10;

}

/**
 * Exception class for access in empty containers such as stacks, queues, and
 * priority queues.
 * 
 * @author Mark Allen Weiss
 */
class UnderflowException extends RuntimeException {
	/**
	 * Construct this exception object.
	 * 
	 * @param message
	 *            the error message.
	 */
	public UnderflowException(String message) {
		super(message);
	}
}

// Stack interface
//
// ******************PUBLIC OPERATIONS*********************
// void push( x ) --> Insert x
// void pop( ) --> Remove most recently inserted item
// Object top( ) --> Return most recently inserted item
// Object topAndPop( ) --> Return and remove most recent item
// boolean isEmpty( ) --> Return true if empty; else false
// void makeEmpty( ) --> Remove all items
// ******************ERRORS********************************
// top, pop, or topAndPop on empty stack

/**
 * Protocol for stacks.
 * 
 * @author Mark Allen Weiss
 */
interface Stack {
	/**
	 * Insert a new item into the stack.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	void push(Object x);

	/**
	 * Remove the most recently inserted item from the stack.
	 * 
	 * @exception UnderflowException
	 *                if the stack is empty.
	 */
	void pop();

	/**
	 * Get the most recently inserted item in the stack. Does not alter the
	 * stack.
	 * 
	 * @return the most recently inserted item in the stack.
	 * @exception UnderflowException
	 *                if the stack is empty.
	 */
	Object top();

	/**
	 * Return and remove the most recently inserted item from the stack.
	 * 
	 * @return the most recently inserted item in the stack.
	 * @exception UnderflowException
	 *                if the stack is empty.
	 */
	Object topAndPop();

	/**
	 * Test if the stack is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	boolean isEmpty();

	/**
	 * Make the stack logically empty.
	 */
	void makeEmpty();
}
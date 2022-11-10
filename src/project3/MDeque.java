/**
 * We learned about stacks, queues, and deques. An mdeque (pronounced "em-deck") 
 * is a variation of double ended queue that also provides a fast access to the 
 * middle element.

The documentation for all the required public methods that you need to implement 
is given at its javadoc page. You may implement additional methods, if you wish. 
They should be private, unless they are designed for use by the third parties. 
All methods need to be documented.

IMPLEMENTATION RESTRICTIONS:

 All push…, pop…, and peek… methods should be O(1).
 The toString method should be implemented recursively.
 Your MDeque design needs to be based on a doubly linked list that you implement. 
 (It does not need to be inheriting from another class, you just need to use the 
 doubly linked list design. You will need to keep track of the middle of the mdeque
 in addition to the front and the back.)

 NOTES:
 * A double ended queue or a deque is similar to a "normal" queue data structure
 	  but provides insertion and deletion at the front and back of the queue

  @author Oliver Tabibzadeh

 */


package project3;

import java.util.Iterator;

public class MDeque<E> {

	public int size;
	private Node head;
	private Node tail;
	private Node middle;

	/** Nested ReverseIterator class
	 * 
	 */
	private class ReverseIterator implements Iterator<E> {

		private Node n = tail;

		@Override
		public boolean hasNext() {
			return n != null;
		}

		@Override
		public E next() {
			// If hasNext() iterate in reverse
			if(hasNext()) {
				Node temp = n;
				n = n.prev;
				return temp.data;
			}
			// If does not have next, return null
			return null;
		}
	}

	/** Nested "normal" Iterator Class
	 * 
	 */
	private class NormalIterator implements Iterator<E>{
		private Node n = head;

		@Override
		public boolean hasNext() {
			return n != null;
		}

		@Override
		public E next() {
			Node temp = n;
			n = n.next;
			return temp.data;
		}
	}
	/**
	 *  Nested node class
	 */
	class Node {
		E data;
		Node next;
		Node prev;

		// Node constructor
		Node(E d){
			data = d;
			next = null;
			prev = null;
			middle = null;
		}
	}

	/** MDeque() Constructor
	 * Zero argument constructor to create an MDeque
	 */
	public MDeque(){

	}

	/** iterator()
	 * @return Returns an iterator over the elements in this mdeque in proper sequence.
	 */
	public Iterator<E> iterator(){
		return new NormalIterator();
	}

	/** peekBack()
	 * @return Retrieves the back element of this mdeque.
	 */
	public E peekBack() {
		return tail.data; 
	}

	/** peekFront()
	 * @return Retrieves the first element of this mdeque.
	 */
	public E peekFront(){
		return head.data;
	}

	/** peekMiddle()
	 * @return Retrieves the middle element of this mdeque.
	 */
	public E peekMiddle(){
		return middle.data; // TODO: implement this method
	}

	/** popBack()
	 * @return Retrieves and removes the back element of this mdeque.
	 */
	public E popBack() {
		E back = tail.data;

		// Cannot pop from empty list
		if( size == 0 ) {
			throw new IndexOutOfBoundsException( "List is size 0" );		
		}

		else if( size == 1 ) {
			tail = null;
			head = null;
			size --;
			return back;
		}

		else {
			// Assign tail to prev, then point tail.next to null (garbage collect)
			tail = tail.prev;
			tail.next = null;
			size--;
			return back;
		}
	}

	/** popFront()
	 * @return Retrieves and removes the first element of this mdeque.
	 */
	public E popFront() {

		if( size == 0 ) {
			throw new IndexOutOfBoundsException( "List is size 0, can not pop" );		
		}

		// Pop and return first element of mdeque
		Node temp = head;

		// If size is 1, reset the mdeque
		if( size == 1 ) {
			head = null;
			tail = null;
			return temp.data;
		}

		head = head.next;
		head.prev = null;
		size--;

		if( size == 0 ) {
			tail = null;
			head = null;
			middle = null;

		}
		// Increment middle if size is divisible by 2
		if( size % 2 == 0 ) {
			middle = middle.next;
		}
		return temp.data;
	}

	/** popMiddle()
	 * @return Retrieves and removes the middle element of this mdeque.
	 */
	public E popMiddle() {
		return null; // TODO: Implement this method
	}

	/** pushBack(E item)
	 * @param Inserts the specified item at the back of this mdeque.
	 */
	public void pushBack(E item) {
		Node n = new Node(item);

		if( size == 0 ) {
			// Set head and tail = to newNode
			head = n;
			tail = n;
			middle = n;
			// Point head and tail to eachother
			head.next = tail;
			tail.prev = head;
			size++;
		}
		else {
			Node oldTail = tail;
			tail.next = n;
			tail = n;
			tail.prev = oldTail;
			size++;
		}

		// Middle:
		int mid = size/2;
		Node temp = head;
		while(mid != 0) {
			temp = temp.next;
			mid--;
		}
		middle = temp;
		middle.next = temp.next;
		middle.prev = temp.prev;
	}

	/** pushFront(E item)
	 * @param Inserts the specified item at the front of this mdeque.
	 */
	public void pushFront(E item) {
		Node n = new Node(item);
		n.next = head;
		n.prev = null;
		if(head != null)
			head.prev = n;
		head = n;
		size++;
		
		// Middle:
		int mid = size/2;
		Node temp = head;
		while(mid != 0) {
			temp = temp.next;
			mid--;
		}
		middle = temp;
		middle.next = temp.next;
		middle.prev = temp.prev;
	}

	/** pushMiddle(E item)
	 * @param Inserts the specified item at the middle of this mdeque.
	 */
	public void pushMiddle(E item) {
		// TODO: implement this method
	}

	/** reverseIterator()
	 * @return Returns an iterator over the elements in this mdeque in reverse sequential order.
	 */
	public Iterator<E> reverseIterator(){
		return new ReverseIterator();
	}

	/** size()
	 * @return Returns the number of elements in this mdeque.
	 */
	public int size() {
		return size;
	}

	/** toString()
	 * @return Returns a string representation of this mdeque.
	 */
	public String toString() {
		StringBuilder SB = new StringBuilder();
		Node h = head;
		while( h != null ) {
			SB.append(h.data + ", ");
			h = h.next;
		}	
		return SB.toString(); // TODO: implement this method RECURSIVELY
	}
}

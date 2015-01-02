/**
 * LinkedListQueue.java - LinkedList implementation of Queue
 *	initialize
 *	enQueue
 *	deQueue
 *	peek
 *	empty
 *	search
 *	Iterator
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<Item> {
	Node FIRST;
	Node LAST;
	int N;

	private class Node<Item> {
		Item content;
		Node next;
	}

	public LinkedListQueue() {
		N = 0;
	}

	public void enQueue(Item item) {
		Node<Item> newN = new Node<Item>();
		newN.content = item;
		if( isEmpty() ) {
			//newN.next = null;
			FIRST = newN;
			LAST = newN;
		} else {
			LAST.next = newN;
			LAST = newN;
		}
		N++;
	}

	public Item deQueue() {
		if( isEmpty() ) { throw new RuntimeException("Queue Underflow"); }
		Item item = (Item) FIRST.content;
		FIRST = FIRST.next;
		N--;
		 if (isEmpty()) LAST = null;   // to avoid loitering
		return item;
	}

	public Item peek() {
		return (Item) FIRST.content;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public static void main( String[] args ) {
		LinkedListQueue<Integer> intQueue = new LinkedListQueue<Integer>();
		for (int i = 0; i < 20; i++ ) {
			System.out.println(i);
			intQueue.enQueue(i);
		}

		/*Iterator it = intQueue.iterator();
		for (int i = 0; i < 30; i++ ) {
			try {
				System.out.println(it.next());
			} catch ( Exception e ) {
				System.out.println(i);
			}
		}*/

		for (int i = 0; i < 30; i++ ) {
			if( intQueue.isEmpty() == false ) { System.out.println(intQueue.deQueue()); }
		}
	}
}
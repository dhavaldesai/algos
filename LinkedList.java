/**
 * LinkedList.java - Linked list implmentation
 *
 * insertAtFirst
 * insertAtLast
 * insertAt
 * removeFirst
 * removeLast
 * removeAt
 * size
 * iterator
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<Item> implements Iterable{
	private int N;
	Node FIRST;
	Node LAST;

	private class Node<Item> {
		Item item;
		Node next;
	}

	public LinkedList() {
		N = 0;
		FIRST = null;
		LAST = null;
	}

	public void insertAtFirst(Item item) {
		Node newNode = new Node();
		newNode.item = item;
		if( FIRST == null ) {
			newNode.next = null;
			LAST = newNode;
		} else {
			newNode.next = FIRST;
		}
		FIRST = newNode;
		N++;
	}

	public void insertAtLast(Item item) {
		Node newNode = new Node();
		newNode.item = item;
		if ( LAST == null ) {
			newNode.next = null;
			LAST = newNode;
			FIRST = newNode;
		} else {
			LAST.next = newNode;
			LAST = newNode;
		}
		N++;
	}

	public void insertAfter(Item item, Node node) {
		Node newNode = new Node();
		newNode.item = item;
		if ( node.next == null ) {
			newNode.next = null;
		} else {
		 	newNode.next = node.next;
		}
		node.next =  newNode;
		if (node == LAST) {
			LAST = newNode;
		}
	}

	public Item removeFirst() {
		Item item = (Item) FIRST.item;
		FIRST = FIRST.next;
		if(isEmpty()) { LAST = null; }
		return item;
	}

	/* Not possible because of absence of back pointer OR would need traverse the entire list */
	/*public Item removeLast() {
		/*Item item = LAST.item;
		LAST = FIRST.next;
		if(isEmpty()) { LAST = null; }
		return item;*/
	//}

	public Item removeAfter(Node node) {
		if (node == LAST ) { throw new NoSuchElementException(); }
		Item item = (Item) node.next.item;
		node.next = node.next.next;
		return item;
	}

	public boolean isEmpty() {
		return FIRST == null;
	}

	public Iterator<Item> iterator() {
		return new LinkedListIterator<Item>();
	}

	public class LinkedListIterator<Item> implements Iterator<Item> {
		Node currentNode = FIRST;

		public boolean hasNext() {
			return currentNode != null;
		}

		public Item next() {
			Item item;
			if( !hasNext() ) { throw new NoSuchElementException(); }
			else {
				item = (Item) currentNode.item;
				currentNode = currentNode.next;
			}
			return item;
		}

		public void remove() { throw new UnsupportedOperationException(); }
	}

	public static void main( String[] args) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for (int i = 0; i < 20; i++ ) {
			System.out.println(i);
			ll.insertAtLast(i);
		}

		Iterator it = ll.iterator();
		for (int i = 0; i < 30; i++ ) {
			try {
				System.out.println(it.next());
			} catch ( Exception e ) {
				System.out.println("Exception:" + i);
			}
		}

		for (int i = 0; i < 30; i++ ) {
			if( ll.isEmpty() == false ) { System.out.println(ll.removeFirst()); }
		}
	}
}
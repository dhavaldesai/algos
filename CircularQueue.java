/**
 * CircularQueue.java 
 * insert
 * 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueue<Item> implements Iterable<Item>{
	Node FIRST;
	Node LAST;
	int N;

	private class Node<Item> {
		Item item;
		Node next;
	}

	public CircularQueue() {
		N = 0;
		FIRST = LAST = null;
	}

	public void enQueue(Item item) {
		Node<Item> newN = new Node<Item>();
		newN.item = item;
		if( isEmpty() ) {
			//newN.next = null;
			newN.next = newN;
			FIRST = newN;
			LAST = newN;			
		} else {
			LAST.next = newN;
			newN.next = FIRST;
			LAST = newN;			
		}
		N++;
	}

	public Item deQueue() {
		if( isEmpty() ) { throw new RuntimeException("Queue Underflow"); }
		Item item = (Item) FIRST.item;
		FIRST = FIRST.next;
		LAST.next = FIRST;
		N--;
		 if (isEmpty()) LAST = null;   // to avoid loitering
		return item;
	}

	public Item peek() {
		return (Item) FIRST.item;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Iterator<Item> iterator() {
		return new CircularQueueIterator();
	}

	public class CircularQueueIterator<Item> implements Iterator<Item> {
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

	public static void main( String[] args ) {
		CircularQueue<Integer> intQueue = new CircularQueue<Integer>();
		for (int i = 0; i < 20; i++ ) {
			System.out.println(i);
			intQueue.enQueue(i);
		}

		Iterator it = intQueue.iterator();
		for (int i = 0; i < 30; i++ ) {
			try {
				System.out.println(it.next());
			} catch ( Exception e ) {
				System.out.println("Exception:" + i + "  " + e);
			}
		}

		for (int i = 0; i < 30; i++ ) {
			if( intQueue.isEmpty() == false ) { System.out.println(intQueue.deQueue()); }
		}
	}
}
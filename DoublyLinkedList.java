/**
 * DoublyLinkedList.java - Doubly Linked List implementation
 * traverse forward
 * traverse backward
 * insertFirst
 * insertLast
 * insertAfter
 * insertBefore
 * removeFirst
 * removeLast
 * removeAfter
 * removeBefore
 * isEmpty
 * iterator
 * size
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> {

	private Node FIRST;
	private Node LAST;
	private int N;

	public DoublyLinkedList() {
		FIRST = null;
		LAST = null;
		N = 0;
	}

	private class Node<Item> {
		Item item;
		Node next;
		Node prev;
	}

	public void insertFirst(Item item) {
		Node<Item> newNode = new Node<Item>();
		newNode.item = item;
		if ( FIRST == null) {
			newNode.next = newNode.prev = null;
			FIRST = LAST = newNode;
		} else {
			FIRST.prev = newNode;
			newNode.next = FIRST;
			newNode.prev =  null;
			FIRST = newNode;
		}
		N++;
	}

	public void insertLast(Item item) {
		Node<Item> newNode = new Node<Item>();
		newNode.item = item;
		if ( FIRST == null ) {
			newNode.next = newNode.prev = null;
			FIRST = LAST = newNode;
		} else {
			newNode.prev = LAST;
			newNode.next = null;
			LAST.next = newNode;
			LAST = newNode;
		}
		N++;
	}

	public void insertAfter(Item item, Node node) {
		Node<Item> newNode = new Node<Item>();
		newNode.item = item;
		if ( LAST == node ) {
			newNode.prev = LAST;
			newNode.next = null;
			LAST.next = newNode;
			LAST = newNode;
		} else {
			newNode.prev = node;
			newNode.next = node.next;
			node.next = newNode;
		}
		N++;
	}

	public void insertBefore(Item item, Node node) {
		Node<Item> newNode = new Node<Item>();
		newNode.item = item;
		if ( FIRST == node ) {
			newNode.prev = LAST;
			newNode.next = null;
			LAST.next = newNode;
			LAST = newNode;
		} else {
			newNode.prev = node;
			newNode.next = node.next;
			node.next = newNode;
		}
		N++;
	}

	public Item remove(Node node) {
		if ( node == LAST && node == FIRST ) {
			FIRST = LAST = null;
		} else if ( FIRST == node ) {
			FIRST = FIRST.next;
			FIRST.prev = null;
		} else if ( LAST == node ) {
			LAST = LAST.prev;
			LAST.next = null;
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.next = node.prev = null;
		}
		Item item = (Item)node.item;
		N--;
		return item;
	}

	public boolean isEmpty() {
		return FIRST == null;
	}

	public int size(){
		return N;
	}

	public Iterator<Item> iterator() {
		return new DoublyLinkedListIterator();
	}

	public class DoublyLinkedListIterator<Item> implements Iterator<Item> {
		Node currentNode;

		public DoublyLinkedListIterator(){
			currentNode = FIRST;
		}
		public boolean hasNext() {//System.out.println("dll hasnext");
			//if(currentNode == null) { /*System.out.println("dll hasnext null");*/return false;}
			return currentNode != null;
		}
		public Item next(){ //System.out.println("dll next");
			if(hasNext()) {
				Item item = (Item) currentNode.item;
				currentNode = currentNode.next;
				return item;
			} else {
				throw new NoSuchElementException();
			}
		}
		public boolean hasPrevious() {
			return currentNode.prev == null;
		}
		public Item previous() {
			if(hasPrevious()) {
				currentNode = currentNode.prev;
				Item item = (Item) currentNode.item;
				return item;
			} else {
				throw new NoSuchElementException();
			}
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args){
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
		for (int i=0; i< 20; i++) {
			System.out.println(i);
			dll.insertLast(i);
		}

		Iterator it = dll.iterator();
		for (int i = 0; it.hasNext(); i++ ) {
			try {
				System.out.println(it.next());
			} catch ( Exception e ) {
				System.out.println("Exception:" + i + "  " + e);
			}
		}

		for (int i=0; i< 20; i++) {
			System.out.println(dll.remove(dll.LAST));
		}
	}

}
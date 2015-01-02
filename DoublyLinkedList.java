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
	}

	public void insertLast(Item item) {
		Node<Item> newNode = new Node<Item>();
		if ( FIRST == null ) {
			newNode.next = = newNode.prev = null;
			FIRST = LAST = newNode;
		} else {
			newNode.prev = LAST;
			newNode.next = null;
			LAST.next = newNode;
			LAST = newNode;
		}
	}

	public void insertAfter(Item item, Node node) {
		Node<Item> newNode = new Node<Item>();
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
	}

	public void insertBefore(Item item, Node node) {
		Node<Item> newNode = new Node<Item>();
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
	}

}
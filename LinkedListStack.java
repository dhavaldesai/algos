/**
 * LinkedListStack.java - LinkedList implementation of Stack
 *	initialize
 *	push
 *	pop
 *	peek
 *	empty
 *	search
 *	Iterator
 */

import java.util.Iterator;

public class LinkedListStack<Item> {
	Node TOP;

	private class Node<Item> {
		Item content;
		Node Prev;
	}

	public LinkedListStack() {

	}

	public void push(Item item) {
		Node<Item> newN = new Node<Item>();
		newN.content = item;
		if( TOP == null ) {
			newN.Prev = null;
		} else {
			newN.Prev = TOP;
		}
		TOP =  newN;
	}

	public Item pop() {
		Item item = (Item) TOP.content;
		TOP = TOP.Prev;
		return item;
	}

	public Item peek() {
		return (Item) TOP.content;
	}

	public boolean isEmpty() {
		return TOP == null;
	}

	public static void main( String[] args ) {
		LinkedListStack<Integer> intStack = new LinkedListStack<Integer>();
		for (int i = 0; i < 20; i++ ) {
			System.out.println(i);
			intStack.push(i);
		}

		/*Iterator it = intStack.iterator();
		for (int i = 0; i < 30; i++ ) {
			try {
				System.out.println(it.next());
			} catch ( Exception e ) {
				System.out.println(i);
			}
		}*/

		for (int i = 0; i < 30; i++ ) {
			if( intStack.isEmpty() == false ) { System.out.println(intStack.pop()); }
		}
	}
}
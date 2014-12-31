/**
 *	ArrayStack.java -- Stack implementation using Array
 *	initialize
 *	push
 *	pop
 *	peek
 *	empty
 *	search
 *	private resize
 *	Iterator
 */
import java.util.Iterator;

public class ArrayStack<Item> implements Iterable<Item> {

	Item[] stackArray;
	int stackSize;
	int maxStacksize;

	public ArrayStack( int max ) {
		stackArray = ( Item[] ) new Object[max+1];
		stackSize = 0;
		maxStacksize = max;
	}

	public void push(Item ele) { stackArray[++stackSize] = ele; }

	public Item pop() { return stackArray[stackSize--]; }

	public Item peek() { return stackArray[stackSize]; }

	public boolean isEmpty() { return stackSize == 0; }

	public boolean isFull() { return stackSize == maxStacksize; }

	public Iterator<Item> iterator() { return new ArrayStackIterator(); }

	public class ArrayStackIterator implements Iterator<Item> {
		private int i = stackSize;

		public boolean hasNext() { return i >= 0; }
		public Item next() { if (!hasNext()) throw new UnsupportedOperationException();return stackArray[i--]; }
		public void remove() { throw new UnsupportedOperationException(); }
	}

	public static void main( String[] args ) {
		ArrayStack<Integer> intStack = new ArrayStack<Integer>(20);
		for (int i = 0; i < 30; i++ ) {
			System.out.println(i);
			if( intStack.isFull() == false ) { intStack.push(i); }
		}

		Iterator it = intStack.iterator();
		for (int i = 0; i < 30; i++ ) {
			try {
				System.out.println(it.next());
			} catch ( Exception e ) {
				System.out.println(i);
			}
		}

		for (int i = 0; i < 30; i++ ) {
			if( intStack.isEmpty() == false ) { System.out.println(intStack.pop()); }
		}
	}
}
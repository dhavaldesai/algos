/**
 * SeparateChain.java - LinkedList for HashTable
 * get
 * set
 * iterate
 * size
 * contains
 * delete
 */

import java.util.Iterator;
public class SeparateChain<Key extends Comparable<Key>, Value> /*implements Iterable*/ {

	private int N;
	private Node FIRST, LAST;

	private class Node {
		Key key;
		Value value;
		Node next;

		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void delete(Key key) {
		FIRST = delete(FIRST, key);
	}

	private Node delete(Node x, Key key) {
		if(x == null) { return null; }
		if (key.equals(x.key)) { N--; return x.next; }
		x.next = delete(x.next,key);
		return x;
	}

	public void put(Key key, Value value) {
		if(value == null) { delete(key); }

		for(Node x = FIRST; x != null; x = x.next) {
			if(key.equals(x.key)) {
				x.value = value;
				return;
			}
		}
		if(FIRST == null) {
			LAST = FIRST = new Node(key, value, FIRST);
		} else {
			FIRST = new Node(key, value, FIRST);
		}
		N++;
	}

	public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        for (Node x = FIRST; x != null; x = x.next) {
            if (key.equals(x.key)) return x.value;
        }
        return null;
    }

	public DoublyLinkedList<Key> keys()  {
        DoublyLinkedList<Key> queue = new DoublyLinkedList<Key>();
        for (Node x = FIRST; x != null; x = x.next){
        	//System.out.println("SC FIRST: "+ (FIRST == null));
            queue.insertLast(x.key);
        }
        /*Iterator it = queue.iterator();
        for (int i = 0; it.hasNext(); i++ ) {
            try {
                System.out.println(it.next());
            } catch ( Exception e ) {
                System.out.println("Exception:" + i + "  " + e);break;
            }
        }*/
        return queue;
    }



}

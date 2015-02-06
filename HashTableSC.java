/**
 * HastTableSC.java - Hash table implementation using Separate Chaining
 * get
 * set
 * hashcode
 * isequal
 * array of linked list
 *
 */

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class HashTableSC<Key extends Comparable<Key>, Value> {

	private static final int INIT_CAPACITY = 4;
	private int N;
	private int M;
	private SeparateChain<Key, Value>[] sc;

    public HashTableSC(){
        this(INIT_CAPACITY);
    }

	public HashTableSC(int capacity) {
		M = capacity;
        sc = (SeparateChain<Key, Value>[]) new SeparateChain[M];
        for (int i = 0; i < M; i++) {
            sc[i] = new SeparateChain<Key, Value>();
        }
	}

    private void resize(int capacity) {
        HashTableSC<Key, Value> temp = new HashTableSC<Key, Value>(capacity);
        for (int i = 0; i< M ; i++) {
            for (Key key : sc[i].keys()) {
                temp.sc[i].put(key,sc[i].get(key));
            }
        }

        this.M  = temp.M;
        this.N  = temp.N;
        this.sc = temp.sc;
    }

	private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

     public int size() {
        return N;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        int i = hash(key);System.out.println("hashOO: "+i);
        return sc[i].get(key);
    }

    public void put(Key key, Value value) {
        if(value == null) { delete(key); return;}

        if(N >= 10*M) { resize(2*M); }

        int i = hash(key);System.out.println("hash: "+i);
        if (!sc[i].contains(key)) N++;
        sc[i].put(key, value);
    }

    public void delete(Key key){
        int i = hash(key);
        if (sc[i].contains(key)) N--;
        sc[i].delete(key);

        if( N <= 2*M && M > INIT_CAPACITY ) { resize(M/2); }
    }

    // return keys in symbol table as an Iterable
    public DoublyLinkedList<Key> keys() {
        DoublyLinkedList<Key> queue = new DoublyLinkedList<Key>();
        for (int i = 0; i < M; i++) {
            //System.out.println("here2: "+sc[i].size());
            try{
                for (Key key : sc[i].keys()){
                    System.out.println("Key "+ key);
                    queue.insertLast(key);
                }
                //System.out.println("I val: " + i);
                //System.out.println(queue.isEmpty());
            } catch(Exception e){
                System.out.println("Exception" + e);
            }
        }
        /*Iterator it = queue.iterator();
        System.out.println(queue.isEmpty());
        for (int i = 0; it.hasNext(); i++ ) {
            try {
                System.out.println(it.next());
            } catch ( Exception e ) {
                System.out.println("Exception:" + i + "  " + e);
            }
        }*/
        return queue;
    }

    /***********************************************************************
    *  Unit test client.
    ***********************************************************************/
    public static void main(String[] args) {
        HashTableSC<String, Integer> st = new HashTableSC<String, Integer>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; ; i++) {
            try{
                String key = br.readLine();
                System.out.println(key + "::" +st.size());
                if(key == null) break;
                st.put(key, i);
            } catch (IOException ioe) {
                 System.out.println("IO error trying to read your name!");
                 System.exit(1);
              }
        }

        // print keys
        for (String s : st.keys())
            System.out.println("here: "+s + " " + st.get(s));
    }
}

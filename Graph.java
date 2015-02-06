import java.util.Iterator;
public class Graph {
	private int V;
	private int E;
	private DoublyLinkedList<Integer>[] adj;
    private int[] edgeTo;
	private boolean[] marked;
    private int count;

	public Graph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (DoublyLinkedList<Integer>[]) new DoublyLinkedList[V];
		for (int i = 0; i < V ; i++) {
			adj[i] = new DoublyLinkedList<Integer>();
		}
		count = 0;
		marked = new boolean[V];
        edgeTo = new int[V];
	}

	public Graph(Graph G){
		this.V = G.V();
		this.E = G.E();
		adj = (DoublyLinkedList<Integer>[]) new DoublyLinkedList[V];
		for (int v = 0;v < V ; v++) {
			ArrayStack<Integer> st = new ArrayStack<Integer>(G.adj[v].size());
			for(int a : G.adj[v]) {
				st.push(a);
			}

			for(int w: st){
				adj[v].insertFirst(w);
			}
		}
		count = 0;
		marked = new boolean[V];
        edgeTo = new int[V];
	}



    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void validateVertex(int v){
    	if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEgde(int v, int w){

    	validateVertex(v);
        validateVertex(w);
        E++;
    	adj[v].insertLast(w);
    	adj[w].insertLast(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString(){
    	String g = new String();
    	g = Integer.toString(V) + " Vertics " + Integer.toString(E) + " Edges\n";

    	for (int v = 0; v < V; v++ ) {
    		g += Integer.toString(v) + ": ";//System.out.println(v + " " + adj[v].size());
			/*Iterator it = adj[v].iterator();
    		for (int i = 0; it.hasNext(); i++ ) {
			try {
					System.out.println(it.next());
				} catch ( Exception e ) {
					System.out.println("Exception:" + i + "  " + e);
				}
			}*/
    		for(int w : adj[v]) {//System.out.println(w);
    			g += Integer.toString(w) + " ";
    		}
    		g += "\n";
    	}
    	return g;
    }

    public void dfs(int v) {
    	count++;
    	marked[v] = true;
    	System.out.println(v);
    	for(int w : adj[v]) {
    		if(!marked[w]) {
                edgeTo[w] = v;
    			dfs(w);
    		}
    	}
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int s, int v){
        if(!hasPathTo(v)) return null;
        DoublyLinkedList<Integer> stk = new DoublyLinkedList<Integer>();
        for(int x = v; x !=s ; x = edgeTo[x])
            stk.insertFirst(x);
        stk.insertFirst(s);
        return stk;
    }

     public static void main(String[] args) {
        Graph G = new Graph(13);
		G.addEgde(0,6);
		G.addEgde(0,2);
		G.addEgde(0,1);
		G.addEgde(0,5);
		G.addEgde(3,5);
		G.addEgde(3,4);
		G.addEgde(4,5);
		G.addEgde(4,6);
		G.addEgde(7,8);
		G.addEgde(9,11);
		G.addEgde(9,10);
		G.addEgde(9,12);
		G.addEgde(11,12);
        System.out.println(G);
        G.dfs(0);

        int s = 0;
        for (int v = 0; v < G.V(); v++) {
            if (G.hasPathTo(v)) {
                System.out.printf("%d to %d:  ", s, v);
                for (int x : G.pathTo(s,v)) {
                    if (x == s) System.out.print(x);
                    else        System.out.print("-" + x);
                }
                System.out.println();
            } else {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }

        }




    }


}
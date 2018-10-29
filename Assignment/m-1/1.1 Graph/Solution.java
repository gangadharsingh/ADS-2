import java.util.Scanner;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public String toString();
}
class GraphUn implements Graph{
	private int V;
	private int E;
	Bag<Integer>[] adj;
	GraphUn(Scanner scan) {
		// try {
			V = Integer.parseInt(scan.nextLine());
			if(V < 0) {
			return;
			}
			E = Integer.parseInt(scan.nextLine());
			if(E < 0) {
			return;
			}
			adj = (Bag<Integer>[]) new Bag[V];
			for (int i = 0; i < E; i++) {
				int v = Integer.parseInt(scan.nextLine());
				int w = Integer.parseInt(scan.nextLine());
				addEdge(v, w);
				
			}
		// } catch(Exception e){
		// 	throw new Exception("", e);
		// }
	}
	public int V() {
		return V;
	}
    public int E() {
    	return E;
    }
    public void addEdge(int v, int w) {
    	E++;
    	adj[v].add(w);
    	adj[w].add(v);
    }
    public Iterable<Integer> adj(int v) {
    	return adj[v];
    }
    public String toString() {
    	String s = "";
    	s += V+" vertices, "+E+" edges "+"\n";
    	for (int v = 0; v < V; v++) {
    		s += (v + ":");
    		for (int w: adj[v]) {
    			s += (w+" ");
    		}
    		s += "\n";
    	}
    	return s.toString();
    }
}

class Solution{
	private Solution() { }
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// try {
		GraphUn gr = new GraphUn(scan);
		System.out.println(gr);
		// } catch(Exception e) {
		// 	throw new Exception(e.getMessage());
		// }
	}
}
/*public class SAP {

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)

    // do unit testing of this class
    public static void main(String[] args)
}*/
import java.util.*;

public class SAP {
	Digraph digraph;
	int distance;
	int ancestor;
	public SAP(Digraph G){
		digraph = G;
		distance = Integer.MAX_VALUE;
		ancestor = -1;
	}
	public int length(int v, int w){
		ancestor(v, w);
		if(ancestor != -1){
			return distance;
		}return -1;

	}
	public int ancestor(int v, int w){
		BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
		for(int i = 0; i < digraph.V(); i++){
			if(bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
				int tempdist = bfsv.distTo(i) + bfsw.distTo(i);
				if(tempdist < distance){
					distance = tempdist;
					ancestor = i;
				}
			}
		}
		return ancestor;
	}
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		ancestor(v, w);
		if(ancestor != -1){
			return distance;
		}return -1;
	}
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		for(int i : v){
			for(int j : w){
				ancestor(i, j);
			}
		}
		return ancestor;
	}
	public static void main(String[] args) throws Exception{
		// Scanner sc = new Scanner(System.in);
		// Digraph g = new Digraph(Integer.parseInt(sc.nextLine()));
		// int ed = Integer.parseInt(sc.nextLine());
		// for(int i = 0; i < ed; i++){
		// 	String[] tokens = sc.nextLine().split(" ");
		// 	// System.out.println(Arrays.toString(tokens));
		// 	g.addEdge(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));
		// }

		// while(sc.hasNextLine()){
		// 	SAP obj = new SAP(g);
		// 	int i = Integer.parseInt(sc.nextLine());
		// 	int j = Integer.parseInt(sc.nextLine());
		// 	System.out.println("ans  "+ obj.ancestor(i, j));
		// 	System.out.println("len  "+ obj.length(i, j));
		// }
	}

}
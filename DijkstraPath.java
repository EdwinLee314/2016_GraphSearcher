import java.util.Stack;

public class DijkstraPath {
	private int[] distTo;				// distance of shortest s -> v path
	private Edge[] edgeTo;				// last edge on shortest s -> v path
	private IndexMinPQ<Integer> pq; 	// priority queue of vertices
	private int V;
	
	public DijkstraPath(int s) {
		for (Edge e: WeightedGraph.getInstance().edges()) {
			if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
		}
		V = WeightedGraph.getInstance().V();
		distTo = new int[V];
		edgeTo = new Edge[V];
		for (int v = 0; v < V; v++) {
			distTo[v] = Integer.MAX_VALUE;
		}
		distTo[s] = 0;
		
		// relax vertices in order of distance from s
		pq = new IndexMinPQ<Integer>(V);
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (Edge e:WeightedGraph.getInstance().adjacencyOf(v) ) {
				relax(e, v);
			}
		}
	}
	
	private void relax(Edge e, int v) {
		int w = e.other(v);
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w)) {
				pq.decreaseKey(w, distTo[w]);
			}
			else {
				pq.insert(w, distTo[w]);
			}
		}
	}
	
	public double distTo(int v) {
        return distTo[v];
    }
	
	public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
	
	public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
	
	public String StringPathTo(int v) {
		String path = WeightedGraph.getInstance().nameOf(v);
		int distance = 0;
		int end = v;
		for (Edge e : pathTo(v)) {
			int s = e.other(end);
			end = s;
			path = WeightedGraph.getInstance().nameOf(s) + path;
			distance = distance + e.weight();

        }
		path = path + distance;
		return path;
    }

}

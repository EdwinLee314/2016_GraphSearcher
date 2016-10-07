import java.util.ArrayList;
import java.util.HashMap;

public class WeightedGraph {
	
	private int V = 0;
	private int E = 0;
	private HashMap<String, Integer> symbols;
	private String[] keys;
	private ArrayList<ArrayList<Edge>> adjacencyList;
	
	private static class WeightGraphHolder {
		private static final WeightedGraph INSTANCE = new WeightedGraph();
	}
	
	public static final WeightedGraph getInstance() {
		return WeightGraphHolder.INSTANCE;
	}
	
	public WeightedGraph () {
		symbols = new HashMap<String, Integer>();
		adjacencyList = new ArrayList<ArrayList <Edge>>();
	}
	
	public void buildInvertedIndex(){
		keys = new String[symbols.size()];
		for(String name: symbols.keySet()){
			keys[symbols.get(name)] = name;	
		}
	}
	
	public void addVertex(String name) {
		if(!symbols.containsKey(name)) {
			symbols.put(name, symbols.size());
			adjacencyList.add(new ArrayList<Edge>());
			V++;
		}
	}
	
	public void addEdge(String ivertex, String jvertex, int weight) {
		
		int v = indexOf(ivertex);
		int w = indexOf(jvertex);
		Edge newE = new Edge(v, w, weight);
        adjacencyList.get(v).add(newE);
        adjacencyList.get(w).add(newE);
        E++;
    }
	
	public boolean contains(String s){
		return symbols.containsKey(s);
	}
	
	public int indexOf(String s){
		return symbols.get(s);
	}
	
	public String nameOf(int v){
		return keys[v];
	}
	
	public Iterable<Edge> adjacencyOf(int v) {
		validateVertex(v);
		return adjacencyList.get(v);
	} 
	
	private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }
	
	public int V() {
        return V;
    }
	
	public int E() {
        return E;
    }
	
	public int calDistance(String route) {
		char[] vertices = route.toCharArray();
		int distance = 0;
		for(int i = 0; i < vertices.length -1 ; i++) {
			int v = indexOf(String.valueOf(vertices[i]));
			int w = indexOf(String.valueOf(vertices[i + 1]));
			for(Edge e: adjacencyOf(v)) {
				if(e.other(v) == w){
					distance = distance + e.weight();
					break;
				}
			}
			
		}
		return distance;
	}
	
	public Iterable<Edge> edges() {
		ArrayList<Edge> list = new ArrayList<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e: adjacencyOf(v)) {
				if(e.other(v) > v) {
					list.add(e);
				}
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0) {
						list.add(e);
					}
					selfLoops++;
				}
			}
		}
		return list;
	}
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adjacencyList.get(v)) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}

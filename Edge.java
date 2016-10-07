public class Edge implements Comparable<Edge>{
	private int v;
	private int w;
	private int weight;
	
	public Edge(int v, int w, int weight) {
        if (v < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (w < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
	
	// Returns the weight of this edge.
	public int weight() {
        return weight;
    }
	
	// Returns either end point of this edge
	public int either() {
        return v;
    }
	
	// Returns the end point of this edge that is different from the given vertex.
	public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }
	
	@Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

	public String toString() {
        return String.format("%d-%d %d", v, w, weight);
    }

}
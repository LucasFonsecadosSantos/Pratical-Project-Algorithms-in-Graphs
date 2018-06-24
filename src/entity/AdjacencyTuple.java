package entity;

public class AdjacencyTuple {

    private String vertex;
    private int weight;
    private int indexOfVertexSet;

    public AdjacencyTuple(String vertex, int weight, int index) {
        setVertex(vertex);
        setWeight(weight);
        setIndexOfVertexSet(index);
    }

    public int getIndexOfVertexSet() {
        return this.indexOfVertexSet;
    }

    public void setIndexOfVertexSet(int index) {
        this.indexOfVertexSet = index;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getVertex() {
        return this.vertex;
    }

    public int getWeight() {
        return this.weight;
    }
}
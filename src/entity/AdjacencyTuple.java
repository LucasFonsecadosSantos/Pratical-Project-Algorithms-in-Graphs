package entity;

public class AdjacencyTuple {

    private String vertex;
    private int weight;

    public AdjacencyTuple(String vertex, int weight) {
        setVertex(vertex);
        setWeight(weight);
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
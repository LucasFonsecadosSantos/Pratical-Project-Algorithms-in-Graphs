package entity;

public class CostTuple {

    private String vertex;
    private int cost;

    public CostTuple(String vertex, int cost) {
        setVertex(vertex);
        setCost(cost);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public int getCost() {
        return this.cost;
    }

    public String getVertex() {
        return this.vertex;
    }
}
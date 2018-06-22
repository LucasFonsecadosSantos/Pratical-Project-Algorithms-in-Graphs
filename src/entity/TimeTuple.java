package entity;

public class TimeTuple {

    private String vertex;
    private int time;

    public TimeTuple(String vertex, int time) {
        setVertex(vertex);
        setTime(time);
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getVertex() {
        return this.vertex;
    }

    public int getTime() {
        return this.time;
    }
}
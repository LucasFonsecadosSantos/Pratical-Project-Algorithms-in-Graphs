package entity;

import java.util.List;
import java.util.ArrayList;

public class Graph {

    private int vertexSetCardinality;
    private int edgeSetCardinality;
    private List<String> vertexSet;
    private List<List<AdjacencyTuple>> adjacencyList;

    public Graph(List<String> vertexSet, List<String[]> edgeSet) {
        this.adjacencyList = new ArrayList<List<AdjacencyTuple>>();
        setVertexSet(vertexSet);
        setVertexSetCardinality(vertexSet.size());
        setEdgeSetCardinality(edgeSet.size());
        adjacencyListInitialize();
        generateAdjacencyList(edgeSet);
    }

    public List getVertexSet() {
        return this.vertexSet;
    }

    public GraphCharacteristicsEnum getGraphCharacteristic() {
        if (this.edgeSetCardinality < Math.pow(this.vertexSetCardinality, 2)*10) {
            return GraphCharacteristicsEnum.SCATTERED_GRAPH;
        } else {
            return GraphCharacteristicsEnum.DENSE_GRAPH;
        }
    }

    public void setVertexSet(List<String> vertexSet) {
        this.vertexSet = vertexSet;
    }

    public int getVertexSetCardinality() {
        return this.vertexSetCardinality;
    }

    public int getEdgeSetCardinality() {
        return this.edgeSetCardinality;
    }

    public void adjacencyListInitialize() {
        for (int i=0 ; i < this.vertexSetCardinality ; i++) {
            this.adjacencyList.add(null);
        }
    }

    public void setVertexSetCardinality(int size) {
        this.vertexSetCardinality = size;
    }

    public void setEdgeSetCardinality(int size) {
        this.edgeSetCardinality = size;
    }

    public void generateAdjacencyList(List<String[]> edgeSet) {
        for (int i=0; i<this.edgeSetCardinality; i++) {
            int index = mappingToIndexOfVertexSet(edgeSet.get(i)[0]);
            if (this.adjacencyList.get(index) == null) {
                this.adjacencyList.set(index, new ArrayList<AdjacencyTuple>());
            }
            this.adjacencyList.get(index).add(new AdjacencyTuple(edgeSet.get(i)[1], Integer.parseInt(edgeSet.get(i)[2]), index));
        }
    }

    private int getExitDegree(String targetVertex) {
        int index = mappingToIndexOfVertexSet(targetVertex);
        List<AdjacencyTuple> tupleList = this.adjacencyList.get(index);
        if (tupleList != null) {
            return tupleList.size();
        }
        return 0;
    }

    private int getEntryDegree(String vertex) {
        int counter=0;
        for (List<AdjacencyTuple> tupleList : this.adjacencyList) {
            if (tupleList != null) {
                for (AdjacencyTuple tuple : tupleList) {
                    if (tuple.getVertex().equals(vertex)) {
                        ++counter;
                    }
                }
            }
        }
        return counter;
    }

    public String mappingToVertexOfVertexSet(int index) {
        return this.vertexSet.get(index);
    }

    public int mappingToIndexOfVertexSet(String targetVertex) {
        int index=0;
        for (String vertex : this.vertexSet) {
            if (vertex.equals(targetVertex)) {
                break;
            }
            ++index;
        }
        return index;
    }

    public float getEntryAverageDegree() {
        int counter = 0;
        for (String vertex : this.vertexSet) {
            counter += getEntryDegree(vertex);
        }
        return (counter / getVertexSetCardinality());
    }

    public float getExitAverageDegree() {
        int counter = 0;
        for (String vertex : this.vertexSet) {
            counter += getExitDegree(vertex);
        }
        return (counter / getVertexSetCardinality());
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int index=0;
        for (List<AdjacencyTuple> tupleList : this.adjacencyList) {
            
            if (tupleList != null) {
                for (AdjacencyTuple tuple : tupleList) {
                    builder.append("->"+tuple.getVertex());
                }
            }else {
                builder.append("-");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public List getAdjacencyList() {
        return this.adjacencyList;
    }
}
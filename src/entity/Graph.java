package entity;

import java.util.List;
import java.util.ArrayList;

public class Graph {

    private int vertexSetCardinality;
    private int edgeSetCardinality;
    private List<String> vertexSet;
    private List<List<AdjacencyTuple>> adjacencyList;
    private List<String[]> edgeSet;

    public Graph(List<String> vertexSet, List<String[]> edgeSet) {
        this.adjacencyList = new ArrayList<List<AdjacencyTuple>>();
        setVertexSet(vertexSet);
        setEdgeSet(edgeSet);
        setVertexSetCardinality(vertexSet.size());
        setEdgeSetCardinality(edgeSet.size());
        adjacencyListInitialize();
        generateAdjacencyList(edgeSet);
    }

    public List getEdgeSet() {
        return this.edgeSet;
    }

    public void setEdgeSet(List edgeSet) {
        this.edgeSet = edgeSet;
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
        int cardinality = this.vertexSetCardinality;
        for (int i=0 ; i < cardinality ; ++i) {
            this.adjacencyList.add(null);
        }
    }

    public void setVertexSetCardinality(int size) {
        this.vertexSetCardinality = size;
    }

    public void setEdgeSetCardinality(int size) {
        this.edgeSetCardinality = size;
    }

    private void generateAdjacencyList(List<String[]> edgeSet) {
        int cardinality = this.edgeSetCardinality;
        int index;
        for (int i=0; i<cardinality; ++i) {
            index = mappingVertexToIndex(edgeSet.get(i)[0]);
            if (this.adjacencyList.get(index) == null) {
                this.adjacencyList.set(index, new ArrayList<AdjacencyTuple>());
            }
            this.adjacencyList.get(index).add(new AdjacencyTuple(edgeSet.get(i)[1], Integer.parseInt(edgeSet.get(i)[2]), index));
        }
    }

    private int getExitDegree(String targetVertex) {
        int index = mappingVertexToIndex(targetVertex);
        List<AdjacencyTuple> tupleList = this.adjacencyList.get(index);
        if (tupleList != null) {
            return tupleList.size();
        }
        return 0;
    }

    /**
     * This method is responsible for gets the vertex entry
     * degree value, that is, the amount of edges that fall on it.
     * 
     * @param vertex The researched vertex.
     * @return int Amount of incident edges.
     */
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

    public String mappingIndexToVertex(int index) {
        return this.vertexSet.get(index);
    }

    public int mappingVertexToIndex(String targetVertex) {
        return this.vertexSet.indexOf(targetVertex);
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
            builder.append(vertexSet.get(index));
            if (tupleList != null) {
                for (AdjacencyTuple tuple : tupleList) {
                    builder.append("->"+tuple.getVertex());
                }
                builder.append("->#");
            }else {
                builder.append("->#");
            }
            builder.append("\n");
            ++index;
        }
        return builder.toString();
    }

    public List getAdjacencyList() {
        return this.adjacencyList;
    }

    public boolean searchVertex(String vertex) {
        for (String vertexElement : this.vertexSet) {
            if (vertex.equals(vertexElement)) {
                return true;
            }
        }
        return false;
    }
}
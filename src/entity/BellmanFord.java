package entity;

import java.util.List;
import java.util.ArrayList;

public class BellmanFord {

    private List<CostTuple> costSet;
    
    public BellmanFord() {
        this.costSet = new ArrayList<CostTuple>();
    }

    public Object BellmanFordInit(Graph graph, List<String[]> shortestPathSet) {
        int vertexSetCardinality = graph.getVertexSetCardinality();
        int vertexIndex;
        List<String[]> edgeSet = graph.getEdgeSet();
        List<AdjacencyTuple> adjacencyVertexes;
        List adjacencyList = graph.getAdjacencyList();
        initializateCostSet(vertexSetCardinality);

        if (!researchValidator(graph, shortestPathSet)) {
            return GraphCharacteristicsEnum.VERTEX_NOT_FOUND;
        }

        for (String[] searchOperations : shortestPathSet) {
            originVertex = mappingVertexToIndex(searchOperations);
            adjacencyVertexes = adjacencyList.get(originVertex);
                //Primeiro laço
            for (int i=0 ; i < vertexSetCardinality ; ++i) {
                if (adjacencyVertexes != null && adjacencyVertexes.size() > 0) {
                    for (AdjacencyTuple tuple : adjacencyVertexes) {
                        relax(originVertex, tuple.getVertex(), edgeSet);
                    }
                }
            }
        }

        return null;
    }

    public void relax(int originVertex, int destinoVertex, List<String[]> edgeSet) {
    
    }

    public boolean researchValidator(Graph graph, List<String[]> shortestPathSet) {
        for (String[] operation : shortestPathSet) {
            for (String researchVertex : operation) {
                if (!graph.searchVertex(researchVertex)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initializateCostSet(int cardinality) {
        for (int i=0 ; i < cardinality ; ++i) {
            this.costSet.add(null);
        }
    }
}
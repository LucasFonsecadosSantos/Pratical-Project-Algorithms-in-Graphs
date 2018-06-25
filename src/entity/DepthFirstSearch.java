package entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch {

    private Graph graph;
    private List<String> vertexSet;
    private List<Boolean> whiteVertexes;
    private List<Boolean> grayVertexes;
    private List<Boolean> blackVertexes;
    private List<TimeTuple> finishTime;
    private List<TimeTuple> discoveryTime;
    private List<List<AdjacencyTuple>> adjacencyList;
    private Stack<Integer> parents;
    private int time;
    private GraphCharacteristicsEnum connectivity;
    private GraphCharacteristicsEnum cycle;

    public DepthFirstSearch() {
        this.cycle = GraphCharacteristicsEnum.UNKNOWN_INFORMATION;
        this.connectivity = GraphCharacteristicsEnum.UNKNOWN_INFORMATION;
        this.cycle = GraphCharacteristicsEnum.UNKNOWN_INFORMATION;
    }

    private void initializeDataStructures() {
        for (String vertex : vertexSet) {
            whiteVertexes.add(true);
            grayVertexes.add(false);
            blackVertexes.add(false);
            discoveryTime.add(null);
            finishTime.add(null);
        }
    }

    private void instantiateObjects(Graph graph) {
        this.finishTime = new ArrayList<TimeTuple>();
        this.discoveryTime= new ArrayList<TimeTuple>();
        this.whiteVertexes = new ArrayList<Boolean>();
        this.grayVertexes = new ArrayList<Boolean>();
        this.blackVertexes = new ArrayList<Boolean>();
        this.parents = new Stack<Integer>();
        this.adjacencyList = graph.getAdjacencyList();
        this.vertexSet = graph.getVertexSet();
    }


    public void ExecDepthFirstSearch(Graph graph) {
        time = 0;
        int vertex = 0;
        instantiateObjects(graph);
        initializeDataStructures();

        for (int i=0 ; i < graph.getVertexSet().size() ; ++i) {
            if (whiteVertexes.get(i)) {
                initDFS(i);
                addVertexFinishTime(i, time);
                setGraphConnectivity(graph,i);
            }
        }
    }

    private void setGraphConnectivity(Graph graph, int index) {
        if (index < graph.getVertexSet().size()-1) {
            this.connectivity = GraphCharacteristicsEnum.CONNECTED_GRAPH;
        } else {
            this.connectivity = GraphCharacteristicsEnum.NOT_CONNECTED_GRAPH;
        }
    }

    private void setGraphCycle() {
        this.cycle = GraphCharacteristicsEnum.HAS_CYCLE;
    }

    public void initDFS(int vertex) {
        ++time;
        boolean occurrency;
        List<AdjacencyTuple> adjacencyVertexes;
        changeToGray(vertex);
        //System.out.print(vertexSet.get(vertex) + " - ");
        addVertexDiscoveryTime(vertex, time);
        this.parents.push(vertex);

        while (!parents.empty()) {
            occurrency = false;
            adjacencyVertexes = adjacencyList.get(vertex);
            //System.out.println(parents);
            //System.out.print(vertexSet.get(vertex) + " - ");
            if (adjacencyVertexes != null && adjacencyVertexes.size() > 0) {
                for (AdjacencyTuple tuple : adjacencyVertexes) {
                    if (whiteVertexes.get(vertexSet.indexOf(tuple.getVertex()))) {
                        vertex = vertexSet.indexOf(tuple.getVertex());
                        changeToGray(vertex);
                        ++time;
                        occurrency = true;
                        //System.out.println(vertexSet.get(vertex));
                        addVertexDiscoveryTime(vertex, time);
                        this.parents.push(vertex);
                        break;
                    } else if (grayVertexes.get(vertexSet.indexOf(tuple.getVertex()))) {
                        setGraphCycle();
                    }
                }
            }
            if (!occurrency) {
                changeToBlack(vertex);
                this.parents.pop();
                ++time;
                addVertexFinishTime(vertex, time);
                if (!parents.empty()) {
                    vertex = parents.peek();
                }
            }
        }
    }

    public GraphCharacteristicsEnum hasCycle() {
        return this.cycle;
    }

    private void addVertexDiscoveryTime(int vertex, int time) {
        this.discoveryTime.add(new TimeTuple(vertexSet.get(vertex), time));
    }

    private void addVertexFinishTime(int vertex, int time) {
        finishTime.add(new TimeTuple(vertexSet.get(vertex), time));
    }
    
    private void changeToBlack(int vertex) {
        this.blackVertexes.set(vertex, true);
        this.whiteVertexes.set(vertex, false);
        this.grayVertexes.set(vertex, false);
    }

    private void changeToGray(int vertex) {
        this.grayVertexes.set(vertex, true);
        this.whiteVertexes.set(vertex, false);
        this.blackVertexes.set(vertex, false);
    }

    public GraphCharacteristicsEnum getConnectivity() {
        return this.connectivity;
    }

    public GraphCharacteristicsEnum searchVertex(String vertex) {
        if (this.discoveryTime != null && this.discoveryTime.size() > 0) {
            for (TimeTuple tuple : this.discoveryTime) {
                if (tuple != null) {
                    if (tuple.getVertex().equals(vertex)) {
                        return GraphCharacteristicsEnum.VERTEX_FOUND;
                    }
                }
            }
        }
        return GraphCharacteristicsEnum.VERTEX_NOT_FOUND;
    }
}
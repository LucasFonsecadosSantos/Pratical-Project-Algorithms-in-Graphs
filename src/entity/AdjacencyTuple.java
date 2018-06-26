/**
 * DEPTH FIRST SEARCH, TOPOLOGICAL ORDERING AND
 * BELLMAN FORD GRAPH ALGORITHMS IMPLEMENTATION
 * 
 * Computer Science Departament of
 * Federal University of Lavras (UFLA)
 * 
 * This project was developed for Design and 
 * Analysis of Algorithms discipline as the
 * main pratical project.
 * 
 * It was developed by:
 * 
 * Lucas Fonseca dos Santos
 * https://github.com/LucasFonsecadosSantos
 * 
 * Marco Aurelio Ferreira de Sousa
 * https://github.com/maureliofs
 * 
 * ~This code can be used for students for
 * to learn more about graphs processing.~
 */
package entity;

/**
 * This abstraction allows the algorithms present
 * in this project, to store a tuple struct, in this
 * case, a adjacency tuple that represents adjacency vertexes
 * in adjacency list representation of graph.
 * 
 * @author Lucas Fonseca dos Santos
 * @author Marco Aurelio Ferreira de Sousa
 * @version 1.0
 * @since 1.0
 */
public class AdjacencyTuple {

    /**
     * Vertex attribute.
     */
    private String vertex;

    /**
     * Weight attribute.
     */
    private int weight;

    /**
     * A vertex integer index. Used for to knows
     * the current vertex position at vertex set.
     */
    private int indexOfVertexSet;

    /**
     * The adjacencyTuple object constructor. It sets states for
     * his attributes.
     * 
     * @param vertex The vertex that will be storage.
     * @param weight The weight value that will be storage.
     * @param index A integer index value for the current vertex.
     */
    public AdjacencyTuple(String vertex, int weight, int index) {
        setVertex(vertex);
        setWeight(weight);
        setIndexOfVertexSet(index);
    }

    /**
     * Index attribute state accessor method.
     * 
     * @return int The integer index attribute state.
     */
    public int getIndexOfVertexSet() {
        return this.indexOfVertexSet;
    }

    /**
     * Index attribute state modifier method.
     * 
     * @param index The new index attribute state value.
     */
    public void setIndexOfVertexSet(int index) {
        this.indexOfVertexSet = index;
    }

    /**
     * Vertex attribute state modifier method.
     * 
     * @param vertex The new vertex attribute state value.
     */
    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    /**
     * Weight attribute state modifier method.
     * 
     * @param weight The new weight attribute state value.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Vertex attribute state accessor method.
     * 
     * @return String The string vertex attribute state.
     */
    public String getVertex() {
        return this.vertex;
    }

    /**
     * Weight attribute state accessor method.
     * 
     * @return int The integer weight attribute state.
     */
    public int getWeight() {
        return this.weight;
    }
}
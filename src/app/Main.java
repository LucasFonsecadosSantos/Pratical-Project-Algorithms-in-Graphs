package app;

import dao.FileHandler;
import java.util.ArrayList;
import java.util.List;
import util.Parser;
import entity.DepthFirstSearch;
import entity.Graph;
import entity.GraphCharacteristicsEnum;

public class Main {

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler(args[0]);
        Parser parser = new Parser(fileHandler);
                //parser.getGraphSearchOperations();
        //parser.getGraphShortestsPathOperations();
        Graph graph = new Graph(
            parser.getGraphVertexes(),
            parser.getGraphEdges()
        );
        System.out.println("GME=" + graph.getEntryAverageDegree());
        System.out.println("GMS=" +graph.getExitAverageDegree());
        System.out.println(graph.getGraphCharacteristic());
        System.out.println();
        System.out.println();
        DepthFirstSearch.ExecDepthFirstSearch(graph);
        //System.out.println(graph.toString());
    }
}
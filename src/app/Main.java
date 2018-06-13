package app;

import dao.FileHandler;
import java.util.ArrayList;
import java.util.List;
import util.Parser;
import entity.Graph;

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
    }
}
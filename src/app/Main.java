package app;

import dao.FileHandler;
import java.util.ArrayList;
import java.util.List;
import util.Parser;
import entity.DepthFirstSearch;
import entity.Graph;
import entity.GraphCharacteristicsEnum;
import gui.Gui;

public class Main {

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler(args[0]);
        DepthFirstSearch dfs = new DepthFirstSearch();
        Gui.printSuccessfullyMessage("File ("+args[0]+") Inputed!");
        Parser parser = new Parser(fileHandler);
        List<String> searchOperations = parser.getGraphSearchOperations();
        Gui.printSuccessfullyMessage("Search Operations Read!");
        Gui.printInformationMessage(searchOperations.toString());
        //parser.getGraphShortestsPathOperations();
        Graph graph = new Graph(
            parser.getGraphVertexes(),
            parser.getGraphEdges()
        );
        Gui.printSuccessfullyMessage("Graph Created!");
        Gui.printInformationMessage("GME=" + graph.getEntryAverageDegree());
        Gui.printInformationMessage("GMS=" + graph.getExitAverageDegree());
        Gui.printInformationMessage(graph.getGraphCharacteristic().toString());
        dfs.ExecDepthFirstSearch(graph);
        Gui.printInformationMessage(dfs.hasCycle().toString());
        Gui.printInformationMessage(dfs.getConnectivity().toString());
        for (String researchVertex : searchOperations) {
            Gui.printActionMessage(researchVertex + "->" +dfs.searchVertex(researchVertex));
        }
        
        //System.out.println(graph.toString());
    }
}
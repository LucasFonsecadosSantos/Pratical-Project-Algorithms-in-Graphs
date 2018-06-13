package app;

import dao.FileHandler;
import java.util.ArrayList;
import java.util.List;
import util.Parser;

public class Main {

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler(args[0]);
        Parser parser = new Parser(fileHandler);
        //parser.getGraphVertexes();
                //parser.getGraphSearchOperations();
        //parser.getGraphShortestsPathOperations();
        List<String> array =  (ArrayList<String>) parser.getGraphShortestsPathOperations();
        for (String s : array) {
            System.out.println("entrada:" + s);
        }

    }
}
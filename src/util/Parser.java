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
package util;

import dao.FileHandler;
import java.util.List;
import java.util.ArrayList;

/**
 * This represents the Parser analyzer of project.
 * It's responsible for analyzing all the content of
 * input data file trying to discovery possible input
 * mistakes, out of the formalism accepted by this
 * project.
 * 
 * THE ACCEPTED INPUT DATAFORMALISM FOR THIS PROJECT IS
 * THE FOLLOWING NOTATION:
 * 
 * <identifier>
 * X1
 * .
 * .
 * .
 * Xn
 * 
 * EXEMPLE:
 * 
 * Vertexes:
 * A
 * B
 * C
 * D
 * 
 * Edges:
 * A->B(1)
 * A->C(2)
 * 
 * @author Lucas Fonseca dos Santos
 * @author Marco Aurelio Ferreira de Sousa
 * @version 1.0
 * @since 1.0
 */
public class Parser {

    /**
     * The fileHandler attribute.
     */
    private FileHandler fileHandler;

    /**
     * The input alphabet accepted by
     * this parser attribute.
     */
    private char[] symbolSet;

    /**
     * The vertex set captured from the input.
     */
    private List<String> vertexes;

    /**
     * The search operations set captured from the
     * input.
     */
    private List<String> searchOperations;

    /**
     * The edges set captured from input.
     */
    private List<String[]> edges;

    /**
     * The shortest path operations set captured from input.
     */
    private List<String[]> shortestPathOperations;

    /**
     * The reserved words of the present parser.
     */
    private String[] identifiers;

    /**
     * A flag that helps to identify the correct information
     * set.
     */
    private String stateFlag;

    /**
     * The parser object constructor.
     * It just instances the attribute objects.
     * 
     * @param fileHandler The file handler object with input file.
     */
    public Parser(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.vertexes = new ArrayList<String>();
        this.searchOperations = new ArrayList<String>();
        this.edges = new ArrayList<String[]>();
        this.shortestPathOperations = new ArrayList<String[]>();
        this.symbolSet = new char[] {
            'a','b','c','d','e',
            'f','g','h','i','j',
            'k','l','m','n','o',
            'p','q','r','s','t',
            'u','v','w','x','y',
            'z',
            'A','B','C','D','E',
            'F','G','H','I','J',
            'K','L','M','N','O',
            'P','Q','R','S','T',
            'U','V','W','X','Y',
            'Z','-','>','(',')',
            '1','2','3','4','5',
            '6','7','8','9',':',
            ' '
        };
        this.identifiers = new String[] {
            "Vertexes:", "Edges:", "Searches:",
            "Shortests:"
        };
        getFileContent();
    }

    /**
     * This method is responsible for getting all
     * data content from input file and processing it.
     */
    public void getFileContent() {
        int row = 0;
        while (!this.fileHandler.hasNextLine()) {
            ++row;
            String currentLine = this.fileHandler.getNextLine();
            if (validLexemes(currentLine)) {
                currentLine = lineTratamento(currentLine);
                if (currentLine.charAt(currentLine.length()-1) == ':') {        
                    this.stateFlag = getIdentifier(currentLine);
                } else {
                    switch(this.stateFlag) {
                        case "Vertexes:":
                            this.vertexes.add(currentLine);
                            break;
                        case "Edges:":
                            this.edges.add(getTokens(currentLine));
                            break;
                        case "Searches:":
                            this.searchOperations.add(currentLine);
                            break;
                        case "Shortests:":
                            this.shortestPathOperations.add(getTokens(currentLine));
                            break;
                        default:
                            break;
                    }
                }
            } else {
                //LANCAR EXCECAO PARA ROW X, LEXEMA INVALIDO
            }
        }
    }

    /**
     * This method is responsible for spliting the
     * edge notations from data input file by -> and
     * () symbols.
     * 
     * @param currentLine The line that will be splited.
     * @return String[] The generated tokens array after split operation.
     */
    public String[] getTokens(String currentLine) {
        return currentLine.split("(->|[()])");
    }

    /**
     * THis method is responsible for gets any identifier
     * presents in string line from data input file.
     * 
     * @param currentLine The string line that will be analyzed.
     * @return String A identifier filtered from the param received.
     */
    public String getIdentifier(String currentLine) {
        for (String identifier : this.identifiers) {
            if (identifier.equals(currentLine)) {
                return identifier;
            }
        }
        return null;
    }

    public String lineTratamento(String line) {
        return line.trim();
    }

    public Boolean validLexemes(String line) {
        Boolean found = false;
        for (int i=0; i<line.length(); i++) {
            for (int j=0; j<symbolSet.length; j++) {
                if (line.charAt(i) == symbolSet[j]) {
                    found = true;
                }
            }
        }
        if (found) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getGraphVertexes() {
        return this.vertexes;
    }

    public List<String[]> getGraphEdges() {
        return this.edges;
    }

    public List<String> getGraphSearchOperations() {
        return this.searchOperations;
    }

    public List<String[]> getGraphShortestsPathOperations() {
        return this.shortestPathOperations;
    }
}
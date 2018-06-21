package util;

import dao.FileHandler;
import java.util.List;
import java.util.ArrayList;

public class Parser {

    private FileHandler fileHandler;
    private char[] symbolSet;
    private List<String> vertexes;
    private List<String> searchOperations;
    private List<String[]> edges;
    private List<String[]> shortestPathOperations;
    private String[] identifiers;
    private String stateFlag;

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

    public void getFileContent() {
        while (!this.fileHandler.hasNextLine()) {
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
            }
        }
    }

    public String[] getTokens(String currentLine) {
        return currentLine.split("(->|[()])");
    }

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
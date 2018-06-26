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
package dao;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a file handler project 
 * component. It access the file in data read or
 * write operations. Your input content sometimes
 * is used by Parser for information generation.
 * 
 * @author Lucas Fonseca dos Santos
 * @author Marco Aurelio Ferreira de Sousa
 * @version 1.0
 * @since 1.0
 */
public class FileHandler {

    /**
     * The buffer attribute. It stores
     * all data loaded and free it for
     * other caller method.
     */
    private List<String> buffer;

    /**
     * The fileName attribute. It contains
     * a input file name received from
     * the user.
     */
    private String fileName;

    /**
     * FileHandler object constructor. It
     * is just responsibe for instances the
     * buffer attribute state and file name.
     * 
     * @param fileName A string data input file name.
     */
    public FileHandler(String fileName) {
        this.buffer = new ArrayList<String>();
        this.fileName = fileName;
        readFile();
    }

    /**
     * This method is responsible for read the
     * data input file from computer memory pool,
     * second by the file name param received from
     * user.
     */
    public void readFile() {
        try {
            String tmpLine;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while((tmpLine = bufferedReader.readLine()) != null) {
                this.buffer.add(tmpLine);
            }
            bufferedReader.close();
        } catch (FileNotFoundException fnfe) {

        } catch (IOException ioe) {
        
        } catch (Exception e) {

        } 
    }

    /**
     * This method is used by caller unit to
     * order a string data line content of
     * input file, where it's stored in file handler
     * buffer.
     * 
     * @return String A string read from data input file.
     */
    public String getNextLine() {
        String line = this.buffer.get(0);
        this.buffer.remove(0);
        return line;
    }

    /**
     * This method allows the caller unit
     * to know the reader loop stop condition.
     *
     * @return Boolean A reader loop stop sign.
     */
    public Boolean hasNextLine() {
        return this.buffer.isEmpty();
    }

}
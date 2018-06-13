package dao;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class FileHandler {

    private List<String> buffer;
    private String fileName;

    public FileHandler(String fileName) {
        this.buffer = new ArrayList<String>();
        this.fileName = fileName;
        readFile();
    }

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

    public String getNextLine() {
        String line = this.buffer.get(0);
        this.buffer.remove(0);
        return line;
    }

    public Boolean hasNextLine() {
        return this.buffer.isEmpty();
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rnexer
 */
public class TextFileReader implements KataFileReader {

    private String filename;
    
    public TextFileReader(String filename){
        this.filename = filename;
    }

    @Override
    public ArrayList<String> getTextLines() throws FileNotFoundException, IOException{
        ArrayList<String> lines = new ArrayList<>();
        FileReader file = new FileReader(filename);
        BufferedReader fileBuffer = new BufferedReader(file);
        String line;
        
        while ((line = fileBuffer.readLine()) != null) {
            lines.add(line);
        }
        
        return lines;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rnexer
 */
public interface KataFileReader {

    public ArrayList<String> getTextLines() throws FileNotFoundException, IOException ;
    
}

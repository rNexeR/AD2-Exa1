/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author rnexer
 */
public class StringArraylistUtils {

    public static ArrayList<String> removeContinuesCharacters(ArrayList<String> textLines, char character) {
        ArrayList<String> newLines = new ArrayList<>();
        for (String textLine : textLines) {
            String newLine = StringUtils.removeContinuesCharacters(textLine, character);
            newLines.add(newLine);
        }
        return newLines;
    }
    
    public static ArrayList<String> removeWhitesLines(ArrayList<String> textLines) {
        ArrayList<String> newlines = new ArrayList<>();
        for (String n : textLines) {
            if (n.length() != 0) 
                newlines.add(n);
        }
        return newlines;
    }

    public static ArrayList<String> removeOneCharacterLines(ArrayList<String> textLines) {
        ArrayList<String> newLines = new ArrayList<>();
        for (String textLine : textLines) {
            if(!(textLine.length() > 0))
                continue;
            char character = textLine.charAt(0);
            if (! StringUtils.isOneCharacterLine(textLine, character) ) {
                newLines.add(textLine);
            }
        }
        return newLines;
    }
    

    public static ArrayList<String> removeEnumerationColumn(ArrayList<String> textLines) {
        ArrayList<String> newLines = new ArrayList<>();
        for (String textLine : textLines) {
            String result = StringUtils.removeStringBeforeCharacter(textLine, ' ');
            newLines.add(result);
        }
        return newLines;
    }
    
    
    public static ArrayList<String> replaceCharacter(ArrayList<String> textLines, char character){
        ArrayList<String> newLines = new ArrayList<>();
        for(String textLine : textLines){
            String newString = textLine.replace(character, ' ');
            newLines.add(newString);
        }
        return newLines;
    }

}

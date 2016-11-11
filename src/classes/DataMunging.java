/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author rnexer
 */
public class DataMunging {

    private KataFileReader fileReader;
    
    public DataMunging(KataFileReader fr) {
        this.fileReader = fr;
    }
    
    private ArrayList<String> removeDecorators(ArrayList<String> textLines, boolean hasFooter, char[] ignoredCharacters){
        ArrayList<String> newLines = new ArrayList<>();
        
        if(hasFooter)
            textLines.remove(textLines.size()-1);
        
        for(char character : ignoredCharacters)
                newLines = StringArraylistUtils.replaceCharacter(textLines, character);
        
        newLines = StringArraylistUtils.removeWhitesLines(newLines);
        newLines = StringArraylistUtils.removeContinuesCharacters(newLines, ' ');
        newLines = StringArraylistUtils.removeOneCharacterLines(newLines);
        return newLines;
    }
    
    public ArrayList<String[]> createDataTable(ArrayList<String> textLines){
        ArrayList<String[]> dataTable = new ArrayList<>();
        
        for(String textLine: textLines){
            String textRow[] = textLine.split(" ");
            dataTable.add(textRow);
        }
        return dataTable;
    }
    
    public int getColumnNamePosition(String headers, String columnName) throws ColumnNameReferenceNotFounException{
        String[] headersArray = headers.split(" ");
        for(int i = 0; i < headersArray.length; i++){
            String header = headersArray[i];
            if(header.equals(columnName))
                return i;
        }
        throw new ColumnNameReferenceNotFounException();
    }
    
    private String getColumnsSearchedValues(ArrayList<Integer> minPositions, ArrayList<String[]> dataTable, int rColumnPos) {
        String columnSearchedValues = "";
        for(int pos : minPositions)
            columnSearchedValues += dataTable.get(pos)[rColumnPos];
        columnSearchedValues.trim();
        return columnSearchedValues;
    }

    private ArrayList<Integer> getMinsPositions(ArrayList<Integer> diferences) {
        Integer minimo;
        minimo = Collections.min(diferences);
        ArrayList<Integer> minPositions = new ArrayList<>();
        for (int i = 0; i < diferences.size(); i++) {
            if(diferences.get(i) == minimo)
                minPositions.add(i);
        }
        return minPositions;
    }

    private ArrayList<Integer> createDiferencesList(ArrayList<String[]> dataTable, int c1ColumnPos, int c2ColumnPos) throws NumberFormatException {
        ArrayList<Integer> diferences = new ArrayList<>();
        for (String[] row : dataTable) {
            int c1Value = Integer.parseInt(row[c1ColumnPos]);
            int c2Value = Integer.parseInt(row[c2ColumnPos]);
            diferences.add(c2Value - c1Value);
        }
        return diferences;
    }

    String getSmallersDifferenceBetweenTwoColumnsWithHeaders(String resultColumnName, String columnName1, String columnName2, boolean hasEnumeration, boolean hasFooter, char[] ignoredCharacters) throws IOException, ColumnNameReferenceNotFounException {
        ArrayList<String> textLines = this.fileReader.getTextLines();
        
        textLines = removeDecorators(textLines, hasFooter, ignoredCharacters);
        
        String headers = textLines.get(0);
        textLines.remove(0);
        
        if(hasEnumeration)
            textLines = StringArraylistUtils.removeEnumerationColumn(textLines);
        
        ArrayList<String[]> dataTable = createDataTable(textLines);
        
        int c1ColumnPos = getColumnNamePosition(headers, columnName1);
        int c2ColumnPos = getColumnNamePosition(headers, columnName2);
        int rColumnPos = getColumnNamePosition(headers, resultColumnName);
        
        ArrayList<Integer> diferences = createDiferencesList(dataTable, c1ColumnPos, c2ColumnPos);
        
        ArrayList<Integer> minPositions = getMinsPositions(diferences);
        
        String columnSearchedValues = getColumnsSearchedValues(minPositions, dataTable, rColumnPos);
        
        return columnSearchedValues;
    }
    
}

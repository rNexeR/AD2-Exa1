/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.readers.KataFileReader;
import classes.utils.StringArraylistUtils;
import classes.table.DataTable;
import classes.exceptions.DataTableColumnPositionOutOfBoundsException;
import classes.exceptions.DataTableRowPositionOutOfBoundsException;
import classes.exceptions.ColumnNameReferenceNotFounException;
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

    String getSmallersDifferenceBetweenTwoColumnsWithHeaders(String resultColumnName, String columnName1, String columnName2, boolean hasEnumeration, boolean hasFooter, char[] ignoredCharacters) throws IOException, ColumnNameReferenceNotFounException, DataTableRowPositionOutOfBoundsException, DataTableColumnPositionOutOfBoundsException {
        ArrayList<String> textLines = this.fileReader.getTextLines();
        
        textLines = removeDecorators(textLines, hasFooter, ignoredCharacters);
        
        DataTable dataTable = new DataTable();
        dataTable.setHeaders(textLines.get(0));
        
        textLines.remove(0);
        
        if(hasEnumeration)
            textLines = StringArraylistUtils.removeEnumerationColumn(textLines);
        
        dataTable.setData(textLines);
        
        int c1ColumnPos = dataTable.getColumnNamePosition(columnName1);
        int c2ColumnPos = dataTable.getColumnNamePosition(columnName2);
        int rColumnPos = dataTable.getColumnNamePosition(resultColumnName);
        
        ArrayList<Integer> diferences = dataTable.createIntegerDiferencesList(c1ColumnPos, c2ColumnPos);
        
        ArrayList<Integer> minPositions = getMinsPositions(diferences);
        
        String columnSearchedValues = dataTable.getColumnsSearchedValues(minPositions, rColumnPos);
        
        return columnSearchedValues;
    }
    
}

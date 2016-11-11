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
public class DataTable {
    private ArrayList<String[]> data;
    private ArrayList<String> headers;
    
    public DataTable(){
        data = new ArrayList<>();
        headers = new ArrayList<>();
    }
    
    public void setData(ArrayList<String> textLines){
        for(String textLine: textLines){
            String textRow[] = textLine.split(" ");
            data.add(textRow);
        }
    }
    
    public void setHeaders(String headers){
        String[] headersArray = headers.split(" ");
        for(String header: headersArray)
            this.headers.add(header);
    }
    
    public ArrayList<Integer> createIntegerDiferencesList(int c1ColumnPos, int c2ColumnPos) throws NumberFormatException {
        ArrayList<Integer> diferences = new ArrayList<>();
        for (String[] row : data) {
            int c1Value = Integer.parseInt(row[c1ColumnPos]);
            int c2Value = Integer.parseInt(row[c2ColumnPos]);
            diferences.add(c2Value - c1Value);
        }
        return diferences;
    }
    
    public String getColumnsSearchedValues(ArrayList<Integer> searchedPositions, int searchedColumnPosition) {
        String columnSearchedValues = "";
        for(int pos : searchedPositions)
            columnSearchedValues += data.get(pos)[searchedColumnPosition];
        columnSearchedValues.trim();
        return columnSearchedValues;
    }
    
    public int getColumnNamePosition(String columnName) throws ColumnNameReferenceNotFounException{
        int pos = this.headers.indexOf(columnName);
        if(pos >= 0)
            return pos;
        throw new ColumnNameReferenceNotFounException();
    }
    
}

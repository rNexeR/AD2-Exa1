/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.readers.TextFileReader;
import classes.exceptions.DataTableRowPositionOutOfBoundsException;
import classes.exceptions.DataTableColumnPositionOutOfBoundsException;
import classes.exceptions.ColumnNameReferenceNotFounException;
import classes.exceptions.DataTableCellIsNotANumberException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rnexer
 */
public class DataMungingTest {
    
    public DataMungingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getDayWithSmallestTemperatureTest() throws IOException, ColumnNameReferenceNotFounException, DataTableRowPositionOutOfBoundsException, DataTableColumnPositionOutOfBoundsException, DataTableCellIsNotANumberException{
        TextFileReader fr = new TextFileReader("weather.dat");
        char ignoredCharacters[] = {'*'};
        
        DataMunging wfr = new DataMunging(fr);
        String day = wfr.getSmallersDifferenceBetweenTwoColumnsWithHeaders("Dy","MnT", "MxT", false, true, ignoredCharacters);
        assertEquals(day, "14");
    }
    
    @Test
    public void getTeamWithSmallestGoalDiferenceTest() throws IOException, ColumnNameReferenceNotFounException, DataTableRowPositionOutOfBoundsException, DataTableColumnPositionOutOfBoundsException, DataTableCellIsNotANumberException{
        TextFileReader fr = new TextFileReader("football.dat");
        char ignoredCharacters[] = {'-'};
        
        DataMunging wfr = new DataMunging(fr);
        String team = wfr.getSmallersDifferenceBetweenTwoColumnsWithHeaders("Team","A", "F", true, false, ignoredCharacters);
        assertEquals(team, "Leicester");
    }
}

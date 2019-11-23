package com.example.simpleres;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    /** 1. getSpinnerPosition */
    //method in WaitlistEntry class
    @Test
    public void getSpinnerPos_isCorrect() {
        int expected = 0;
        int actual = WaitlistEntry.getSpinnerPos(4, 16);
        assertEquals(expected, actual);

        expected = 8;
        actual = WaitlistEntry.getSpinnerPos(8, 19999);
        assertEquals(expected, actual);

        expected = 12;
        actual = WaitlistEntry.getSpinnerPos(10, 0);
        assertEquals(expected, actual);

        expected = -1;
        actual = WaitlistEntry.getSpinnerPos(3, 30);
        assertEquals(expected, actual);

        expected = 14;
        actual = WaitlistEntry.getSpinnerPos(11, 15);
        assertEquals(expected, actual);
    }


    /** 2. getMonthString */
    //method in FutureDatePopup
    @Test
    public void getMonthString_isCorrect(){
        String formattedDate = "1999-01-10";
        String expected = "January";
        String actual = FutureDatePopup.getMonthString(formattedDate);
        assertEquals(expected, actual);

        formattedDate = "1000-03-12";
        expected = "March";
        actual = FutureDatePopup.getMonthString(formattedDate);
        assertEquals(expected, actual);

        formattedDate = "2012-13-21";
        expected = "ERROR";
        actual = FutureDatePopup.getMonthString(formattedDate);
        assertEquals(expected, actual);

        formattedDate = "12341234";
        expected = "ERROR";
        actual = FutureDatePopup.getMonthString(formattedDate);
        assertEquals(expected,actual);
    }


    /** 3. getDayString */
    //method in FutureDatePopup
    @Test
    public void getDayString_isCorrect(){
        String formattedDate = "1995-08-22";
        String expected = "22";
        String actual = FutureDatePopup.getDayString(formattedDate);
        assertEquals(expected, actual);

        formattedDate = "1995-08-00";
        expected = "00";
        actual = FutureDatePopup.getDayString(formattedDate);
        assertEquals(expected, actual);

        formattedDate = "1995-08---";
        expected = "--";
        actual = FutureDatePopup.getDayString(formattedDate);
        assertEquals(expected, actual);
    }


    /** 4. getYearString */
    //method in FutureDatePopup
    @Test
    public void getYearString_isCorrect(){
        String formattedDate = "1995-08-22";
        String expected = "1995";
        String actual = FutureDatePopup.getYearString(formattedDate);
        assertEquals(expected, actual);

        formattedDate = "0000-08-00";
        expected = "0000";
        actual = FutureDatePopup.getYearString(formattedDate);
        assertEquals(expected, actual);

        formattedDate = "-----08-00";
        expected = "----";
        actual = FutureDatePopup.getYearString(formattedDate);
        assertEquals(expected, actual);
    }


    /** 5. addToCover */
    //method in Cover class
    @Test
    public void addToCover_isCorrect(){
        String dateAsString = "2019-11-23";
        Cover testCover = new Cover(dateAsString);

        int addValue = 132;
        testCover.addToCover(addValue);

        int expected = 132;
        int actual = testCover.getDailyCover();
        assertEquals(expected, actual);

        addValue = 32;
        testCover.addToCover(addValue);
        expected += addValue;
        actual = testCover.getDailyCover();
        assertEquals(expected, actual);

        testCover = new Cover(dateAsString);
        addValue = Integer.MAX_VALUE;
        testCover.addToCover(addValue);
        expected = Integer.MAX_VALUE;
        actual = testCover.getDailyCover();
        assertEquals(expected, actual);
    }


    /** 6. getName & setName */
    //methods in TableClass
    @Test
    public void getName_setName_isCorrect(){
        TableClass testTable = new TableClass();

        String testName = "Bella";
        String expected = "Bella";
        testTable.setTableName(testName);
        String actual = testTable.getTableName();
        assertEquals(expected, actual);

        testName = "1234";
        expected = "1234";
        testTable.setTableName(testName);
        actual = testTable.getTableName();
        assertEquals(expected, actual);

        testName = "1@V#%^# @$RF%#$@$FWE@%GFEACS ";
        expected = "1@V#%^# @$RF%#$@$FWE@%GFEACS ";
        testTable.setTableName(testName);
        actual = testTable.getTableName();
        assertEquals(expected, actual);

        testName = "";
        expected = "";
        testTable.setTableName(testName);
        actual = testTable.getTableName();
        assertEquals(expected, actual);
    }
}
package com.example.simpleres;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class ExampleUnitTest {
    @Test
    public void toggleCheckbox_isCorrect(){
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 1;
        obj.toggleCheckBox();
        int actual = obj.getCheckBox();
        assertEquals(expected,actual);

        expected = 0;
        obj.toggleCheckBox();
        actual = obj.getCheckBox();
        assertEquals(expected,actual);

    }
    @Test
    public void toggleCheckbox_isIncorrect(){
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 0;
        obj.toggleCheckBox();
        int actual = obj.getCheckBox();
        assertNotEquals(expected,actual);

        expected = 1;
        obj.toggleCheckBox();
        actual = obj.getCheckBox();
        assertNotEquals(expected,actual);
    }
    @Test
    public void getDateAsString_isCorrect(){
        LocalDate date = LocalDate.of(1995,8,4);
        Cover obj = new Cover(10,date);
        String expected = "1995-08-04";
        String actual =  obj.getDateAsString();
        assertEquals(expected,actual);
    }
    @Test
    public void getDateAsString_isIncorrect() {
        LocalDate date = LocalDate.of(2010, 3, 7);
        Cover obj = new Cover(10, date);
        String expected = "1995-08-04";
        String actual = obj.getDateAsString();
        assertNotEquals(expected, actual);
    }
    @Test
    public void idCreation_isCorrect(){
       // WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(MainInterface.this);//
        WaitlistEntry obj = new WaitlistEntry("name","3133133333",3,"2019-11-21",0,"notes");
        //wdb.idCreation(obj);
        int actual = obj.getId();
        assertNotNull(actual);
    }
    @Test
    public void getCheckBox_isCorrect(){
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 0;
        int actual = obj.getCheckBox();
        assertEquals(expected,actual);
    }
    @Test
    public void setCheckBox_isCorrect(){
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 189;
        obj.setCheckBox(expected);
        int actual = obj.getCheckBox();
        assertEquals(expected,actual);

        expected = 0;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected,actual);

        expected = 1;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected,actual);

        expected = -1;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected,actual);

        expected = Integer.MAX_VALUE;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected,actual);

        expected = Integer.MIN_VALUE;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected,actual);

    }
    @Test
    public void parseDate_isCorrect(){
        WaitlistEntry obj = new WaitlistEntry();
        LocalDateTime date = LocalDateTime.of(LocalDate.of(2019,11,21), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        String expected = "11/21/2019";
        String actual = obj.parseDate();
        assertEquals(expected,actual);

        date = LocalDateTime.of(LocalDate.of(9999,12,31), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "12/31/9999";
        actual = obj.parseDate();
        assertEquals(expected,actual);

        date = LocalDateTime.of(LocalDate.of(1000,1,1), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "01/01/1000";
        actual = obj.parseDate();
        assertEquals(expected,actual);

    }





}

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
    public void ParseTime_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        LocalDateTime date = LocalDateTime.of(LocalDate.of(2019,11,24), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        String expected = "6:11pm";
        String actual = obj.parseTime();
        assertEquals(expected,actual);

        date = LocalDateTime.of(LocalDate.of(2019,11,24), LocalTime.of(12,59));
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "12:59am";
        actual = obj.parseTime();
        assertEquals(expected,actual);

        date = LocalDateTime.of(LocalDate.of(2019,11,24), LocalTime.of(15,59));
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "3:59pm";
        actual = obj.parseTime();
        assertEquals(expected,actual);

        date = LocalDateTime.of(LocalDate.of(2019,11,24), LocalTime.of(00,00));
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "12:00am";
        actual = obj.parseTime();
        assertEquals(expected,actual);
    }
    @Test
    public void SetCheckboxOn_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 1;
        obj.setCheckBoxOn();
        int actual = obj.getCheckBox();
        assertEquals(expected,actual);

        expected = 2;
        obj.setCheckBoxOn();
        actual = obj.getCheckBox();
        assertNotEquals(expected,actual);

    }
    @Test
    public void Cover_isCorrect() {
        Cover cover = new Cover(24,"2019-11-24");
        int expected = 24;
        int actual = cover.getDailyCover();
        assertEquals(expected,actual);

        String expectedString = "2019-11-24";
        String actualString = cover.getDateAsString();
        assertEquals(expectedString,actualString);

        Cover cover2 = new Cover(24,"9999-12-31");
        expectedString = "9999-12-31";
        actualString = cover2.getDateAsString();
        assertEquals(expectedString,actualString);

        Cover cover3 = new Cover(24,"0001-01-01");
        expectedString = "0001-01-01";
        actualString = cover3.getDateAsString();
        assertEquals(expectedString,actualString);

        Cover cover4 = new Cover(9999,"2019-11-24");
        expected = 9999;
        actual = cover4.getDailyCover();
        assertEquals(expected,actual);

        Cover cover5 = new Cover(0,"2019-11-24");
        expected = 0;
        actual = cover5.getDailyCover();
        assertEquals(expected,actual);

        Cover cover6 = new Cover(-1,"2019-11-24");
        expected = -1;
        actual = cover6.getDailyCover();
        assertEquals(expected,actual);

    }
    @Test
    public void SetDate_isCorrect() {
        Cover cover = new Cover(1,"2019-11-24");
        LocalDate expected = LocalDate.of(2019,11,24);
        cover.setDate(LocalDate.of(2019,11,24));
        LocalDate actual = cover.getDate();
        assertEquals(expected,actual);

        expected = LocalDate.of(9999,12,31);
        cover.setDate(LocalDate.of(9999,12,31));
        actual = cover.getDate();
        assertEquals(expected,actual);

        expected = LocalDate.of(1000,1,1);
        cover.setDate(LocalDate.of(1000,1,1));
        actual = cover.getDate();
        assertEquals(expected,actual);
    }
    @Test
    public void SetTableNumber_isCorrect() {
        TableClass table = new TableClass();
        table.setTableNumber(101);
        int expected = 101;
        int actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(-1);
        expected = -1;
        actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(0);
        expected = 0;
        actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(Integer.MAX_VALUE);
        expected = Integer.MAX_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(Integer.MIN_VALUE);
        expected = Integer.MIN_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected,actual);

    }
    @Test
    public void GetTableNumber_isCorrect() {
        TableClass table = new TableClass();
        table.setTableNumber(101);
        int expected = 101;
        int actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(-1);
        expected = -1;
        actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(0);
        expected = 0;
        actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(Integer.MAX_VALUE);
        expected = Integer.MAX_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected,actual);

        table.setTableNumber(Integer.MIN_VALUE);
        expected = Integer.MIN_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected,actual);
    }

}
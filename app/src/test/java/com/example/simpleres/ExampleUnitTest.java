package com.example.simpleres;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends Context {
    //BELLA'S TESTS

    /**
     * 1. getSpinnerPosition
     */
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

    /**
     * 2. getMonthString
     */
    //method in FutureDatePopup
    @Test
    public void getMonthString_isCorrect() {
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
        assertEquals(expected, actual);
    }

    /**
     * 3. getDayString
     */
    //method in FutureDatePopup
    @Test
    public void getDayString_isCorrect() {
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

    /**
     * 4. getYearString
     */
    //method in FutureDatePopup
    @Test
    public void getYearString_isCorrect() {
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


    /**
     * 5. addToCover
     */
    //method in Cover class
    @Test
    public void addToCover_isCorrect() {
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


    /**
     * 6. getName & setName
     */
    //methods in TableClass
    @Test
    public void getName_setName_isCorrect() {
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

    //FERNANDO'S TESTS
    @Test
    public void toggleCheckbox_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 1;
        obj.toggleCheckBox();
        int actual = obj.getCheckBox();
        assertEquals(expected, actual);

        expected = 0;
        obj.toggleCheckBox();
        actual = obj.getCheckBox();
        assertEquals(expected, actual);

    }

    @Test
    public void toggleCheckbox_isIncorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 0;
        obj.toggleCheckBox();
        int actual = obj.getCheckBox();
        assertNotEquals(expected, actual);

        expected = 1;
        obj.toggleCheckBox();
        actual = obj.getCheckBox();
        assertNotEquals(expected, actual);
    }

    @Test
    public void getDateAsString_isCorrect() {
        LocalDate date = LocalDate.of(1995, 8, 4);
        Cover obj = new Cover(10, date);
        String expected = "1995-08-04";
        String actual = obj.getDateAsString();
        assertEquals(expected, actual);
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
    public void idCreation_isCorrect() {
        // WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(MainInterface.this);//
        WaitlistEntry obj = new WaitlistEntry("name", "3133133333", 3, "2019-11-21", 0, "notes");
        //wdb.idCreation(obj);
        int actual = obj.getId();
        assertNotNull(actual);
    }

    @Test
    public void getCheckBox_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 0;
        int actual = obj.getCheckBox();
        assertEquals(expected, actual);
    }

    @Test
    public void setCheckBox_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 189;
        obj.setCheckBox(expected);
        int actual = obj.getCheckBox();
        assertEquals(expected, actual);

        expected = 0;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected, actual);

        expected = 1;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected, actual);

        expected = -1;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected, actual);

        expected = Integer.MAX_VALUE;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected, actual);

        expected = Integer.MIN_VALUE;
        obj.setCheckBox(expected);
        actual = obj.getCheckBox();
        assertEquals(expected, actual);

    }

    @Test
    public void parseDate_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        LocalDateTime date = LocalDateTime.of(LocalDate.of(2019, 11, 21), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        String expected = "11/21/2019";
        String actual = obj.parseDate();
        assertEquals(expected, actual);

        date = LocalDateTime.of(LocalDate.of(9999, 12, 31), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "12/31/9999";
        actual = obj.parseDate();
        assertEquals(expected, actual);

        date = LocalDateTime.of(LocalDate.of(1000, 1, 1), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "01/01/1000";
        actual = obj.parseDate();
        assertEquals(expected, actual);

    }

    //MICHAEL'S TESTS
    @Test
    public void ParseTime_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        LocalDateTime date = LocalDateTime.of(LocalDate.of(2019, 11, 24), LocalTime.now());
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        String expected = "6:11pm";
        String actual = obj.parseTime();


        date = LocalDateTime.of(LocalDate.of(2019, 11, 24), LocalTime.of(12, 59));
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "12:59am";
        actual = obj.parseTime();
        assertEquals(expected, actual);

        date = LocalDateTime.of(LocalDate.of(2019, 11, 24), LocalTime.of(15, 59));
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "3:59pm";
        actual = obj.parseTime();
        assertEquals(expected, actual);

        date = LocalDateTime.of(LocalDate.of(2019, 11, 24), LocalTime.of(00, 00));
        obj.setFormattedDateTime(WaitlistEntry.formatDate(date));
        expected = "12:00am";
        actual = obj.parseTime();
        assertEquals(expected, actual);
    }

    @Test
    public void SetCheckboxOn_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 1;
        obj.setCheckBoxOn();
        int actual = obj.getCheckBox();
        assertEquals(expected, actual);

        expected = 2;
        obj.setCheckBoxOn();
        actual = obj.getCheckBox();
        assertNotEquals(expected, actual);

    }

    @Test
    public void Cover_isCorrect() {
        Cover cover = new Cover(24, "2019-11-24");
        int expected = 24;
        int actual = cover.getDailyCover();
        assertEquals(expected, actual);

        String expectedString = "2019-11-24";
        String actualString = cover.getDateAsString();
        assertEquals(expectedString, actualString);

        Cover cover2 = new Cover(24, "9999-12-31");
        expectedString = "9999-12-31";
        actualString = cover2.getDateAsString();
        assertEquals(expectedString, actualString);

        Cover cover3 = new Cover(24, "0001-01-01");
        expectedString = "0001-01-01";
        actualString = cover3.getDateAsString();
        assertEquals(expectedString, actualString);

        Cover cover4 = new Cover(9999, "2019-11-24");
        expected = 9999;
        actual = cover4.getDailyCover();
        assertEquals(expected, actual);

        Cover cover5 = new Cover(0, "2019-11-24");
        expected = 0;
        actual = cover5.getDailyCover();
        assertEquals(expected, actual);

        Cover cover6 = new Cover(-1, "2019-11-24");
        expected = -1;
        actual = cover6.getDailyCover();
        assertEquals(expected, actual);

    }

    @Test
    public void SetDate_isCorrect() {
        Cover cover = new Cover(1, "2019-11-24");
        LocalDate expected = LocalDate.of(2019, 11, 24);
        cover.setDate(LocalDate.of(2019, 11, 24));
        LocalDate actual = cover.getDate();
        assertEquals(expected, actual);

        expected = LocalDate.of(9999, 12, 31);
        cover.setDate(LocalDate.of(9999, 12, 31));
        actual = cover.getDate();
        assertEquals(expected, actual);

        expected = LocalDate.of(1000, 1, 1);
        cover.setDate(LocalDate.of(1000, 1, 1));
        actual = cover.getDate();
        assertEquals(expected, actual);
    }

    @Test
    public void SetTableNumber_isCorrect() {
        TableClass table = new TableClass();
        table.setTableNumber(101);
        int expected = 101;
        int actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(-1);
        expected = -1;
        actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(0);
        expected = 0;
        actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(Integer.MAX_VALUE);
        expected = Integer.MAX_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(Integer.MIN_VALUE);
        expected = Integer.MIN_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected, actual);

    }

    @Test
    public void GetTableNumber_isCorrect() {
        TableClass table = new TableClass();
        table.setTableNumber(101);
        int expected = 101;
        int actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(-1);
        expected = -1;
        actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(0);
        expected = 0;
        actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(Integer.MAX_VALUE);
        expected = Integer.MAX_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected, actual);

        table.setTableNumber(Integer.MIN_VALUE);
        expected = Integer.MIN_VALUE;
        actual = table.getTableNumber();
        assertEquals(expected, actual);
    }


    //HADI'S TESTS

    /**
     * 1.get_setNotes
     */
    //method in WaitlistEntry class
    @Test
    public void getNotes_setNotes() {
        WaitlistEntry obj = new WaitlistEntry();
        String expected = "Extra Cheese";
        String Test = "Extra Cheese";
        obj.setNotes(Test);
        String actual = obj.getNotes();
        assertEquals(expected, actual);

        expected = "42332";
        Test = "42332";
        obj.setNotes(Test);
        actual = obj.getNotes();
        assertEquals(expected, actual);

        expected = "";
        Test = "";
        obj.setNotes(Test);
        actual = obj.getNotes();
        assertEquals(expected, actual);
    }

    /**
     * 2.get_setReservationFlag
     */
    //method in WaitlistEntry class
    @Test
    public void getReservationFlag_setReservationFlag() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 0;
        int Test = 0;
        obj.setReservationFlag(Test);
        int actual = obj.getReservationFlag();
        assertEquals(expected, actual);

    }

    /**
     * 3.get_setTelephone
     */
    //method in WaitlistEntry class
    @Test
    public void get_setTelephone() {
        WaitlistEntry obj = new WaitlistEntry();
        String expected = "3137251017";
        String Test = "3137251017";
        obj.setTelephone(Test);
        String actual = obj.getTelephone();
        assertEquals(expected, actual);

        expected = "123456789";
        Test = "123456789";
        obj.setTelephone(Test);
        actual = obj.getTelephone();
        assertEquals(expected, actual);

    }

    /**
     * 4.Contents
     */
    //method in WaitlistDatabaseHelper class
    @Test
    public void contents_isCorrect() {
        WaitlistEntry obj = new WaitlistEntry();
        String expected = "id:" + 1 + ", " + "" + "Hadi" + ", " + "" + "3137251017" + ", " + "" + 3 + ", " + "" + "2019-11-21" + ", " + "" + 0;
        String Testnumber = "3137251017";
        obj.setTelephone(Testnumber);
        obj.setReservationFlag(0);
        obj.setId(1);
        obj.setName("Hadi");
        obj.setFormattedDateTime("2019-11-21");
        obj.setNumberOfPeople(3);
        String actual = obj.contents();
        assertEquals(expected, actual);
    }

    /**
     * 5.set_dailyCovers
     */
    //method in Cover class
    @Test
    public void set_dailyCovers() {
        Cover obj = new Cover(3, "2019-11-21");
        int expected = 12;
        int Test = 12;
        obj.setDailyCover(Test);
        int actual = obj.getDailyCover();
        assertEquals(expected, actual);
    }

    /**
     * 6.get_numberofpeople
     */
    //method in WaitlistEntry class
    @Test
    public void getNumberofpeople() {
        WaitlistEntry obj = new WaitlistEntry();
        int expected = 12;
        int Test = 12;
        obj.setNumberOfPeople(Test);
        int actual = obj.getNumberOfPeople();
        assertEquals(expected, actual);
    }

    @Override
    public AssetManager getAssets() {
        return null;
    }

    @Override
    public Resources getResources() {
        return null;
    }

    @Override
    public PackageManager getPackageManager() {
        return null;
    }

    @Override
    public ContentResolver getContentResolver() {
        return null;
    }

    @Override
    public Looper getMainLooper() {
        return null;
    }

    @Override
    public Context getApplicationContext() {
        return null;
    }

    @Override
    public void setTheme(int resid) {

    }

    @Override
    public Resources.Theme getTheme() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public String getPackageName() {
        return null;
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return null;
    }

    @Override
    public String getPackageResourcePath() {
        return null;
    }

    @Override
    public String getPackageCodePath() {
        return null;
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return null;
    }

    @Override
    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        return false;
    }

    @Override
    public boolean deleteSharedPreferences(String name) {
        return false;
    }

    @Override
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        return null;
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        return null;
    }

    @Override
    public boolean deleteFile(String name) {
        return false;
    }

    @Override
    public File getFileStreamPath(String name) {
        return null;
    }

    @Override
    public File getDataDir() {
        return null;
    }

    @Override
    public File getFilesDir() {
        return null;
    }

    @Override
    public File getNoBackupFilesDir() {
        return null;
    }

    @Nullable
    @Override
    public File getExternalFilesDir(@Nullable String type) {
        return null;
    }

    @Override
    public File[] getExternalFilesDirs(String type) {
        return new File[0];
    }

    @Override
    public File getObbDir() {
        return null;
    }

    @Override
    public File[] getObbDirs() {
        return new File[0];
    }

    @Override
    public File getCacheDir() {
        return null;
    }

    @Override
    public File getCodeCacheDir() {
        return null;
    }

    @Nullable
    @Override
    public File getExternalCacheDir() {
        return null;
    }

    @Override
    public File[] getExternalCacheDirs() {
        return new File[0];
    }

    @Override
    public File[] getExternalMediaDirs() {
        return new File[0];
    }

    @Override
    public String[] fileList() {
        return new String[0];
    }

    @Override
    public File getDir(String name, int mode) {
        return null;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return null;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, @Nullable DatabaseErrorHandler errorHandler) {
        return null;
    }

    @Override
    public boolean moveDatabaseFrom(Context sourceContext, String name) {
        return false;
    }

    @Override
    public boolean deleteDatabase(String name) {
        return false;
    }

    @Override
    public File getDatabasePath(String name) {
        return null;
    }

    @Override
    public String[] databaseList() {
        return new String[0];
    }

    @Override
    public Drawable getWallpaper() {
        return null;
    }

    @Override
    public Drawable peekWallpaper() {
        return null;
    }

    @Override
    public int getWallpaperDesiredMinimumWidth() {
        return 0;
    }

    @Override
    public int getWallpaperDesiredMinimumHeight() {
        return 0;
    }

    @Override
    public void setWallpaper(Bitmap bitmap) throws IOException {

    }

    @Override
    public void setWallpaper(InputStream data) throws IOException {

    }

    @Override
    public void clearWallpaper() throws IOException {

    }

    @Override
    public void startActivity(Intent intent) {

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {

    }

    @Override
    public void startActivities(Intent[] intents) {

    }

    @Override
    public void startActivities(Intent[] intents, Bundle options) {

    }

    @Override
    public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {

    }

    @Override
    public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {

    }

    @Override
    public void sendBroadcast(Intent intent) {

    }

    @Override
    public void sendBroadcast(Intent intent, @Nullable String receiverPermission) {

    }

    @Override
    public void sendOrderedBroadcast(Intent intent, @Nullable String receiverPermission) {

    }

    @Override
    public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String receiverPermission, @Nullable BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {

    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission) {

    }

    @Override
    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    @Override
    public void sendStickyBroadcast(Intent intent) {

    }

    @Override
    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    @Override
    public void removeStickyBroadcast(Intent intent) {

    }

    @Override
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {

    }

    @Override
    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    @Override
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {

    }

    @Nullable
    @Override
    public Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter) {
        return null;
    }

    @Nullable
    @Override
    public Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter, int flags) {
        return null;
    }

    @Nullable
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, @Nullable String broadcastPermission, @Nullable Handler scheduler) {
        return null;
    }

    @Nullable
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, @Nullable String broadcastPermission, @Nullable Handler scheduler, int flags) {
        return null;
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {

    }

    @Nullable
    @Override
    public ComponentName startService(Intent service) {
        return null;
    }

    @Nullable
    @Override
    public ComponentName startForegroundService(Intent service) {
        return null;
    }

    @Override
    public boolean stopService(Intent service) {
        return false;
    }

    @Override
    public boolean bindService(Intent service, @NonNull ServiceConnection conn, int flags) {
        return false;
    }

    @Override
    public void unbindService(@NonNull ServiceConnection conn) {

    }

    @Override
    public boolean startInstrumentation(@NonNull ComponentName className, @Nullable String profileFile, @Nullable Bundle arguments) {
        return false;
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        return null;
    }

    @Nullable
    @Override
    public String getSystemServiceName(@NonNull Class<?> serviceClass) {
        return null;
    }

    @Override
    public int checkPermission(@NonNull String permission, int pid, int uid) {
        return 0;
    }

    @Override
    public int checkCallingPermission(@NonNull String permission) {
        return 0;
    }

    @Override
    public int checkCallingOrSelfPermission(@NonNull String permission) {
        return 0;
    }

    @Override
    public int checkSelfPermission(@NonNull String permission) {
        return 0;
    }

    @Override
    public void enforcePermission(@NonNull String permission, int pid, int uid, @Nullable String message) {

    }

    @Override
    public void enforceCallingPermission(@NonNull String permission, @Nullable String message) {

    }

    @Override
    public void enforceCallingOrSelfPermission(@NonNull String permission, @Nullable String message) {

    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {

    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {

    }

    @Override
    public void revokeUriPermission(String toPackage, Uri uri, int modeFlags) {

    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        return 0;
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        return 0;
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        return 0;
    }

    @Override
    public int checkUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags) {
        return 0;
    }

    @Override
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {

    }

    @Override
    public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {

    }

    @Override
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {

    }

    @Override
    public void enforceUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags, @Nullable String message) {

    }

    @Override
    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        return null;
    }

    @Override
    public Context createContextForSplit(String splitName) throws PackageManager.NameNotFoundException {
        return null;
    }

    @Override
    public Context createConfigurationContext(@NonNull Configuration overrideConfiguration) {
        return null;
    }

    @Override
    public Context createDisplayContext(@NonNull Display display) {
        return null;
    }

    @Override
    public Context createDeviceProtectedStorageContext() {
        return null;
    }

    @Override
    public boolean isDeviceProtectedStorage() {
        return false;
    }
}
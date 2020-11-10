package com.mikel.intentsimplicitos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String USER = "user";
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String QUIZ = "quiz";
    public static final String TERM = "term";

    public DataBaseHelper(@Nullable Context context) {
        super(context, USER + "s.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table1 = "CREATE TABLE " + USER + " (id INTEGER PRIMARY KEY AUTOINCREMENT," + LOGIN + " VARCHAR, " + NAME + " VARCHAR, " + LASTNAME + " VARCHAR, " + EMAIL + " VARCHAR, " + PASSWORD + " VARCHAR)";
        String table2 = "CREATE TABLE " + QUIZ + " (id integer PRIMARY KEY AUTOINCREMENT,  " + TERM + " VARCHAR)";

        db.execSQL(table1);
        db.execSQL(table2);

        String insert = "INSERT INTO " + QUIZ + " VALUES ('limon'), ('lampara'),('ordenador'),('sol'),('patata'),('guitarra'),('balon'),('raqueta')";

        db.execSQL(insert);
    }

    public List<String> getWords(){
        List<String> wordsList = new ArrayList<>();

        String obtainWords = "SELECT * FROM " + QUIZ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(obtainWords, null);

        if(cursor.moveToFirst()){
            do{
                String newWord = cursor.getString(1);
                wordsList.add(newWord);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return wordsList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addOne(User user){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LOGIN, user.getLogin());
        cv.put(NAME, user.getName());
        cv.put(LASTNAME, user.getLastname());
        cv.put(EMAIL, user.getEmail());
        cv.put(PASSWORD, user.getPassword());

        long insert = db.insert(USER, null, cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public User getUser(String loginText, String passwordText){

        User returnUser = null;

        String query = String.format("SELECT * FROM  %s WHERE %s='%s' AND %s='%s'", USER, LOGIN, loginText, PASSWORD, passwordText);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null); 

        if(cursor.moveToFirst()){
            String userLogin = cursor.getString(0);
            String userName = cursor.getString(1);
            String userLastname = cursor.getString(2);
            String userEmail = cursor.getString(3);
            String userPassword = cursor.getString(4);
            returnUser = new User(userLogin,userName, userLastname, userEmail, userPassword);
        }else{
            //poner un toast
        }
        cursor.close();
        db.close();
        
        return returnUser;
    }
}

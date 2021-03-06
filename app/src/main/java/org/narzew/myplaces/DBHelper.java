package org.narzew.myplaces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.res.AssetManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class DBHelper {

    protected Context context;

    ContextWrapper cw;
    String db_path;

    public DBHelper(Context context){
        this.context = context;
        cw = new ContextWrapper(context);
        db_path = cw.getFilesDir().getAbsolutePath()+ "/databases/";
    }

    public SQLiteDatabase openDatabase() {
        File dbFile = context.getDatabasePath(Config.DB_NAME);
        Log.d(Config.LOG_TAG,"dbFile variable = "+dbFile.toString());
        if (!dbFile.exists()) {
            copyDatabase();
            put_int("db_version", Config.DB_VERSION);
        } else {
            SharedPreferences sharedpreferences = context.getSharedPreferences(Config.PREFS_NAME, Context.MODE_PRIVATE);
            if(Config.DB_VERSION>sharedpreferences.getInt("db_version",0)){
                // Nieaktualna baza danych
                copyDatabase();
                put_int("db_version", Config.DB_VERSION);
                Log.d(Config.LOG_TAG, "Database updated");
            } else {
                Log.d(Config.LOG_TAG, "Database up to date");
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    private void copyDatabase(){
        Log.d(Config.LOG_TAG, "Prepare for database copying");
        String path = Config.DB_PATH;
        File dbfolder = new File(Config.DB_FOLDER);
        if(!dbfolder.exists()){
            dbfolder.mkdir();
        }
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        InputStream myInput = null;
        try
        {
            myInput = context.getAssets().open(Config.DB_NAME);
            //myOutput =new FileOutputStream(db_path + DB_NAME);
            myOutput = new FileOutputStream(Config.DB_PATH);
            while((length = myInput.read(buffer)) > 0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.d(Config.LOG_TAG, "Database copied");
        }
        catch(IOException e)
        {
            Log.d(Config.LOG_TAG, "Fail to copy database");
            e.printStackTrace();
        }
    }

    public void put_int(String name, Integer value){
        SharedPreferences sharedpreferences = context.getSharedPreferences(Config.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public String file_read(String filename){
        InputStream input;
        String text = "";
        try {
            AssetManager assetManager = context.getAssets();
            input = assetManager.open(filename);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            text = new String(buffer);
        } catch(IOException e){
            e.printStackTrace();
        }
        return text;
    }

    public Cursor getCities(){
        SQLiteDatabase database = openDatabase();
        return database.rawQuery("select id, name from cities", null, null);
    }

    public String getCityName(int id){
        SQLiteDatabase database = openDatabase();
        Cursor cursor = database.rawQuery("select name from cities where id = "+id+" limit 1", null, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public Cursor getCityEvents(int city_id){
        SQLiteDatabase database = openDatabase();
        return database.rawQuery("select id, city_id, name, author, description, location, timing from events where city_id = "+city_id, null, null);
    }

    public Cursor getAllEvents(){
        SQLiteDatabase database = openDatabase();
        Cursor cursor = database.rawQuery("select id, city_id, name, author, description, location, timing from events", null, null);
        return cursor;
    }

    public Cursor getEvent(int id){
        SQLiteDatabase database = openDatabase();
        Cursor cursor = database.rawQuery("select id, city_id, name, author, description, location, timing from events where id = "+id+" limit 1", null, null);
        cursor.moveToFirst();
        return cursor;
    }

    public Event getEventByIdAsObject(int id){
        SQLiteDatabase database = openDatabase();
        Cursor cursor = database.rawQuery("select id, city_id, name, author, description, location, timing from events where id = "+id+" limit 1", null, null);
        cursor.moveToFirst();
        Event result = new Event(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getString(6));
        database.close();
        return result;
    }

    public void addEvent(Event e){
        // Add one to ID

        SharedPreferences sharedpreferences = context.getSharedPreferences(Config.PREFS_NAME, Context.MODE_PRIVATE);
        // Update event nr
        Integer old_event_id = sharedpreferences.getInt("events_nr",Config.FIRST_ID);
        Integer new_event_id = old_event_id+1;
        sharedpreferences.edit().putInt("events_nr", new_event_id).commit();

        // Open DB connection
        SQLiteDatabase database = openDatabase();
        String sql = String.format("insert into events (id, city_id, name, author, description, location, timing) values (%d, %d, '%s', '%s', '%s', '%s', '%s')",
                new_event_id, e.getCity_id(), e.getName(), e.getAuthor(), e.getDescription(), e.getLocation(), e.getDate());
        Log.d(Config.LOG_TAG, "SQL: " + sql);
        database.execSQL(sql);
        database.close();
    }

}

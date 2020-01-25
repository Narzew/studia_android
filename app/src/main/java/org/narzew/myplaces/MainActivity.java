package org.narzew.myplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // ONLY FOR DEBUG PURPOSES
    Integer[] idArray = {1, 2, 3, 4, 5, 6};
    String[] nameArray = {"Sanok","Warszawa","Rzeszów","Lublin","Wrocław","Kraków" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listCities=(ListView) findViewById(R.id.listCities);
        DBHelper db = new DBHelper(this);

        Cursor cities = db.getCities();

        CitiesListAdapter citiesadapter = new CitiesListAdapter(this, idArray, nameArray);
        listCities.setAdapter(citiesadapter);

    }

    public void openEventsList(int event_id){
        Intent intent = new Intent(this, EventsActivity.class);
        intent.putExtra("event_id", event_id);
        startActivity(intent);
    }


}

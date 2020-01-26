package org.narzew.myplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ONLY FOR DEBUG PURPOSES
    ArrayList<City> cityList = new ArrayList<City>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listCities=(ListView) findViewById(R.id.listCities);
        DBHelper db = new DBHelper(this);

        Cursor cities = db.getCities();
        Cursor city_data = db.getCities();
        if (city_data != null && city_data.moveToFirst()) {
            // id=0, city_id=1, name=2, author=3, description=4, location=5, timing=6
            cityList.add(new City(city_data.getInt(0),city_data.getString(1)));
        }

        CitiesListAdapter citiesadapter = new CitiesListAdapter(this, cityList);
        listCities.setAdapter(citiesadapter);

    }

    public void openEventsList(int event_id){
        Intent intent = new Intent(this, EventsActivity.class);
        intent.putExtra("event_id", event_id);
        startActivity(intent);
    }


}

package org.narzew.myplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ONLY FOR DEBUG PURPOSES
    ArrayList<City> cityList = new ArrayList<City>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        ListView listCities=(ListView) findViewById(R.id.listCities);
        DBHelper db = new DBHelper(this);

        Cursor cities = db.getCities();
        Cursor city_data = db.getCities();
        if (city_data != null && city_data.moveToFirst()) {
            do {
                // id=0, city_id=1, name=2, author=3, description=4, location=5, timing=6
                cityList.add(new City(city_data.getInt(0), city_data.getString(1)));
            } while (city_data.moveToNext());
        }

        CitiesListAdapter citiesadapter = new CitiesListAdapter(this, cityList);
        listCities.setAdapter(citiesadapter);

        // Add onClick listener to a list

        listCities.setClickable(true);
        listCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                City city = cityList.get(position);
                Intent eventList = new Intent(context, EventsActivity.class);
                eventList.putExtra("city_id", city.getId());
                startActivity(eventList);
            }
        });

    }

}

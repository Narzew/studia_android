package org.narzew.myplaces;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    Integer event_id;
    ArrayList<Event> eventList;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Bundle extras = getIntent().getExtras();
        DBHelper db = new DBHelper(this);
        event_id = extras.getInt("event_id");
        ListView listCities=(ListView) findViewById(R.id.listCities);

        // Fill events

        Cursor event_data = db.getEvent(event_id);
        if (event_data != null && event_data.moveToFirst()) {
            // id=0, city_id=1, name=2, author=3, description=4, location=5, timing=6
            eventList.add(new Event(event_data.getInt(0),event_data.getInt(1),event_data.getString(2),event_data.getString(3),event_data.getString(4),event_data.getString(5),event_data.getString(6)));
        }

        // Toolbar actions

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /* Fill ListView with events */

    private static void fillEvents(){


    }

}

package org.narzew.myplaces;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class EventsActivity extends AppCompatActivity {

    Integer city_id;
    String city_name;
    Integer event_id;
    ArrayList<Event> eventList = new ArrayList<Event>();
    Event event;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_events);
        Bundle extras = getIntent().getExtras();
        DBHelper db = new DBHelper(this);
        city_id = extras.getInt("city_id");
        city_name = extras.getString("city_name");
        setTitle(getResources().getString(R.string.events_in) + " " + city_name);
        ListView listEvents=(ListView) findViewById(R.id.listEvents);

        // Fill events

        Cursor event_data = db.getCityEvents(city_id);
        if (event_data != null && event_data.moveToFirst()) {
            do {
                // id=0, city_id=1, name=2, author=3, description=4, location=5, timing=6
                eventList.add(new Event(event_data.getInt(0), event_data.getInt(1), event_data.getString(2), event_data.getString(3), event_data.getString(4), event_data.getString(5), event_data.getString(6)));
            } while (event_data.moveToNext());
        }

        EventsListAdapter eventsListAdapter = new EventsListAdapter(this, eventList);
        listEvents.setAdapter(eventsListAdapter);

        // Add onClick listener to a list

        listEvents.setClickable(true);
        listEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Event event = eventList.get(position);
                Intent eventDetails = new Intent(context, EventDetailsActivity.class);
                eventDetails.putExtra("event_id", event.getId());
                startActivity(eventDetails);
            }
        });

        // Toolbar actions

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newEvent = new Intent(context, AddEventActivity.class);
                newEvent.putExtra("city_id",city_id);
                newEvent.putExtra("city_name",city_name);
                startActivity(newEvent);
            }
        });
    }

}

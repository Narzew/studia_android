package org.narzew.myplaces;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class EventDetailsActivity extends AppCompatActivity {

    public String PREFS_NAME = Config.PREFS_NAME;
    Context context = this;
    Integer event_id; // Event id

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Get event ID
        Bundle b = getIntent().getExtras();
        event_id = b.getInt("event_id");

        DBHelper dbhelper = new DBHelper(context);

        // Dopasowanie do pól w layoucie
        TextView event_name = (TextView) findViewById(R.id.event_name);
        TextView event_description = (TextView) findViewById(R.id.event_description);
        TextView event_author = (TextView) findViewById(R.id.event_author);
        TextView event_location = (TextView) findViewById(R.id.event_location);
        TextView event_timing = (TextView) findViewById(R.id.event_timing);

        // Wypełnienie danymi

        Log.d(Config.LOG_TAG, "Event ID: " + event_id);
        Cursor event_data = dbhelper.getEvent(event_id);
        if (event_data != null && event_data.moveToFirst()) {
            // id=0, city_id=1, name=2, author=3, description=4, location=5, timing=6
            event_name.setText(event_data.getString(2));
            event_author.setText(event_data.getString(3));
            event_description.setText(event_data.getString(4));
            event_location.setText(event_data.getString(5));
            event_timing.setText(event_data.getString(6));

        }

        setTitle(event_name.getText());

    }

}


package org.narzew.myplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Integer city_id;
    String city_name;
    EditText event_name, event_description, event_author, event_location;
    TextView event_timing;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Bundle bundle = getIntent().getExtras();
        city_id = bundle.getInt("city_id");
        city_name = bundle.getString("city_name");
        setTitle(getResources().getString(R.string.add_event_in) + " " + city_name);

        event_name = (EditText)findViewById(R.id.textName);
        event_description = (EditText)findViewById(R.id.textDescription);
        event_author = (EditText)findViewById(R.id.textAuthor);
        event_location = (EditText)findViewById(R.id.textLocation);
        event_timing = (TextView)findViewById(R.id.textDate);

        db = new DBHelper(this);

        // Date Picker dialog
        findViewById(R.id.textDate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog(){
        Log.d(Config.LOG_TAG,"Calling Date Picker");
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void addEvent(View v){
        // id 0 in object, id will be given after adding the object in the database.

        Event e = new Event(0, city_id, event_name.getText().toString(), event_author.getText().toString(),event_description.getText().toString(), event_location.getText().toString(), event_timing.getText().toString());
        db.addEvent(e);
        // After adding event, go to main activity
        Toast.makeText(this, getResources().getString(R.string.event_added_ok), Toast.LENGTH_LONG);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month+1; // Add 1 to month to get normal month (1 for January instead of 0)
        String date = dayOfMonth+"/"+month+"/"+year;
        event_timing.setText(date);
    }
}

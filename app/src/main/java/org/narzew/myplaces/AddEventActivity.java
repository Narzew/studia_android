package org.narzew.myplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Integer city_id;
    EditText event_name, event_description, event_author, event_location;
    TextView event_timing;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Bundle bundle = getIntent().getExtras();
        city_id = bundle.getInt("city_id");

        event_name = (EditText)findViewById(R.id.textName);
        event_description = (EditText)findViewById(R.id.textDescription);
        event_author = (EditText)findViewById(R.id.textAuthor);
        event_location = (EditText)findViewById(R.id.textLocation);
        event_timing = (EditText)findViewById(R.id.textDate);

        db = new DBHelper(this);

        // Date Picker dialog
        findViewById(R.id.textDate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }

    private void showDatePickerDialog(){
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void addEvent(View v){
        Event e = new Event(0, city_id, event_name.getText().toString(), event_author.getText().toString(),event_description.getText().toString(), event_location.getText().toString(), event_timing.getText().toString());
        db.addEvent(e);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+"/"+month+"/"+year;
        event_timing.setText(date);
    }
}

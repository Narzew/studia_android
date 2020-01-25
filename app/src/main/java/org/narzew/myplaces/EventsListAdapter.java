package org.narzew.myplaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventsListAdapter extends ArrayAdapter {

    private Context context;
    private final Integer[] idArray;
    private final String[] nameArray;
    private final String[] locationArray;
    private final String[] dateArray;

    public EventsListAdapter(Context contextParam, Integer[] idArrayParam, String[] nameArrayParam, String[] dateArrayParam, String[] locationArrayParam){
        super(contextParam,R.layout.events_row , nameArrayParam);
        this.context = contextParam;
        this.idArray = idArrayParam;
        this.nameArray = nameArrayParam;
        this.locationArray = locationArrayParam;
        this.dateArray = dateArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        int current_id = 0;
        View rowView=LayoutInflater.from(context).inflate(R.layout.events_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView list_eventName  = (TextView) rowView.findViewById(R.id.list_eventName);
        TextView list_eventDate = (TextView) rowView.findViewById(R.id.list_eventDate);
        TextView list_eventLocation = (TextView) rowView.findViewById(R.id.list_eventLocation);

        //this code sets the values of the objects to values from the arrays
        list_eventName.setText(nameArray[position]);
        list_eventDate.setText(dateArray[position]);
        list_eventLocation.setText(locationArray[position]);
        return rowView;
    }

}
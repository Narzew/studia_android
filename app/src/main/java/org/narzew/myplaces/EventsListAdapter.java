package org.narzew.myplaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsListAdapter extends ArrayAdapter {

    private Context context;
    private final ArrayList<Event> eventArray;

    public EventsListAdapter(Context contextParam, ArrayList<Event> eventArray){
        super(contextParam,R.layout.events_row , eventArray);
        this.context = contextParam;
        this.eventArray = eventArray;
    }

    public View getView(int position, View view, ViewGroup parent) {
        int current_id = 0;
        View rowView=LayoutInflater.from(context).inflate(R.layout.events_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView list_eventName  = (TextView) rowView.findViewById(R.id.list_eventName);
        TextView list_eventDate = (TextView) rowView.findViewById(R.id.list_eventDate);
        TextView list_eventLocation = (TextView) rowView.findViewById(R.id.list_eventLocation);

        //this code sets the values of the objects to values from the arrays
        list_eventName.setText(eventArray.get(position).getName());
        list_eventDate.setText(eventArray.get(position).getDate());
        list_eventLocation.setText(eventArray.get(position).getLocation());
        return rowView;
    }

}
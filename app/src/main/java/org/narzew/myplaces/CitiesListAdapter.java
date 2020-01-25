package org.narzew.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CitiesListAdapter extends ArrayAdapter {

    private final Activity context;
    private final Integer[] idArray;
    private final String[] nameArray;

    public CitiesListAdapter(Activity contextParam, Integer[] idArrayParam, String[] nameArrayParam){
        super(context,R.layout.cities_row , nameArrayParam);
        this.context=contextParam;
        this.idArray = idArrayParam;
        this.nameArray = nameArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        int current_id = 0;
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.cities_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView listCityName  = (TextView) rowView.findViewById(R.id.list_cityName);

        //this code sets the values of the objects to values from the arrays
        listCityName.setText(nameArray[position]);
        return rowView;
    }

}
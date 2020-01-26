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

public class CitiesListAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<City> cityArray;

    public CitiesListAdapter(Context contextParam, ArrayList<City> cityArrayParam){
        super(contextParam,R.layout.cities_row , cityArrayParam);
        this.context = contextParam;
        this.cityArray = cityArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        int current_id = 0;
        View rowView=LayoutInflater.from(context).inflate(R.layout.cities_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView listCityName  = (TextView) rowView.findViewById(R.id.list_cityName);

        //this code sets the values of the objects to values from the arrays
        listCityName.setText(cityArray.get(position).getName());
        return rowView;
    }

}
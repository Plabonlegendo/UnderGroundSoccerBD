package com.example.plabon.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.plabon.myapplication.EventInfo;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<EventInfo> {

    public MyAdapter(Context context, ArrayList<EventInfo> array) {

        super(context, 0, array);

    }



    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        EventInfo event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        }

        // Lookup view for data population

        TextView eventName = (TextView) convertView.findViewById(R.id.eventName);

        TextView eventTime = (TextView) convertView.findViewById(R.id.eventTime);

        // Populate the data into the template view using the data object

        eventName.setText(event.getEventName());

        eventTime.setText(event.getStartingDate());

        // Return the completed view to render on screen

        return convertView;

    }

}
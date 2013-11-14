package com.InAndOut.com.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.InAndOut.R;
import com.parse.ParseUser;

public class MySimpleArrayAdapter extends ArrayAdapter<ParseUser> {
    private final Context context;
    private final ParseUser[] values;

    public MySimpleArrayAdapter(Context context, ParseUser[] values) {
        super(context, R.layout.homescreen_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.homescreen_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.name);
        TextView state = (TextView) rowView.findViewById(R.id.state);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.thumbnail);
        TextView status = (TextView) rowView.findViewById(R.id.status);
        textView.setText(values[position].getString("username"));
        Boolean inout = values[position].getBoolean("ishome");
        if (inout == true) {
            state.setText("In");
            state.setTextColor(Color.parseColor("#12a976"));
        } else {
            state.setText("Out");
            status.setText("Busy");
            state.setTextColor(Color.parseColor("#FF0000"));
        }
        if (position == 0) {
            imageView.setImageResource(R.drawable.alec);
        } else if (position == 1) {
            imageView.setImageResource(R.drawable.jesse);
        } else if (position == 2) {
            imageView.setImageResource(R.drawable.vic);
        } else if (position == 3) {
            imageView.setImageResource(R.drawable.linda);
        } else {
            imageView.setImageResource(R.drawable.sylvia);
        }

        return rowView;
    }
} 
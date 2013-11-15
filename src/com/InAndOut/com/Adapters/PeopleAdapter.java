package com.InAndOut.com.Adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.InAndOut.R;
import com.InAndOut.Views.RoundedImageView;
import com.parse.ParseUser;

public class PeopleAdapter extends ArrayAdapter<ParseUser> {

	private Context mContext;
	private PeopleAdapter adapter;
	private LayoutInflater mInflater;
	private ArrayList<ParseUser> contacts = new ArrayList<ParseUser>();

	public PeopleAdapter(Context context, int resource,
			List<ParseUser> objects, LayoutInflater layoutInflater) {
		super(context, resource, objects);
		mContext = context;
		mInflater = layoutInflater;
		contacts = (ArrayList<ParseUser>) objects;
		adapter = this;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.homescreen_item, null);
			holder = new ContactViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.thumbnail = (RoundedImageView) convertView.findViewById(R.id.thumbnail);
			holder.state = (TextView) convertView.findViewById(R.id.state);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			holder.indicator = (LinearLayout) convertView.findViewById(R.id.indicator);

			convertView.setTag(holder);
		} else {
			holder = (ContactViewHolder) convertView.getTag();
		}
		boolean isIn = contacts.get(position).getBoolean("ishome");

		if (isIn) {
			holder.state.setText("in");
			holder.indicator.setBackgroundColor(mContext.getResources().getColor(R.color.green));
			holder.state.setTextColor(Color.parseColor("#12a976"));

		} else {
			holder.state.setText("Out");
			holder.indicator.setBackgroundColor(mContext.getResources().getColor(R.color.yellow));
			holder.status.setText("Busy");
			holder.state.setTextColor(Color.parseColor("#FF0000"));
		}

		if (position == 0) {
			holder.thumbnail.setImageResource(R.drawable.alec);
		} else if (position == 1) {
			holder.thumbnail.setImageResource(R.drawable.jesse);
		} else if (position == 2) {
			holder.thumbnail.setImageResource(R.drawable.vic);
		} else if (position == 3) {
			holder.thumbnail.setImageResource(R.drawable.linda);
		} else {
			holder.thumbnail.setImageResource(R.drawable.sylvia);
		}
		

		return convertView;
	}
}

class ContactViewHolder {
	public TextView name;
	public TextView state;
	public TextView status;
	public RoundedImageView thumbnail;
	public LinearLayout indicator;
}

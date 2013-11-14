package com.InAndOut;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class enter extends Activity implements OnItemClickListener {
    ParseUser currentUser = null;
    String TAG = this.getClass().getCanonicalName();

    ListView list;
    Dialog listDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentUser = ParseUser.getCurrentUser();
        if (currentUser.get("House") != null && !currentUser.get("House").equals(JSONObject.NULL)) {
            Intent i = new Intent(this, HomeScreen.class);
            startActivity(i);
        }

        setContentView(R.layout.enter_view);
    }

    public void onResume(Bundle savedInstanceState) {
        currentUser = ParseUser.getCurrentUser();
        if (currentUser.get("House").equals(JSONObject.NULL) && currentUser.get("House") != null) {
            Log.d(TAG, "this is resuming");
            Intent i = new Intent(this, HomeScreen.class);
            startActivity(i);
        }
    }

    private void showdialog() {
        listDialog = new Dialog(this);
        listDialog.setTitle("Select Item");
        LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.list, null, false);
        listDialog.setContentView(v);
        listDialog.setCancelable(true);
        Log.d(TAG, "inloop");
        String[] homes = getallhomes();

        ListView list1 = (ListView) listDialog.findViewById(R.id.listview);
        list1.setOnItemClickListener(this);
        list1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, homes));
        listDialog.show();
    }

    public String[] getallhomes() {
        ArrayList<String> names = new ArrayList<String>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        List<ParseObject> homes;
        Log.d(TAG, "dump");
        try {
            homes = query.find();
            Log.d(TAG, "asdiasd");
            for (ParseObject content : homes) {
                String name = content.getString("name");
                names.add(name);
                Log.d(TAG, "inloop");
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d(TAG, "outloop");
        String[] array = names.toArray(new String[names.size()]);
        return array;
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        try {
            List<ParseObject> homes = query.find();
            ParseObject home = homes.get(arg2);
            currentUser.put("House", home);
            currentUser.saveInBackground();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);

    }

    public void onClickJoin(View v) {
        showdialog();
    }

    public void onClickRegister(View v) {
        Intent i = new Intent(this, CreateHome.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                settings();
                return true;
            case R.id.action_logout:
                LogOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void settings() {

    }

    public void LogOut() {
        ParseUser.logOut();
        Intent i = new Intent(this, LoginScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

}

package com.InAndOut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class CreateHome extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_home);
    }

    public void onClickCreate(View v) {
        EditText hn = (EditText) findViewById(R.id.housename);
        EditText pw = (EditText) findViewById(R.id.address);
        EditText wt = (EditText) findViewById(R.id.wifitype);
        EditText wn = (EditText) findViewById(R.id.wifiname);
        EditText wp = (EditText) findViewById(R.id.wifipassword);

        String housename = hn.getText().toString();
        String password = pw.getText().toString();
        String wifitype = wt.getText().toString();
        String wifiname = wn.getText().toString();
        String wifipassword = wp.getText().toString();

        ParseObject Home = new ParseObject("Home");
        Home.put("name", housename);
        Home.put("address", password);
        Home.put("type", wifitype);
        Home.put("wifiname", wifiname);
        Home.put("wifipassword", wifipassword);

        try {
            Home.save();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ParseUser currentUser = ParseUser.getCurrentUser();

        currentUser.put("House", Home);
        currentUser.saveInBackground();

        finish();

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

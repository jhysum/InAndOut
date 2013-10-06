package com.InAndOut;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.InAndOut.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreen extends Activity {
	String TAG = this.getClass().getCanonicalName();
	ParseUser currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		Parse.initialize(this, "5ubKTKBXqJBpiIeg4PhWJZ0YX8iqR2qjFp3IqcOL", "nzvCpvA76A6wXU28N3LgpCANEOgH3hTe6qdJV6q9");
		
		currentUser = ParseUser.getCurrentUser();
		
		if (currentUser != null) {
			Log.d(TAG,"this is logging in with: " + currentUser);
    	Intent i = new Intent(this, enter.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(i);
    	finish();
		} 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
		return true;
	}
	
	public void onClickLogin(View v){
		EditText un = (EditText) findViewById(R.id.username);
		EditText pw = (EditText) findViewById(R.id.password);
		
		String username = un.getText().toString();
		String password = pw.getText().toString();
		
		try {
			currentUser = ParseUser.logIn(username, password);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (currentUser != null){
			Log.d(TAG,"Logged in");
    	Intent i = new Intent(this, enter.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(i);
    	finish();
		} else {
			Log.d(TAG, "failed logged in");
		}
		
	}
	
	public void onClickRegister(View v){
		ParseUser user = new ParseUser();
		
		EditText un = (EditText) findViewById(R.id.username);
		EditText pw = (EditText) findViewById(R.id.password);
		
		String username = un.getText().toString();
		String password = pw.getText().toString();
		
		Log.d(TAG,"this is username: " +username);
		Log.d(TAG,"this is username: " +password);

		if (!username.equals("") || !password.equals("")){
		user.setUsername(username);
		user.setPassword(password);
		//user.setEmail("email@example.com");
		 
		// other fields can be set just like with ParseObject
		user.put("phone", "650-253-0000");
		
		try {
			user.signUp();
		} catch (ParseException e) {
			Log.d(TAG,"failed register");
			e.printStackTrace();
		}
		
	} else {
		Toast t = Toast.makeText(this, "missing field", Toast.LENGTH_SHORT);
		t.show();
	}
		
		try {
			currentUser = ParseUser.logIn(username, password);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (currentUser != null){
			Log.d(TAG,"Logged in");
    	Intent i = new Intent(this, enter.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(i);
    	finish();
		} else {
			Log.d(TAG, "failed logged in");
		}
	}

}

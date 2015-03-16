package com.curesec.android;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

public class RemoveLocks extends IntentService {
	
	public RemoveLocks() {
		super("RemoveLocks");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		removeLocks();
	}

	
	private void removeLocks() { 
		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.ChooseLockGeneric"));
		intent.putExtra("confirm_credentials", false);
		intent.putExtra("lockscreen.password_type",0);
		intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}


	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	

}
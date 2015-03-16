package com.curesec.android.kolme;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		TextView tv = (TextView) this.findViewById(R.id.textView1);
		tv.setText(Html.fromHtml("<br>Curesec GmbH is a company of dedicated IT Security experts that offers comprehensive, professional advice and education in the IT security field.<br><br>To learn more about Curesec, you can visit us at: <br><br> <center><a href=\"http://curesec.com\">www.curesec.com</a></center><br><br>or email us at: <br><br><center><a href=\"mailto:ping@curesec.com\">ping@curesec.com</a></center>"));
		tv.setMovementMethod(LinkMovementMethod.getInstance());
	
	}
}

package com.sadcheese;



import com.sadcheese.surefire.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SnoreLax extends Activity {
	Button bt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.the_snorer);
		bt = (Button) findViewById(R.id.bAccept);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sendIntent = new Intent(Intent.ACTION_VIEW);
				sendIntent.setType("plain/text");
				sendIntent.setData(Uri.parse("surefireapp@gmail.com"));
				sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
				sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "surefireapp@gmail.com" });
				sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Snorelax");
				sendIntent.putExtra(Intent.EXTRA_TEXT, "Send Us some Pics!!");
				startActivity(sendIntent);
				
			}});
	}
	
	
}

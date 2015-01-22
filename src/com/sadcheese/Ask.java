package com.sadcheese;


import com.sadcheese.surefire.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class Ask extends Activity {

	private static final int SELECT_PICTURE_CALLBACK = 0;
	ImageButton ibSend;
	EditText etTake;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ask);

		initialize();

	}

	private void initialize() {
		// TODO Auto-generated method stub

		ibSend = (ImageButton) findViewById(R.id.bSendSMS);

		ibSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Ask.this, Paying.class);
				startActivity(intent);
			}
		});
	}

}

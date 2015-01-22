package com.sadcheese;

import java.util.ArrayList;

import com.sadcheese.surefire.R;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;

public class Texting extends Fragment {
	String name, body, address, type;
	int id, thread_id;
	int inboxCounter = 0;
	TextView display;
	QuickContactBadge bg;
	TextView percent;
	ProgressBar pb;
	TableRow tr;
	LinearLayout tl1;
	TextView t;
	ProgressBar spinner;
	Button nameT;
	LinearLayout layout;
	Context context;
	LinearLayout addable;
	// new page stuff
	TextView profileN;
	TextView profileN2;
	TextView knownK;
	TextView knowC;
	TextView cost;
	TextView costP;
	ImageView jakePic;
	RatingBar jakeRate;

	double inboxCount;
	double outboxCount;
	double meP = 0;
	double themP = 0;
	LayoutInflater inflater;
	View view;

	String sall;
	int x, g;
	ArrayList<Double> outBoxAr = new ArrayList<Double>(15);;
	ArrayList<Double> InboxAr = new ArrayList<Double>(15);;
	ArrayList<Double> inboxA = new ArrayList<Double>(15);
	ArrayList<Double> outBoxA = new ArrayList<Double>(15);
	ArrayList<String> numbersArray = new ArrayList<String>();
	String[] all = new String[9];
	String[] num;
	LinearLayout lly, linearLayout;
	int y1, y;

	boolean flipped = false;
	boolean done = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.texting_layout, container,
				false);

		// initialize

		linearLayout = (LinearLayout) rootView.findViewById(R.id.textLay);
		display = (TextView) rootView.findViewById(R.id.contact);
		percent = (TextView) rootView.findViewById(R.id.percentage);
		
		Button toGuide = (Button) rootView.findViewById(R.id.bGuide);

		toGuide.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myWebLink = new Intent(
						android.content.Intent.ACTION_VIEW);
				myWebLink.setData(Uri
						.parse("http://howtotextgirls.com"));
				startActivity(myWebLink);
			}
		});

		// ends
		Button bRec = (Button) rootView.findViewById(R.id.bRec);
		bRec.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), recomend.class);
				getActivity().startActivity(intent);

			}
		});


		View button = inflater.inflate(R.layout.addable, null);
		linearLayout.addView(button);
		nameT = (Button) rootView.findViewById(R.id.nameZ);
		nameT.setText("Texting Stats");

		nameT.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ProcessAid.class);
				getActivity().startActivity(intent);

			}
		});

		return rootView;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.

		return true;
	}

}

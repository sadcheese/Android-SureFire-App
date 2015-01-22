package com.sadcheese;

import java.util.ArrayList;



import com.sadcheese.surefire.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;

public class ProcessAid extends Activity {
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
	TextView knowC,action;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_texting);

		if (!done) {

			someStuff();
			initialize();
			done = true;
		}

	}

	public void start() {
		someStuff();

	}

	private void initialize() {
		// TODO Auto-generated method stub
		context = this;
		display = (TextView) findViewById(R.id.contact);
		percent = (TextView) findViewById(R.id.percentage);
		// TODO Auto-generated method stub

		context = this;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) findViewById(R.id.mainLayout);
		addable = (LinearLayout) findViewById(R.id.addable);

		for (int y = 0; y < numbersArray.size(); y++) {

			view = inflater.inflate(
					R.layout.addable, null);

			layout.addView(view);
	
			action = (TextView) findViewById(R.id.action);
			nameT = (Button) view
					.findViewById(R.id.nameZ);

			nameT.setText(numbersArray.get(y));

			nameT.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Button b = (Button) v;
					int k = numbersArray.indexOf(b.getText());
					percent.setText("From: " + InboxAr.get(k).toString()
							+ "%   " + "To: " + outBoxAr.get(k).toString()
							+ "%");
					
					if (InboxAr.get(k) >= 80){
						action.setText("Action 1");
					}
					if (InboxAr.get(k) < 80 && InboxAr.get(k) >= 60){
						action.setText("Action 2");
					}
					if (InboxAr.get(k) < 60 && InboxAr.get(k) >= 40){
						action.setText("Action 3");
					}
					if (InboxAr.get(k) < 40 && InboxAr.get(k) >= 20){
						action.setText("Action 4");
					}
					if (InboxAr.get(k) < 20 && InboxAr.get(k) >= 0){
						action.setText("Action 5");
					}
					

				}
			});
		}
	}

	private void someStuff() {

		Cursor cursor = getContentResolver().query(Uri.parse("content://sms"),
				null, null, null, null);
		cursor.moveToFirst();

		for (x = 0; x < 100; x++) {

			body = cursor.getString(12); // message contents
			type = cursor.getString(9); // 1- incoming 2-outgoing 3-Drafts 4-Out
										// box 5- Failed 6- Queued

			int i = 0;
			id = cursor.getInt(0);// unique message id
			thread_id = cursor.getInt(1);// thread number of a
											// conversation(unique for that
											// conversation)
			address = cursor.getString(2);// Phone number from which the message
											// came

			name = getContactName(this, address);// Using function get contact
													// name to retrieve name of
													// the contact from contact
													// list
			// Using function get contact name to retrieve name of the contact
			// from contact list
			if (!numbersArray.contains(name)) {
				numbersArray.add(name);
			}
			g = numbersArray.indexOf(name);
			if (type.equals("1")) {

				setInBox(g, body);

			} else if (type.equals("2")) {

				setOutbox(g, body);

			}

			// use g to look through second array for outbox and third for inbox

			percentI();
			// use two arrays that run next to each other
			sall = "working"; // just a display to know whether its
								// working as needed
			all[i] = sall;
			i++;

			cursor.moveToNext();

			// final String x=msgData;

		}

	}

	private void percentI() {
		// TODO Auto-generated method stub

	}

	private void setInBox(int g2, String body2) {
		// TODO Auto-generated method stub
		inboxCount = 0.0;
		initalizeA(inboxA);
		inboxCount = inboxA.get(g);
		inboxCount = body2.length() + inboxCount;
		inboxA.set(g, inboxCount);
		// works

		// now for percents
		double total = inboxA.get(g) + outBoxA.get(g);
		meP = Math.ceil((inboxA.get(g) * 100) / total);

		InboxAr.set(g, meP);
		double themP1 = 100 - meP;
		outBoxAr.set(g, themP1);

	}

	private void setOutbox(int g2, String body2) {
		// TODO Auto-generated method stub
		outboxCount = 0.0;
		initalizeA(outBoxA);
		outboxCount = outBoxA.get(g);
		outboxCount = body2.length() + outboxCount;
		outBoxA.set(g, outboxCount);

		double total = inboxA.get(g) + outBoxA.get(g);
		themP = Math.ceil((outBoxA.get(g) * 100) / total);

		outBoxAr.set(g, themP);
		double themP1 = Math.ceil(100.0 - themP);
		InboxAr.set(g, themP1);

	}

	private void initalizeA(ArrayList<Double> inboxA2) {
		// TODO Auto-generated method stub
		inboxA.add((double) 0);
		inboxA.add((double) 0);

		InboxAr.add(0.0);
		InboxAr.add(0.0);

		outBoxA.add((double) 0);
		outBoxA.add((double) 0);

		outBoxAr.add(0.0);
		outBoxAr.add(0.0);

	}

	private String getContactName(Context context, String number) {

		String name = "Not Saved";

		// define the columns I want the query to return
		String[] projection = new String[] {
				ContactsContract.PhoneLookup.DISPLAY_NAME,
				ContactsContract.PhoneLookup._ID };

		// encode the phone number and build the filter URI
		Uri contactUri = Uri.withAppendedPath(
				ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(number));

		// query time
		Cursor cursor = context.getContentResolver().query(contactUri,
				projection, null, null, null);

		if (cursor != null) {
			if (cursor.moveToFirst()) {

				name = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
			}
			cursor.close();
		}
		return name;
	}

}
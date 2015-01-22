package com.sadcheese;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import com.sadcheese.surefire.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Coach extends Activity implements OnClickListener {
	TextView tvComp;
	ImageView ivCharm, ivLive, ivSpark, ivMins, ivSash;
	RelativeLayout charm, sash, spark, mins, live;
	Users p;
	boolean btMinsOn, btSashOn, btLiveOn, btCharmOn, btSparksOn;
	ProgressDialog pDialog;
	String minsPrice, charmPrice, sparkPrice, livePrice, sashaPrice;
	String minsEmail, charmEmail, sparkEmail, liveEmail, sashaEmail, numbMins,
			numbCharm, numbSpark, numbLive, numbSasha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.sadcheese.surefire.R.layout.final_listlayout);

		initialize();

		new CreateUser().execute();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Intent intent;
		switch (v.getId()) {

		case com.sadcheese.surefire.R.id.RLartofcharm:

			if (btCharmOn) {
				Bundle price = new Bundle();
				Bundle email = new Bundle();
				Bundle number = new Bundle();
				email.putString("email", charmEmail);
				price.putString("price", charmPrice);
				number.putString("number", numbCharm );

				intent = new Intent(Coach.this, Paying.class);
				intent.putExtras(price);
				intent.putExtras(email);
				intent.putExtras(number);
				startActivity(intent);
			}

			break;

		case R.id.RLlive:
			if (btLiveOn) {

				Bundle price = new Bundle();
				Bundle email = new Bundle();
				Bundle number = new Bundle();
				email.putString("email", liveEmail);
				price.putString("price", livePrice);
				number.putString("number", numbLive );


				intent = new Intent(Coach.this, Paying.class);
				intent.putExtras(price);
				intent.putExtras(email);
				intent.putExtras(number);
				startActivity(intent);
			}
			break;

		case R.id.RLminstry:
			if (btMinsOn) {
				Bundle price = new Bundle();
				Bundle email = new Bundle();
				Bundle number = new Bundle();
				email.putString("email", minsEmail);
				price.putString("price", minsPrice);
				number.putString("number", numbMins);


				intent = new Intent(Coach.this, Paying.class);
				intent.putExtras(price);
				intent.putExtras(email);
				intent.putExtras(number);
				startActivity(intent);
			}
			break;

		case R.id.RLsasha:
			if (btSashOn) {
				Bundle price = new Bundle();
				Bundle email = new Bundle();
				Bundle number = new Bundle();
				email.putString("email", sashaEmail);
				price.putString("price", sashaPrice);
				number.putString("number", numbSasha );


				intent = new Intent(Coach.this, Paying.class);
				intent.putExtras(price);
				intent.putExtras(email);
				intent.putExtras(number);
				startActivity(intent);
			}
			break;

		case R.id.RLsparks:
			if (btSparksOn) {
				Bundle price = new Bundle();
				Bundle email = new Bundle();
				Bundle number = new Bundle();
				email.putString("email", sparkEmail);
				price.putString("price", sparkPrice);
				number.putString("number", numbSpark );


				intent = new Intent(Coach.this, Paying.class);
				intent.putExtras(price);
				intent.putExtras(email);
				intent.putExtras(number);
				startActivity(intent);
			}
			break;

		}
	}

	private void initialize() {
		// TODO Auto-generated method stub
		tvComp = (TextView) findViewById(R.id.TVdesssy);

		ivCharm = (ImageView) findViewById(R.id.rbCharm);
		ivLive = (ImageView) findViewById(R.id.rbLive);
		ivSpark = (ImageView) findViewById(R.id.rbLSparks);
		ivMins = (ImageView) findViewById(R.id.rbMins);
		ivSash = (ImageView) findViewById(R.id.rbLSasha);

		btMinsOn = false;
		btSashOn = false;
		btLiveOn = false;
		btCharmOn = false;
		btSparksOn = false;

		charm = (RelativeLayout) findViewById(R.id.RLartofcharm);
		sash = (RelativeLayout) findViewById(R.id.RLsasha);
		spark = (RelativeLayout) findViewById(R.id.RLsparks);
		mins = (RelativeLayout) findViewById(R.id.RLminstry);
		live = (RelativeLayout) findViewById(R.id.RLlive);

		sash.setOnClickListener(this);
		spark.setOnClickListener(this);
		live.setOnClickListener(this);
		mins.setOnClickListener(this);
		charm.setOnClickListener(this);

		Drawable res = getResources().getDrawable(R.drawable.presence_online);

	}

	class CreateUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */

		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(Coach.this);
			pDialog.setMessage("Connecting...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		String data = "";
		TableLayout tl;
		TableRow tr;
		String label;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			final GetDataFromDB getdb = new GetDataFromDB();

			new Thread(new Runnable() {
				public void run() {
					data = getdb.getDataFromDB();
					System.out.println(data);

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							ArrayList<Users> users = parseJSON(data);
							getData(users);
						}

					});

				}
			}).start();

			return null;

		}

		// breaking though database
		private void getData(ArrayList<Users> users) {
			// TODO Auto-generated method stub
			label = "offline";
			for (Iterator i = users.iterator(); i.hasNext();) {

				Users p = (Users) i.next();

				pDialog.dismiss();

				if (p.getName().contains("Mins") && p.getPlace().contains("on")) {

					ivMins.setImageDrawable(getResources().getDrawable(
							R.drawable.presence_online));

					minsPrice = p.getPrice();
					minsEmail = p.getEmail();

					if (!p.getNumb1().contains("off")) {
						numbMins = p.getNumb1();

					}
					if (!p.getNumb1().contains("off")) {
						numbMins = p.getNumb2();
					}
					if (!p.getNumb1().contains("off")) {
						numbMins = p.getNumb3();
					}

					btMinsOn = true;

				}

			
			if (p.getName().contains("Charm") && p.getPlace().contains("on")) {

				if (!p.getNumb1().contains("off")) {
					numbCharm = p.getNumb1();
					
				}
				if (!p.getNumb2().contains("off")) {
					numbCharm = p.getNumb2();
				}
				if (!p.getNumb3().contains("off")) {
					numbCharm = p.getNumb3();
				}
				

				ivCharm.setImageDrawable(getResources().getDrawable(
						R.drawable.presence_online));
				btCharmOn = true;
				charmPrice = p.getPrice();
				charmEmail = p.getEmail();
				label = "Online " + charmEmail;
				

			}

			if (p.getName().contains("Sparks") && p.getPlace().contains("on")) {

				ivSpark.setImageDrawable(getResources().getDrawable(
						R.drawable.presence_online));

				if (!p.getNumb1().contains("off")) {
					numbSpark = p.getNumb1();
				
				}
				if (!p.getNumb2().contains("off")) {
					numbSpark = p.getNumb2();
				}
				if (!p.getNumb3().contains("off")) {
					numbSpark = p.getNumb3();
				}
				btSparksOn = true;
				sparkPrice = p.getPrice();
				sparkEmail = p.getEmail();

			}

			if (p.getName().contains("Sash") && p.getPlace().contains("on")) {

				ivSash.setImageDrawable(getResources().getDrawable(
						R.drawable.presence_online));

				
				btSashOn = true;
				sashaPrice = p.getPrice();
				sashaEmail = p.getEmail();
				
				if (!p.getNumb1().contains("off")) {
					numbSasha = p.getNumb1();
				
				}
				if (!p.getNumb2().contains("off")) {
					numbSasha = p.getNumb2();
				}
				if (!p.getNumb3().contains("off")) {
					numbSasha = p.getNumb3();
				}


			}
			if (p.getName().contains("Live") && p.getPlace().contains("on")) {

				ivLive.setImageDrawable(getResources().getDrawable(
						R.drawable.presence_online));

				btLiveOn = true;
				livePrice = p.getPrice();
				liveEmail = p.getEmail();

				if (!p.getNumb1().contains("off")) {
					numbLive = p.getNumb1();
				
				}
				if (!p.getNumb2().contains("off")) {
					numbLive = p.getNumb2();
				}
				if (!p.getNumb3().contains("off")) {
					numbLive = p.getNumb3();
				}

			}

			}
		
			pDialog.dismiss();
		}

		public ArrayList<Users> parseJSON(String result) {
			ArrayList<Users> users = new ArrayList<Users>();
			try {
				JSONArray jArray = new JSONArray(result);
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					Users user = new Users();
					user.setId(json_data.getInt("id"));
					user.setName(json_data.getString("name"));
					user.setPrice(json_data.getString("price"));
					user.setNumb1(json_data.getString("number1"));
					user.setNumb2(json_data.getString("number2"));
					user.setNumb3(json_data.getString("number3"));
					user.setEmail(json_data.getString("paypal"));
					user.setPlace(json_data.getString("status"));
					users.add(user);
				}
			} catch (JSONException e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
			}
			return users;

		}

	}

}

package com.sadcheese;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sadcheese.surefire.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class DatingFragment extends Fragment {
	Button podcast, web, advice;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_dating, container,
				false);
		web = (Button) rootView.findViewById(R.id.bWebsites);
		web.setText("Ebook");
		web.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myWebLink = new Intent(
						android.content.Intent.ACTION_VIEW);
				myWebLink.setData(Uri
						.parse("http://attractionalchemy.com"));
				startActivity(myWebLink);
			}
		});
		

	

		podcast = (Button) rootView.findViewById(R.id.bpodcast);

		podcast.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myWebLink = new Intent(
						android.content.Intent.ACTION_VIEW);
				myWebLink.setData(Uri
						.parse("http://pickuppodcast.com/category/podcasts/"));
				startActivity(myWebLink);
			}
		});
		
		advice = (Button) rootView.findViewById(R.id.bAdvice);
		advice.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), Coach.class);
				getActivity().startActivity(intent);
			}
		});

		return rootView;
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
	                user.setPlace(json_data.getString("place"));
	                users.add(user);
	            }
	        } catch (JSONException e) {
	            Log.e("log_tag", "Error parsing data " + e.toString());  
	        }
	        return users;
	    }
	 
}

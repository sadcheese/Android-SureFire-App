package com.sadcheese;


import com.sadcheese.surefire.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChallengeFragment extends Fragment {
	Button snore, looser, friendly;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_challenges,
				container, false);
		
		snore = (Button) rootView.findViewById(R.id.bSnorelax);
		snore.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { 
				Intent intent = new Intent(getActivity(), SnoreLax.class);
				getActivity().startActivity(intent);
			}
		});
		
		looser = (Button) rootView.findViewById(R.id.bLooser);
		looser.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), Looser.class);
				getActivity().startActivity(intent);
			}
		});
		friendly = (Button) rootView.findViewById(R.id.bFriendly);
		friendly.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), Friendly.class);
				getActivity().startActivity(intent);
		
				
			}
		});
		return rootView;
	}
	
}

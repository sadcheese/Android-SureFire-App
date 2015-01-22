package com.sadcheese.adapter;

import com.sadcheese.ChallengeFragment;
import com.sadcheese.DatingFragment;
import com.sadcheese.Texting;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return (Fragment) new Texting();
		case 1:
			// Games fragment activity
			return new ChallengeFragment();
		case 2:
			// Movies fragment activity
			return new DatingFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}

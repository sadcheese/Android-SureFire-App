package com.sadcheese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;




import com.sadcheese.surefire.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RunList extends Activity {

	ExpandableListView expListView;
	List<View> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	LayoutInflater inflater;
	TextView tvComp, tvDes;
	View view;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);

		Activity context = this;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		//

		LinearLayout layout = (LinearLayout) findViewById(R.id.mainlay);
		view = inflater.inflate(R.layout.final_listlayout, null);
		layout.addView(view);

		layout = (LinearLayout) findViewById(R.id.mainlay);
		tvComp = (TextView) findViewById(R.id.TVcomp);
		tvDes = (TextView) findViewById(R.id.TVdes);
		image = (ImageView) findViewById(R.id.IVicon);
		// first coach

		// second coach
	

	}

	/*
	 * Preparing the list data
	 */

}

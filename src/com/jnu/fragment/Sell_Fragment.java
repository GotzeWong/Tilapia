package com.jnu.fragment;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import com.jnu.chart.daily_price;
import com.jnu.tilapia_activity.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Sell_Fragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textView=new TextView(getActivity());
		textView.setText("敬请期待");
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(20);
		return textView;
	}
}

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

public class News_Fragment extends Fragment implements OnItemSelectedListener{
	View chartView;
	LinearLayout contentView;
	Spinner sp1,sp2;
	ArrayAdapter<CharSequence> adapter1,adapter2;
	ViewGroup.LayoutParams vlp;
	private OnClickListener showTagListener;
	private float oldX;
	private float oldY;
	public static int XOFFSET_L=40;
	public static int XOFFSET_R=150;
	public static int YOFFSET=150;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentView=(LinearLayout) inflater.inflate(R.layout.spinner, container, false);
		sp1=(Spinner) contentView.findViewById(R.id.spinner1);
		sp2=(Spinner) contentView.findViewById(R.id.spinner2);
		adapter1=ArrayAdapter.createFromResource(getActivity(), R.array.省份, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter1);
		sp2.setAdapter(adapter2);
		sp1.setOnItemSelectedListener(this);
		sp2.setOnItemSelectedListener(this);
		chartView=new daily_price().getchart(getActivity());
		vlp = new ViewGroup.LayoutParams( 
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
		chartView.setLayoutParams(vlp);
		chartView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					oldX = event.getX();
					oldY = event.getY();
				}
				return false;
			}
		});
		showTagListener = new OnClickListener() {

			public void onClick(View v) {

				GraphicalView graphicalView = (GraphicalView) v;
				// 获取当前点击点
				SeriesSelection seriesSelection = graphicalView
						.getCurrentSeriesAndPoint();

				if (seriesSelection == null) {
					return;
				} else {
					LayoutInflater inflater =LayoutInflater.from(getActivity());
					View tag_layout = inflater.inflate(R.layout.chart_tag, null);

					TextView tv = (TextView) tag_layout.findViewById(R.id.tag);
					tv.setText("您点击了"+seriesSelection.getXValue()+","+seriesSelection.getValue());

					ImageView arrow_left = (ImageView) tag_layout
							.findViewById(R.id.arrowL);
					ImageView arrow_right = (ImageView) tag_layout
							.findViewById(R.id.arrowR);

					int[] location = new int[2];
					graphicalView.getLocationOnScreen(location);
					int xPos = location[0];
					int yPos = location[1];
					int xOffset;
					
					final DisplayMetrics displayMetrics = new DisplayMetrics();
					getActivity().getWindowManager().getDefaultDisplay()
							.getMetrics(displayMetrics);
					final int width = displayMetrics.widthPixels;
					
					if (oldX <= width / 2) {
						arrow_right.setVisibility(View.INVISIBLE);
						arrow_left.setVisibility(View.VISIBLE);
						if (seriesSelection.getSeriesIndex() == 0) {
							tv.setBackgroundResource(R.drawable.icon_tag_bg_1);
							arrow_left
									.setBackgroundResource(R.drawable.icon_tag_1_left);
						} else {
							tv.setBackgroundResource(R.drawable.icon_tag_bg_2);
							arrow_left
									.setBackgroundResource(R.drawable.icon_tag_2_left);
						}
						xOffset = (int) (xPos + oldX - XOFFSET_L);
					} else {
						arrow_left.setVisibility(View.INVISIBLE);
						arrow_right.setVisibility(View.VISIBLE);
						if (seriesSelection.getSeriesIndex() == 0) {
							tv.setBackgroundResource(R.drawable.icon_tag_bg_1);
							arrow_right
									.setBackgroundResource(R.drawable.icon_tag_1_right);
						} else {
							tv.setBackgroundResource(R.drawable.icon_tag_bg_2);
							arrow_right
									.setBackgroundResource(R.drawable.icon_tag_2_right);
						}
						xOffset = (int) (xPos + oldX - XOFFSET_R);
					}

					Toast tag = new Toast(getActivity());

					int yOffset = (int) (yPos + oldY - YOFFSET);
					tag.setGravity(Gravity.LEFT | Gravity.TOP, xOffset, yOffset);
					tag.setDuration(Toast.LENGTH_SHORT);
					tag.setView(tag_layout);

					tag.show();

				}
			}
		};
		chartView.setOnClickListener(showTagListener);
		contentView.addView(chartView);
		return contentView;
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		if(parent.equals(sp1)){
			switch (position) {
			case 0:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
				break;
			case 1:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.河南, android.R.layout.simple_spinner_item);
				break;
			case 2:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.河北, android.R.layout.simple_spinner_item);
				break;
			case 3:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.安徽, android.R.layout.simple_spinner_item);
				break;
			case 4:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.湖北, android.R.layout.simple_spinner_item);
				break;
			case 5:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.新疆, android.R.layout.simple_spinner_item);
				break;
			default:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
				break;
			}
			adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp2.setAdapter(adapter2);
		}
		else{
			
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		adapter1=ArrayAdapter.createFromResource(getActivity(), R.array.省份, android.R.layout.simple_spinner_item);
		adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter1);
		sp2.setAdapter(adapter2);
	}
}

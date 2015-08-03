package com.jnu.tilapia_activity;

import java.util.ArrayList;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import com.jnu.chart.daily_price;
import com.jnu.data_provider.data_provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Detailed_information extends Activity {

	private LinearLayout layout_today,layout_chart_holder;
	private GraphicalView chart;
	private Spinner spinner1,spinner2;
	
	private Context context;
	private float oldX;
	private float oldY;
	public static int XOFFSET_L=40;
	public static int XOFFSET_R=150;
	public static int YOFFSET=150;
	private String[] size={"0.6~1.0","1.0~1.6",">1.6"},
					 date={"最近7天","最近15天","最近30天","最近1年"};
	private data_provider provider;
	private ArrayList<String> citylList;
	private ArrayList<double[]> priceList;
	private int city_tag;
	
	private OnTouchListener chart_ontouchListener;
	private OnClickListener showTagListener;
	private ArrayAdapter<String> adapter1,adapter2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_information_layout);
		context=this;
		provider=new data_provider();
		citylList=provider.get_citylist();
		priceList=provider.get_daily_price();
		Intent intent=getIntent();
		city_tag=intent.getIntExtra("name", -1);
		initview();
		initlistener();
		initadapter();
		addview();
		addadapter();
		
//		Toast.makeText(this, String.valueOf(intent.getIntExtra("name", -1)), Toast.LENGTH_SHORT).show();
	}
	void initview(){
		layout_today=(LinearLayout) findViewById(R.id.listcontent_holder);
		layout_chart_holder=(LinearLayout) findViewById(R.id.chart_holder);
		spinner1=(Spinner) findViewById(R.id.spinner_size);
		spinner2=(Spinner) findViewById(R.id.spinner_date);
	}
	
	void initlistener(){
		chart_ontouchListener=new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					oldX = event.getX();
					oldY = event.getY();
				}
				return false;
			}
		};
		showTagListener = new OnClickListener() {
			public void onClick(View v) {
				GraphicalView graphicalView = (GraphicalView) v;
				// 获取当前点击点
				SeriesSelection seriesSelection = graphicalView
						.getCurrentSeriesAndPoint();
				if (seriesSelection == null) {
					return;
				} else {
					LayoutInflater inflater =LayoutInflater.from(context);
					View tag_layout = inflater.inflate(R.layout.chart_tag, null);

					TextView tv = (TextView) tag_layout.findViewById(R.id.tag);
					tv.setText("价格:"+seriesSelection.getValue());

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
					
					getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
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

					Toast tag = new Toast(context);

					int yOffset = (int) (yPos + oldY - YOFFSET);
					tag.setGravity(Gravity.LEFT | Gravity.TOP, xOffset, yOffset);
					tag.setDuration(Toast.LENGTH_SHORT);
					tag.setView(tag_layout);

					tag.show();

				}
			}
		};
	}
	
	void initadapter(){
		adapter1=new ArrayAdapter<String>(context, 
				android.R.layout.simple_spinner_item, 
				size);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2=new ArrayAdapter<String>(context, 
				android.R.layout.simple_spinner_item, 
				date);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	
	void addview(){
		View view=getLayoutInflater().inflate(R.layout.listview_childview, null);
		View list_goto=view.findViewById(R.id.list_goto);
		list_goto.setVisibility(View.GONE);
		TextView textView1,textView2,textView3;
		TextView textView=(TextView) view.findViewById(R.id.list_text);
		textView1=(TextView) view.findViewById(R.id.text_min);
		textView2=(TextView) view.findViewById(R.id.text_mid);
		textView3=(TextView) view.findViewById(R.id.text_max);
		textView.setText(citylList.get(city_tag));
		textView1.setText(String.valueOf(priceList.get(city_tag)[0]));
		textView2.setText(String.valueOf(priceList.get(city_tag)[1]));
		textView3.setText(String.valueOf(priceList.get(city_tag)[2]));
		layout_today.addView(view);
		chart=(GraphicalView) new daily_price().getchart(context);
		chart.setOnTouchListener(chart_ontouchListener);
		chart.setOnClickListener(showTagListener);
		layout_chart_holder.addView(chart);
	}
	void addadapter(){
		spinner1.setAdapter(adapter1);
		spinner2.setAdapter(adapter2);
	}
}

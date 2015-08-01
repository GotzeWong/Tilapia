package com.jnu.tilapia_activity;

import java.util.ArrayList;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import com.jnu.chart.daily_price;
import com.jnu.data_provider.data_provider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class compare_fresh_price extends Activity{

	private Context context;
	private data_provider provider;
	
	private Spinner spinner1,spinner2,spinner_size;
	private Button button_add,button_ok;
	private TextView textView1,textView2,textView3;
	private GraphicalView chart;
	private LinearLayout layout;
	
	private OnItemSelectedListener spinner1_listener;
	private OnTouchListener chart_ontouchListener;
	private OnClickListener button_listener,button_listener_show,textview_hide,showTagListener;;
	private ArrayAdapter<String> adapter1,adapter2,adapter_size;
	
	private ArrayList<String> selected;
	private boolean vis[];
	private float oldX;
	private float oldY;
	public static int XOFFSET_L=40;
	public static int XOFFSET_R=150;
	public static int YOFFSET=150;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compare_fresh_price);
		context=this;
		selected=new ArrayList<String>();
		vis=new boolean[3];
		vis[0]=vis[1]=vis[2]=false;
		provider=new data_provider();
		initview();
		initlistener();
		initadapter();
		setadapter();
		setlistener();
		addview();
	}
	void initview(){
		spinner1=(Spinner) findViewById(R.id.spinner1);
		spinner2=(Spinner) findViewById(R.id.spinner2);
		spinner_size=(Spinner) findViewById(R.id.spinner_choose_size);
		button_add=(Button) findViewById(R.id.button_add_city);
		button_ok=(Button) findViewById(R.id.button_show_chart);
		textView1=(TextView) findViewById(R.id.selected_1);
		textView2=(TextView) findViewById(R.id.selected_2);
		textView3=(TextView) findViewById(R.id.selected_3);
		layout=(LinearLayout) findViewById(R.id.compare_layout);
		
	}
	void initlistener(){
		spinner1_listener=new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				adapter2=new ArrayAdapter<String>(context, 
						android.R.layout.simple_spinner_item,
						provider.get_citis().get(position)
						);
				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner2.setAdapter(adapter2);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		};
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
		button_listener=new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str=(String)spinner2.getSelectedItem();
				if(selected.contains(str)) {
					Toast.makeText(context, "已选择", Toast.LENGTH_SHORT).show();
					return;
				}
				if(selected.size()==3){
					Toast.makeText(context, "最多选三个", Toast.LENGTH_SHORT).show();
					return;
				}
				int now=0;
				while(now<3&&vis[now]) now++;
				if(now==3) Toast.makeText(context, "what?", Toast.LENGTH_SHORT).show();
				switch(now){
				case 0:
					textView1.setText(str);
					selected.add(str);
					textView1.setVisibility(View.VISIBLE);
					vis[0]=true;
					break;
				case 1:
					textView2.setText(str);
					selected.add(str);
					textView2.setVisibility(View.VISIBLE);
					vis[1]=true;
					break;
				case 2:
					textView3.setText(str);
					selected.add(str);
					textView3.setVisibility(View.VISIBLE);
					vis[2]=true;
					break;
				default:break;
				}
			}
		};
		textview_hide=new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textView=(TextView)v;
				selected.remove(textView.getText());
				textView.setVisibility(View.GONE);
				final int id=textView.getId(),
					id1=textView1.getId(),
					id2=textView2.getId(),
					id3=textView3.getId();
				if(id==id1){
					vis[0]=false;
				}
				else if(id==id2){
					vis[1]=false;
				}
				else if(id==id3){
					vis[2]=false;
				}
			}
		};
		button_listener_show=new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(chart!=null){
					layout.removeView(chart);
				}
				chart=(GraphicalView) new daily_price().getchart(context);
				chart.setOnTouchListener(chart_ontouchListener);
				chart.setOnClickListener(showTagListener);
				layout.addView(chart);
				Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
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
				provider.get_Provinces());
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2=new ArrayAdapter<String>(context, 
				android.R.layout.simple_spinner_item,
				provider.get_citis().get(0)
				);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter_size=new ArrayAdapter<String>(context, 
				android.R.layout.simple_spinner_item, 
				provider.get_size());
		adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	void setadapter(){
		spinner1.setAdapter(adapter1);
		spinner2.setAdapter(adapter2);
		spinner_size.setAdapter(adapter_size);
	}
	void setlistener(){
		spinner1.setOnItemSelectedListener(spinner1_listener);
		button_add.setOnClickListener(button_listener);
		button_ok.setOnClickListener(button_listener_show);
		textView1.setOnClickListener(textview_hide);
		textView2.setOnClickListener(textview_hide);
		textView3.setOnClickListener(textview_hide);
	}
	void addview(){
		chart=(GraphicalView) new daily_price().getchart(context);
		chart.setOnTouchListener(chart_ontouchListener);
		chart.setOnClickListener(showTagListener);
		layout.addView(chart);
	}
}

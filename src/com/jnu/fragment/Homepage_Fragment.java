package com.jnu.fragment;

import java.util.ArrayList;

import com.jnu.tilapia_activity.R;
import com.jnu.tilapia_activity.compare_fresh_price;
import com.jnu.adapter.homepage_listview_adapter;
import com.jnu.adapter.listview_more_adapter;
import com.jnu.data_provider.data_provider;
import com.jnu.data_provider.user_setting;

import android.app.AlertDialog;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class Homepage_Fragment extends Fragment {

	private Context context;
	private Button button_compare,button_warning;
	private EditText city_search_EditText;
	private ExpandableListView listView=null;
	private ViewGroup viewGroup;
	private InputMethodManager imm;
	
	private OnClickListener listener;
	private OnFocusChangeListener edit_listener;
	private OnKeyListener edit_KeyListener;
	private homepage_listview_adapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		container=(ViewGroup) inflater.inflate(R.layout.homepage_listview_holder, null);
		viewGroup=container;
		initview();
		initlistener();
		setadapter();
		setlistener();
		return container;
	}
	private void initview(){
		button_compare=(Button) viewGroup.findViewById(R.id.button_compare);
		button_warning=(Button) viewGroup.findViewById(R.id.button_warning);
		city_search_EditText=(EditText) viewGroup.findViewById(R.id.text_search);
		listView=(ExpandableListView) viewGroup.findViewById(R.id.expandable_listview);
	}
	private void initlistener(){
		adapter=new homepage_listview_adapter(context);
		listener=new OnClickListener() {
			@Override
			public void onClick(View v) {
				viewGroup.requestFocus();
				if(v.getId()==R.id.button_compare){
					Intent intent=new Intent(context, compare_fresh_price.class);
					startActivity(intent);
				}
				else{
					double min[]=new double[]{user_setting.min_fresh_price1,
											  user_setting.min_fresh_price2,
											  user_setting.min_fresh_price3},
						   max[]=new double[]{user_setting.max_fresh_price1,
											  user_setting.max_fresh_price2,
											  user_setting.max_fresh_price3};
					if(min[0]<0||min[1]<0||min[2]<0||
					   max[0]<0||max[1]<0||max[2]<0){
						new AlertDialog.Builder(context)
		   				   .setTitle("hint")
		   				   .setMessage("请到“其它”页面设置预警值")
		   				   .setPositiveButton("OK", null)
		   				   .show();
						return;
					}
					String outString="";
					String warning[]={" 0.6~1.0  （斤）预警:"," 1.0~1.6 （斤）预警"," >1.6 （斤）预警"	};
					data_provider provider=new data_provider();
					ArrayList<String> citylist=provider.get_citylist();
					ArrayList<double[]> price=provider.get_daily_price();
					String tString;
					for(int k=0;k<3;k++){
						tString="";
						for(int i=0;i<citylist.size();i++){
							if(price.get(i)[k]<min[k]||price.get(i)[k]>max[k])
								tString+=citylist.get(i)+",";
						}
						if(tString.length()!=0)
							outString+="\n"+warning[k]+"\n"+tString+"\n\n";
					}
					new AlertDialog.Builder(context)
	   				   .setTitle("warning~")
	   				   .setMessage(outString)
	   				   .setPositiveButton("OK", null)
	   				   .show();
				}
			}
		};
		edit_listener=new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					if(imm.isActive()){
						imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0 ); 
					}
				}
			}
		};
		edit_KeyListener=new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode==KeyEvent.KEYCODE_ENTER){
					InputMethodManager imm = (InputMethodManager)context.getSystemService(context.INPUT_METHOD_SERVICE);
					if(imm.isActive()){
						imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0 ); 
					}
					Editable content=city_search_EditText.getText();
					adapter.Update(content.toString());
				}
				return false;
			}
		};
	}
	private void setadapter(){
		listView.setAdapter(adapter);
	}
	private void setlistener(){
		button_compare.setOnClickListener(listener);
		button_warning.setOnClickListener(listener);
		city_search_EditText.setOnFocusChangeListener(edit_listener);
		city_search_EditText.setOnKeyListener(edit_KeyListener);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=getActivity();
		imm = (InputMethodManager)context.getSystemService(context.INPUT_METHOD_SERVICE);
	}
}

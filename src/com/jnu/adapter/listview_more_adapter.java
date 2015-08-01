package com.jnu.adapter;

import java.util.zip.Inflater;

import com.jnu.tilapia_activity.R;

import android.content.Context;
import android.support.v7.appcompat.R.string;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class listview_more_adapter extends BaseAdapter{
	private LayoutInflater mInflater;
	private String[] listStrings={"设置预警值","联系客服","联系客服","联系客服","联系客服","联系客服","关于..."};
	public listview_more_adapter(Context context){
		mInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return 7;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		if(position==2||position==5) return false;
		else return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position==2||position==5){
			return mInflater.inflate(R.layout.list_gap, parent, false);
		}
		View view=mInflater.inflate(R.layout.list_others_row, parent, false);
		TextView textView=(TextView) view.findViewById(R.id.listview_morefragment_item);
		textView.setText(listStrings[position]);
		if(position==0||position==3){
			textView.setBackgroundResource(R.drawable.item_first);
		}
		else if(position==6){
			textView.setBackgroundResource(R.drawable.item_one);
		}
		else{
			textView.setBackgroundResource(R.drawable.item_last);
		}
		return view;
	}
}

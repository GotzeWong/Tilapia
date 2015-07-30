package com.jnu.adapter;

import java.util.zip.Inflater;

import com.jnu.tilapia_activity.R;

import android.content.Context;
import android.support.v7.appcompat.R.string;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class listview_more_adapter extends BaseAdapter{
	private LayoutInflater mInflater;
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
		return mInflater.inflate(R.layout.list_others_row, parent, false);
	}
}

package com.jnu.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jnu.chart.daily_price;
import com.jnu.data_provider.data_provider;
import com.jnu.listenner.chart_change_to_detail_listenner;
import com.jnu.tilapia_activity.R;

public class homepage_listview_adapter extends BaseExpandableListAdapter{
	private LayoutInflater mInflater;
	ArrayList<String> list_item_all;
	ArrayList<String> list_item;
//	String list_text[]={"大宿迁","小六安","小安阳"};
    public homepage_listview_adapter(Context context){
        this.mInflater = LayoutInflater.from(context);
        list_item_all=new data_provider().get_citylist();
        list_item=list_item_all;
    }
    public void Update(String string){
    	Log.i("update", string);
    	if(string.length()==0){
    		list_item=list_item_all;
    		Log.i("update", string+"*");
    	}
    	else{
    		list_item=new ArrayList<String>();
    		if(list_item_all.contains(string))
    			list_item.add(string);
    		Log.i("update", string+"**");
    	}
    	notifyDataSetChanged();
    }
	@Override
	public int getGroupCount() {
		return list_item.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}
	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 1;
	}
	@Override
	public boolean hasStableIds() {
		return false;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		convertView=mInflater.inflate(R.layout.list_content, parent, false);
		TextView te=(TextView) convertView.findViewById(R.id.list_text);
		te.setText(list_item.get(groupPosition));
		ImageView iv=(ImageView) convertView.findViewById(R.id.list_goto);
		if(isExpanded){
			iv.setImageResource(R.drawable.goto_pull);
		}
		return convertView;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
//		convertView=mInflater.inflate(R.layout.chart, parent, false);
		LinearLayout ans=(LinearLayout) mInflater.inflate(R.layout.chart, parent, false);
		View view=new daily_price().getchart(parent.getContext());
		view.setOnClickListener(new chart_change_to_detail_listenner());
		view.setTag("宿迁");
		ans.addView(view);
		parent.requestFocus();
		return ans;
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}

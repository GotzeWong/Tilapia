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
	private ArrayList<String> list_item;
	private ArrayList<double[]> price;
	private String suffix=" 元",prefix=" ";
	private data_provider provider;
	private String showString;
    public homepage_listview_adapter(Context context){
        this.mInflater = LayoutInflater.from(context);
        provider=new data_provider();
        list_item=provider.get_citylist();
        price=provider.get_daily_price();
        showString="";
    }
    public void Update(String string){
    	showString=string;
    	notifyDataSetChanged();
    }
	@Override
	public int getGroupCount() {
		if(showString.length()==0)
			return list_item.size();
		else return 1;
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
		if(showString.length()!=0){
			groupPosition=list_item.indexOf(showString);
		}
		convertView=mInflater.inflate(R.layout.listview_childview, parent, false);
		
		TextView te,textView1,textView2,textView3;
		te=(TextView) convertView.findViewById(R.id.list_text);
		textView1=(TextView) convertView.findViewById(R.id.text_min);
		textView2=(TextView) convertView.findViewById(R.id.text_mid);
		textView3=(TextView) convertView.findViewById(R.id.text_max);
		te.setText(list_item.get(groupPosition));
		double[] mprice=price.get(groupPosition);
		textView1.setText(prefix+mprice[0]+suffix);
		textView2.setText(prefix+mprice[1]+suffix);
		textView3.setText(prefix+mprice[2]+suffix);
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

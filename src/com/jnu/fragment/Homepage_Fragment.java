package com.jnu.fragment;

import java.util.List;

import org.achartengine.ChartFactory;

import com.jnu.chart.daily_price;
import com.jnu.tilapia.R;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Homepage_Fragment extends Fragment {

	ExpandableListView listView=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		listView=new ExpandableListView(getActivity());
		listView.setGroupIndicator(null);
//		listView=(ExpandableListView) inflater.inflate(R.layout.homepage_layout, container, false);
		listView.setAdapter(new myListAdapter(getActivity()));
		return listView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				switch(position){
//				case 1:{
//					Intent intent=new Intent();
//					intent.setClass(getActivity(), Chart1.class);
//					startActivity(intent);
//				}
//				default:
//				}
//			}
//			
//		});
	}
	
	private class myListAdapter extends BaseExpandableListAdapter{
		
		private LayoutInflater mInflater;
		String list_text[]={"大宿迁","小六安","小安阳"};
        public myListAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
		@Override
		public int getGroupCount() {
			return 3;
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
			te.setText(list_text[groupPosition]);
			ImageView iv=(ImageView) convertView.findViewById(R.id.list_goto);
			if(isExpanded){
				iv.setImageResource(R.drawable.goto_pull);
			}
			return convertView;
		}
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
//			convertView=mInflater.inflate(R.layout.chart, parent, false);
			LinearLayout ans=(LinearLayout) mInflater.inflate(R.layout.chart, parent, false);
			ans.addView(new daily_price().getchart(getActivity()));
			return ans;
		}
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}
		
	}
}

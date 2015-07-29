package com.jnu.fragment;

import com.jnu.adapter.listview_more_adapter;
import com.jnu.listenner.more_listview_item_listenner;
import com.jnu.tilapia.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class More_Fragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.listview_more, container, false);
		ListView listView=(ListView) view.findViewById(R.id.list_view_more);
		listView.setAdapter(new listview_more_adapter(getActivity()));
		listView.setOnItemClickListener(new more_listview_item_listenner());
		return view;
	}
}

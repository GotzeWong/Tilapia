package com.jnu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Import_Fragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textView=new TextView(getActivity());
		textView.setText("敬请期待");
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(20);
		return textView;
	}
}

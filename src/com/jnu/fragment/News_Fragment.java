package com.jnu.fragment;

import com.jnu.chart.daily_price;
import com.jnu.tilapia_activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class News_Fragment extends Fragment implements OnItemSelectedListener{
	View chartView;
	LinearLayout contentView;
	Spinner sp1,sp2;
	ArrayAdapter<CharSequence> adapter1,adapter2;
	ViewGroup.LayoutParams vlp;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentView=(LinearLayout) inflater.inflate(R.layout.spinner, container, false);
		sp1=(Spinner) contentView.findViewById(R.id.spinner1);
		sp2=(Spinner) contentView.findViewById(R.id.spinner2);
		adapter1=ArrayAdapter.createFromResource(getActivity(), R.array.省份, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter1);
		sp2.setAdapter(adapter2);
		sp1.setOnItemSelectedListener(this);
		sp2.setOnItemSelectedListener(this);
		chartView=new daily_price().getchart(getActivity());
		vlp = new ViewGroup.LayoutParams( 
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
		chartView.setLayoutParams(vlp);
		contentView.addView(chartView);
		return contentView;
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		if(parent.equals(sp1)){
			switch (position) {
			case 0:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
				break;
			case 1:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.河南, android.R.layout.simple_spinner_item);
				break;
			case 2:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.河北, android.R.layout.simple_spinner_item);
				break;
			case 3:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.安徽, android.R.layout.simple_spinner_item);
				break;
			case 4:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.湖北, android.R.layout.simple_spinner_item);
				break;
			case 5:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.新疆, android.R.layout.simple_spinner_item);
				break;
			default:
				adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
				break;
			}
			adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp2.setAdapter(adapter2);
		}
		else{
			
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		adapter1=ArrayAdapter.createFromResource(getActivity(), R.array.省份, android.R.layout.simple_spinner_item);
		adapter2=ArrayAdapter.createFromResource(getActivity(), R.array.江苏, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter1);
		sp2.setAdapter(adapter2);
	}
}

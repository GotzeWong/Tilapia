package com.jnu.listenner;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class more_listview_item_listenner implements OnItemClickListener{

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch(position){
		case 6:
			new AlertDialog.Builder(parent.getContext())
						   .setTitle("关于")
						   .setMessage("Tilapia\n哈哈哈")
						   .setPositiveButton("OK", null)
						   .show();
			break;
		default:
			new AlertDialog.Builder(parent.getContext())
			   .setTitle("Tilapia")
			   .setMessage("逗逼")
			   .setPositiveButton("OK", null)
			   .show();
			break;
		}
	}
}

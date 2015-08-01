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
		case 0:
			new AlertDialog.Builder(parent.getContext())
						   .setTitle("Tilapia")
						   .setMessage("敬请期待")
						   .setPositiveButton("OK", null)
						   .show();
			
			break;
		case 6:
			new AlertDialog.Builder(parent.getContext())
						   .setTitle("关于")
						   .setMessage("开发单位:\n"
						   			 + "\t江南大学 物联网工程学院\n"
						   			 + "版本号：1.0\n")
						   .setPositiveButton("OK", null)
						   .show();
			break;
		default:
			new AlertDialog.Builder(parent.getContext())
						   .setTitle("Tilapia")
						   .setMessage("Tel: 0512-88888888")
						   .setPositiveButton("OK", null)
						   .show();
			break;
		}
	}
}

package com.jnu.listenner;

import com.jnu.tilapia_activity.Detailed_information;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class chart_change_to_detail_listenner implements OnClickListener{

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(v.getContext(), Detailed_information.class);
		v.getContext().startActivity(intent);
	}

}

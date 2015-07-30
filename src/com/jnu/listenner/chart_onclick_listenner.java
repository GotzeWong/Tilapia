package com.jnu.listenner;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class chart_onclick_listenner implements OnClickListener{

	@Override
	public void onClick(View v) {
		GraphicalView gv = (GraphicalView) v;
		SeriesSelection ss = gv.getCurrentSeriesAndPoint();
		if (ss == null) return ;  
        double[] point = new double[]{ss.getXValue(),ss.getValue()};
        Toast.makeText(v.getContext().getApplicationContext(), ""+point[0]+","+point[1],1).show();
	}
}

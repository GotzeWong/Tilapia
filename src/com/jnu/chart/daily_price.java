package com.jnu.chart;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

public class daily_price extends AbstractDemoChart{
	
	@Override
	public String getName() {
		return "罗非鱼价格变化图";
	}

	@Override
	public String getDesc() {
		return "罗非鱼价格变化图(test)";
	}

	@Override
	public Intent execute(Context context) {
		String[] titles = new String[] { "塘口价", "批发价", "零售价"};
		List<double[]> x = new ArrayList<double[]>();
		List<double[]> values = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
		      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		values.add(new double[] { 4.9, 5.1, 4.7, 4.8, 4.8, 5.0, 5.3, 5.6, 5.4, 5.5, 5.3, 5.2 });
		values.add(new double[] { 5.3, 5.3, 4.8, 4.9, 4.9, 5.6, 5.9, 5.9, 6.0, 5.7, 5.7, 5.8 });
		values.add(new double[] { 6.0, 6.2, 6.4, 6.2, 6.0, 6.5, 6.2, 6.4, 6.6, 6.4, 6.2, 6.5 });
		int[] colors = new int[] { Color.RED, Color.GREEN, Color.BLUE };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE};
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
		}
		setChartSettings(renderer, "罗非鱼价格变化图", "月份", "单位：元/公斤", 0, 12.5, 0.5, 9.5, Color.RED, Color.RED);
		renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    renderer.setZoomButtonsVisible(true);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setPanEnabled(true);
	    renderer.setPanLimits(new double[] { 0, 12, 0, 10 });
	    renderer.setZoomLimits(new double[] { 0, 12, 0, 10 });
	    
	    XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
	    Intent intent = ChartFactory.getLineChartIntent(context, dataset, renderer,
	            "罗非鱼价格变化图");
	    return intent;
	}
	
	public View getchart(Context context){
		String[] titles = new String[] { "塘口价", "批发价", "零售价"};
		List<double[]> x = new ArrayList<double[]>();
		List<double[]> values = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
		      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		values.add(new double[] { 4.9, 5.1, 4.7, 4.8, 4.8, 5.0, 5.3, 5.6, 5.4, 5.5, 5.3, 5.2 });
		values.add(new double[] { 5.3, 5.3, 4.8, 4.9, 4.9, 5.6, 5.9, 5.9, 6.0, 5.7, 5.7, 5.8 });
		values.add(new double[] { 6.0, 6.2, 6.4, 6.2, 6.0, 6.5, 6.2, 6.4, 6.6, 6.4, 6.2, 6.5 });
		int[] colors = new int[] { Color.RED, Color.GREEN, Color.BLUE };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE};
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
		}
		setChartSettings(renderer, "罗非鱼价格变化图", "月份", "单位：元/公斤", 0, 12.5, 0.5, 9.5, Color.RED, Color.RED);
		renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    renderer.setClickEnabled(true);
	    renderer.setSelectableBuffer(30);
//	    renderer.setZoomButtonsVisible(true);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setPanEnabled(true);
	    renderer.setPanLimits(new double[] { 0, 12, 0, 10 });
	    renderer.setZoomLimits(new double[] { 0, 12, 0, 10 });
	    
	    XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
	    View view=ChartFactory.getLineChartView(context, dataset, renderer);
	    return view;
	}
	
}

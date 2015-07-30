package com.jnu.tilapia_activity;

import com.jnu.adapter.FragmentAdapter;

import android.app.ActionBar;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class MainActivity extends FragmentActivity implements OnClickListener{
	public static final int TAB_HOME = 0;
	public static final int TAB_PAGE1 = 1;
	public static final int TAB_PAGE2 = 2;
	public static final int TAB_PAGE3 = 3;
	public static final int TAB_PAGE4 = 4;
	
	private ViewPager viewPager;
	private RadioButton main_tab_home, main_tab_page1, main_tab_page2,
			main_tab_page3, main_tab_page4;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_main);
		initView();
		addListener();
	}
	
	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		main_tab_home = (RadioButton) findViewById(R.id.main_tab_home);
		main_tab_page1 = (RadioButton) findViewById(R.id.main_tab_page1);
		main_tab_page2 = (RadioButton) findViewById(R.id.main_tab_page2);
		main_tab_page3 = (RadioButton) findViewById(R.id.main_tab_page3);
		main_tab_page4 = (RadioButton) findViewById(R.id.main_tab_page4);
		main_tab_home.setOnClickListener(this);
		main_tab_page1.setOnClickListener(this);
		main_tab_page2.setOnClickListener(this);
		main_tab_page3.setOnClickListener(this);
		main_tab_page4.setOnClickListener(this);
		
		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_tab_home:
			viewPager.setCurrentItem(TAB_HOME);
			break;
		case R.id.main_tab_page1:
			viewPager.setCurrentItem(TAB_PAGE1);
			break;
		case R.id.main_tab_page2:
			viewPager.setCurrentItem(TAB_PAGE2);
			break;
		case R.id.main_tab_page3:
			viewPager.setCurrentItem(TAB_PAGE3);
			break;
		case R.id.main_tab_page4:
			viewPager.setCurrentItem(TAB_PAGE4);
			break;

		default:
			break;
		}		
	}
	
	private void addListener() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int id) {
				switch (id) {
				case TAB_HOME:
					main_tab_home.setChecked(true);
					break;
				case TAB_PAGE1:
					main_tab_page1.setChecked(true);
					break;
				case TAB_PAGE2:
					main_tab_page2.setChecked(true);
					break;
				case TAB_PAGE3:
					main_tab_page3.setChecked(true);
					break;
				case TAB_PAGE4:
					main_tab_page4.setChecked(true);
					break;

				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}
}

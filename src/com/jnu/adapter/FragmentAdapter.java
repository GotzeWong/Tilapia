package com.jnu.adapter;

import com.jnu.fragment.News_Fragment;
import com.jnu.fragment.CN_Market_Fragment;
import com.jnu.fragment.Foreign_Fragment;
import com.jnu.fragment.Homepage_Fragment;
import com.jnu.fragment.More_Fragment;
import com.jnu.tilapia_activity.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{
	public final static int TAB_COUNT = 5;
	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int id) {
		switch (id) {
		case MainActivity.TAB_HOME:
			Homepage_Fragment homeFragment = new Homepage_Fragment();
			return homeFragment;
		case MainActivity.TAB_PAGE1:
			CN_Market_Fragment categoryFragment = new CN_Market_Fragment();
			return categoryFragment;
		case MainActivity.TAB_PAGE2:
			Foreign_Fragment carFragment = new Foreign_Fragment();
			return carFragment;
		case MainActivity.TAB_PAGE3:
			News_Fragment buyFragment = new News_Fragment();
			return buyFragment;
		case MainActivity.TAB_PAGE4:
			More_Fragment moreFragment = new More_Fragment();
			return moreFragment;
		}
		return null;
	}

	@Override
	public int getCount() {
		return TAB_COUNT;
	}
}


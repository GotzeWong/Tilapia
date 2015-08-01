package com.jnu.adapter;

import com.jnu.fragment.Sell_Fragment;
import com.jnu.fragment.Import_Fragment;
import com.jnu.fragment.Wholesale_Fragment;
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
			Import_Fragment categoryFragment = new Import_Fragment();
			return categoryFragment;
		case MainActivity.TAB_PAGE2:
			Wholesale_Fragment carFragment = new Wholesale_Fragment();
			return carFragment;
		case MainActivity.TAB_PAGE3:
			Sell_Fragment buyFragment = new Sell_Fragment();
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


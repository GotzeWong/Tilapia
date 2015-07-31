package com.jnu.tilapia_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Detailed_information extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		Toast.makeText(this, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
	}
}

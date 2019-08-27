package com.example.goldsdktest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.safegold.SafeGoldModule;
import com.sg.tapzo.ui.BuyGoldActivity;
import com.sg.tapzo.ui.DisclaimerActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// init your app
		App.init(getApplicationContext());

		Button safegoldButton = (Button) findViewById(R.id.safegoldButton);

		safegoldButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		Intent i = new Intent(MainActivity.this, DisclaimerActivity.class);
		startActivity(i);
	}
}

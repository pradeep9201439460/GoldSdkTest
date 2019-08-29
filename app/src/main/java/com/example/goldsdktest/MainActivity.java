package com.example.goldsdktest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.safegold.SafeGoldModule;
import com.sg.tapzo.ui.BuyGoldActivity;
import com.sg.tapzo.ui.DisclaimerActivity;
import com.sg.tapzo.ui.KycInterface;
import com.sg.tapzo.ui.PaymentInitInterface;


public class MainActivity extends AppCompatActivity implements KycInterface, PaymentInitInterface {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void toSafeGoldDisclaimerActivity(View view) {
		// Initialize SafeGold Module
		SafeGoldModule.initSDK("https://partners-staging.safegold.com", "1779effef2e29102fb1d1aee1bee5447")
				.initUser("Dhaval Maru", "9619769676", "400602", "dhavalbright@gmail.com", "USER0001")
				.initKey("Public Key Here...")
				.initSecret("Private Key Here...")
				.registerKycInterface(this)
				.registerPaymentInitInterface(this);


		// Open disclaimer activity
		Intent i = new Intent(MainActivity.this, DisclaimerActivity.class);
		startActivity(i);
	}

	@Override
	public void onKycError(String partnerRefId) {
		Log.i("Federal Bank", "KYC Error Encountered For PartnerRefId: " + partnerRefId);
	}

	@Override
	public void onPaymentInit(double amount, String transactionId, String signature) {
		Log.i("Federal Bank", "Initialize payment of Rs. " + amount + " for transaction no. " + transactionId);
	}
}

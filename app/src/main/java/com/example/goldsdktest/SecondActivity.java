package com.example.goldsdktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.safegold.InitializationException;
import com.safegold.SafeGoldModule;
import com.safegold.User;
import com.safegold.interfaces.HeartBeatInterface;
import com.safegold.interfaces.InvoiceInitInterface;
import com.safegold.interfaces.PaymentInitInterface;
import com.safegold.models.FetchInvoice;
import com.safegold.models.data.Address;
import com.sg.tapzo.ui.KycInterface;

public class SecondActivity extends AppCompatActivity implements KycInterface, HeartBeatInterface, PaymentInitInterface, InvoiceInitInterface {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// Initialize SafeGold Module
				SafeGoldModule.initSDK("https://partners-staging.safegold.com", "1779effef2e29102fb1d1aee1bee5447")
						.initUser("Dhaval Maru", "9619769676", "dhavalbright@gmail.com", "USER0001", new Address(
								"A-401, Goregaon East", "Near KFC", "Mumbai", "Maharashtra", "401609"
						)).initParentActivity(SecondActivity.class)
						.registerHeartBeatInterface(SecondActivity.this)
						.registerKycInterface(SecondActivity.this)
						.registerPaymentInitInterface(SecondActivity.this)
						.registerInvoiceInitInterface(SecondActivity.this)
						.launchSafeGoldApp(SecondActivity.this);
			}
		}).start();
	}

	@Override
	public void onKycError(String partnerRefId) {
		// handle if KYC error is thrown
	}

	@Override
	public void onHandleHeartBeat() {
		// handle heartbeat of the app here
		// reset timers so that the app does not log out
	}

	@Override
	public void onBuyTransaction(String partnerRefId, int txId, float amount, float buyGoldRate, float goldWeight, float netValue, float gstValue, float totalAmount) {
		// handle a payment towards buy transaction

		try {
			// Passing a dummy payment successful for the transaction id
			SafeGoldModule.getInstance().updateBuyTransaction(partnerRefId, txId, "BANKREFNO0001", "Success");
		} catch (InitializationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSellTransaction(String partnerRefId, int txId, float amount, float goldSellRate, float goldWeight, float netValue, float totalAmount) {
		// handle a payment towards sell transaction

		// after the payment is complete make a call to
		// SafeGoldModule.getInstance().updateSellTransaction(partnerRefId, txId, "Success/Failure");
	}

	@Override
	public void onRedeemTransaction(String partnerRefId, int txId, String itemName, float amount, float goldWeight, float makingCharges, float totalAmount) {
		// handle payment towards redeem transaction

		// after payment is complete make a call to
		// SafeGoldModule.getInstance().updateRedeemTransaction(partnerRefId, txId, "Bank Reference Number", "Success/Failure");
	}

	@Override
	public void updateInvoice(String partnerRefId, int txId, String invoiceId) {
		// a call will be made to this method after any buy sell or redeem transaction is completed
		// an invoice id will be generated on successful transaction
		// if the invoice id is null then the transaction is failed


		// Additional step required only for a successful sell transaction
		// once federal system generates a bank reference number, make a call to
		// SafeGoldModule.getInstance().updateSellBankRefNumber(partnerRefId, txId, bankRefNumber);


		try {
			// Invoice Link
			User user = SafeGoldModule.getInstance().getUser();
			FetchInvoice invoice = user.fetchInvoice(txId);
			Log.i("Invoice Link", invoice.link);

		} catch (InitializationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}

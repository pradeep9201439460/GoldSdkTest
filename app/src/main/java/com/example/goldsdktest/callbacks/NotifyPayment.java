package com.example.goldsdktest.callbacks;

import android.content.Context;
import android.widget.Toast;

import com.sg.tapzo.ui.SDKPaymentCommunicationInterface;

public class NotifyPayment implements SDKPaymentCommunicationInterface {

    private Context context;

    private NotifyPayment() {}

    public NotifyPayment(Context context) {
        this.context = context;
    }

    @Override
    public void onPaymentInit(double amount, String txId, String signature) {
        String message = "Payment for the amount of Rs. " + amount + " has been processed successfully. Your transaction ID is " + txId + ".";
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}

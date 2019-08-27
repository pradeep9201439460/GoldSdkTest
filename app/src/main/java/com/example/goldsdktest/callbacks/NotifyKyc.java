package com.example.goldsdktest.callbacks;

import android.content.Context;
import android.widget.Toast;

import com.sg.tapzo.ui.CheckKYCInterface;

public class NotifyKyc implements CheckKYCInterface {
    private Context context;

    private NotifyKyc() {}

    public NotifyKyc(Context context) {
        this.context = context;
    }

    @Override
    public void onKYCError(int i) {
        String message = "Code [" + i + "]: KYC limit reached. Visit nearest branch for updating your KYC.";
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}

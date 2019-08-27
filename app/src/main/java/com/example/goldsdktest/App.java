package com.example.goldsdktest;

import android.content.Context;

import com.example.goldsdktest.callbacks.NotifyKyc;
import com.example.goldsdktest.callbacks.NotifyPayment;
import com.safegold.SafeGoldModule;

final class App {

    private NotifyPayment notifyPayment;
    private NotifyKyc notifyKyc;
    private static App instance = null;

    private App() {}

    private App(Context context) {
        // create instances of your implementation
        notifyPayment = new NotifyPayment(context);
        notifyKyc = new NotifyKyc(context);

        initSafeGold();
    }

    private void initSafeGold() {
        SafeGoldModule.registerOnPaymentInit(notifyPayment);
        SafeGoldModule.registerCheckKYCInterface(notifyKyc);
    }

    public static App init(Context context) {

        if (App.instance == null) {
            App.instance = new App(context);
        }

        return App.instance;
    }
}

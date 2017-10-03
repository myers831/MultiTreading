package com.example.admin.multitreading;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by Admin on 10/3/2017.
 */

public class MyRunnable implements Runnable {

    TextView textView;
    Handler handler = new Handler(Looper.getMainLooper());

    public MyRunnable(TextView textView) {
        this.textView = textView;
    }

    public static final String TAG = "MyRunnable";
    int i;
    @Override
    public void run() {
        for(i = 0; i <= 20; i++){

            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.valueOf(i));
                }
            });

            Log.d(TAG, "run: " + i + "Thread: " + Thread.currentThread());
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

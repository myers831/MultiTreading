package com.example.admin.multitreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Admin on 10/3/2017.
 */

public class MyThread extends Thread {

    public static final String TAG = "MyThread";

    Handler handler;

    public MyThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        int total = 0;

        for(int i = 0; i < 5; i++){
            Log.d(TAG, "run: " + i + "Thread: " + Thread.currentThread());
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            total = total + i;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("total", total);

        Message message = new Message();
        message.setData(bundle);

        handler.sendMessage(message);


        EventBus.getDefault().post(new HelloEvent(String.valueOf(total)));
    }
}

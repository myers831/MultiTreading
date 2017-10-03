package com.example.admin.multitreading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Handler.Callback{

    private TextView tvResults;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResults = (TextView) findViewById(R.id.tvResults);
    }

    public void creatingThreads(View view) {
        switch (view.getId()){
            case R.id.btnUsingRunnable:
                MyRunnable myRunnable = new MyRunnable(tvResults);

                Thread thread = new Thread(myRunnable);
                thread.start();

                break;

            case R.id.btnUsingThread:

                Handler handler = new Handler(this);

                Handler handler1 = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        int total = msg.getData().getInt("total");
                        String totalString = String.valueOf(total);
                        Toast.makeText(MainActivity.this, totalString, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                MyThread myThread = new MyThread(handler);
                Toast.makeText(this, "Running", Toast.LENGTH_SHORT).show();
                myThread.start();
                break;

            case R.id.btnUsingAsyncTask:
                MyAsyncTask myAsyncTask = new MyAsyncTask(tvResults);
                myAsyncTask.execute();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHelloEvent(HelloEvent helloEvent){
        Toast.makeText(this, "Eventbus: " + helloEvent.getData(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "EventBus: " + helloEvent.getData());
    }

    @Override
    public boolean handleMessage(Message msg) {
        int total = msg.getData().getInt("total");
        tvResults.setText(String.valueOf(total));
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

package com.blazeey.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Random random = new Random();
    Runnable positionRunnable = new Runnable() {
        @Override
        public void run() {
            customView.updatePosition();
            handler.postDelayed(positionRunnable,100);
        }
    };

    Runnable colorRunnable = new Runnable() {
        @Override
        public void run() {
            customView.updateColor();
            handler.postDelayed(colorRunnable, 200);
        }
    };

    CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customView = new CustomView(this);
        setContentView(customView);
        handler.postDelayed(colorRunnable,500);
        handler.postDelayed(positionRunnable,500);
    }
}

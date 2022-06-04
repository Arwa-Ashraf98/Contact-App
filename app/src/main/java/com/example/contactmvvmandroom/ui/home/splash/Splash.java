package com.example.contactmvvmandroom.ui.home.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactmvvmandroom.R;
import com.example.contactmvvmandroom.databinding.ActivitySplashBinding;
import com.example.contactmvvmandroom.ui.home.main.MainActivity;

public class Splash extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
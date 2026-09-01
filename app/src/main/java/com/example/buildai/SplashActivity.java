package com.example.buildai;

/* ==========================================================
 *  BUILD STUDIO CALCULATOR PRO - SPLASH / OPEN SCREEN 🚀
 * ========================================================== */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    private static final int SPLASH_DELAY = 2200; // 2.2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Handler to delay launching MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close SplashActivity so user cannot return to it via Back button
            }
        }, SPLASH_DELAY);
    }
}

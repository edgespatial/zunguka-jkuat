package com.edgespatial.zunguka.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edgespatial.zunguka.R;
import com.mg.surblime.activities.SurblimeActivity;

/**
 * Created by moses on 6/21/18.
 */

public class SplashActivity extends SurblimeActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public int getStyleTheme() {
        return R.style.AppTheme_Splash;
    }
}

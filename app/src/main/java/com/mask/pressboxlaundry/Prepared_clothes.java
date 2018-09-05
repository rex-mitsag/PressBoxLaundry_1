package com.mask.pressboxlaundry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mask.pressboxlaundry.R;

public class Prepared_clothes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepared_clothes);
    }

    public void Home_func(View view) {
        finish();
    }
}

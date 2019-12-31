package com.pyd.postuciapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pyd.postuciapp.R;

public class TestIntroductionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_heading);

        findViewById(R.id.test_heading_understood_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestIntroductionActivity.this, TestEIEActivity.class);
                startActivity(intent);
            }
        });
    }
}

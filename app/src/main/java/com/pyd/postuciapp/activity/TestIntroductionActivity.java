package com.pyd.postuciapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Test;

public class TestIntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_heading);

        final Test.TestType testType = (Test.TestType) getIntent().getSerializableExtra("type");

        switch (testType) {
            case ESCALA_IMPACTO_EVENTO:
                ((AppCompatTextView)findViewById(R.id.test_heading_heading)).setText(getResources().getString(R.string.test_heading_eie));
                break;
        }

        findViewById(R.id.test_heading_understood_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                switch (testType) {
                    case ESCALA_IMPACTO_EVENTO:
                        intent = new Intent(TestIntroductionActivity.this, TestEscalaImpactoEventoActivity.class);
                }

                startActivity(intent);
                finish();
            }
        });
    }
}

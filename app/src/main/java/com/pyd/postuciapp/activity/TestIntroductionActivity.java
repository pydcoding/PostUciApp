package com.pyd.postuciapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
        AppCompatTextView heading = findViewById(R.id.test_heading_heading);

        switch (testType) {
            case ESCALA_IMPACTO_EVENTO:
                heading.setText(getResources().getString(R.string.test_heading_eie));
                break;
            case HAD:
                heading.setText(getResources().getString(R.string.test_heading_had));
                break;
        }

        heading.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.test_heading_understood_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                switch (testType) {
                    case ESCALA_IMPACTO_EVENTO:
                        intent = new Intent(TestIntroductionActivity.this, TestEscalaImpactoEventoActivity.class);
                        break;
                    case HAD:
                        intent = new Intent(TestIntroductionActivity.this, TestHadActivity.class);
                        break;
                }

                startActivity(intent);
                finish();
            }
        });
    }
}

package com.pyd.postuciapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.pyd.postuciapp.R;

public class TestEIEActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_escala_impacto_evento);

        ((AppCompatTextView)findViewById(R.id.test_first_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_1));
        ((AppCompatTextView)findViewById(R.id.test_second_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_2));
        ((AppCompatTextView)findViewById(R.id.test_third_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_3));
        ((AppCompatTextView)findViewById(R.id.test_fourth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_4));
        ((AppCompatTextView)findViewById(R.id.test_fifth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_5));
        ((AppCompatTextView)findViewById(R.id.test_sixth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_6));
        ((AppCompatTextView)findViewById(R.id.test_seventh_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_7));
        ((AppCompatTextView)findViewById(R.id.test_eighth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_8));
        ((AppCompatTextView)findViewById(R.id.test_ninth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_9));
        ((AppCompatTextView)findViewById(R.id.test_tenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_10));
        ((AppCompatTextView)findViewById(R.id.test_eleventh_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_11));
        ((AppCompatTextView)findViewById(R.id.test_twelfth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_12));
        ((AppCompatTextView)findViewById(R.id.test_thirteenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_13));
        ((AppCompatTextView)findViewById(R.id.test_fourteenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_14));
        ((AppCompatTextView)findViewById(R.id.test_fifteenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_15));
    }
}

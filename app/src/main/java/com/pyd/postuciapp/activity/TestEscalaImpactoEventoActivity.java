package com.pyd.postuciapp.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.bean.TestEscalaImpactoEvento;
import com.pyd.postuciapp.constants.Constants;
import com.pyd.postuciapp.utils.StorageManager;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.HashMap;
import java.util.Map;

public class TestEscalaImpactoEventoActivity extends AppCompatActivity {

    private enum ButtonState {
        UNDEFINED,
        NEVER,
        RARELY,
        SOMETIMES,
        OFTEN
    }

    private AppCompatButton mSubmitButton;

    private MultiStateToggleButton[] mButtons = new MultiStateToggleButton[15];
    private ButtonState[] mButtonsState = new ButtonState[15];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_escala_impacto_evento);

        mSubmitButton = findViewById(R.id.test_submit_button);
        mSubmitButton.setEnabled(false);
        mSubmitButton.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.LIGHTEN);

        ((AppCompatTextView) findViewById(R.id.test_first_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_1));
        ((AppCompatTextView) findViewById(R.id.test_second_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_2));
        ((AppCompatTextView) findViewById(R.id.test_third_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_3));
        ((AppCompatTextView) findViewById(R.id.test_fourth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_4));
        ((AppCompatTextView) findViewById(R.id.test_fifth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_5));
        ((AppCompatTextView) findViewById(R.id.test_sixth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_6));
        ((AppCompatTextView) findViewById(R.id.test_seventh_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_7));
        ((AppCompatTextView) findViewById(R.id.test_eighth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_8));
        ((AppCompatTextView) findViewById(R.id.test_ninth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_9));
        ((AppCompatTextView) findViewById(R.id.test_tenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_10));
        ((AppCompatTextView) findViewById(R.id.test_eleventh_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_11));
        ((AppCompatTextView) findViewById(R.id.test_twelfth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_12));
        ((AppCompatTextView) findViewById(R.id.test_thirteenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_13));
        ((AppCompatTextView) findViewById(R.id.test_fourteenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_14));
        ((AppCompatTextView) findViewById(R.id.test_fifteenth_question).findViewById(R.id.test_template_question)).setText(getResources().getString(R.string.test_eie_question_15));

        mButtons[0]  = findViewById(R.id.test_first_question).findViewById(R.id.test_template_button);
        mButtons[1]  = findViewById(R.id.test_second_question).findViewById(R.id.test_template_button);
        mButtons[2]  = findViewById(R.id.test_third_question).findViewById(R.id.test_template_button);
        mButtons[3]  = findViewById(R.id.test_fourth_question).findViewById(R.id.test_template_button);
        mButtons[4]  = findViewById(R.id.test_fifth_question).findViewById(R.id.test_template_button);
        mButtons[5]  = findViewById(R.id.test_sixth_question).findViewById(R.id.test_template_button);
        mButtons[6]  = findViewById(R.id.test_seventh_question).findViewById(R.id.test_template_button);
        mButtons[7]  = findViewById(R.id.test_eighth_question).findViewById(R.id.test_template_button);
        mButtons[8]  = findViewById(R.id.test_ninth_question).findViewById(R.id.test_template_button);
        mButtons[9]  = findViewById(R.id.test_tenth_question).findViewById(R.id.test_template_button);
        mButtons[10] = findViewById(R.id.test_eleventh_question).findViewById(R.id.test_template_button);
        mButtons[11] = findViewById(R.id.test_twelfth_question).findViewById(R.id.test_template_button);
        mButtons[12] = findViewById(R.id.test_thirteenth_question).findViewById(R.id.test_template_button);
        mButtons[13] = findViewById(R.id.test_fourteenth_question).findViewById(R.id.test_template_button);
        mButtons[14] = findViewById(R.id.test_fifteenth_question).findViewById(R.id.test_template_button);

        mButtonsState[0]  = ButtonState.UNDEFINED;
        mButtonsState[1]  = ButtonState.UNDEFINED;
        mButtonsState[2]  = ButtonState.UNDEFINED;
        mButtonsState[3]  = ButtonState.UNDEFINED;
        mButtonsState[4]  = ButtonState.UNDEFINED;
        mButtonsState[5]  = ButtonState.UNDEFINED;
        mButtonsState[6]  = ButtonState.UNDEFINED;
        mButtonsState[7]  = ButtonState.UNDEFINED;
        mButtonsState[8]  = ButtonState.UNDEFINED;
        mButtonsState[9]  = ButtonState.UNDEFINED;
        mButtonsState[10] = ButtonState.UNDEFINED;
        mButtonsState[11] = ButtonState.UNDEFINED;
        mButtonsState[12] = ButtonState.UNDEFINED;
        mButtonsState[13] = ButtonState.UNDEFINED;
        mButtonsState[14] = ButtonState.UNDEFINED;

        for (int i = 0; i < 15; ++i) {
            final int j = i;
            mButtons[i].setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
                @Override
                public void onValueChanged(int value) {
                    switch (value) {
                        case 0:
                            mButtonsState[j] = ButtonState.NEVER;
                            break;
                        case 1:
                            mButtonsState[j] = ButtonState.RARELY;
                            break;
                        case 2:
                            mButtonsState[j] = ButtonState.SOMETIMES;
                            break;
                        case 3:
                            mButtonsState[j] = ButtonState.OFTEN;
                            break;
                    }

                    boolean enabled = true;
                    for (ButtonState bs : mButtonsState) {
                        if (bs == ButtonState.UNDEFINED) {
                            enabled = false;
                            break;
                        }
                    }

                    if (enabled) {
                        mSubmitButton.setEnabled(true);
                        mSubmitButton.getBackground().setColorFilter(null);
                    }
                }
            });
        }

        findViewById(R.id.test_submit_button).setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("UseSparseArrays")
            @Override
            public void onClick(View v) {
                Map<Integer, TestEscalaImpactoEvento.Answer> answersMap = new HashMap<>();

                int index = 0;
                for (ButtonState bs : mButtonsState) {
                    switch (bs) {
                        case NEVER:
                            answersMap.put(index, TestEscalaImpactoEvento.Answer.NEVER);
                            break;
                        case RARELY:
                            answersMap.put(index, TestEscalaImpactoEvento.Answer.RARELY);
                            break;
                        case SOMETIMES:
                            answersMap.put(index, TestEscalaImpactoEvento.Answer.SOMETIMES);
                            break;
                        case OFTEN:
                            answersMap.put(index, TestEscalaImpactoEvento.Answer.OFTEN);
                            break;
                    }

                    ++index;
                }

                Patient patient =
                        new StorageManager(TestEscalaImpactoEventoActivity.this).getPatient(Constants.KEY_PATIENT);

                TestEscalaImpactoEvento test = new TestEscalaImpactoEvento(patient, answersMap);
            }
        });
    }
}

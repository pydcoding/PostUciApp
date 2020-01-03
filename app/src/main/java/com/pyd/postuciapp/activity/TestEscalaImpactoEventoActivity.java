package com.pyd.postuciapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.bean.Test;
import com.pyd.postuciapp.bean.TestEscalaImpactoEvento;
import com.pyd.postuciapp.constants.Constants;
import com.pyd.postuciapp.network.VolleyManager;
import com.pyd.postuciapp.utils.StorageManager;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestEscalaImpactoEventoActivity extends AppCompatActivity {

    private enum ButtonState {
        UNDEFINED,
        NEVER,
        RARELY,
        SOMETIMES,
        OFTEN
    }

    private Patient mPatient;

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

                try {
                    mPatient = new StorageManager(TestEscalaImpactoEventoActivity.this).getPatient(Constants.KEY_PATIENT);

                    TestEscalaImpactoEvento test = new TestEscalaImpactoEvento(Test.TestType.ESCALA_IMPACTO_EVENTO, mPatient.getDni(), answersMap);

                    Gson gson = new Gson();
                    String string = gson.toJson(test);

                    JSONObject jsonObject = new JSONObject(string);

                    final ProgressDialog dialog = ProgressDialog.show(TestEscalaImpactoEventoActivity.this, "",
                            "Enviando resultados. Por favor, espere...", true);

                    if (Constants.DEBUG) {
                        new FakeConnection(TestEscalaImpactoEventoActivity.this, dialog).execute();

                    } else {
                        String url = Constants.SERVER_URL + Constants.SERVER_SEND_TEST_RESULTS;

                        JsonObjectRequest request = new JsonObjectRequest(
                                Request.Method.POST,
                                url,
                                jsonObject,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        dialog.dismiss();

                                        // Se borra el test de la lista de pendientes
                                        List<Test.TestType> pendingTests = mPatient.getPendingTests();
                                        pendingTests.remove(Test.TestType.ESCALA_IMPACTO_EVENTO);
                                        mPatient.setPendingTests(pendingTests);

                                        StorageManager sm = new StorageManager(TestEscalaImpactoEventoActivity.this);
                                        sm.storePatient(Constants.KEY_PATIENT, mPatient);

                                        finish();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO mostrar snackbar
                                    }
                                });

                        VolleyManager.getInstance(TestEscalaImpactoEventoActivity.this).addToRequestQueue(request);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressWarnings("StaticFieldLeak")
    private class FakeConnection extends AsyncTask<Void, Void, Void> {

        private WeakReference<Context> mContext;
        private ProgressDialog mDialog;

        FakeConnection(Context context, ProgressDialog dialog) {
            super();

            mContext = new WeakReference<>(context);
            mDialog = dialog;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mDialog.dismiss();

            // Se borra el test de la lista de pendientes
            List<Test.TestType> pendingTests = mPatient.getPendingTests();
            pendingTests.remove(Test.TestType.ESCALA_IMPACTO_EVENTO);
            mPatient.setPendingTests(pendingTests);

            StorageManager sm = new StorageManager(mContext.get());
            sm.storePatient(Constants.KEY_PATIENT, mPatient);

            finish();
        }
    }
}

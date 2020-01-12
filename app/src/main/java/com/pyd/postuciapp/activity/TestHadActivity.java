package com.pyd.postuciapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.bean.Test;
import com.pyd.postuciapp.bean.TestHad;
import com.pyd.postuciapp.constants.Constants;
import com.pyd.postuciapp.network.VolleyManager;
import com.pyd.postuciapp.utils.StorageManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHadActivity extends AppCompatActivity {

    private enum RadioGroupState {
        UNCHECKED,
        CHECKED
    }

    private Patient mPatient;

    private AppCompatButton mSubmitButton;

    private RadioGroup[] mRadioGroups;
    private Map<Integer, RadioGroupState> mRadioGroupStates;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_had);

        mSubmitButton = findViewById(R.id.test_submit_button);
        mSubmitButton.setEnabled(false);
        mSubmitButton.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.LIGHTEN);

        mRadioGroups = new RadioGroup[14];
        mRadioGroupStates = new HashMap<>();

        mRadioGroupStates.put(R.id.had_a1_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_d1_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_a2_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_d2_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_a3_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_d3_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_a4_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_d4_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_a5_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_d5_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_a6_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_d6_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_a7_radio_group, RadioGroupState.UNCHECKED);
        mRadioGroupStates.put(R.id.had_d7_radio_group, RadioGroupState.UNCHECKED);

        RadioGroup a1_radio_group = findViewById(R.id.had_a1_radio_group);
        RadioGroup d1_radio_group = findViewById(R.id.had_d1_radio_group);
        RadioGroup a2_radio_group = findViewById(R.id.had_a2_radio_group);
        RadioGroup d2_radio_group = findViewById(R.id.had_d2_radio_group);
        RadioGroup a3_radio_group = findViewById(R.id.had_a3_radio_group);
        RadioGroup d3_radio_group = findViewById(R.id.had_d3_radio_group);
        RadioGroup a4_radio_group = findViewById(R.id.had_a4_radio_group);
        RadioGroup d4_radio_group = findViewById(R.id.had_d4_radio_group);
        RadioGroup a5_radio_group = findViewById(R.id.had_a5_radio_group);
        RadioGroup d5_radio_group = findViewById(R.id.had_d5_radio_group);
        RadioGroup a6_radio_group = findViewById(R.id.had_a6_radio_group);
        RadioGroup d6_radio_group = findViewById(R.id.had_d6_radio_group);
        RadioGroup a7_radio_group = findViewById(R.id.had_a7_radio_group);
        RadioGroup d7_radio_group = findViewById(R.id.had_d7_radio_group);

        mRadioGroups[0]  = a1_radio_group;
        mRadioGroups[1]  = d1_radio_group;
        mRadioGroups[2]  = a2_radio_group;
        mRadioGroups[3]  = d2_radio_group;
        mRadioGroups[4]  = a3_radio_group;
        mRadioGroups[5]  = d3_radio_group;
        mRadioGroups[6]  = a4_radio_group;
        mRadioGroups[7]  = d4_radio_group;
        mRadioGroups[8]  = a5_radio_group;
        mRadioGroups[9]  = d5_radio_group;
        mRadioGroups[10] = a6_radio_group;
        mRadioGroups[11] = d6_radio_group;
        mRadioGroups[12] = a7_radio_group;
        mRadioGroups[13] = d7_radio_group;

        for (RadioGroup rb : mRadioGroups) {
            rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    mRadioGroupStates.put(group.getId(), RadioGroupState.CHECKED);

                    boolean enabled = true;
                    for (Map.Entry<Integer, RadioGroupState> entry : mRadioGroupStates.entrySet()) {
                        if (entry.getValue() == RadioGroupState.UNCHECKED) {
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

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, TestHad.Answer> answersMap = getAnswers();

                try {
                    mPatient = new StorageManager(TestHadActivity.this).getPatient(Constants.KEY_PATIENT);

                    TestHad test = new TestHad(Test.TestType.HAD, mPatient.getDni(), answersMap);

                    Gson gson = new Gson();
                    String string = gson.toJson(test);

                    JSONObject jsonObject = new JSONObject(string);

                    final ProgressDialog dialog = ProgressDialog.show(TestHadActivity.this, "",
                            "Enviando resultados. Por favor, espere...", true);

                    if (Constants.DEBUG) {
                        new FakeConnection(TestHadActivity.this, dialog).execute();

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

                                        pendingTests.remove(Test.TestType.HAD);
                                        mPatient.setPendingTests(pendingTests);

                                        StorageManager sm = new StorageManager(TestHadActivity.this);
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

                        VolleyManager.getInstance(TestHadActivity.this).addToRequestQueue(request);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, TestHad.Answer> getAnswers() {
        HashMap<String, TestHad.Answer> answers = new HashMap<>();

        switch (mRadioGroups[0].getCheckedRadioButtonId()) {
            case R.id.had_a1_0_radio_button:
                answers.put("A1", TestHad.Answer.ZERO);
                break;
            case R.id.had_a1_1_radio_button:
                answers.put("A1", TestHad.Answer.ONE);
                break;
            case R.id.had_a1_2_radio_button:
                answers.put("A1", TestHad.Answer.TWO);
                break;
            case R.id.had_a1_3_radio_button:
                answers.put("A1", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[1].getCheckedRadioButtonId()) {
            case R.id.had_d1_0_radio_button:
                answers.put("D1", TestHad.Answer.ZERO);
                break;
            case R.id.had_d1_1_radio_button:
                answers.put("D1", TestHad.Answer.ONE);
                break;
            case R.id.had_d1_2_radio_button:
                answers.put("D1", TestHad.Answer.TWO);
                break;
            case R.id.had_d1_3_radio_button:
                answers.put("D1", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[2].getCheckedRadioButtonId()) {
            case R.id.had_a2_0_radio_button:
                answers.put("A2", TestHad.Answer.ZERO);
                break;
            case R.id.had_a2_1_radio_button:
                answers.put("A2", TestHad.Answer.ONE);
                break;
            case R.id.had_a2_2_radio_button:
                answers.put("A2", TestHad.Answer.TWO);
                break;
            case R.id.had_a2_3_radio_button:
                answers.put("A2", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[3].getCheckedRadioButtonId()) {
            case R.id.had_d2_0_radio_button:
                answers.put("D2", TestHad.Answer.ZERO);
                break;
            case R.id.had_d2_1_radio_button:
                answers.put("D2", TestHad.Answer.ONE);
                break;
            case R.id.had_d2_2_radio_button:
                answers.put("D2", TestHad.Answer.TWO);
                break;
            case R.id.had_d2_3_radio_button:
                answers.put("D2", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[4].getCheckedRadioButtonId()) {
            case R.id.had_a3_0_radio_button:
                answers.put("A3", TestHad.Answer.ZERO);
                break;
            case R.id.had_a3_1_radio_button:
                answers.put("A3", TestHad.Answer.ONE);
                break;
            case R.id.had_a3_2_radio_button:
                answers.put("A3", TestHad.Answer.TWO);
                break;
            case R.id.had_a3_3_radio_button:
                answers.put("A3", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[5].getCheckedRadioButtonId()) {
            case R.id.had_d3_0_radio_button:
                answers.put("D3", TestHad.Answer.ZERO);
                break;
            case R.id.had_d3_1_radio_button:
                answers.put("D3", TestHad.Answer.ONE);
                break;
            case R.id.had_d3_2_radio_button:
                answers.put("D3", TestHad.Answer.TWO);
                break;
            case R.id.had_d3_3_radio_button:
                answers.put("D3", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[6].getCheckedRadioButtonId()) {
            case R.id.had_a4_0_radio_button:
                answers.put("A4", TestHad.Answer.ZERO);
                break;
            case R.id.had_a4_1_radio_button:
                answers.put("A4", TestHad.Answer.ONE);
                break;
            case R.id.had_a4_2_radio_button:
                answers.put("A4", TestHad.Answer.TWO);
                break;
            case R.id.had_a4_3_radio_button:
                answers.put("A4", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[7].getCheckedRadioButtonId()) {
            case R.id.had_d4_0_radio_button:
                answers.put("D4", TestHad.Answer.ZERO);
                break;
            case R.id.had_d4_1_radio_button:
                answers.put("D4", TestHad.Answer.ONE);
                break;
            case R.id.had_d4_2_radio_button:
                answers.put("D4", TestHad.Answer.TWO);
                break;
            case R.id.had_d4_3_radio_button:
                answers.put("D4", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[8].getCheckedRadioButtonId()) {
            case R.id.had_a5_0_radio_button:
                answers.put("A5", TestHad.Answer.ZERO);
                break;
            case R.id.had_a5_1_radio_button:
                answers.put("A5", TestHad.Answer.ONE);
                break;
            case R.id.had_a5_2_radio_button:
                answers.put("A5", TestHad.Answer.TWO);
                break;
            case R.id.had_a5_3_radio_button:
                answers.put("A5", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[9].getCheckedRadioButtonId()) {
            case R.id.had_d5_0_radio_button:
                answers.put("D5", TestHad.Answer.ZERO);
                break;
            case R.id.had_d5_1_radio_button:
                answers.put("D5", TestHad.Answer.ONE);
                break;
            case R.id.had_d5_2_radio_button:
                answers.put("D5", TestHad.Answer.TWO);
                break;
            case R.id.had_d5_3_radio_button:
                answers.put("D5", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[10].getCheckedRadioButtonId()) {
            case R.id.had_a6_0_radio_button:
                answers.put("A6", TestHad.Answer.ZERO);
                break;
            case R.id.had_a6_1_radio_button:
                answers.put("A6", TestHad.Answer.ONE);
                break;
            case R.id.had_a6_2_radio_button:
                answers.put("A6", TestHad.Answer.TWO);
                break;
            case R.id.had_a6_3_radio_button:
                answers.put("A6", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[11].getCheckedRadioButtonId()) {
            case R.id.had_d6_0_radio_button:
                answers.put("D6", TestHad.Answer.ZERO);
                break;
            case R.id.had_d6_1_radio_button:
                answers.put("D6", TestHad.Answer.ONE);
                break;
            case R.id.had_d6_2_radio_button:
                answers.put("D6", TestHad.Answer.TWO);
                break;
            case R.id.had_d6_3_radio_button:
                answers.put("D6", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[12].getCheckedRadioButtonId()) {
            case R.id.had_a7_0_radio_button:
                answers.put("A7", TestHad.Answer.ZERO);
                break;
            case R.id.had_a7_1_radio_button:
                answers.put("A7", TestHad.Answer.ONE);
                break;
            case R.id.had_a7_2_radio_button:
                answers.put("A7", TestHad.Answer.TWO);
                break;
            case R.id.had_a7_3_radio_button:
                answers.put("A7", TestHad.Answer.THREE);
                break;
        }

        switch (mRadioGroups[13].getCheckedRadioButtonId()) {
            case R.id.had_d7_0_radio_button:
                answers.put("D7", TestHad.Answer.ZERO);
                break;
            case R.id.had_d7_1_radio_button:
                answers.put("D7", TestHad.Answer.ONE);
                break;
            case R.id.had_d7_2_radio_button:
                answers.put("D7", TestHad.Answer.TWO);
                break;
            case R.id.had_d7_3_radio_button:
                answers.put("D7", TestHad.Answer.THREE);
                break;
        }

        return answers;
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
            pendingTests.remove(Test.TestType.HAD);
            mPatient.setPendingTests(pendingTests);

            StorageManager sm = new StorageManager(mContext.get());
            sm.storePatient(Constants.KEY_PATIENT, mPatient);

            finish();
        }
    }
}

package com.pyd.postuciapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Message;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.constants.Constants;
import com.pyd.postuciapp.network.VolleyManager;
import com.pyd.postuciapp.utils.StorageManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class ContactActivity extends AppCompatActivity {

    private AppCompatButton mSubmitButton;
    private AppCompatEditText mMessageEditText;

    private boolean mIsMessageEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        initToolbar();

        mSubmitButton = findViewById(R.id.test_submit_button);
        mSubmitButton.setEnabled(false);
        mSubmitButton.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.LIGHTEN);

        mMessageEditText = findViewById(R.id.contact_message);

        mIsMessageEmpty = true;
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isEmpty = count == 0;
                boolean hasChanged = isEmpty != mIsMessageEmpty;

                if (hasChanged) {
                    mIsMessageEmpty = isEmpty;
                    if (mIsMessageEmpty) {
                        mSubmitButton.setEnabled(false);
                        mSubmitButton.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.LIGHTEN);
                    } else {
                        mSubmitButton.setEnabled(true);
                        mSubmitButton.getBackground().setColorFilter(null);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(ContactActivity.this, "",
                        "Enviando mensaje. Por favor, espere...", true);

                if (Constants.DEBUG) {
                    new FakeConnection(dialog).execute();
                } else {
                    String text = mMessageEditText.getText().toString();
                    Patient patient = new StorageManager(ContactActivity.this).getPatient(Constants.KEY_PATIENT);

                    Message message = new Message(patient.getDni(), text);

                    Gson gson = new Gson();
                    String string = gson.toJson(message);

                    try {
                        JSONObject jsonObject = new JSONObject(string);

                        String url = Constants.SERVER_URL + Constants.SERVER_SEND_TEST_RESULTS;
                        JsonObjectRequest request = new JsonObjectRequest(
                                Request.Method.POST,
                                url,
                                jsonObject,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        dialog.dismiss();

                                        finish();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO mostrar snackbar
                                    }
                                });

                        VolleyManager.getInstance(ContactActivity.this).addToRequestQueue(request);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Inicializa la toolbar.
     */
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @SuppressWarnings("StaticFieldLeak")
    private class FakeConnection extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mDialog;

        FakeConnection(ProgressDialog dialog) {
            super();

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

            finish();
        }
    }
}

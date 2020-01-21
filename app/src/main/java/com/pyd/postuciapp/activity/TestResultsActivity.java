package com.pyd.postuciapp.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pyd.postuciapp.R;
import com.pyd.postuciapp.constants.Constants;
import com.pyd.postuciapp.network.VolleyManager;

public class TestResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_results);

        initToolbar();

        final ProgressDialog dialog = ProgressDialog.show(TestResultsActivity.this, "",
                "Obteniendo resultados. Por favor, espere...", true);

        if (Constants.DEBUG) {
            new FakeConnection(dialog).execute();
        } else {
            String url = Constants.SERVER_URL + Constants.SERVER_GET_RESULTS;

            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ((AppCompatTextView) findViewById(R.id.test_results_textview)).setText(response);

                            dialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO mostrar snackbar
                        }
                    });

            VolleyManager.getInstance(TestResultsActivity.this).addToRequestQueue(request);
        }
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

            ((AppCompatTextView) findViewById(R.id.test_results_textview)).setText("Test HAD:\n\nPuntuación obtenida: 12");
        }
    }
}

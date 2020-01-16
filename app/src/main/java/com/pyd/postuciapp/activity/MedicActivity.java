package com.pyd.postuciapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.adapter.PatientAdapter;
import com.pyd.postuciapp.bean.Message;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.bean.Test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicActivity extends AppCompatActivity {

    private List<Patient> mPatients;
    private Map<String, List<Message>> mMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medic);

        initToolbar();

        new FakeConnection(this).execute();
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

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.patients_recycler_view);
        PatientAdapter patientAdapter = new PatientAdapter(this, mPatients, mMessages);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(patientAdapter);
    }

    @SuppressWarnings("StaticFieldLeak")
    private class FakeConnection extends AsyncTask<Void, Void, Void> {

        private WeakReference<Context> mContext;
        private ProgressDialog mDialog;

        FakeConnection(Context context) {
            super();

            this.mContext = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            mDialog = ProgressDialog.show(mContext.get(), "",
                    "Por favor, espere...", true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);

                Patient patient1 = new Patient(
                        0,
                        "12345678Y",
                        "Pilar Gómez Hernández",
                        true,
                        true,
                        false,
                        false,
                        true,
                        false,
                        true,
                        false,
                        Arrays.asList(Test.TestType.HAD),
                        new ArrayList<Test.TestType>());

                Patient patient2 = new Patient(
                        1,
                        "47524198Z",
                        "Mario González Torres",
                        false,
                        true,
                        true,
                        false,
                        true,
                        false,
                        false,
                        false,
                        Arrays.asList(Test.TestType.HAD, Test.TestType.ESCALA_IMPACTO_EVENTO),
                        new ArrayList<Test.TestType>());

                Patient patient3 = new Patient(
                        2,
                        "12524258A",
                        "Eva Martínez Pérez",
                        true,
                        false,
                        false,
                        false,
                        true,
                        false,
                        false,
                        true,
                        new ArrayList<Test.TestType>(),
                        Arrays.asList(Test.TestType.HAD, Test.TestType.ESCALA_IMPACTO_EVENTO));

                Patient patient4 = new Patient(
                        3,
                        "50750511B",
                        "Catherine Blanco Machado",
                        true,
                        false,
                        true,
                        false,
                        true,
                        true,
                        false,
                        true,
                        Arrays.asList(Test.TestType.ESCALA_IMPACTO_EVENTO),
                        Arrays.asList(Test.TestType.HAD));

                mPatients = Arrays.asList(patient1, patient2, patient3, patient4);
                mMessages = new HashMap<>();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mDialog.dismiss();

            initRecyclerView();
        }
    }
}

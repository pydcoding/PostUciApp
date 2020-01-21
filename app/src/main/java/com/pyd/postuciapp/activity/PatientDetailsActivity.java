package com.pyd.postuciapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.adapter.TestAdapter;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.bean.Test;

public class PatientDetailsActivity extends AppCompatActivity {

    private static final String PLACEHOLDER = "%1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_details);

        initToolbar();

        Intent intent = getIntent();

        Patient patient = (Patient) intent.getSerializableExtra("patient");

        ((AppCompatTextView) findViewById(R.id.patient_name)).setText(patient.getName());
        ((AppCompatTextView) findViewById(R.id.patient_dni)).setText(patient.getDni());
        ((AppCompatTextView) findViewById(R.id.patient_grade)).setText(
                getResources().getString(R.string.patient_grade_template)
                        .replaceAll(PLACEHOLDER, Integer.toString(patient.calculateSeverity())));

        // Tests pendientes
        if (!patient.getPendingTests().isEmpty()) {
            StringBuilder pendingTests = new StringBuilder();
            for (Test.TestType testType : patient.getPendingTests()) {
                pendingTests.append(" - ");
                pendingTests.append(Test.getNameByType(testType));
                pendingTests.append("\n");
            }

            ((AppCompatTextView) findViewById(R.id.pending_tests_textview)).setText(pendingTests);
        }

        RecyclerView pendingRecyclerView = findViewById(R.id.patient_done_tests_recycler_view);
        TestAdapter testAdapter = new TestAdapter(TestAdapter.Type.MEDIC_DONE, this, patient.getDoneTests());

        pendingRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        pendingRecyclerView.setAdapter(testAdapter);

        if (!patient.getDoneTests().isEmpty()) {
            findViewById(R.id.patient_no_done_tests).setVisibility(View.GONE);
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
}

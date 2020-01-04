package com.pyd.postuciapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.adapter.TestAdapter;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.constants.Constants;
import com.pyd.postuciapp.utils.StorageManager;

public class PendingTestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tests_pending);

        initToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();

        StorageManager sm = new StorageManager(this);
        Patient patient = sm.getPatient(Constants.KEY_PATIENT);

        RecyclerView recyclerView = findViewById(R.id.tests_pending_recycler_view);
        TestAdapter testAdapter = new TestAdapter(this, patient.getPendingTests());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(testAdapter);
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
}

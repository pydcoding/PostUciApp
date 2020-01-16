package com.pyd.postuciapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Message;
import com.pyd.postuciapp.bean.Patient;

import java.util.List;

public class PatientDetailsActivity extends AppCompatActivity {

    private Patient mPatient;
    private List<Message> mMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_details);

        initToolbar();
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

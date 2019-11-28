package com.pyd.postuciapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pyd.postuciapp.R;

public class LoginActivity extends AppCompatActivity {

    private enum Type {
        PATIENT,
        MEDIC
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initButtons();
    }

    private void initButtons() {
        Button patientButton = findViewById(R.id.patient_button);
        Button medicButton = findViewById(R.id.medic_button);

        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Cucu, soy un paciente", Toast.LENGTH_SHORT).show();
            }
        });

        medicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Cucu, soy un médico", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AlertDialog buildLoginDialog(Type type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();

        return null;
    }
}

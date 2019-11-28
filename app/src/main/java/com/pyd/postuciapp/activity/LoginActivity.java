package com.pyd.postuciapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.view.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {

    private enum Type {
        PATIENT,
        MEDIC
    }

    private AlertDialog mAlertDialog;
    private View mAlertDialogView;

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
                mAlertDialog = buildSignInDialog(Type.PATIENT);

                mAlertDialog.show();
            }
        });

        medicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Cucu, soy un médico", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AlertDialog buildSignInDialog(Type type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();

        if (type == Type.PATIENT) {
            mAlertDialogView = inflater.inflate(R.layout.dialog_sign_in_patient, null);
        } else {
            mAlertDialogView = inflater.inflate(R.layout.dialog_sign_in_medic, null);
        }

        builder.setView(mAlertDialogView);

        initSignInButtons(mAlertDialogView);
        // TODO
        //initSignInEditTexts(mAlertDialogView);
        //initSignInViews(mAlertDialogView);

        return builder.create();
    }

    private void initSignInButtons(View parent) {
        final Button createAccountButton         = parent.findViewById(R.id.create_account);
        final CircularProgressButton enterButton = parent.findViewById(R.id.enter);

        enterButton.setIndeterminateProgressMode(true);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se permite cambiar de pantalla siempre que no se este cargando.
                if (enterButton.getProgress() == 0) {
                    mAlertDialog.dismiss();

                    // TODO
                    // mAlertDialog = createDialogSignUp();
                    // mAlertDialog.show();
                }
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterButton.setProgress(50);

                // TODO simular acceso a BD
            }
        });
    }
}

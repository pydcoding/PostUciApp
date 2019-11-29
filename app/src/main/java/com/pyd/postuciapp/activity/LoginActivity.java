package com.pyd.postuciapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.view.CircularProgressButton;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

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
                mAlertDialog = buildSignInDialog();

                mAlertDialog.show();
            }
        });

        medicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog = buildSignUpDialog();

                mAlertDialog.show();
            }
        });
    }

    @NotNull
    private AlertDialog buildSignInDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();

        mAlertDialogView = inflater.inflate(R.layout.dialog_sign_in_patient, null);

        builder.setView(mAlertDialogView);

        initSignInButtons(mAlertDialogView);
        // TODO
        //initSignInEditTexts(mAlertDialogView);
        //initSignInViews(mAlertDialogView);

        return builder.create();
    }

    private void initSignInButtons(@NotNull View parent) {
        final Button createAccountButton         = parent.findViewById(R.id.create_account);
        final CircularProgressButton enterButton = parent.findViewById(R.id.enter);

        enterButton.setIndeterminateProgressMode(true);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se permite cambiar de pantalla siempre que no se este cargando.
                if (enterButton.getProgress() == 0) {
                    mAlertDialog.dismiss();

                    mAlertDialog = buildSignUpDialog();
                    mAlertDialog.show();
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

    private AlertDialog buildSignUpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();

        mAlertDialogView = inflater.inflate(R.layout.dialog_sign_up_patient, null);

        builder.setView(mAlertDialogView);

        initSignUpButtons(mAlertDialogView);
        // TODO
        //initSignInEditTexts(mAlertDialogView);
        //initSignInViews(mAlertDialogView);

        return builder.create();
    }

    private void initSignUpButtons(@NotNull View parent) {
        final CircularProgressButton enterButton = parent.findViewById(R.id.enter);

        enterButton.setIndeterminateProgressMode(true);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterButton.setProgress(50);

                // TODO simular acceso a BD
            }
        });
    }
}

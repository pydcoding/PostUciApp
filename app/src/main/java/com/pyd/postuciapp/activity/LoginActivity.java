package com.pyd.postuciapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.pyd.postuciapp.R;
import com.pyd.postuciapp.utils.Utils;
import com.pyd.postuciapp.view.CircularProgressButton;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mDniInputLayout;
    private TextInputLayout mNameInputLayout;
    private TextInputLayout mPasswordInputLayout;

    private EditText mDniEditText;
    private EditText mNameEditText;
    private EditText mPasswordEditText;

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
        Button medicButton   = findViewById(R.id.medic_button);

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
        LayoutInflater inflater     = this.getLayoutInflater();

        mAlertDialogView = inflater.inflate(R.layout.dialog_sign_in_patient, null);

        builder.setView(mAlertDialogView);

        initSignInButtons(mAlertDialogView);
        initSignInEditTexts(mAlertDialogView);
        // TODO
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

    private void initSignInEditTexts(@NotNull View parent) {
        mDniInputLayout      = parent.findViewById(R.id.dni_input_layout);
        mPasswordInputLayout = parent.findViewById(R.id.password_input_layout);

        mDniEditText      = parent.findViewById(R.id.dni_edittext);
        mPasswordEditText = parent.findViewById(R.id.password_edittext);

        mDniEditText.addTextChangedListener(new MyTextWatcher(mDniEditText));
        mPasswordEditText.addTextChangedListener(new MyTextWatcher(mPasswordEditText));
    }

    @NotNull
    private AlertDialog buildSignUpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater     = this.getLayoutInflater();

        mAlertDialogView = inflater.inflate(R.layout.dialog_sign_up_patient, null);

        builder.setView(mAlertDialogView);

        initSignUpButtons(mAlertDialogView);
        initSignUpEditTexts(mAlertDialogView);
        // TODO
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

    private void initSignUpEditTexts(@NotNull View parent) {
        mDniInputLayout      = parent.findViewById(R.id.dni_input_layout);
        mPasswordInputLayout = parent.findViewById(R.id.password_input_layout);
        mNameInputLayout     = parent.findViewById(R.id.name_input_layout);

        mDniEditText      = parent.findViewById(R.id.dni_edittext);
        mPasswordEditText = parent.findViewById(R.id.password_edittext);
        mNameEditText     = parent.findViewById(R.id.name_edittext);

        mDniEditText.addTextChangedListener(new MyTextWatcher(mDniEditText));
        mPasswordEditText.addTextChangedListener(new MyTextWatcher(mPasswordEditText));
    }

    /**
     * TextWatcher propio para validar todos los campos.
     */
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.password_edittext:
                    validatePassword(mPasswordEditText, mPasswordInputLayout);
                    break;

                case R.id.name_edittext:
                    validateName(mNameEditText, mNameInputLayout);
                    break;

                case R.id.dni_edittext:
                    validateDni(mDniEditText, mDniInputLayout);
                    break;
            }
        }
    }

    /**
     * Metodo que valida la contraseña y muestra un error si es necesario.
     *
     * @return true si la contraseña es correcta.
     */
    private boolean validatePassword(@NotNull EditText editText, @NotNull TextInputLayout textInputLayout) {
        String password = editText.getText().toString();

        if (!Utils.isValidPassword(password)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Contraseña incorrecta (6 caracteres min)");

            requestFocus(editText);

            return false;

        } else {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Metodo que valida el nombre y muestra un error si es necesario.
     *
     * @return true si el nombre es correcto.
     */
    private boolean validateName(@NotNull EditText editText, @NotNull TextInputLayout textInputLayout) {
        String name = editText.getText().toString();

        if (!Utils.isValidName(name)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Nombre incorrecto");

            requestFocus(editText);

            return false;

        } else {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Metodo que valida el DNI y muestra un error si es necesario.
     *
     * @return true si el DNI es correcto.
     */
    private boolean validateDni(@NotNull EditText editText, @NotNull TextInputLayout textInputLayout) {
        String dni = editText.getText().toString();

        if (!Utils.isValidDni(dni)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("DNI incorrecto");

            requestFocus(editText);

            return false;

        } else {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(@NotNull final View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}

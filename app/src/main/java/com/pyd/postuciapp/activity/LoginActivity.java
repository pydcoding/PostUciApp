package com.pyd.postuciapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;
import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Medic;
import com.pyd.postuciapp.bean.Patient;
import com.pyd.postuciapp.bean.Test;
import com.pyd.postuciapp.constants.Constants;
import com.pyd.postuciapp.network.VolleyManager;
import com.pyd.postuciapp.utils.StorageManager;
import com.pyd.postuciapp.utils.Utils;
import com.pyd.postuciapp.view.CircularProgressButton;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private enum UserType {
        PATIENT,
        MEDIC
    }

    private enum OperationType {
        SIGN_IN,
        SIGN_UP
    }

    private TextInputLayout mDniInputLayout;
    private TextInputLayout mNameInputLayout;
    private TextInputLayout mPasswordInputLayout;

    private AppCompatEditText mDniEditText;
    private AppCompatEditText mNameEditText;
    private AppCompatEditText mPasswordEditText;

    private AlertDialog mAlertDialog;
    private View mAlertDialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initButtons();
        initToolbar();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Inicializa los botones para el paciente y el médico.
     */
    private void initButtons() {
        Button patientButton = findViewById(R.id.patient_button);
        Button medicButton   = findViewById(R.id.medic_button);

        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog = buildSignInDialog(UserType.PATIENT);

                mAlertDialog.show();
            }
        });

        medicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog = buildSignInDialog(UserType.MEDIC);

                mAlertDialog.show();
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

    /**
     * Construye un diálogo para iniciar sesión tanto para el paciente como para el médico en
     * función del tipo recibido.
     *
     * @param userType: tipo de usuario (PATIENT o MEDIC)
     * @return objeto de tipo AlertDialog.
     */
    @NotNull
    private AlertDialog buildSignInDialog(UserType userType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater     = this.getLayoutInflater();

        mAlertDialogView = inflater.inflate((userType == UserType.PATIENT) ? R.layout.dialog_sign_in_patient : R.layout.dialog_sign_in_medic, null);

        builder.setView(mAlertDialogView);

        initSignInButtons(mAlertDialogView, userType);
        initSignInEditTexts(mAlertDialogView);
        initSignInViews(mAlertDialogView);

        return builder.create();
    }

    /**
     * Inicializa los botones del dialogo de "iniciar sesión". Este método es común tanto
     * para el paciente como para el médico, ya que son muy parecidos.
     */
    private void initSignInButtons(@NotNull View parent, final UserType userType) {
        final CircularProgressButton enterButton = parent.findViewById(R.id.enter);

        if (userType == UserType.PATIENT) {
            final Button createAccountButton = parent.findViewById(R.id.create_account);

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
        }

        enterButton.setIndeterminateProgressMode(true);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateDni(mDniEditText, mDniInputLayout) && validatePassword(mPasswordEditText, mPasswordInputLayout) && (enterButton.getProgress() == 0)) {
                    enterButton.setProgress(50);

                    if (!Constants.DEBUG) {
                        String dni = mDniEditText.getText().toString();
                        String password = mPasswordEditText.getText().toString();

                        // TODO cifrar contraseña
                        String url = Constants.SERVER_URL +
                                ((userType == UserType.PATIENT) ? Constants.SERVER_SIGN_IN_PATIENT : Constants.SERVER_SIGN_IN_MEDIC) +
                                Constants.SERVER_LOGIN_PARAM_DNI + dni + "&" +
                                Constants.SERVER_LOGIN_PARAM_PASSWORD + password;

                        StringRequest request = new StringRequest(
                                Request.Method.GET,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals(Constants.SERVER_RESPONSE_OK)) {
                                            animateSuccess(enterButton, userType, OperationType.SIGN_IN);
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO mostrar snackbar
                                    }
                                });

                        VolleyManager.getInstance(LoginActivity.this).addToRequestQueue(request);

                    } else {
                        new FakeConnection(LoginActivity.this, enterButton, userType, OperationType.SIGN_IN).execute();
                    }
                }
            }
        });
    }

    /**
     * Inicializa los EditText del diálogo de "Iniciar sesión"
     */
    private void initSignInEditTexts(@NotNull View parent) {
        mDniInputLayout      = parent.findViewById(R.id.dni_input_layout);
        mPasswordInputLayout = parent.findViewById(R.id.password_input_layout);

        mDniEditText      = parent.findViewById(R.id.dni_edittext);
        mPasswordEditText = parent.findViewById(R.id.password_edittext);

        mDniEditText.addTextChangedListener(new MyTextWatcher(mDniEditText));
        mPasswordEditText.addTextChangedListener(new MyTextWatcher(mPasswordEditText));
    }

    /**
     * Inicializa los componentes restantes de los diálogos.
     */
    private void initSignInViews(@NonNull View parent) {
        AppCompatCheckBox rememberMeCheckBox = parent.findViewById(R.id.remember_me_checkbox);
        rememberMeCheckBox.setChecked(true);
        rememberMeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                StorageManager storageManager = new StorageManager(getApplicationContext());
                storageManager.storeBoolean(Constants.KEY_LOGGED_IN, b);
            }
        });
    }

    /**
     * Construye un diálogo para que el paciente se registre.
     *
     * @return objeto de tipo AlertDialog.
     */
    @NotNull
    private AlertDialog buildSignUpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater     = this.getLayoutInflater();

        mAlertDialogView = inflater.inflate(R.layout.dialog_sign_up_patient, null);

        builder.setView(mAlertDialogView);

        initSignUpButtons(mAlertDialogView);
        initSignUpEditTexts(mAlertDialogView);

        return builder.create();
    }

    /**
     * Inicializa los botones del diálogo para registrarse.
     */
    private void initSignUpButtons(@NotNull View parent) {
        final CircularProgressButton enterButton = parent.findViewById(R.id.enter);

        enterButton.setIndeterminateProgressMode(true);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateDni(mDniEditText, mDniInputLayout) && validatePassword(mPasswordEditText, mPasswordInputLayout) && validateName(mNameEditText, mNameInputLayout) && (enterButton.getProgress() == 0)) {
                    enterButton.setProgress(50);

                    if (!Constants.DEBUG) {
                        String dni = mDniEditText.getText().toString();
                        String password = mPasswordEditText.getText().toString();

                        // TODO cifrar contraseña
                        String url = Constants.SERVER_URL +
                                Constants.SERVER_SIGN_UP_PATIENT +
                                Constants.SERVER_LOGIN_PARAM_DNI + dni + "&" +
                                Constants.SERVER_LOGIN_PARAM_PASSWORD + password;

                        StringRequest request = new StringRequest(
                                Request.Method.GET,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals(Constants.SERVER_RESPONSE_OK)) {
                                            animateSuccess(enterButton, UserType.PATIENT, OperationType.SIGN_UP);
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO mostrar snackbar
                                    }
                                });

                        VolleyManager.getInstance(LoginActivity.this).addToRequestQueue(request);

                    } else {
                        new FakeConnection(LoginActivity.this, enterButton, UserType.PATIENT, OperationType.SIGN_UP).execute();
                    }
                }
            }
        });
    }

    /**
     * Inicializa los EditText del diálogo de registrarse.
     */
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

    /**
     * Inicia la animación cuando la conexión con el servidor es correcta y el usuario se ha logueado
     * correctamente
     */
    private void animateSuccess(@NotNull View origin, final UserType userType, final OperationType operationType) {
        int enterButtonX = (origin.getLeft()
                + origin.getRight()) / 2;

        int enterButtonY = (origin.getTop()
                + origin.getBottom()) / 2;

        View background = mAlertDialogView.findViewById((operationType == OperationType.SIGN_UP) ? R.id.sign_up_dialog_background : R.id.sign_in_dialog_background);

        int radiusReveal = Math.max(background.getWidth()
                , background.getHeight());

        background.setVisibility(View.VISIBLE);

        Animator animator =
                android.view.ViewAnimationUtils.createCircularReveal(background
                        , enterButtonX
                        , enterButtonY
                        , 0
                        , radiusReveal);

        animator.setDuration(500);
        animator.setInterpolator(
                AnimationUtils.loadInterpolator(LoginActivity.this, R.anim.accelerator_interpolator));

        animator.start();

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Avanzamos automaticamente a la siguiente pantalla.
                Intent intent = new Intent(LoginActivity.this, (userType == UserType.PATIENT) ? PatientActivity.class : MedicActivity.class);

                startActivity(intent);

                finish();
            }
        });
    }

    @SuppressWarnings("StaticFieldLeak")
    private class FakeConnection extends AsyncTask<Void, Void, Void> {

        private UserType mUserType;
        private OperationType mOperationType;

        private WeakReference<Context> mContext;
        private View mOrigin;

        FakeConnection(Context context, View origin, UserType userType, OperationType operationType) {
            super();

            mContext = new WeakReference<>(context);
            mOrigin = origin;

            mUserType = userType;
            mOperationType = operationType;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);

                StorageManager storageManager = new StorageManager(mContext.get());

                if (mUserType == UserType.PATIENT) {
                    // TODO usar los campos introducidos
                    List<Test.TestType> pendingTests = new ArrayList<>();
                    pendingTests.add(Test.TestType.ESCALA_IMPACTO_EVENTO);
                    pendingTests.add(Test.TestType.HAD);

                    Patient patient = new Patient(
                            0,
                            "11223344A",
                            "Daniel",
                            true,
                            true,
                            true,
                            false,
                            true,
                            false,
                            true,
                            false,
                            pendingTests
                    );

                    storageManager.storePatient(Constants.KEY_PATIENT, patient);

                } else {
                    Medic medic = new Medic("12345678B", "Sandra");

                    storageManager.storeMedic(Constants.KEY_MEDIC, medic);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            animateSuccess(mOrigin, mUserType, mOperationType);
        }
    }
}

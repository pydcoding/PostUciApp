package com.pyd.postuciapp.constants;

public class Constants {

    // Debug flag
    public static final boolean DEBUG = true;

    // Información del servidor
    public static final String SERVER_URL = "192.168.0.5";
    public static final String SERVER_SIGN_IN_MEDIC = "/sign_in_medic?";
    public static final String SERVER_SIGN_IN_PATIENT = "/sign_in_patient?";
    public static final String SERVER_SIGN_UP_PATIENT = "/sign_up_patient?";
    public static final String SERVER_SEND_TEST_RESULTS = "/test";
    public static final String SERVER_SEND_MESSAGE= "/contact";

    public static final String SERVER_LOGIN_PARAM_DNI = "dni=";
    public static final String SERVER_LOGIN_PARAM_PASSWORD = "password=";

    public static final String SERVER_RESPONSE_OK = "Ok";

    public static final String KEY_LOGGED_IN = "logged_in";
    public static final String KEY_MEDIC = "medic";
    public static final String KEY_PATIENT = "patient";
}

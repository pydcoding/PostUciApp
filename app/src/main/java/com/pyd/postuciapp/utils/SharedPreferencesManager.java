package com.pyd.postuciapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class SharedPreferencesManager {

    private static final String KEY_LOGGED_IN = "logged_in";

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    public SharedPreferencesManager(@NotNull Context context) {
        mSharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    /**
     * Metodo que elimina las preferencias.
     *
     * @return true si se han borrado correctamentes
     */
    public synchronized boolean clear() {
        return mEditor.clear().commit();
    }

    /**
     * Metodo que inserta si el usuario esta logueado.
     *
     * @param loggedIn: true si el usuario esta logeado
     * @return true si se ha insertado correctamente.
     */
    public synchronized boolean setLoggedIn(boolean loggedIn) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(KEY_LOGGED_IN, loggedIn);

        return mEditor.commit();
    }

    /**
     * Metodo que devuelve si el usuario esta logueado.
     *
     * @return true si el usuario esta logeado.
     */
    public boolean isLoggedIn() {
        return mSharedPreferences.getBoolean(KEY_LOGGED_IN, false);
    }
}

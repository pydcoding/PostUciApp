package com.pyd.postuciapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pyd.postuciapp.bean.Medic;
import com.pyd.postuciapp.bean.Patient;

import org.jetbrains.annotations.NotNull;

public class StorageManager {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    public StorageManager(@NotNull Context context) {
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
     * Metodo que inserta un booleano.
     *
     * @param key: clave con la que guardar el valor.
     * @param value: true si el usuario esta logeado.
     * @return true si se ha insertado correctamente.
     */
    public synchronized boolean storeBoolean(String key, boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);

        return mEditor.commit();
    }

    /**
     * Metodo que devuelve un booleano con la clave recibida.
     *
     * @return boolean
     */
    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    /**
     * Método que inserta un objecto de tipo Medic.
     *
     * @param key: clave con la que guardar el valor.
     * @param medic: objeto de tipo Medic.
     * @return true si se ha insertado correctamente.
     */
    public synchronized boolean storeMedic(String key, Medic medic) {
        mEditor = mSharedPreferences.edit();

        Gson gson = new Gson();
        String string = gson.toJson(medic);

        mEditor.putString(key, string);

        return mEditor.commit();
    }

    /**
     * Método que inserta un objecto de tipo Patient.
     *
     * @param key: clave con la que guardar el valor.
     * @param patient: objeto de tipo Patient.
     * @return true si se ha insertado correctamente.
     */
    public synchronized boolean storePatient(String key, Patient patient) {
        mEditor = mSharedPreferences.edit();

        Gson gson = new Gson();
        String string = gson.toJson(patient);

        mEditor.putString(key, string);

        return mEditor.commit();
    }

    /**
     * Metodo que devuelve un objeto de tipo Medic.
     * @return objeto de tipo Medic.
     */
    public Medic getMedic(String key)
    {
        final Gson gson = new Gson();
        final String json = mSharedPreferences.getString(key, null);

        return gson.fromJson(json, Medic.class);
    }

    /**
     * Metodo que devuelve un objeto de tipo Patient.
     * @return objeto de tipo Patient.
     */
    public Patient getPatient(String key)
    {
        final Gson gson = new Gson();
        final String json = mSharedPreferences.getString(key, null);

        return gson.fromJson(json, Patient.class);
    }
}

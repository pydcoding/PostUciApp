package com.pyd.postuciapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

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
}

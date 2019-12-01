package com.pyd.postuciapp.network;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;

public class VolleyManager {

    private static final int REQUEST_TIMEOUT = 60000;
    private static final int MAX_NUM_RETRIES = 2;
    private static final int BACK_OFF_MULTIPLIER = 0;

    private static VolleyManager mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private VolleyManager(final Context context) {
        mContext = context;

        mRequestQueue = getRequestQueue();
    }

    /**
     * Metodo que devuelve una instancia usando un Singleton.
     *
     * @param context: contexto.
     * @return instancia de Volley.
     */
    public static synchronized VolleyManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyManager(context);
        }

        return mInstance;
    }

    /**
     * Metodo que añade una peticion a la lista de peticiones.
     *
     * @param req: peticion a enviar.
     * @param <T>: tipo de la peticion.
     */
    public <T> void addToRequestQueue(@NotNull Request<T> req) {
        req.setRetryPolicy(
                new DefaultRetryPolicy(REQUEST_TIMEOUT, MAX_NUM_RETRIES, BACK_OFF_MULTIPLIER));

        getRequestQueue().add(req);
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }
}

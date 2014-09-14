package br.com.juliocnsouza.rachamanager.util;

import java.io.Serializable;

import android.app.Activity;

public class RecoverBundle {

    private static RecoverBundle recoverBundle;

    public static RecoverBundle getInstance() {
        if (recoverBundle == null) {
            recoverBundle = new RecoverBundle();
        }
        return recoverBundle;
    }

    /**
     * busca serializable nos paramentros de uma activity
     * 
     * @param activity
     * @param key
     * @return
     */
    public Serializable getSerializable(final Activity activity, final BundleBuilder.Key key) {
        final Serializable object = activity.getIntent().getSerializableExtra(key.toString());
        return object;
    }

}

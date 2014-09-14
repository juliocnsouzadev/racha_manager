package br.com.juliocnsouza.rachamanager.util;

import java.io.Serializable;

import android.os.Bundle;

public class BundleBuilder {

    public enum Key {
        SELECTED_PLAYER;
    }

    private final Bundle bundle;
    private static BundleBuilder builder;

    public BundleBuilder() {
        this.bundle = new Bundle();
    }

    public static BundleBuilder getInstance() {
        if (builder == null) {
            builder = new BundleBuilder();
        }
        return builder;
    }

    public BundleBuilder putSerializable(final Key key, final Serializable value) {
        this.bundle.putSerializable(key.toString(), value);
        return this;
    }

    public Bundle create() {
        return this.bundle;
    }

}

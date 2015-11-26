package org.gdgsantodomingo.apec.android.devfestapec.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

public final class DevfestApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(Modules.list(this));
        registerActivityLifecycleCallbacks(new ActivityInjectionHandler());
    }

    public static void injectWith(final Object target, final Context source) {
        ((DevfestApp) source.getApplicationContext()).getObjectGraph().inject(target);
    }

    public static void inject(final Activity target) {
        injectWith(target, target);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}

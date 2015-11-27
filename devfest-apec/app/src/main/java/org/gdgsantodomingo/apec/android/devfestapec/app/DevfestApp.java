package org.gdgsantodomingo.apec.android.devfestapec.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import dagger.ObjectGraph;
import org.gdgsantodomingo.apec.android.devfestapec.service.ServiceModule;

public final class DevfestApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        setupModules(this);
        objectGraph = ObjectGraph.create(Modules.list());
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

    private static void setupModules(DevfestApp devfestApp) {
        Modules.addModule(new DevfestModule(devfestApp));
        Modules.addModule(new ServiceModule());
    }
}

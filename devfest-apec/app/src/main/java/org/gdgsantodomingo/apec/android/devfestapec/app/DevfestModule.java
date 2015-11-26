package org.gdgsantodomingo.apec.android.devfestapec.app;

import android.app.Application;

import org.gdgsantodomingo.apec.android.devfestapec.DetailActivity;
import org.gdgsantodomingo.apec.android.devfestapec.MainActivity;
import org.gdgsantodomingo.apec.android.devfestapec.service.ServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    complete = false,
    library = true,
    includes = {
                   ServiceModule.class
    },
    injects = {
                  MainActivity.class,
                  DetailActivity.class
    }
)
public class DevfestModule {
    private final DevfestApp app;

    public DevfestModule(DevfestApp app) {
        this.app = app;
    }

    @Provides @Singleton Application provideApplication() {
        return app;
    }
}

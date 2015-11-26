package org.gdgsantodomingo.apec.android.devfestapec.app;

import org.gdgsantodomingo.apec.android.devfestapec.service.ServiceModule;

final class Modules {
    static Object[] list(DevfestApp app) {
        return new Object[] {
            new DevfestModule(app),
            new ServiceModule()
        };
    }

    private Modules() {

    }
}

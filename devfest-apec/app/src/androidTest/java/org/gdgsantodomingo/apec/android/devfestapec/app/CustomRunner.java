package org.gdgsantodomingo.apec.android.devfestapec.app;

import android.support.test.runner.AndroidJUnitRunner;

public class CustomRunner extends AndroidJUnitRunner {
    static {
        Modules.addModule(TestModule.class);
    }
}

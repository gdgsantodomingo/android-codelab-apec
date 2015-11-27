package org.gdgsantodomingo.apec.android.devfestapec.app;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

final class Modules {
    private static final List<Object> modules = new ArrayList<>();

    static Object[] list() {
        return modules.toArray();
    }

    public static void addModule(@NonNull final Object o) {
        modules.add(o);
    }

    private Modules() {

    }
}

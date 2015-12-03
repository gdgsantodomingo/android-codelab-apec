package org.gdgsantodomingo.apec.android.devfestapec.app;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

public class BaseTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    public BaseTestCase(final Class<T> activityClass) {
        super(activityClass);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getTargetContext();
        DevfestApp.injectWith(this, context);
    }
}

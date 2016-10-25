package gdky005.accesibilitydemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by WangQing on 2016/10/25.
 */
@RunWith(AndroidJUnit4.class)
public class TestSetting {

    @Test
    public void startSetting() {
        Context context = InstrumentationRegistry.getTargetContext();

        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");
//        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.SubSettings");


        Intent intent = new Intent();
        intent.setComponent(componentName);
        context.startActivity(intent);
    }
}

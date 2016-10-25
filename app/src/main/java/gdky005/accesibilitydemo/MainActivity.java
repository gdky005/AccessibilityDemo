package gdky005.accesibilitydemo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Toast.makeText(this, "请优先开启 " + getString(R.string.accessibilityTab) + " 的权限", Toast.LENGTH_SHORT).show();

//        startService(new Intent(this, WQAccessibilityService.class));




        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");

        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);

    }

}

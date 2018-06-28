package com.example.nsoftware.httpcallsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class WelcomeActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_NAME = "name";
    public static final String EXTRA_KEY_AGE = "age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // todo/findView.
        final TextView et_name = findViewById(R.id.et_name);
        final TextView et_age = findViewById(R.id.et_age);
        findViewById(R.id.btn_welcome)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // todo/intent.
                        WelcomeActivity welcomeActivity = WelcomeActivity.this;
                        Intent intent = new Intent(welcomeActivity, MainActivity.class);
                        intent.putExtra(EXTRA_KEY_NAME, et_name.getText().toString());
                        intent.putExtra(EXTRA_KEY_AGE,
                                Integer.parseInt(et_age.getText().toString()));
                        welcomeActivity.startActivity(intent);
                    }
                });


    }

    // todo/toasty.
    public void showToast(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                Toasty.normal(this, "Normal Toast", Toast.LENGTH_LONG,
                        ContextCompat.getDrawable(this, R.drawable.ic_android)).show();
                break;
            case R.id.btn_info:
                Toasty.info(this, "Info Toast", Toast.LENGTH_LONG)
                        .show();
                break;
            case R.id.btn_warning:
                Toasty.warning(this, "Warning Toast", Toast.LENGTH_LONG)
                        .show();
                break;
            case R.id.btn_success:
                Toasty.success(this, "Success Toast", Toast.LENGTH_LONG)
                        .show();
                break;
            case R.id.btn_error:
                Toasty.error(this, "Error Toast", Toast.LENGTH_LONG)
                        .show();
                break;
        }
    }
}

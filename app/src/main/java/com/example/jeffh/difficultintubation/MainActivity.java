package com.example.jeffh.difficultintubation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonToStartEmptyActivity((Button) findViewById(R.id.start_scanner_button), ScannerActivity.class);
    }

    private void setButtonToStartEmptyActivity(Button button, final Class newActivity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newActivity);
                startActivity(intent);
            }
        });
    }
}
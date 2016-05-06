package com.example.jeffh.difficultintubation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1972;
    private Button startScannerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startScannerButton = (Button) findViewById(R.id.start_scanner_button);
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int googlePlayServicesAvailability = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (googlePlayServicesAvailability != ConnectionResult.SUCCESS) {
            googleApiAvailability.getErrorDialog(this, googlePlayServicesAvailability, PLAY_SERVICES_RESOLUTION_REQUEST).show();
        } else {
            startScannerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {}
}
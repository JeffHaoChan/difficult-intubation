package com.example.jeffh.difficultintubation;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.File;

public class MainActivity extends AppCompatActivity implements EULADialogFragment.EULADialogListener{

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1972;
    private Button startScannerButton;
    private Button viewInformationButton;
    private TextView viewInformationPromptTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!EULAManager.getHasUserAgreed()) {
            EULADialogFragment eulaDialog = new EULADialogFragment();
            eulaDialog.show(getFragmentManager(), "EULADialog");
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EULAManager.setHasUserAgreed(true);
    };

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        EULAManager.setHasUserAgreed(false);
        System.exit(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startScannerButton = (Button) findViewById(R.id.start_scanner_button);
        viewInformationButton = (Button) findViewById(R.id.view_information_button);
        viewInformationPromptTextView = (TextView) findViewById(R.id.view_information_prompt_text_view);
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
            File file = new File(this.getFilesDir(), FileStorageParameters.FILE_NAME);
            if (!file.exists()) {
                viewInformationButton.setVisibility(View.GONE);
                viewInformationPromptTextView.setVisibility(View.GONE);
            } else {
                viewInformationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ViewInformationActivity.class);
                        startActivity(intent);
                    }
                });
                viewInformationButton.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed() {}
}
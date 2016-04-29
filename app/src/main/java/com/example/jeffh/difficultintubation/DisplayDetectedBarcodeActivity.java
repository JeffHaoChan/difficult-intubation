package com.example.jeffh.difficultintubation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayDetectedBarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detected_barcode);
        TextView patientInfoTextView = (TextView)findViewById(R.id.detected_barcode_info_text_view);
        String patientInfo = getIntent().getExtras().getString("PatientInfo");
        patientInfoTextView.setText(patientInfo);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DisplayDetectedBarcodeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

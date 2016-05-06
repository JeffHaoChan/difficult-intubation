package com.example.jeffh.difficultintubation;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DisplayDetectedBarcodeActivity extends AppCompatActivity {

    private static String TAG = "DisplayDetectedBarcodeActivity";

    private static int EXPECTED_ENTRY_COUNT = 27;

    private TextView readableDecodedBarcodeTextView;
    private TextView invalidBarcodeTextView;
    private Button returnToScannerActivityButton;
    private LinearLayout shareButtonsLinearLayout;
    private Button exportButton;
    private Button saveButton;

    private Form form;

    private String readableDecodedBarcode;

    private static String fileName = "My_Information";
    private static String appDirectory = "DifficultIntubationApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detected_barcode);

        readableDecodedBarcodeTextView = (TextView)findViewById(R.id.readable_decoded_barcode_text_view);
        invalidBarcodeTextView = (TextView)findViewById(R.id.invalid_barcode_text_view);
        returnToScannerActivityButton = (Button)findViewById(R.id.return_to_scanner_activity_button);
        returnToScannerActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayDetectedBarcodeActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });
        shareButtonsLinearLayout = (LinearLayout) findViewById(R.id.share_buttons_linear_layout);
        exportButton = (Button)findViewById(R.id.export_button);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, readableDecodedBarcode);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.chooser_message)));
            }
        });
        saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + appDirectory;
                File fileDirectory = new File(fileDirectoryPath);
                fileDirectory.mkdirs();
                if (fileDirectory.isDirectory()) {
                    Calendar calendar = Calendar.getInstance();
                    String date = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.get(Calendar.MONTH))
                                + "-" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "_" + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))
                                + "-" + String.valueOf(calendar.get(Calendar.MINUTE)) + "-" + String.valueOf(calendar.get(Calendar.SECOND));
                    File file = new File(fileDirectoryPath + "/" + fileName + "_" + date + ".txt");
                    try {
                        byte[] readableDecodedBarcodeByteArray = readableDecodedBarcode.getBytes();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            fileOutputStream.write(readableDecodedBarcodeByteArray, 0, readableDecodedBarcodeByteArray.length);
                            Toast.makeText(getApplicationContext(), "File saved successfully at " + file.getAbsolutePath() + " !", Toast.LENGTH_LONG).show();
                        } catch (IOException exception) {
                            Toast.makeText(getApplicationContext(), "File cannot be saved!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, exception.getMessage());
                        }
                    } catch (FileNotFoundException exception) {
                        Toast.makeText(getApplicationContext(), "File cannot be saved!", Toast.LENGTH_LONG).show();
                        Log.d(TAG, exception.getMessage());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "File cannot be saved!", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Directory creation failed!");
                }
            }
        });

        readableDecodedBarcodeTextView.setVisibility(View.GONE);
        invalidBarcodeTextView.setVisibility(View.GONE);
        returnToScannerActivityButton.setVisibility(View.GONE);
        shareButtonsLinearLayout.setVisibility(View.GONE);

        form = new Form();

        String barcodeString = getIntent().getExtras().getString("BarcodeString");

        List<String> entryEncodings = EntryEncodingExtractor.getEntryEncodings(barcodeString, EntryEncodingParameters.DELIMITER);

        try {
            readableDecodedBarcode = form.getReadableString(entryEncodings);
            readableDecodedBarcodeTextView.setVisibility(View.VISIBLE);
            shareButtonsLinearLayout.setVisibility(View.VISIBLE);
            invalidBarcodeTextView.setVisibility(View.GONE);
            returnToScannerActivityButton.setVisibility(View.GONE);
            readableDecodedBarcodeTextView.setText(readableDecodedBarcode);
        } catch (InvalidEncodingCountException exception) {
            readableDecodedBarcodeTextView.setVisibility(View.GONE);
            shareButtonsLinearLayout.setVisibility(View.GONE);
            invalidBarcodeTextView.setVisibility(View.VISIBLE);
            returnToScannerActivityButton.setVisibility(View.VISIBLE);
        } catch (InvalidEncodingException exception) {
            readableDecodedBarcodeTextView.setVisibility(View.GONE);
            shareButtonsLinearLayout.setVisibility(View.GONE);
            invalidBarcodeTextView.setVisibility(View.VISIBLE);
            returnToScannerActivityButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DisplayDetectedBarcodeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}

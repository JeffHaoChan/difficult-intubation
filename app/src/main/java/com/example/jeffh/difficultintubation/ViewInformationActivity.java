package com.example.jeffh.difficultintubation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ViewInformationActivity extends AppCompatActivity {

    private TextView informationTextView;
    private Button exportButton;
    private Button scanNewCodeButton;
    private String informationString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_information);
        informationTextView = (TextView) findViewById(R.id.information_text_view);
        exportButton = (Button) findViewById(R.id.information_export_button);
        scanNewCodeButton = (Button) findViewById(R.id.scan_new_code_button);
        File file = new File(this.getFilesDir(), FileStorageParameters.FILE_NAME);
        informationString = "";
        try {
            scanNewCodeButton.setVisibility(View.GONE);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                informationString += line;
                informationString += "\n";
            }
            bufferedReader.close();
            informationTextView.setText(informationString);
            exportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, informationString);
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.chooser_message)));
                }
            });
        } catch (Exception exception) {
            informationTextView.setText(R.string.file_not_found);
            exportButton.setVisibility(View.GONE);
            scanNewCodeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewInformationActivity.this, ScannerActivity.class);
                    startActivity(intent);
                }
            });
            scanNewCodeButton.setVisibility(View.VISIBLE);
        }
    }
}

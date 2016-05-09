package com.example.jeffh.difficultintubation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScannerActivity extends AppCompatActivity {
    private static String TAG = "SCANNER_ACTIVITY";
    private boolean isSearchingForNewBarcode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        final SurfaceView cameraPreview = (SurfaceView)findViewById(R.id.camera_preview);
        final BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector).setFacing(CameraSource.CAMERA_FACING_BACK).setAutoFocusEnabled(true).build();
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ContextCompat.checkSelfPermission(ScannerActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(cameraPreview.getHolder());
                    }
                } catch (IOException ie) {
                    Log.e(TAG, ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {}
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() == 1 && isSearchingForNewBarcode()) {
                    displayDetectedBarcodeString(barcodes.valueAt(0).displayValue);
                }
            }
        });
        startNewBarcodeSearch();
    }

    @Override
    public void onResume() {
        super.onResume();
        startNewBarcodeSearch();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopNewBarcodeSearch();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void startNewBarcodeSearch() {
        isSearchingForNewBarcode = true;
    }

    private void stopNewBarcodeSearch() { isSearchingForNewBarcode = false; }

    private boolean isSearchingForNewBarcode() {
        return isSearchingForNewBarcode;
    }

    private void displayDetectedBarcodeString(String barcodeString) {
        Intent intent = new Intent(ScannerActivity.this, DisplayDetectedBarcodeActivity.class);
        intent.putExtra("BarcodeString", barcodeString);
        startActivity(intent);
    }
}

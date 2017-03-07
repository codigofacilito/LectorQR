package com.example.marines.qrandroid;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void QrScanner(View view){
        mScannerView = new ZXingScannerView(this);  //Crear instancia
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();         // iniciar camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {

        Log.e("handler", rawResult.getText()); // Resultado del QR
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Resultado del codigo de barra

        // mostramos en un alertDialog el resultado del escaner
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado del escaner");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

    }

}

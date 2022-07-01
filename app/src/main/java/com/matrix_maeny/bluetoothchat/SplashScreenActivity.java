package com.matrix_maeny.bluetoothchat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Thread(){
            @RequiresApi(api = Build.VERSION_CODES.S)
            public void run(){
                try{
                    sleep(2500);
                }catch (Exception ignored){

                }finally {
                    startActivity(new Intent(SplashScreenActivity.this,MainActivity2.class));
                    finish();
                }
            }
        }.start();
    }
}
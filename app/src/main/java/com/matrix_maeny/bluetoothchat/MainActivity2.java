package com.matrix_maeny.bluetoothchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private static final int FIND_REQUEST = 3;

    AppCompatButton hostBtn, clientBtn;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestFindDevicesPermission();
        }


        hostBtn = findViewById(R.id.hostBtn);
        clientBtn = findViewById(R.id.clientBtn);
        intent = new Intent(MainActivity2.this, MainActivity.class);

    }

    public void HOSTBtn(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestFindDevicesPermission();
        } else {
            intent.putExtra("name", "Host");
            startActivity(intent);
        }
    }

    public void CLIENTBtn(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestFindDevicesPermission();
        } else {
            intent.putExtra("name", "Client");
            startActivity(intent);
        }
    }


    final void requestFindDevicesPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(MainActivity2.this)
                    .setTitle("Permission needed")
                    .setMessage("requires access to location to find devices")
                    .setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FIND_REQUEST))
                    .setNegativeButton("cancel", (dialog, which) -> {
                        tempToast();
                        dialog.dismiss();
                    }).create().show();
        } else {

            ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FIND_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == FIND_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted for finding devices", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied for finding devices", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void tempToast() {
        Toast.makeText(this, "Permission cancelled", Toast.LENGTH_LONG).show();
    } // this is used for toast messages

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(MainActivity2.this, AboutActivity.class));
        return true;
    }
}
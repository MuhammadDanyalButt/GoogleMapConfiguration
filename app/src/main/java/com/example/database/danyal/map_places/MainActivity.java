package com.example.database.danyal.map_places;

import android.app.Dialog;
import android.content.Intent;
import android.nfc.tech.TagTechnology;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int Error_Dialog_Request=9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesOK()){
            init();
        }
    }

    public void init(){
        Button btnMap=findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean isServicesOK(){

        Log.d(TAG, "isServicesOK: Checking Google Service version");
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available== ConnectionResult.SUCCESS){
                //everything is ok and the user can make Map request
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
                //Error but we can resolve it
            Log.d(TAG, "isServicesOK: Error but we can resolve it");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,Error_Dialog_Request);
            dialog.show();
        }else{
            Toast.makeText(this,"You can't make Map Requests",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

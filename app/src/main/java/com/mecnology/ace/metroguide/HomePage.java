package com.mecnology.ace.metroguide;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {


    //ImageView metromap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //metromap=findViewById(R.id.metroimg);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            switch (requestCode) {
                case 1:
                    onResume();
                    break;
            }
        }
    }

/*
    @Override
    public void onBackPressed() {
        if(metromap.getVisibility()==View.VISIBLE)
        {
            metromap.setVisibility(View.INVISIBLE);
        }
        else
            finish();
    }*/

    public void show_map(View view) {
        //metromap.setVisibility(View.VISIBLE);
        Intent i=new Intent(getApplicationContext(),MetroMapImage.class);
        startActivity(i);
    }

    public void MetroLines(View view) {
        Intent i=new Intent(getApplicationContext(),MetoLines.class);
        startActivity(i);
    }

    public void FromTo(View view) {
        Intent i=new Intent(getApplicationContext(),From_To.class);
        startActivity(i);
    }

    public void nearest(View view) {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
        else {
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(i);
        }
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}

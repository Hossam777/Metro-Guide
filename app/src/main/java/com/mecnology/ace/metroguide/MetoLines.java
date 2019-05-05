package com.mecnology.ace.metroguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MetoLines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meto_lines);
    }

    public void FirstLine(View view) {
        Intent i=new Intent(getApplicationContext(),List_of_Metro_Stations.class);
        i.putExtra("LINE","FL");
        startActivity(i);
    }

    public void SecondLine(View view) {
        Intent i=new Intent(getApplicationContext(),List_of_Metro_Stations.class);
        i.putExtra("LINE","SL");
        startActivity(i);
    }

    public void ThirdLine(View view) {
        Intent i=new Intent(getApplicationContext(),List_of_Metro_Stations.class);
        i.putExtra("LINE","TL");
        startActivity(i);
    }
}

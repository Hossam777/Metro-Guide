package com.mecnology.ace.metroguide;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,LocationListener {

    static final int REQUEST_LOCATION=1;
    LocationManager locationmanager;
    private GoogleMap mMap;
    LatLng latLng;
    float distance=0;
    int postion=0;
    LatLng NS;
    Location location;

    String[] stationsname;
    LatLng[] stationspos={new LatLng(29.848978,31.334233),new LatLng(29.86260,31.324879),
            new LatLng(29.869472,31.320063),new LatLng(29.879016,31.313434),
            new LatLng(29.897138,31.304005),new LatLng(29.906345,31.299557),
            new LatLng(29.925962,31.287543),new LatLng(29.936417,31.281399),
            new LatLng(29.946760,31.272982),new LatLng(29.953319,31.262972),
            new LatLng(29.960301,31.257646),new LatLng(29.969797,31.250864),
            new LatLng(29.982010,31.242161),new LatLng(29.995480,31.231158),
            new LatLng(30.006187,31.229484),new LatLng(30.029292,31.235320),
            new LatLng(30.037017,31.238357),new LatLng(30.044539,31.234488),
            new LatLng(30.053420,31.238720),new LatLng(30.056971,31.242370),
            new LatLng(30.061071,31.246054),new LatLng(30.069024,31.264616),
            new LatLng(30.077259,31.277783),new LatLng(30.082030,31.287480),
            new LatLng(30.087210,31.294124),new LatLng(30.091252,31.298901),
            new LatLng(30.097646,31.304558),new LatLng(30.105929,31.310452),
            new LatLng(30.105928,31.310448),new LatLng(30.113256,31.313952),
            new LatLng(30.120906,31.313710),new LatLng(30.131049,31.318989),
            new LatLng(30.139315,31.324423),new LatLng(30.152143,31.335564),
            new LatLng(30.163643,31.338367),new LatLng(29.981054,31.212333),
            new LatLng(29.995574,31.208514),new LatLng(30.005494,31.207830),
            new LatLng(30.010622,31.207055),new LatLng(30.017435,31.203760),
            new LatLng(30.026074,31.200958),new LatLng(30.035866,31.200136),
            new LatLng(30.038436,31.212228),new LatLng(30.041853,31.225074),
            new LatLng(30.045323,31.244166),new LatLng(30.052334,31.246801),
            new LatLng(30.070894,31.245104),new LatLng(30.080584,31.245409),
            new LatLng(30.087958,31.245507),new LatLng(30.097834,31.245013),
            new LatLng(30.104062,31.245591),new LatLng(30.113668,31.248754),
            new LatLng(30.122429,31.244547)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        Resources res=getResources();
        stationsname=res.getStringArray(R.array.from_to);
        locationmanager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getLocation();
        mapFragment.getMapAsync(this);


    }




    private void getLocation() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }
        else
        {
            locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, (LocationListener) this);
            location= locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location!=null)
            {
                double lat=location.getLatitude();
                double lon=location.getLongitude();
                latLng=new LatLng(lat,lon);
            }
            else
                Toast.makeText(getApplicationContext(),"Unable to get location",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(latLng).title("My Postion"));
        float zoom = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        postion=find_nearest();

        NS=stationspos[postion];
        mMap.addMarker(new MarkerOptions().position(NS).title(stationsname[postion]));
        String url = getRequestUrl(latLng,NS);
        TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
        taskRequestDirections.execute(url);
    }


    private String getRequestUrl(LatLng origin, LatLng dest) {
        //Value of origin
        String str_org = "origin=" + origin.latitude +","+origin.longitude;
        //Value of destination
        String str_dest = "destination=" + dest.latitude+","+dest.longitude;
        //Set value enable the sensor
        String sensor = "sensor=false";
        //Mode for find direction
        String mode = "mode=driving";
        //Build the full param
        String param = str_org +"&" + str_dest + "&" +sensor+"&" +mode;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onLocationChanged(location);
    }

    @Override
    public void onLocationChanged(Location location) {

        float[] newdistance=new float[10];

        Location.distanceBetween(latLng.latitude,latLng.longitude,stationspos[postion].latitude,stationspos[postion].longitude,newdistance);
        if(newdistance[0]-distance<0)
        {
            latLng=new LatLng(location.getLatitude(),location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
            mMap.addMarker(new MarkerOptions().position(NS).title(stationsname[postion]));
            String url = getRequestUrl(latLng,NS);
            TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
            taskRequestDirections.execute(url);
            Toast.makeText(getApplicationContext(),"Distance = "+String.format("%.2f",distance/1000)+" KM ",Toast.LENGTH_SHORT).show();
            distance=newdistance[0];

        }
        else if(newdistance[0]-distance>50)
        {
            latLng=new LatLng(location.getLatitude(),location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
            mMap.addMarker(new MarkerOptions().position(NS).title(stationsname[postion]));
            String url = getRequestUrl(latLng,NS);
            TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
            taskRequestDirections.execute(url);
            Toast.makeText(getApplicationContext(),"Distance = "+String.format("%.2f",distance/1000)+" KM ",Toast.LENGTH_SHORT).show();
            distance=newdistance[0];

            AlertDialog alertDialog = new AlertDialog.Builder(MapsActivity.this).create();
            alertDialog.setTitle("Alert Dialog");
            alertDialog.setMessage("You are Going far form your destination.");

            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            alertDialog.show();
        }
        else
        {
            latLng=new LatLng(location.getLatitude(),location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
            mMap.addMarker(new MarkerOptions().position(NS).title(stationsname[postion]));
            String url = getRequestUrl(latLng,NS);
            TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
            taskRequestDirections.execute(url);

            Toast.makeText(getApplicationContext(),"Distance = "+String.format("%.2f",distance/1000)+" KM ",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> > {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions!=null) {
                mMap.addPolyline(polylineOptions);
            } else {
                Toast.makeText(getApplicationContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    int find_nearest()
    {
        float[]distanses=new float[53];
        float[]tmp=new float[10];
        for(int i=0;i<53;i++)
        {
            Location.distanceBetween(latLng.latitude,latLng.longitude,stationspos[i].latitude,stationspos[i].longitude,tmp);
            distanses[i]=tmp[0];
        }
        float least=distanses[0];
        int pos=0;
        for(int i=1;i<53;i++) {
            if (least > distanses[i]) {
                pos = i;
                least = distanses[i];
            }
        }
        distance=least;
        Toast.makeText(getApplicationContext(),"Distance = "+String.format("%.2f",least/1000)+" KM ",Toast.LENGTH_SHORT).show();
        return pos;
    }

}

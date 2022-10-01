package com.rsydfhmy.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocationn extends AppCompatActivity {
    //inisiasi variable location
    Button btnLocation;
    TextView etLoc1, etLoc2, etLoc3, etLoc4, etLoc5;
    FusedLocationProviderClient fusedLocationProviderClient;
//    private Object Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_locationn);

        //Assign variable
        btnLocation = findViewById(R.id.btnLoc);
        etLoc1 = findViewById(R.id.etLoc1);
        etLoc2 = findViewById(R.id.etLoc2);
        etLoc3 = findViewById(R.id.etLoc3);
        etLoc4 = findViewById(R.id.etLoc4);
        etLoc5 = findViewById(R.id.etLoc5);
        //inisiasi fused location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(CurrentLocationn.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //when permissionn granted
                    getMyLocation();
                } else {
                    //when permissionn denided
                    ActivityCompat.requestPermissions(CurrentLocationn.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getMyLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        //inisiasi geoCoder
                        Geocoder geocoder = new Geocoder(CurrentLocationn.this,
                                Locale.getDefault());
                        //inisiasi address List
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);

                        //set Latitude on text view
                        etLoc1.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Latitude :</b><br></font>"
                                        + addresses.get(0).getLatitude()
                        ));
                        //set Longtitude
                        etLoc2.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Longtitude :</b><br></font>"
                                        + addresses.get(0).getLongitude()
                        ));
                        //set Country Name
                        etLoc3.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Country Name :</b><br></font>"
                                        + addresses.get(0).getCountryName()
                        ));
                        //set Locality
                        etLoc4.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Locality :</b><br></font>"
                                        + addresses.get(0).getLocality()
                        ));
                        //set Address
                        etLoc5.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Address :</b><br></font>"
                                        + addresses.get(0).getAddressLine(0)
                        ));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
package gosoccour.com.gosuccour.Vistas.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Mechanic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmergencyActivity extends AppCompatActivity {
    //TextView mensaje1, mensaje2, mensaje3;
    Localizacion Local;
    LocationManager mlocManager;
    ImageView imageView;
    Coordenadas coordenadas;
    private String client, carData;
    private Long idFactura;
    private ArrayList<String> servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        mlocManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //obtener extras
        client=getIntent().getStringExtra("client");
        servicios=getIntent().getStringArrayListExtra("servicios");
        idFactura=getIntent().getLongExtra("idFactura",Integer.MAX_VALUE);
        carData=getIntent().getStringExtra("car");
        //permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
      /*
        mensaje1 = (TextView) findViewById(R.id.texto1);
        mensaje2 = (TextView) findViewById(R.id.texto2);
        mensaje3 = (TextView) findViewById(R.id.texto3);
*/
        //  codigo para pedir permisos, va junto al codigo comentado de abajo
/*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
*/
/*
        mensaje2.setText(lastLocation.getLatitude()+"");
        mensaje3.setText(lastLocation.getLongitude()+"");
*/
        imageView = (ImageView)findViewById(R.id.boton_emergencia);

        imageView.bringToFront();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coordenadas = new Coordenadas();

                coordenadas.setLatitude(lastLocation.getLatitude());
                coordenadas.setLongitude(lastLocation.getLongitude());

                APIService apiService = ApiUtils.getAPIService();

                apiService.getMechanic(coordenadas.getLatitude(),coordenadas.getLongitude()).enqueue(new Callback<Mechanic>() {
                    @Override
                    public void onResponse(Call<Mechanic> call, Response<Mechanic> response) {

                        if(response.isSuccessful()){
                            Log.e("Success", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));                        }
                        Toast.makeText(EmergencyActivity.this, "Peticion de emergencia enviada!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),MechanicActivity.class);
                        i.putExtra("client",client);
                        i.putExtra("idFactura",idFactura);
                        Gson gson = new Gson();
                        i.putExtra("mechanic",gson.toJson(response.body()));
                        servicios =getIntent().getStringArrayListExtra("servicios");
                        i.putStringArrayListExtra("servicios",servicios);
                        i.putExtra("car",carData);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<Mechanic> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fallo!!!", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });


            }
        });




    }

    //Codi per obtenir la localitzacio actualitzada cada X milisegons

    private void locationStart() {
         mlocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean networkProviderExists = false;
        if(mlocManager.getAllProviders().contains("network")){
            networkProviderExists  = true;
        }

        Local = new Localizacion();


        Local.setEmergencyActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,}, 1000);
            return;
        }
        //mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100000,0,(LocationListener)Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100000, 0, (LocationListener) Local);
/*
        mensaje1.setText("Localización agregada");
        mensaje2.setText("");
*/
        //setLocation(Local);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    /*
                    mensaje2.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));
                            */
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Aqui empieza la Clase Localizacion
    public class Localizacion implements LocationListener {
        EmergencyActivity emergencyActivity;

        public EmergencyActivity getEmergencyActivity() {
            return emergencyActivity;
        }

        public void setEmergencyActivity(EmergencyActivity emergencyActivity) {
            this.emergencyActivity = emergencyActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas Coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            //mensaje1.setText(Text);
            this.emergencyActivity.setLocation(loc);

            Coordenadas coordenadas = new Coordenadas();

            coordenadas.setLatitude(loc.getLatitude());
            coordenadas.setLongitude(loc.getLongitude());

            APIService apiService = ApiUtils.getAPIService();

            apiService.sendCoordenadas(coordenadas).enqueue(new Callback<Coordenadas>() {
                @Override
                public void onResponse(Call<Coordenadas> call, Response<Coordenadas> response) {
                    //Coordenadas coordenadas1=response.body();
                    Toast.makeText(emergencyActivity, "Entra en el response", Toast.LENGTH_SHORT).show();

                    if(response.isSuccessful()){
                        Toast.makeText(emergencyActivity, new GsonBuilder().setPrettyPrinting().create().toJson(response.body()), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Coordenadas> call, Throwable t) {
                    Toast.makeText(emergencyActivity, "Fallo!!!", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            //mensaje1.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //mensaje1.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }


}

package gosoccour.com.gosuccour.Vistas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.models.Client;
import gosoccour.com.gosuccour.models.Mechanic;

public class MechanicActivity extends AppCompatActivity {

    private String mechanicObject;
    private TextView nombre, pais, correo, telefono, address,cp;
    private Mechanic mechanic;
    private Button buttonVolver;
    private ArrayList<String> servicios;
    private String carData;
    private String client;
    private long idFactura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);

        init();


        //obtener el objeto
        Gson gson = new Gson();
        mechanicObject=getIntent().getStringExtra("mechanic");
        mechanic = gson.fromJson(mechanicObject, Mechanic.class);

        setComponentes();

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);

                i.putExtra("idFactura",idFactura);
                //enviamos datos del coche
                i.putExtra("car",carData);
                i.putExtra("client",client);
                //pasar arraylist de servicios usados para no volver a dejar al
                // usuario solicitar el mismo servicio para el mismo coche
                i.putStringArrayListExtra("servicios",servicios);
                startActivity(i);

            }
        });



    }

    public void setComponentes(){
        nombre.setText(mechanic.getSurname() + " " + mechanic.getLastname());
        pais.setText(mechanic.getCountry());
        correo.setText(mechanic.getEmail());
        telefono.setText(mechanic.getPhone());
        address.setText(mechanic.getAddress());
        cp.setText(mechanic.getCp());



    }

    public void init(){
        nombre=(TextView)findViewById(R.id.nombreApellido_mechanic);
        pais=(TextView)findViewById(R.id.pais_mechanic);
        correo=(TextView)findViewById(R.id.email_mechanic);
        telefono=(TextView)findViewById(R.id.num_mechanic);
        address=(TextView)findViewById(R.id.address);
        cp=(TextView)findViewById(R.id.cp);
        buttonVolver= (Button)findViewById(R.id.boton_volver);


        idFactura=getIntent().getLongExtra("idFactura",Integer.MAX_VALUE);
        servicios =getIntent().getStringArrayListExtra("servicios");
        carData=getIntent().getStringExtra("car");
        client=getIntent().getStringExtra("client");

    }
}

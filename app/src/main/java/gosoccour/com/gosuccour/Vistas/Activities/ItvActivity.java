package gosoccour.com.gosuccour.Vistas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Car;
import gosoccour.com.gosuccour.models.Client;
import gosoccour.com.gosuccour.models.Itv;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItvActivity extends AppCompatActivity {

    private ImageView cocheLogo;
    private TextView modelo, matricula, neumaticos, motor, añoItv;
    private Button contratar_itv;
    private String carData;
    private int año_proxima_itv;
    private APIService apiService;
    private Car car;
    private ArrayList<String> servicios;
    private Long idFactura;
    private Itv itvObjectToSend;
    private final String ITV_IDENT ="itv";
    private String client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itv);


        getDataCarFromExtras();
        init();
        setComponents();

        //funcion boton
        contratar_itv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    sendPost();
                    //añadimos identificador itv para no volver a entrar en esta activity si ya se ha contratado itv
                    servicios.add(ITV_IDENT);

            }
        });

    }

    //obtener extras, objeto Car del coche escogido en la activity CochesActivity
    public void getDataCarFromExtras(){
        carData=getIntent().getStringExtra("car");
        car = new Gson().fromJson(carData, Car.class);
    }

    //setear componentes
    public void setComponents(){
        //calcular año de la siguiente itv
        año_proxima_itv= car.calculItvYear(car.getMatriculation());

        //setear textos
        modelo.setText(car.getModel());
        matricula.setText("Matricula: " + car.getMatriculation());
        neumaticos.setText("Neumaticos: " + car.getPneumatic());
        motor.setText("Motor: " + car.getTypeMotor());
        añoItv.setText("Proxim año ITV: " + año_proxima_itv);
        //setear imagen
        setImagen(car.getModel());
    }


    //enviar post retrofit
    public void sendPost(){


                //creamos objeto itv a enviar


                apiService = ApiUtils.getAPIService();
                apiService.savePostItv(itvObjectToSend).enqueue(new Callback<Itv>() {
                    @Override
                    public void onResponse(Call<Itv> call, Response<Itv> response) {


                        if (response.isSuccessful()){
                            Log.e("Success", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.putExtra("idFactura",idFactura);
                            //enviar datos coche
                            i.putExtra("car",carData);
                            //pasar arraylist de servicios usados para no volver a dejar al
                            // usuario solicitar el mismo servicio para el mismo coche
                            Toast.makeText(ItvActivity.this, "Servicio ITV contratado!", Toast.LENGTH_SHORT).show();
                            i.putStringArrayListExtra("servicios",servicios);
                            i.putExtra("client",client);
                            startActivity(i);
                        }

                    }

                    @Override
                    public void onFailure(Call<Itv> call, Throwable t) {
                        Toast.makeText(ItvActivity.this, "Error, comprueba tu conexion a internet", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });



    }

    //inicializar componentes
    public void init(){

        //interfaz
        modelo=(TextView) findViewById(R.id.modelo_coche_itv);
        matricula=(TextView) findViewById(R.id.matricula_coche_itv);
        neumaticos= (TextView) findViewById(R.id.neumaticos_coche_itv);
        motor= (TextView) findViewById(R.id.motor_coche_itv);
        cocheLogo=(ImageView) findViewById(R.id.logo_marca_itv);
        contratar_itv=(Button) findViewById(R.id.contratar_itv);
        añoItv=(TextView)findViewById(R.id.proximo_año_itv);

        //extras
        servicios=getIntent().getStringArrayListExtra("servicios");
        idFactura = getIntent().getLongExtra("idFactura", Integer.MAX_VALUE);
        client = getIntent().getStringExtra("client");

        //objeto a enviar
        itvObjectToSend = new Itv(idFactura,String.valueOf(año_proxima_itv));
    }


    //metodo par setear imagen, logo de marca segun nombre de marca del coche
    public void setImagen(String marca){

        switch (marca){
            case "Audi":
                cocheLogo.setImageResource(R.drawable.audi);
                break;
            case "Alpina":
                cocheLogo.setImageResource(R.drawable.alpina);
                break;
            case "Chevrolet":
                cocheLogo.setImageResource(R.drawable.chevrolet);
                break;
            case "Suzuki":
                cocheLogo.setImageResource(R.drawable.suzuki);
                break;
            case "Bmw":
                cocheLogo.setImageResource(R.drawable.bmw);
                break;
            case "Honda":
                cocheLogo.setImageResource(R.drawable.honda);
                break;
            case "Cadillac":
                cocheLogo.setImageResource(R.drawable.cadillac);
                break;
            case "Toyota":
                cocheLogo.setImageResource(R.drawable.toyota);
                break;
            case "Citroen":
                cocheLogo.setImageResource(R.drawable.citroen);
                break;
            case "Fiat":
                cocheLogo.setImageResource(R.drawable.fiat);
                break;
            case "Lancia":
                cocheLogo.setImageResource(R.drawable.lancia);
                break;
            case "Ford":
                cocheLogo.setImageResource(R.drawable.ford);
                break;
            case "Hyundai":
                cocheLogo.setImageResource(R.drawable.hyundai);
                break;
        }
    }
}

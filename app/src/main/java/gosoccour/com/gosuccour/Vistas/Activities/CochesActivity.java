package gosoccour.com.gosuccour.Vistas.Activities;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.adapters.AdaptadorSelecCoche;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Car;
import gosoccour.com.gosuccour.models.Client;
import gosoccour.com.gosuccour.models.RequestFactura;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CochesActivity extends Activity implements AdaptadorSelecCoche.ItemClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Car> cars;
    private AdaptadorSelecCoche adapter;
    int idClient;
    private String client;

    APIService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio);
        idClient=getIntent().getIntExtra("idClient",0);

        recyclerView = (RecyclerView) findViewById(R.id.cochesSelector);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));


        apiService = ApiUtils.getAPIService();
        Call<Client> call = apiService.getClient(idClient);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client client = response.body();
                cars = new ArrayList<>(client.getListCars());
                adapter = new AdaptadorSelecCoche(cars);
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(CochesActivity.this);

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    @Override
    public void onClick(View view, int position) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String token = preferences.getString("token","");

        apiService.getFacturaId(cars.get(position).getId(), token).enqueue(new Callback<RequestFactura>() {
            @Override
            public void onResponse(Call<RequestFactura> call, Response<RequestFactura> response) {
                /*
                obtener id factura enviando id del coche, por cada envio de id coche se le asigna un id factura nuevo
                ya que se interpreta que esta iniciando una nueva factura.
                 */

                RequestFactura requestFactura=response.body();
                Log.d("succes",new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));
                Long id = requestFactura.getId();
                client=getIntent().getStringExtra("client");
                Intent i;
                //inicializacion del arraylist para que puedan usarlo el resto de activities y fragments
                ArrayList<String> servicios = new ArrayList<>();

                //obtener el numero de la activity a lanzar

                /*
                    El numero de la activity hace referencia al servicio elcogido y es el numero de la posicion
                    en el grid cardview del servicio escogido
                    Es decir, si se esoje el servicio Mantenimiento, el numero de la posicion en el que se encuentra en el grid, es 0
                    entonces ese numero se le pasa a esta activity (CochesActivity), el numero se almacena en la variable numActivity,
                    y se usa en una sentencia switch para iniciar mas tarde la activity correspondiente al servicio escogido enviandole la
                    idFactura que se ha obtenido del servidor.
                 */


                int numActivity= getIntent().getIntExtra("activity",Integer.MAX_VALUE);
                System.out.println("num activity: " +numActivity);
                //Toast.makeText(CochesActivity.this, numActivity+"", Toast.LENGTH_SHORT).show();
                switch (numActivity){
                    case 0:
                         i = new Intent(getApplicationContext(), MantenimientoActivity.class);
                         enviarExtrasIniciarActivity(i,id,servicios,position,client);
                            break;
                    case 1:
                        i = new Intent(getApplicationContext(), EmergencyActivity.class);
                        enviarExtrasIniciarActivity(i,id,servicios,position,client);
                        break;
                    case 2:
                        i = new Intent(getApplicationContext(), RevisionActivity.class);
                        enviarExtrasIniciarActivity(i,id,servicios,position,client);
                            break;
                    case 3:
                        i = new Intent(getApplicationContext(), ItvActivity.class);
                        enviarExtrasIniciarActivity(i,id,servicios,position,client);
                            break;
                    case 4:
                        //este caso se da cuando se esta cambiando de coche, al pulsar el boton cambiar de coche en el Mainactivity en el fragment ServiciosFragment
                        i= new Intent(getApplicationContext(),MainActivity.class);
                        //en parametroarrayList servicios se envia inicializado porque se seleccionara un nuevo coche
                        enviarExtrasIniciarActivity(i,id,new ArrayList<String>(),position,client);
                            break;
                }
                finish();

            }

            @Override
            public void onFailure(Call<RequestFactura> call, Throwable t) {
                    t.printStackTrace();
            }
        });


    }

    //metodo para enviar la informacion necesaria a las activities
    public void enviarExtrasIniciarActivity(Intent i, Long id, ArrayList<String> servicios, int position, String client){
        i.putExtra("idFactura",id);
        i.putStringArrayListExtra("servicios",servicios);
        i.putExtra("client",client);
        //enviar por extra el objeto Car
        i.putExtra("car", new Gson().toJson(cars.get(position)));
        startActivity(i);
    }

}

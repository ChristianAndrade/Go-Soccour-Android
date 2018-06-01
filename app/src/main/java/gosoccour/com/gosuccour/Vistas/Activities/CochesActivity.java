package gosoccour.com.gosuccour.Vistas.Activities;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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

    APIService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio);


        recyclerView = (RecyclerView) findViewById(R.id.cochesSelector);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));



        apiService = ApiUtils.getAPIService();
        Call<Client> call = apiService.getClient(1);

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

        //String token = preferences.getString("token","");
        String token ="IB2I4UB23I42323O";
        apiService.getFacturaId(cars.get(position).getId(), token).enqueue(new Callback<RequestFactura>() {
            @Override
            public void onResponse(Call<RequestFactura> call, Response<RequestFactura> response) {
                RequestFactura requestFactura=response.body();
                Toast.makeText(CochesActivity.this, new GsonBuilder().setPrettyPrinting().create().toJson(response.body()), Toast.LENGTH_SHORT).show();
                Long id = requestFactura.getId();
                Intent i;
                //inicializacion del arraylist para que puedan usarlo el resto de activities y fragments
                ArrayList<String> servicios = new ArrayList<>();
                int numActivity= getIntent().getIntExtra("activity",9);
                Toast.makeText(CochesActivity.this, numActivity+"", Toast.LENGTH_SHORT).show();
                switch (numActivity){
                    case 0:
                         i = new Intent(getApplicationContext(), MantenimientoActivity.class);
                         i.putExtra("idFactura",id);
                         i.putStringArrayListExtra("servicios",servicios);
                         startActivity(i);
                            break;
                    case 2:
                        i = new Intent(getApplicationContext(), RevisionActivity.class);
                        i.putExtra("idFactura",id);
                        i.putStringArrayListExtra("servicios",servicios);
                        Toast.makeText(CochesActivity.this, "revision", Toast.LENGTH_SHORT).show();
                        startActivity(i);
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

}

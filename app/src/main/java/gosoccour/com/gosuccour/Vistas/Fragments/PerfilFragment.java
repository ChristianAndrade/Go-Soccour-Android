package gosoccour.com.gosuccour.Vistas.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.Vistas.Activities.MainActivity;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    public PerfilFragment() {
        // Required empty public constructor
    }
    Client clientObject;
    APIService apiService;
    TextView nombre,email,telefono,pais,ciudad;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_perfil, container, false);

        initInterface(view);


/*
        Gson gson = new Gson();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        String json = sharedPreferences.getString("client", "");
        clientObject = gson.fromJson(json, Client.class);
*/
        MainActivity mainActivity = (MainActivity)getActivity();
        clientObject=mainActivity.getCliente();

        apiService = ApiUtils.getAPIService();
        Call<Client> call = apiService.getClient(clientObject.getId());

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client client = response.body();
                nombre.setText(client.getSurname() + " " + client.getLastname());
                email.setText(client.getEmail());
                telefono.setText(client.getPhone());
                pais.setText(client.getCountry());
                ciudad.setText(client.getCity());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });


        return view;
    }

    public void initInterface(View view){
        nombre = (TextView) view.findViewById(R.id.texto_nombre);
        email = (TextView) view.findViewById(R.id.texto_email);
        telefono = (TextView) view.findViewById(R.id.texto_telefono);
        pais = (TextView) view.findViewById(R.id.texto_pais);
        ciudad = (TextView) view.findViewById(R.id.texto_ciudad);

    }

}

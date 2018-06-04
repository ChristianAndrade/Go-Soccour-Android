package gosoccour.com.gosuccour.Vistas.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gosoccour.com.gosuccour.Vistas.Activities.MainActivity;
import gosoccour.com.gosuccour.adapters.AdaptadorMicuentaCoche;
import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Car;
import gosoccour.com.gosuccour.models.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CochesFragment extends Fragment {


    public CochesFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private ArrayList<Car> cars;
    private AdaptadorMicuentaCoche adapter;

    APIService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_coches, container, false);

        MainActivity mainActivity=(MainActivity)getActivity();

        int idClient=mainActivity.getCliente().getId();

        recyclerView = (RecyclerView) view.findViewById(R.id.cochesRecycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));


        apiService = ApiUtils.getAPIService();
        Call<Client> call = apiService.getClient(idClient);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client client = response.body();
                cars = new ArrayList<>(client.getListCars());
                adapter = new AdaptadorMicuentaCoche(cars);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });

        return view;
    }

    private void initViews(){

    }

}

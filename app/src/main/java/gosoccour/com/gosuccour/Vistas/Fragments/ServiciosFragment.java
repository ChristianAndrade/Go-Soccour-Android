package gosoccour.com.gosuccour.Vistas.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.Vistas.Activities.CochesActivity;
import gosoccour.com.gosuccour.Vistas.Activities.EmergencyActivity;
import gosoccour.com.gosuccour.Vistas.Activities.ItvActivity;
import gosoccour.com.gosuccour.Vistas.Activities.MainActivity;
import gosoccour.com.gosuccour.Vistas.Activities.MantenimientoActivity;
import gosoccour.com.gosuccour.Vistas.Activities.RevisionActivity;
import gosoccour.com.gosuccour.models.Client;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiciosFragment extends Fragment {


    public ServiciosFragment() {
        // Required empty public constructor
    }
    GridLayout gridInicio;
    Button selecCoche;
    Client client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_servicios, container, false);

        selecCoche =(Button)view.findViewById(R.id.selecCoche);
        selecCoche.setVisibility(View.GONE);
        gridInicio = (GridLayout) view.findViewById(R.id.gridInicio);

        setEvents(gridInicio);

        return view;

    }

    private void setEvents(GridLayout gridInicio) {
        //Intent para cocheActivity
        Intent intentCocheActivity = new Intent(getActivity(), CochesActivity.class);
        /*
        Instanciamos la activity padre contenedora de este fragment para obtener los extras
        con los metodos creados en la activity
         */
        MainActivity activity=(MainActivity)getActivity();
        Long id_factura=activity.getIdFactura();
        ArrayList<String> servicios = activity.getServicios();
        String carData = activity.getCarData();
        int idClient = activity.getCliente().getId();
        client=activity.getCliente();


        //si id_factura no es 10, decir, no coge el valor por defecto asignado, quiere decir que se ha seleccionado un coche, entonces hacemos aparecer un boton que
        //nos pueda dejar volver a cambiar de coche
        if (id_factura!=Integer.MAX_VALUE) {
            selecCoche.setVisibility(View.VISIBLE);
            selecCoche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentCocheActivity.putStringArrayListExtra("servicios", new ArrayList<String>());
                    intentCocheActivity.putExtra("idClient",idClient);
                    intentCocheActivity.putExtra("activity", 4);
                    intentCocheActivity.putExtra("client",new Gson().toJson(client));
                    startActivity(intentCocheActivity);

                }
            });
        }

        for (int i=0; i<gridInicio.getChildCount(); i++){
            CardView servicio = (CardView) gridInicio.getChildAt(i);

            int finalI = i;
            servicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i;
                    if (id_factura==Integer.MAX_VALUE) {
                        intentCocheActivity.putExtra("activity", finalI);
                        intentCocheActivity.putExtra("idClient",idClient);
                        intentCocheActivity.putExtra("client",new Gson().toJson(client));
                        startActivity(intentCocheActivity);
                       // Toast.makeText(getActivity(), "servicio " + finalI, Toast.LENGTH_SHORT).show();
                    } else{
                        /*
                        si idFactura no es 10, quiere decir que se ha seleccionado coche.
                        entonces damos la opcion al usuairo de cambiar de coche


                        carData es un json con los datos del objeto Car

                         */

                        switch (finalI){
                            case 0:
                                if(servicios.contains("mantenimiento")){
                                    Toast.makeText(activity, "Ya has contratado mantenimiento para este coche", Toast.LENGTH_SHORT).show();

                                }else {
                                    i = new Intent(getActivity(), MantenimientoActivity.class);
                                    i.putExtra("idFactura",id_factura);
                                    i.putExtra("client",new Gson().toJson(client));
                                    i.putStringArrayListExtra("servicios",servicios);
                                    i.putExtra("car", carData);
                                    startActivity(i);

                                }
                                break;

                            case 1:

                                i = new Intent(getActivity(), EmergencyActivity.class);
                                i.putExtra("idFactura",id_factura);
                                i.putExtra("client",new Gson().toJson(client));
                                i.putStringArrayListExtra("servicios",servicios);
                                i.putExtra("car", carData);
                                startActivity(i);

                                break;


                            case 2:
                                if(servicios.contains("revision")) {

                                    Toast.makeText(activity, "Ya has contratado revisión para este coche", Toast.LENGTH_SHORT).show();

                                }else {

                                    i = new Intent(getActivity(), RevisionActivity.class);
                                    i.putExtra("idFactura", id_factura);
                                    i.putStringArrayListExtra("servicios",servicios);
                                    i.putExtra("client",new Gson().toJson(client));
                                    i.putExtra("car", carData);
                                    startActivity(i);}
                                    break;

                            case 3:
                                if(servicios.contains("itv")){
                                    Toast.makeText(getActivity(), "Ya has contratado ITV para este coche", Toast.LENGTH_SHORT).show();
                                }else {
                                    i = new Intent(getActivity(), ItvActivity.class);
                                    i.putExtra("idFactura",id_factura);
                                    i.putStringArrayListExtra("servicios",servicios);
                                    i.putExtra("client",new Gson().toJson(client));
                                    i.putExtra("car", carData);
                                    startActivity(i);
                                }
                                break;





                        }

                    }
                }
            });
        }

    }



}

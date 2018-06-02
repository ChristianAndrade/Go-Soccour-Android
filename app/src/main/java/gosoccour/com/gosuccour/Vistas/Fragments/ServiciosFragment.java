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
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.Vistas.Activities.CochesActivity;
import gosoccour.com.gosuccour.Vistas.Activities.MainActivity;
import gosoccour.com.gosuccour.Vistas.Activities.MantenimientoActivity;
import gosoccour.com.gosuccour.Vistas.Activities.RevisionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiciosFragment extends Fragment {


    public ServiciosFragment() {
        // Required empty public constructor
    }
    GridLayout gridInicio;
    Button selecCoche;

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



        for (int i=0; i<gridInicio.getChildCount(); i++){
            CardView servicio = (CardView) gridInicio.getChildAt(i);

            int finalI = i;
            servicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent para cocheActivity
                    Intent intentCocheActivity = new Intent(getActivity(), CochesActivity.class);
                    MainActivity activity=(MainActivity)getActivity();
                    Long id=activity.getIdFactura();
                    ArrayList<String> servicios = activity.getServicios();
                    Intent i;
                    if (id==10) {
                        //CochesActivity.class

                        intentCocheActivity.putExtra("activity", finalI);
                        startActivity(intentCocheActivity);
                        Toast.makeText(getActivity(), "servicio " + finalI, Toast.LENGTH_SHORT).show();
                    } else{
                        //si idFactura no es 10, quiere decir que se ha seleccionado coche.
                        //entonces damos la opcion al usuairo de cambiar de coche
                        selecCoche.setVisibility(View.VISIBLE);
                        selecCoche.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(intentCocheActivity);
                            }
                        });

                        switch (finalI){
                            case 0:
                                if(servicios.contains("mantenimiento")){
                                    Toast.makeText(activity, "Ya has contratado servicio para este coche", Toast.LENGTH_SHORT).show();

                                }else {
                                    i = new Intent(getActivity(), MantenimientoActivity.class);
                                    i.putExtra("idFactura",id);
                                    i.putStringArrayListExtra("servicios",servicios);
                                    startActivity(i);

                                }


                                break;
                            case 2:
                                if(servicios.contains("revision")) {

                                    Toast.makeText(activity, "Ya has contratado revisión para este coche", Toast.LENGTH_SHORT).show();

                                }else {

                                    i = new Intent(getActivity(), RevisionActivity.class);
                                    i.putExtra("idFactura", id);
                                    i.putStringArrayListExtra("servicios",servicios);
                                    Toast.makeText(getActivity(), "revision", Toast.LENGTH_SHORT).show();
                                    startActivity(i);}

                        }

                    }
                }
            });
        }


/*
        CardView mantenimiento = (CardView) gridInicio.getChildAt(0);

        mantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MantenimientoActivity.class );
                startActivity(intent);
                Toast.makeText(getActivity(), "servicio_mantenimiento_icon", Toast.LENGTH_SHORT).show();

            }
        });

        CardView emergency = (CardView) gridInicio.getChildAt(1);

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Emergencia", Toast.LENGTH_SHORT).show();

            }
        });

        CardView revision = (CardView) gridInicio.getChildAt(2);

        revision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "revisión", Toast.LENGTH_SHORT).show();

            }
        });

        CardView itv = (CardView) gridInicio.getChildAt(3);

        itv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "ITV", Toast.LENGTH_SHORT).show();

            }
        });
        */

    }



}

package gosoccour.com.gosuccour.Vistas.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.Vistas.Activities.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {


    public InicioFragment() {
        // Required empty public constructor
    }

    GridLayout gridInicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_inicio, container, false);

        gridInicio = (GridLayout) view.findViewById(R.id.gridInicio);

        setEvents(gridInicio);

        return view;

    }

    private void setEvents(GridLayout gridInicio) {


        CardView mantenimiento = (CardView) gridInicio.getChildAt(0);

        mantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), mantenimientoActivity.class );
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

    }

}

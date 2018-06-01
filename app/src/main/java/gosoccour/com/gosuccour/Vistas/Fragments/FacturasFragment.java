package gosoccour.com.gosuccour.Vistas.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gosoccour.com.gosuccour.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacturasFragment extends Fragment {


    public FacturasFragment() {
        // Required empty public constructor
    }

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_facturas, container, false);

       // textView=(TextView) view.findViewById(R.id.textFacturas);



       // textView.setText();

        return view;
    }

}

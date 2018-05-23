package gosoccour.com.gosuccour.Vistas.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gosoccour.com.gosuccour.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MicuentaFragment extends Fragment {

    AppBarLayout appbar;
    TabLayout tabs;

    public MicuentaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_micuenta, container, false);

            if(savedInstanceState==null){
                View parent =(View) container.getParent();
                appbar = (AppBarLayout) parent.findViewById(R.id.appbar);
                tabs = new TabLayout(getActivity());
                tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
                appbar.addView(tabs);
            }


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appbar.removeView(tabs);
    }

}

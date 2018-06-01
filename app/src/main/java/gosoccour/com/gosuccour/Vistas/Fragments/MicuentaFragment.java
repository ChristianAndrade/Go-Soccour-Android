package gosoccour.com.gosuccour.Vistas.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.adapters.AdaptadorMiCuenta;

/**
 * A simple {@link Fragment} subclass.
 */
public class MicuentaFragment extends Fragment {

    AppBarLayout appbar;
    TabLayout tabs;
    ViewPager viewPager;

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


                viewPager = (ViewPager) view.findViewById(R.id.pager);
                tabsViewPager(viewPager);
                tabs.setupWithViewPager(viewPager);
            }


        return view;
    }

    private void tabsViewPager(ViewPager viewPager) {
        AdaptadorMiCuenta adapter = new AdaptadorMiCuenta(getFragmentManager());
        adapter.addFragment(new PerfilFragment(), getString(R.string.titulo_tab_perfil));
        adapter.addFragment(new CochesFragment(), getString(R.string.titulo_tab_coches));
        adapter.addFragment(new FacturasFragment(), getString(R.string.titulo_tab_facturas));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appbar.removeView(tabs);
    }


}






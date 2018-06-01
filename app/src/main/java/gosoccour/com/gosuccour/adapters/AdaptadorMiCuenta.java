package gosoccour.com.gosuccour.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alguien on 05/23/2018.
 */

public class AdaptadorMiCuenta extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentos = new ArrayList<>();
    private final List<String> titulosFragmentos = new ArrayList<>();

    public AdaptadorMiCuenta(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return fragmentos.size();
    }

    public void addFragment(android.support.v4.app.Fragment fragment, String title) {
        fragmentos.add(fragment);
        titulosFragmentos.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titulosFragmentos.get(position);
    }
}

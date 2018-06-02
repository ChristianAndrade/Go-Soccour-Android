package gosoccour.com.gosuccour.Vistas.Activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;


import java.util.ArrayList;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.Vistas.Fragments.MicuentaFragment;

import gosoccour.com.gosuccour.Vistas.Fragments.InfoFragment;
import gosoccour.com.gosuccour.Vistas.Fragments.ServiciosFragment;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView;
    private APIService apiService;
    private final String IMAGE_URL= ApiUtils.BASE_URL+"/images";
    private Long id;
    private ArrayList<String> servicios; //servicios usados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        //id factura pasada per les activitys dels serveis tramitats
        //es per no tornar a la activity coches
        id=getIntent().getLongExtra("idFactura", 10);
        servicios=getIntent().getStringArrayListExtra("servicios");

        //borrar sombra
        getSupportActionBar().setElevation(0);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);
       // imageView = (ImageView) findViewById(R.id.)

        setFragmentByDefault();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction=false;
                Fragment fragment=null;

                switch (item.getItemId()){
                    case R.id.menu_inicio:
                        fragment = new ServiciosFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_cuenta:
                        fragment = new MicuentaFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                }

                if (fragmentTransaction) {
                    changeFragment(fragment,item);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });

    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault(){
        changeFragment(new ServiciosFragment(), navigationView.getMenu().getItem(0));

    }

    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                // abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Funcio per enviar la id al fragment
    public Long getIdFactura() {
        return id;
    }

    public ArrayList<String> getServicios(){
        return servicios;
    }
}

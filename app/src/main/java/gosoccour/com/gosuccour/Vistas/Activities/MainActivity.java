package gosoccour.com.gosuccour.Vistas.Activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.Vistas.Fragments.MicuentaFragment;

import gosoccour.com.gosuccour.Vistas.Fragments.InfoFragment;
import gosoccour.com.gosuccour.Vistas.Fragments.ServiciosFragment;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView;
    private APIService apiService;
    private final String IMAGE_URL= ApiUtils.BASE_URL+"/uploads/";
    SharedPreferences mSettings;
    Client cli;
    SharedPreferences  mPrefs;
    //extras
    private Long id; //id de la factura, se pasa por extra
    private ArrayList<String> servicios; //servicios usados
    private String objetoCoche;
    private String token;

    //variables de los datos del usuario que se mostraran en el navigation drawer
    ImageView fotoPerfil;
    TextView nombreApellidos, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String client = getIntent().getStringExtra("client");
        System.out.println(client);


        cli=getCliente();
        System.out.println("Main activity");
        System.out.println(cli.getSurname());
        System.out.println(cli.getCity());
        System.out.println(cli.getCountry());


        apiService=ApiUtils.getAPIService();
        token=getIntent().getStringExtra("token");

        setToolbar();
        //id factura pasada per les activitys dels serveis tramitats
        //datos necesarios para no volver a pasar por la activity coches si ya se ha seleccionado un coche y ya tiene un id factura
        id=getIntent().getLongExtra("idFactura", Integer.MAX_VALUE);
        servicios=getIntent().getStringArrayListExtra("servicios");

        objetoCoche=getIntent().getStringExtra("car");

        //borrar sombra
        getSupportActionBar().setElevation(0);
        //obtener objeto cliente de sharedpreferences


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);
       // imageView = (ImageView) findViewById(R.id.)

        setFragmentByDefault();
        //datos usuairo navDrawer
        datosNavDrawer();

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
                    case R.id.cerrar:
                        moveTaskToBack(true);
                        finish();
                        break;
                    case R.id.aviso_legal:
                        Toast.makeText(MainActivity.this, "Aviso Legal", Toast.LENGTH_SHORT).show();
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


    public String getCarData(){
        return objetoCoche;
    }

    public void datosNavDrawer(){




        //obtener el header del navigationdrawer
        View viewNavHeader = navigationView.getHeaderView(0);
        fotoPerfil=(ImageView) viewNavHeader.findViewById(R.id.fotoPerfil);
        nombreApellidos=(TextView) viewNavHeader.findViewById(R.id.nombreApellido);
        correo=(TextView) viewNavHeader.findViewById(R.id.correo);


        nombreApellidos.setText(cli.getSurname()+" "+ cli.getLastname());
        correo.setText(cli.getEmail());

        Bundle extraUrl = getIntent().getExtras();
        //image1.setImageBitmap(bmp);
        Picasso.get().load(IMAGE_URL+ cli.getPhoto()).into(fotoPerfil);
        System.out.println("llegado aqui");
    }

    public Client getCliente(){

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("client");
        Client obj = gson.fromJson(strObj, Client.class);

        return obj;
    }
}

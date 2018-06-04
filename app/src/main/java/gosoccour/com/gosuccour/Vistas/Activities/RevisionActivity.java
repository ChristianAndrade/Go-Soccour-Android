package gosoccour.com.gosuccour.Vistas.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Plan;
import gosoccour.com.gosuccour.models.Products;
import gosoccour.com.gosuccour.models.Revision;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevisionActivity extends AppCompatActivity {


    private final String REVISION_HUELLA="revision";


    private GridLayout gridMant;
    private List<Products> tasks;
    private Products[] product;
    private FloatingActionButton fab;
    private Revision revisionPost;
    private Plan plan;
    private List<Plan> planList;
    private RadioButton radioButton;
    private RadioGroup opciones;

    /*
        Una vez contratado el servicio ya no puede volver a contratarse
        El ArrayList<String> servicios se pasa alrededor de todas las activities registrando su huella
        para que puedan ser iniciadas solo una vez
     */
    private ArrayList<String> servicios;


    private String carData;
    private String client;
    private long idFactura;

    //API
    private APIService apiService;

    //Variables per a la selecció dels items
    Boolean item1, item2, item3, item4, item5, item6, item7, item8, item9, item10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision);
        tasks = new ArrayList<>();

        init();
        gridMant = (GridLayout) findViewById(R.id.gridRevision);

        functionRadioButtons();


        //accions dels cardView
        setEvents(gridMant);

        apiService = ApiUtils.getAPIService();

        fab = (FloatingActionButton) findViewById(R.id.fabRevision);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(RevisionActivity.this, "id factura: "+idFactura, Toast.LENGTH_SHORT).show();
                revisionPost = new Revision( idFactura,13.0,planList, tasks);
                sendPost(revisionPost);



            }
        });


    }

    public  void functionRadioButtons(){

        //variables per a guardar el radio seleccionado
        planList = new ArrayList<>();
        plan = new Plan();

        //parametros por defecto
        plan.setPorcentaje(0.15);
        plan.setName("6 meses");
        //---------------------

        planList.add(plan);
        //radio group
        opciones = (RadioGroup) findViewById(R.id.opciones);


        opciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.seis_meses){

                    planList.get(0).setName("6 meses");
                    planList.get(0).setPorcentaje(0.15);
                }
                else if(i == R.id.un_año){
                    planList.get(0).setName("1 año");
                    planList.get(0).setPorcentaje(0.25);
                }
                else if(i == R.id.dos_años){
                    planList.get(0).setName("2 años");
                    planList.get(0).setPorcentaje(0.5);
                }
            }
        });



    }

    public void sendPost(Revision revision) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);

        apiService.savePostRevision(revision).enqueue(new Callback<Revision>() {
            @Override
            public void onResponse(Call<Revision> call, Response<Revision> response) {
                if (response.isSuccessful()) {
                    Log.e("Success", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));
                    servicios.add(REVISION_HUELLA);
                    Toast.makeText(RevisionActivity.this, "Servicio revision contratado!", Toast.LENGTH_SHORT).show();
                    //enviar extras
                    i.putExtra("idFactura",idFactura);
                    i.putExtra("car",carData);
                    i.putExtra("client",client);
                    i.putStringArrayListExtra("servicios",servicios);
                    startActivity(i);

                }
            }

            @Override
            public void onFailure(Call<Revision> call, Throwable t) {
                Toast.makeText(RevisionActivity.this, "Error!, comprueba conexion a internet", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                startActivity(i);
            }
        });

    }


    private void setEvents(GridLayout gridMant) {

        for (int i = 0; i < gridMant.getChildCount(); i++) {

            final CardView cardView = (CardView) gridMant.getChildAt(i);

            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {

                        if (item1 != null && item1) {

                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item1 = false;
                            //afegeix la product a la llista de tasques
                            tasks.remove(product[finalI]);

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));

                            //S'elimina de la llista
                            tasks.add(product[finalI]);

                            item1 = true;
                        }


                    } else if (finalI == 1) {

                        if (item2 != null && item2) {

                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            tasks.remove(product[finalI]);
                            item2 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));

                            tasks.add(product[finalI]);
                            item2 = true;

                        }

                    } else if (finalI == 2) {


                        if (item3 != null && item3) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            tasks.remove(product[finalI]);
                            item3 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item3 = true;

                        }

                    } else if (finalI == 3) {

                        if (item4 != null && item4) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item4 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item4 = true;

                        }

                    } else if (finalI == 4) {


                        if (item5 != null && item5) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item5 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item5 = true;

                        }

                    } else if (finalI == 5) {

                        if (item6 != null && item6) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item6 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item6 = true;

                        }


                    } else if (finalI == 6) {

                        if (item7 != null && item7) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item7 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item7 = true;

                        }

                    } else if (finalI == 7) {


                        if (item8 != null && item8) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item8 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item8 = true;

                        }

                    } else if (finalI == 8) {

                        if (item9 != null && item9) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item9 = false;


                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item9 = true;
                        }

                    } else if (finalI == 9) {
                        if (item10 != null && item10) {
                            tasks.remove(product[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item10 = false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(product[finalI]);
                            item10 = true;


                        }
                    }
                }
            });

        }


    }


    public void init() {

        //crear array con posiciones fijas (10 productos en total)
        product = new Products[10];

        //inicializar productos
        product[0] = new Products(1L, "Direction", "Revision de sistemas y mecanismos", 100);
        product[1] = new Products(2L, "Brakes", "Cambio de Pastillas y Discos", 150);
        product[2] = new Products(3L, "Suspension", "Revisión y cambio de Amortiguadores", 120);
        product[3] = new Products(4L, "Pneumatic", "Revisión y Camio de Neumaticos", 80);
        product[4] = new Products(5L, "Lights", "Revisión y Camio de luces", 90);
        product[5] = new Products(6L, "Batery", "Revision, Carga, Cambio de bateria", 20);
        product[6] = new Products(7L, "Levels and Filters", "Revisión y cambio de Niveles y Filtros", 110);
        product[7] = new Products(8L, "Air Conditioner", "Revisión del Aire Acondicionado", 40);
        product[8] = new Products(9L, "Moons and CrystalCleaner", "Revisión y cambio de Limpia Cristales", 70);
        product[9] = new Products(10L, "Injection", "Revisión y cambio de Inyectores", 120);


        //obtener extras
        idFactura=getIntent().getLongExtra("idFactura",Integer.MAX_VALUE);
        carData=getIntent().getStringExtra("car");
        servicios =getIntent().getStringArrayListExtra("servicios");
        client=getIntent().getStringExtra("client");

    }


}

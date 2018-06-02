package gosoccour.com.gosuccour.Vistas.Activities;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import gosoccour.com.gosuccour.models.Maintenance;
import gosoccour.com.gosuccour.models.Products;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MantenimientoActivity extends AppCompatActivity {

   /*
    int opcion = 0;
    ImageView imageCambiar;
     */

    final static String ID = "mant";

    private GridLayout gridMant;
    private List<Products> tasks;
    private Products[] tasca;
    private FloatingActionButton fab;
    private Maintenance mantenimientoPost;
    ArrayList<String> servicios;

    //prueba
    TextView postSend;

    //API
    private APIService apiService;

    //Variables per a la selecció dels items
    Boolean item1,item2,item3,item4,item5,item6,item7,item8,item9,item10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento);

        tasks= new ArrayList<>();

        generateTasks();
        gridMant = (GridLayout) findViewById(R.id.gridMantenimiento);

        //prueba
        postSend = (TextView) findViewById(R.id.postSend);


        //accions dels cardView
        setEvents(gridMant);

        apiService = ApiUtils.getAPIService();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MantenimientoActivity.this, "funka 1", Toast.LENGTH_SHORT).show();


                /*
                Codigo para que el usuario pueda seleccionar un servicio del mismo tipo por coche
                 */

                // Obtenir el id de la factura
                long idFactura=getIntent().getLongExtra("idFactura",0);

                 servicios =getIntent().getStringArrayListExtra("servicios");
                //añadir el nombre del servicio
                servicios.add("mantenimiento");




                Toast.makeText(MantenimientoActivity.this, "id factura: "+idFactura, Toast.LENGTH_SHORT).show();
                mantenimientoPost =  new Maintenance(idFactura,13.0,tasks);
                /*
                mantenimientoPost.setId(ID);
                mantenimientoPost.setListProducts(tasks);
*/
                sendPost(mantenimientoPost);

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("idFactura",idFactura);
                //pasar arraylist de servicios usados para no volver a dejar al
                // usuario solicitar el mismo servicio para el mismo coche
                i.putStringArrayListExtra("servicios",servicios);
                startActivity(i);

            }
        });


    }

    public void sendPost(Maintenance post){



        apiService.savePostMantenimiento(post).enqueue(new Callback<Maintenance>() {
            @Override
            public void onResponse(Call<Maintenance> call, Response<Maintenance> response) {
                Toast.makeText(MantenimientoActivity.this, "funka 2", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()) {
                    Log.e("Success", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));
                    showResponse(new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));
                    Toast.makeText(MantenimientoActivity.this, "Enviado!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Maintenance> call, Throwable t) {
                Toast.makeText(MantenimientoActivity.this, "Error al enviar!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showResponse(String response) {
        if(postSend.getVisibility() == View.GONE) {
            postSend.setVisibility(View.VISIBLE);
        }
        postSend.setText(response);
    }


    private void setEvents(GridLayout gridMant) {

        for(int i = 0; i < gridMant.getChildCount(); i++){

            final CardView cardView = (CardView) gridMant.getChildAt(i);

            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI ==0){

                        if(item1 != null && item1){

                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item1=false;
                            //afegeix la tasca a la llista de tasques
                            tasks.remove(tasca[finalI]);

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));

                            //S'elimina de la llista
                            tasks.add(tasca[finalI]);

                            item1=true;
                        }


                    }else if (finalI == 1){

                        if(item2 != null && item2){

                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            tasks.remove(tasca[finalI]);
                            item2=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));

                            tasks.add(tasca[finalI]);
                            item2=true;

                        }

                    }else if (finalI == 2) {


                        if(item3 != null && item3){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            tasks.remove(tasca[finalI]);
                            item3=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item3=true;

                        }

                    }else if (finalI == 3){

                        if(item4 != null && item4){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item4=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item4=true;

                        }

                    }else if (finalI == 4) {


                        if(item5 != null && item5){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item5=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item5=true;

                        }

                    }else if (finalI == 5) {

                        if(item6 != null && item6){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item6=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item6=true;

                        }


                    }else if (finalI == 6) {

                        if(item7 != null && item7){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item7=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item7=true;

                        }

                    }else if (finalI == 7) {


                        if(item8 != null && item8){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item8=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item8=true;

                        }

                    }else if (finalI == 8) {

                        if(item9 != null && item9){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item9=false;



                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item9=true;
                        }

                    }else if (finalI == 9) {
                        if(item10 != null && item10){
                            tasks.remove(tasca[finalI]);
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            item10=false;

                        } else {
                            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorGreen));
                            tasks.add(tasca[finalI]);
                            item10=true;


                        }
                    }

                    for (Products tasa: tasks) {
                        System.out.print(tasa.getId()+" ");
                    }
                    System.out.println("\n");
                }
            });

        }


    }


    public void generateTasks(){
        tasca=new Products[10];
        for (int i =0; i<tasca.length ; i++){
            tasca[i]=new Products();
        }

        //tasks
        tasca[0] = new Products(1,"Direction","Revision de sistemas y mecanismos",100);
        tasca[1] = new Products(2,"Brakes","Cambio de Pastillas y Discos",150);
        tasca[2] = new Products(3,"Suspension","Revisión y cambio de Amortiguadores",120);
        tasca[3] = new Products(4,"Pneumatic","Revisión y Camio de Neumaticos",80);
        tasca[4] = new Products(5,"Lights","Revisión y Camio de luces",90);
        tasca[5] = new Products(6,"Batery","Revision, Carga, Cambio de bateria",20);
        tasca[6] = new Products(7,"Levels and Filters","Revisión y cambio de Niveles y Filtros",110);
        tasca[7] = new Products(8,"Air Conditioner","Revisión del Aire Acondicionado",40);
        tasca[8] = new Products(9,"Moons and CrystalCleaner","Revisión y cambio de Limpia Cristales",70);
        tasca[9] = new Products(10,"Injection","Revisión y cambio de Inyectores",120);


    }



}
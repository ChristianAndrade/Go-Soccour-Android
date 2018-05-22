package gosoccour.com.gosuccour.Vistas.Activities;


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
import gosoccour.com.gosuccour.models.Task;
import gosoccour.com.gosuccour.models.MantenimientoPost;
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
    private List<Task> tasks;
    private Task [] tasca;
    private FloatingActionButton fab;
    private MantenimientoPost mantenimientoPost;


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
                // Click action
                mantenimientoPost =  new MantenimientoPost();
                mantenimientoPost.setId(ID);
                mantenimientoPost.setTask(tasks);

                sendPost(mantenimientoPost);



            }
        });


    }

    public void sendPost(MantenimientoPost post){



        apiService.savePostMantenimiento(post).enqueue(new Callback<MantenimientoPost>() {
            @Override
            public void onResponse(Call<MantenimientoPost> call, Response<MantenimientoPost> response) {
                Toast.makeText(MantenimientoActivity.this, "funka 2", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()) {
                    Log.e("Success", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));
                    showResponse(new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));
                    Toast.makeText(MantenimientoActivity.this, "Enviado!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MantenimientoPost> call, Throwable t) {
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
        tasca=new Task[10];
        for (int i =0; i<tasca.length ; i++){
            tasca[i]=new Task();
        }
        for(int i = 0; i < gridMant.getChildCount(); i++){

            final CardView cardView = (CardView) gridMant.getChildAt(i);

            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(finalI ==0){
                            tasca[finalI].setId("dir");
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
                            tasca[finalI].setId("fre");
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
                            tasca[finalI].setId("sus");

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
                            tasca[finalI].setId("neu");
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
                            tasca[finalI].setId("luc");

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
                            tasca[finalI].setId("bat");
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
                            tasca[finalI].setId("niv");
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
                            tasca[finalI].setId("air");

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
                            tasca[finalI].setId("lun");
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
                            tasca[finalI].setId("iny");
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

                        for (Task tasa: tasks) {
                            System.out.print(tasa.getId()+" ");
                        }
                        System.out.println("\n");
                    }
                });

        }


    }
}

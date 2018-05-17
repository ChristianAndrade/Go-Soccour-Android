package gosoccour.com.gosuccour.Vistas.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gosoccour.com.gosuccour.R;
import gosoccour.com.gosuccour.models.Task;


public class mantenimientoActivity extends AppCompatActivity {

   /*
    int opcion = 0;
    ImageView imageCambiar;
     */

   final static String ID = "mant";

    GridLayout gridMant;
    List<Task> tasks;

    //Variables per a la selecció dels items
    Boolean item1,item2,item3,item4,item5,item6,item7,item8,item9,item10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento);

        tasks= new ArrayList<>();

        gridMant = (GridLayout) findViewById(R.id.gridMantenimiento);

        setEvents(gridMant);

/*

        imageCambiar = (ImageView) findViewById(R.id.imageCambiar);

        imageCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(opcion != 0 && opcion ==2){

                    imageCambiar.setImageResource(R.drawable.servicio_itv_icon);
                    opcion =1;

                } else { imageCambiar.setImageResource(R.drawable.servicio_mantenimiento_icon);
                    opcion = 2;

                }



            }
        });
       */

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

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item1=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item1=true;

                            }


                        }else if (finalI == 1){

                            if(item2 != null && item2){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item2=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item2=true;

                            }

                        }else if (finalI == 2) {

                            if(item3 != null && item3){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item3=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item3=true;

                            }

                        }else if (finalI == 3){

                            if(item4 != null && item4){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item4=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item4=true;

                            }

                        }else if (finalI == 4) {

                            if(item5 != null && item5){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item5=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item5=true;

                            }

                        }else if (finalI == 5) {
                            if(item6 != null && item6){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item6=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item6=true;

                            }


                        }else if (finalI == 6) {

                            if(item7 != null && item7){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item7=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item7=true;

                            }

                        }else if (finalI == 7) {

                            if(item8 != null && item8){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item8=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item8=true;

                            }

                        }else if (finalI == 8) {
                            if(item9 != null && item9){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item9=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item9=true;
                            }

                        }else if (finalI == 9) {
                            if(item10 != null && item10){

                                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                item10=false;

                            } else {
                                cardView.setBackgroundColor(Color.parseColor("#289745"));
                                item10=true;
                            }

                        }
                    }
                });

        }

    }
}

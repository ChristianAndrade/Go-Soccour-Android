package gosoccour.com.gosuccour.Login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import gosoccour.com.gosuccour.R;




public class MainImage extends AppCompatActivity {

    ImageView image;
    Bitmap bmp;
    ImageView image1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_lista);

       // Bundle extras =getIntent().getExtras();
       // image1 = new ImageView(this);
        image = (ImageView)findViewById(R.id.imagenCliente);
        //System.out.println(extra);
        //byte[] byteArray = android.util.Base64.decode(extra, android.util.Base64.DEFAULT);
/*
        byte [] byteArray = extras.getByteArray("imageBytes");
        System.out.println("hola caracola");


        SavePhotoTask savePhotoTask = new SavePhotoTask();
        savePhotoTask.doInBackground(byteArray);

        File file =  new File(getApplicationContext().getFilesDir()+"/photo.jpg");


        bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

        System.out.println("bitmap");
*/
        Bundle extraUrl = getIntent().getExtras();
        String URL = extraUrl.getString("url");
        //image1.setImageBitmap(bmp);
        Picasso.get().load(URL+"/images/spring.png").into(image);
        System.out.println("llegado aqui");

    }

    public class SavePhotoTask extends AsyncTask<byte[], String, String> {

        @Override
        protected String doInBackground(byte[]... bytes) {
            File photo=new File(getApplicationContext().getFilesDir(),"photo.jpg");

            if (photo.exists()) {
                photo.delete();
            }

            try {
                FileOutputStream fos=new FileOutputStream(photo.getPath());

                fos.write(bytes[0]);
                fos.close();
            }
            catch (java.io.IOException e) {
                Log.e("PictureDemo", "Exception in photoCallback", e);
            }

            return(null);
        }
    }

}

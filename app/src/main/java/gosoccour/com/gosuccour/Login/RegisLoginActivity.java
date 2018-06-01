package gosoccour.com.gosuccour.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import gosoccour.com.gosuccour.Login.models.Token;
import gosoccour.com.gosuccour.Login.models.image;
import gosoccour.com.gosuccour.data.ApiUtils;
import gosoccour.com.gosuccour.interfaces.APIService;
import retrofit2.Call;
import gosoccour.com.gosuccour.Vistas.Activities.MainActivity;

import gosoccour.com.gosuccour.R;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisLoginActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText editUser, editPassword;
    ImageView image1;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnSendLogin=  findViewById(R.id.btnSendLogin);
        editUser = (EditText) findViewById(R.id.user);
        editPassword = (EditText) findViewById(R.id.password);
        btnSendLogin.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                String user = editUser.getText().toString();
                String password = editPassword.getText().toString();
                if(!user.isEmpty() && !password.isEmpty()){
                      // sendImage();
                     checkLogin(user, password);
                }else{
                    Toast.makeText(RegisLoginActivity.this, "User / Password Field Empty !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void sendImage (){
        apiService = ApiUtils.getAPIService();
//192.168.1.10
        Call<image> calls = apiService.getImageProfile(3);

        calls.enqueue(new Callback<image>() {
            @Override
            public void onResponse(Call<image> call, Response<image> response) {
               // image image=response.body();
                //System.out.println(image.getPhoto().charAt(image.getPhoto().length()-1));
               // byte[] backToBytes = Base64.decode(image.getPhoto() , Base64.DEFAULT);
                /*
                SavePhotoTask savePhotoTask = new SavePhotoTask();
                savePhotoTask.doInBackground(backToBytes);


                File images = new File(String.valueOf(getApplicationContext().getFilesDir()+"/photo.jpg"));


                Bitmap myBitmap = BitmapFactory.decodeByteArray(backToBytes,0,backToBytes.length);
                System.out.println("");
                image1.setImageBitmap(myBitmap);
                */



                Intent intent = new Intent(RegisLoginActivity.this, MainImage.class);
                intent.putExtra("url",ApiUtils.BASE_URL);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<image> call, Throwable t) {
                Toast.makeText(RegisLoginActivity.this, "Image not shared !", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void checkLogin(final String username, final String password){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080").addConverterFactory(GsonConverterFactory.create()).build();
        //192.168.1.10
        //10.10.25.237
        ClientInterface service = retrofit.create(ClientInterface.class);
        Call<Token> call = service.loginCheck(username, password);

        call.enqueue(new Callback<Token>(){

            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                //recibimos como respuesta un token que lo guardaremos en Shared Preferences
                String token = "[uuid=" + response.body().getUuid() + "]";
                if (token != "") {
                    preferences = PreferenceManager.getDefaultSharedPreferences(RegisLoginActivity.this);
                    preferences.edit().putString("token", token).commit();
                    Toast.makeText(RegisLoginActivity.this, "Register Sucessfully!", Toast.LENGTH_SHORT).show();



                    Intent intent = new Intent(RegisLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisLoginActivity.this, "You not Register¡¡", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(RegisLoginActivity.this, "User not exists !", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

}


package gosoccour.com.gosuccour.Vistas.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gosoccour.com.gosuccour.Login.RegisLoginActivity;
import gosoccour.com.gosuccour.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, RegisLoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}

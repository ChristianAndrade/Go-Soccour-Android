package gosoccour.com.gosuccour.Login.models;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import gosoccour.com.gosuccour.R;

public class Authentication  {
    private String username;
    private Boolean authenticate;
    private String token;

    public Authentication() {}

    public Authentication(Boolean authenticate, String token, String username) {
        super();
        this.authenticate = authenticate;
        this.token = token;
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String userId) {
        this.username = userId;
    }

    public Boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Boolean authenticate) {
        this.authenticate = authenticate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString(){
        return "Auth {username: "+this.username+", token: "+this.token+", authenticate: "+this.authenticate+"}";
    }

}

package gosoccour.com.gosuccour.Login.models;

public class Login {
    private String username;
    private Boolean authentication;
    private String token;

    public Login(){};

    public Login(String username, Boolean authentication, String token) {
        this.username = username;
        this.authentication = authentication;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", authentication=" + authentication +
                ", token='" + token + '\'' +
                '}';
    }
}

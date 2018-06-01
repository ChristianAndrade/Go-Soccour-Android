package gosoccour.com.gosuccour.Login;

import gosoccour.com.gosuccour.Login.models.Token;
import gosoccour.com.gosuccour.Login.models.image;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClientInterface {
    @POST("/api/login")
    Call<Token> loginCheck(@Query("username") String username, @Query("password")String password);

    @GET("/pg/image")
    Call<image> getImageProfile(@Query("id") long id);


}

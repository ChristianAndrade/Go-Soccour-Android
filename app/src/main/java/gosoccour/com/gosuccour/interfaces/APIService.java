package gosoccour.com.gosuccour.interfaces;


import gosoccour.com.gosuccour.models.Task;
import gosoccour.com.gosuccour.models.MantenimientoPost;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Mohamed on 05/17/2018.
 */

public interface APIService {

    @POST("/posts")
    Call<MantenimientoPost> savePostMantenimiento(@Body MantenimientoPost post);

}

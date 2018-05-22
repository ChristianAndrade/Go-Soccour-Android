package gosoccour.com.gosuccour.interfaces;

import android.database.Observable;

import gosoccour.com.gosuccour.models.Task;
import gosoccour.com.gosuccour.models.mantenimientoPost;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mohamed on 05/17/2018.
 */

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Observable<Task> savePostMantenimiento(@Body mantenimientoPost post);

}

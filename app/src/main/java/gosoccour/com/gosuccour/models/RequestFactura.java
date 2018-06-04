package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamed on 05/29/2018.
 */

//CLASE Modelo JSON

public class RequestFactura {


    //id factura
    @SerializedName("id")
    @Expose
    Long id;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

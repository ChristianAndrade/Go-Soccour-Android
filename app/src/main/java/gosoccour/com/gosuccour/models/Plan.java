package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamed on 05/31/2018.
 */

public class Plan {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("porcentaje")
    @Expose
    private Double porcentaje;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}

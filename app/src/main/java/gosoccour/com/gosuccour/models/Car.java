package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alguien on 05/24/2018.
 */

public class Car {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("model")
    @Expose
    private String model;

    @SerializedName("matriculation")
    @Expose
    private String matriculation;

    @SerializedName("pneumatic")
    @Expose
    private String pneumatic;

    @SerializedName("typeMotor")
    @Expose
    private String typeMotor;

    @SerializedName("displacement")
    @Expose
    private String displacement;

    @SerializedName("client")
    @Expose
    private Client client;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMatriculation() {
        return matriculation;
    }

    public void setMatriculation(String matriculation) {
        this.matriculation = matriculation;
    }

    public String getPneumatic() {
        return pneumatic;
    }

    public void setPneumatic(String pneumatic) {
        this.pneumatic = pneumatic;
    }

    public String getTypeMotor() {
        return typeMotor;
    }

    public void setTypeMotor(String typeMotor) {
        this.typeMotor = typeMotor;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}

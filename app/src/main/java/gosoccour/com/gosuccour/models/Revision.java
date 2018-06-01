package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alguien on 05/31/2018.
 */

public class Revision {

    @SerializedName("factura_id")
    @Expose
    private Long id;

    @SerializedName("identify")
    @Expose
    final  String identify="revision";

    private Double price;

    private List<Plan> listPlan;

    private List<Products> listProducts;

    public Revision(Long id,Double price, List<Plan> listPlan, List<Products> listProducts) {
        this.id=id;
        this.price = price;
        this.listPlan = listPlan;
        this.listProducts = listProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentify() {
        return identify;
    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Plan> getListPlan() {
        return listPlan;
    }

    public void setListPlan(List<Plan> listPlan) {
        this.listPlan = listPlan;
    }

}

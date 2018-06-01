package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alguien on 05/17/2018.
 */

public class Maintenance {



    @SerializedName("factura_id")
    @Expose
    private Long id;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("identify")
    @Expose
    private String identify="maintenance";

    @SerializedName("listProducts")
    @Expose
    private List<Products> listProducts;

    public Maintenance() {}

    public Maintenance(Long id, Double price, List<Products> listProducts) {
        this.id = id;
        this.price = price;
        this.listProducts = listProducts;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

}

package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itv {
    @SerializedName("factura_id")
    @Expose
    private Long id;
    private Double price;
    private String year;
    private String identify="itv"

    public Itv(Long id, Double price, String year, String identify) {
        this.id = id;
        this.price = price;
        this.year = year;
        this.identify = identify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}

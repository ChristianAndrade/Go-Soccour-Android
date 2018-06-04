package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itv {

    @SerializedName("factura_id")
    @Expose
    private Long id;
    @SerializedName("price")
    @Expose
    private Double price=120.0;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("identify")
    @Expose
    private String identify="itv";

    public Itv(Long id, String year) {
        this.id = id;
        this.year = year;
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

package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alguien on 05/16/2018.
 */

public class Task {
    @SerializedName("id")
    @Expose
    String id;

    public Task() {
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

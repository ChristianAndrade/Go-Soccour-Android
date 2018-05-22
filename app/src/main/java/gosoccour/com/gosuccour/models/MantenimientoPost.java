package gosoccour.com.gosuccour.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alguien on 05/17/2018.
 */

public class MantenimientoPost {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("task")
    @Expose
    private List<Task> task = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}

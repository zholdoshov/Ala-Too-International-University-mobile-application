package zholdoshov.ala_tooexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Transcript {

    @SerializedName("All marks")
    @Expose
    private ArrayList<Mark> marks = null;

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Mark> marks) {
        this.marks = marks;
    }
}

package ala_too.mob_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mark {

    @SerializedName("Final")
    @Expose
    private String _final;
    @SerializedName("Midterm")
    @Expose
    private String midterm;
    @SerializedName("Subject")
    @Expose
    private String subject;

    public String getFinal() {
        return _final;
    }

    public void setFinal(String _final) {
        this._final = _final;
    }

    public String getMidterm() {
        return midterm;
    }

    public void setMidterm(String midterm) {
        this.midterm = midterm;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "_final='" + _final + '\'' +
                ", midterm='" + midterm + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

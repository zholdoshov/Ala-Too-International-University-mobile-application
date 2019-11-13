package ala_too.mob_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentInfo {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Academic status")
    @Expose
    private String academicStatus;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Birth Date")
    @Expose
    private String birthDate;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Surname")
    @Expose
    private String surname;
    @SerializedName("Department")
    @Expose
    private String department;
    @SerializedName("Education status")
    @Expose
    private String educationStatus;
    @SerializedName("Enter year")
    @Expose
    private String enterYear;
    @SerializedName("Group")
    @Expose
    private String group;

    public StudentInfo(String name, String phone, String academic_Status, String email, String gender, String birth_Date, String id, String surname, String department, String education_Status, String group) {
        this.name = name;
        this.phone = phone;
        academicStatus = academic_Status;
        this.email = email;
        this.gender = gender;
        birthDate = birth_Date;
        this.id = id;
        this.surname = surname;
        this.department = department;
        educationStatus = education_Status;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(String educationStatus) { this.educationStatus = educationStatus; }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
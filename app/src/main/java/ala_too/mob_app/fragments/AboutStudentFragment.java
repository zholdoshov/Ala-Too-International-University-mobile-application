package ala_too.mob_app.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import ala_too.mob_app.MainActivity;
import ala_too.mob_app.R;
import ala_too.mob_app.model.StudentInfo;
import ala_too.mob_app.remote.IAUApi;

public class AboutStudentFragment extends Fragment {

    TextView name, surname, id, department, group, phone, email, birthDate, educationStatus, academicStatus, gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_student, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.student_name);
        surname = view.findViewById(R.id.student_surname);
        id =view.findViewById(R.id.student_id);
        department = view.findViewById(R.id.student_department);
        group =view.findViewById(R.id.student_group);
        phone = view.findViewById(R.id.student_phone);
        email =view.findViewById(R.id.student_email);
        birthDate =view.findViewById(R.id.student_birthDate);
        educationStatus =view.findViewById(R.id.student_educationStatus);
        academicStatus =view.findViewById(R.id.student_academicStatus);
        gender =view.findViewById(R.id.student_gender);

        doAboutStudent();
    }

    private  void doAboutStudent(){
        IAUApi.getInstance(getActivity()).getStudentInfo(new IAUApi.OnStudentInfo() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onStudentInfo(StudentInfo studentInfo) {
                MainActivity activity = (MainActivity) getActivity();
                StudentInfo info = Objects.requireNonNull(activity).getStudentInfo();
                name.setText("Name: " + info.getName());
                surname.setText("Surname: " + info.getSurname());
                id.setText("Id: " + info.getId());
                department.setText("Department: " + info.getDepartment());
                group.setText("Group: " + info.getGroup());
                phone.setText("Phone: " + info.getPhone());
                email.setText("Email: " + info.getEmail());
                birthDate.setText("Birth Date: " + info.getBirthDate());
                educationStatus.setText("Education Status: " + info.getEducationStatus());
                academicStatus.setText("Academic Status: " + info.getAcademicStatus());
                gender.setText("Gender: " + info.getGender());
            }

            @Override
            public void onStudentInfoError(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}

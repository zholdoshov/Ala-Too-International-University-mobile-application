package zholdoshov.ala_tooexample.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import zholdoshov.ala_tooexample.MainActivity;
import zholdoshov.ala_tooexample.R;
import zholdoshov.ala_tooexample.model.StudentInfo;

public class AboutStudentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_student, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView name = view.findViewById(R.id.student_name);
        TextView surname = view.findViewById(R.id.student_surname);
        TextView id =view.findViewById(R.id.student_id);
        TextView department = view.findViewById(R.id.student_department);
        TextView group =view.findViewById(R.id.student_group);
        TextView phone = view.findViewById(R.id.student_phone);
        TextView email =view.findViewById(R.id.student_email);
        TextView birthDate =view.findViewById(R.id.student_birthDate);
        TextView educationStatus =view.findViewById(R.id.student_educationStatus);
        TextView academicStatus =view.findViewById(R.id.student_academicStatus);
        TextView gender =view.findViewById(R.id.student_gender);

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

}

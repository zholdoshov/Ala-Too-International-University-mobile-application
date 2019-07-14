package zholdoshov.ala_tooexample.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Objects;

import zholdoshov.ala_tooexample.Activities.MapsActivity;
import zholdoshov.ala_tooexample.R;

public class ContactsFragment extends Fragment/* implements View.OnClickListener*/ {

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton tV = Objects.requireNonNull(getActivity()).findViewById(R.id.findUs);
        tV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.findUs) {
                    Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    /*@Override
    public void onClick(View v) {
        if (v.getId() == R.id.findUs) {
            Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), MapsActivity.class);
            startActivity(intent);
        }
    }*/

}

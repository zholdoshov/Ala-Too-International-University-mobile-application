package zholdoshov.ala_tooexample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import zholdoshov.ala_tooexample.Activities.MapsActivity;
import zholdoshov.ala_tooexample.R;

public class ContactsFragment extends Fragment implements View.OnClickListener {

    Button findUs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        findUs = v.findViewById(R.id.findUs);

        findUs.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }
}

package ala_too.mob_app.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ala_too.mob_app.R;
import ala_too.mob_app.TranscriptAdapter;
import ala_too.mob_app.model.Transcript;
import ala_too.mob_app.remote.IAUApi;

public class TranscriptFragment extends Fragment {

    private static final String TAG = "TranscriptFragment";
    private TranscriptAdapter transcriptAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transcript, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        transcriptAdapter = new TranscriptAdapter();
        rv.setAdapter(transcriptAdapter);

        doTranscript();
    }

    private void doTranscript() {
        IAUApi.getInstance(getActivity()).getTranscript(new IAUApi.OnTranscript() {
            @Override
            public void onTranscript(Transcript transcript) {
                String message = "Total # of subjects: " + transcript.getMarks().size();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                transcriptAdapter.setItems(transcript.getMarks());
            }

            @Override
            public void onTranscriptError(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
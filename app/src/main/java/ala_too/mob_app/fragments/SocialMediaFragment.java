package ala_too.mob_app.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ala_too.mob_app.R;

public class SocialMediaFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_social_media, container, false);
        v.findViewById(R.id.fL).setOnClickListener(this);
        v.findViewById(R.id.tL).setOnClickListener(this);
        v.findViewById(R.id.iL).setOnClickListener(this);
        v.findViewById(R.id.yL).setOnClickListener(this);
        v.findViewById(R.id.vL).setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fL) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/AlaTooInternationalUniversity/"));
            startActivity(Intent.createChooser(intent, "Choose app to open"));
        }
        if (v.getId() == R.id.tL) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mobile.twitter.com/alatooedukg/"));
            startActivity(Intent.createChooser(intent, "Choose app to open"));
        }
        if (v.getId() == R.id.iL) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/alatoo.edu.kg/"));
            startActivity(Intent.createChooser(intent, "Choose app to open"));
        }
        if (v.getId() == R.id.yL) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCRwPUKmeoiUausG3luv7d3Q/"));
            startActivity(Intent.createChooser(intent, "Choose app to open"));
        }
        if (v.getId() == R.id.vL) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.vk.com/aiuedukg"));
            startActivity(Intent.createChooser(intent, "Choose app to open"));
        }
    }
}

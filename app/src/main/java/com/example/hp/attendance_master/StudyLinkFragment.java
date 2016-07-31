package com.example.hp.attendance_master;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudyLinkFragment extends Fragment {


    public StudyLinkFragment() {
        // Required empty public constructor
    }

    AlertDialog.Builder builder;
    ImageView imageView[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AllThingsActivity) getActivity()).getSupportActionBar().setTitle(
                "Some Useful Links");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        return inflater.inflate(R.layout.fragment_study_link, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView = new ImageView[4];
        imageView[0] = (ImageView)getActivity().findViewById(R.id.tutpoint);
        imageView[1] = (ImageView)getActivity().findViewById(R.id.nptel);
        imageView[2] = (ImageView)getActivity().findViewById(R.id.geeks);
        imageView[3] = (ImageView)getActivity().findViewById(R.id.cousera);

        imageView[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tutorialspoint.com/"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        imageView[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nptel.ac.in/"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        imageView[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.geeksforgeeks.org/"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        imageView[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coursera.org/"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
    }
}

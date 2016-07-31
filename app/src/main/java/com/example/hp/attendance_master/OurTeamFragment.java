package com.example.hp.attendance_master;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OurTeamFragment extends Fragment {
    Button b[];

    public OurTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AllThingsActivity) getActivity()).getSupportActionBar().setTitle(
                "Our Team");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        return inflater.inflate(R.layout.fragment_our_team, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        b = new Button[12];
        b[0] = (Button) getActivity().findViewById(R.id.button_fn);
        b[1] = (Button) getActivity().findViewById(R.id.button_tn);
        b[2] = (Button) getActivity().findViewById(R.id.button_gn);
        b[3] = (Button) getActivity().findViewById(R.id.button_f_av);
        b[4] = (Button) getActivity().findViewById(R.id.button_tav);
        b[5] = (Button) getActivity().findViewById(R.id.button_gav);
        b[6] = (Button) getActivity().findViewById(R.id.button_fak);
        b[7] = (Button) getActivity().findViewById(R.id.button_tak);
        b[8] = (Button) getActivity().findViewById(R.id.button_gak);
        b[9] = (Button) getActivity().findViewById(R.id.button_fr);
        b[10] = (Button) getActivity().findViewById(R.id.button2);
        b[11] = (Button) getActivity().findViewById(R.id.button3);
        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/nsniteshsahni"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/nsniteshsahni"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/+NiteshSahni007/posts"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("about:blank"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("about:blank"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("about:blank"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/profile.php?id=100010299584593&tsid=0.47418042026964125"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/akshats063"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/102506241364926312626/posts"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/rishabhvidhani"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/rishabhvidhani"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });
        b[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/101114077899841178099/posts"));
                Intent chooser = Intent.createChooser(intent, "Choose a Way to open link");
                startActivity(chooser);
            }
        });

    }
}

package com.example.akshat.attendence;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LinkActivity extends AppCompatActivity {

    Button b1, b2;
    AlertDialog.Builder builder;
    ImageView imageView[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        b1 = (Button) findViewById(R.id.button3);
        b2 = (Button) findViewById(R.id.button4);
        imageView = new ImageView[4];
        imageView[0] = (ImageView) findViewById(R.id.tutpoint);
        imageView[1] = (ImageView) findViewById(R.id.nptel);
        imageView[2] = (ImageView) findViewById(R.id.geeks);
        imageView[3] = (ImageView) findViewById(R.id.cousera);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LinkActivity.this, HomeActivity.class));
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(LinkActivity.this);
                builder.setMessage("Are you sure you want to exit?").setTitle("Confirm Exit");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent j = new Intent(Intent.ACTION_MAIN);
                        j.addCategory(Intent.CATEGORY_HOME);
                        j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(j);
                        finish();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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

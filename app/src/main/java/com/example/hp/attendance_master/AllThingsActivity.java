package com.example.hp.attendance_master;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AllThingsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar = null;
    NavigationView navigationView = null;
    AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_things);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, homeFragment);
        fragmentTransaction.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(AllThingsActivity.this);
                LayoutInflater inflater = AllThingsActivity.this.getLayoutInflater();
                final View view1 = inflater.inflate(R.layout.mail_us, null);
                builder.setView(view1);
                builder.setPositiveButton("Mail Us", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText e = (EditText) view1.findViewById(R.id.editText3);
                        final String content = e.getText().toString();
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:hacksters@googlegroups.com"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Problems and Suggestion to You");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
                        try {
                            startActivity(Intent.createChooser(emailIntent, "My Problems and Suggestion to You"));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(AllThingsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                builder.show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.all_things, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pick a Color")
                    .setItems(R.array.my_colors, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            android.support.v7.app.ActionBar bar = getSupportActionBar();
                            switch (which) {
                                case 0:
                                    bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 105, 180)));
                                    break;
                                case 1:
                                    bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(65, 105, 225)));
                                    break;
                                case 2:
                                    bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(127, 255, 0)));
                                    break;
                                case 3:
                                    bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 255, 0)));
                                    break;
                                case 4:
                                    bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 0, 0)));
                                    break;
                                case 5:
                                    bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 0, 0)));
                                    break;
                            }
                        }
                    });
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, homeFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_attendance) {
            android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
            alertDialog.setTitle("View IT Great");
            alertDialog.setMessage("Best Viewed in LANDSCAPE Mode\nTry it.....");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    AttendanceFragment attendanceFragment = new AttendanceFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, attendanceFragment);
                    fragmentTransaction.commit();
                }
            });
            alertDialog.show();

        } else if (id == R.id.nav_result) {
            android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
            alertDialog.setTitle("View IT Great");
            alertDialog.setMessage("Best Viewed in LANDSCAPE Mode\nTry it.....");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ResultFragment resultFragment = new ResultFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, resultFragment);
                    fragmentTransaction.commit();
                }
            });
            alertDialog.show();

        } else if (id == R.id.nav_study_link) {
            StudyLinkFragment studyLinkFragment = new StudyLinkFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, studyLinkFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_exit) {
            builder = new AlertDialog.Builder(AllThingsActivity.this);
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

        } else if (id == R.id.nav_share) {

            final TextView textView = new TextView(this);
            textView.setText(R.string.app_link);
            textView.setMovementMethod(LinkMovementMethod.getInstance()); // this is important to make the links clickable
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Share BPIT Attendance App")
                    .setPositiveButton("SHARE INFO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.setData(Uri.parse("mailto:hacksters@googlegroups.com"));
                            emailIntent.setType("text/plain");
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "BPIT Attendance App");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, "I am using an app named BPIT Attendance for our college BPIT to keep myself aware of my attendance.\nTry this app Now\n\n" + Html.fromHtml("<a href=\"https://drive.google.com/open?id=0BwNKV9VfKOJ5WkJLS1pMMVhQejQ\">Link To Download App</a>"));

                            try {
                                startActivity(Intent.createChooser(emailIntent, "Share Info Using....."));
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(AllThingsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setView(textView)
                    .create();
            alertDialog.show();

        } else if (id == R.id.nav_our_team) {
            OurTeamFragment ourTeamFragment = new OurTeamFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, ourTeamFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_renew_settings) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View view = inflater.inflate(R.layout.first_time_settings, null);
            builder.setView(view);
            builder.setPositiveButton("Remember this Data", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("attendance", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.clear();
                    editor.apply();
                    //editor.commit();
                    SharedPreferences sharedPref2 = getApplicationContext().getSharedPreferences("attendance", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPref2.edit();
                    EditText e = (EditText) view.findViewById(R.id.editText);
                    EditText e2 = (EditText) view.findViewById(R.id.editText2);
                    final String newName = e.getText().toString();
                    final String pass = e2.getText().toString();
                    editor2.putString("CredentialsLogin", newName);
                    editor2.putString("CredentialsPassword", pass);
                    editor2.apply();
                    //editor.commit();

                }
            })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            android.app.AlertDialog dialog = builder.create();
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

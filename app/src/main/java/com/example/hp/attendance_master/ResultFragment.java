package com.example.hp.attendance_master;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {


    public ResultFragment() {
        // Required empty public constructor
    }

    WebView webView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AllThingsActivity) getActivity()).getSupportActionBar().setTitle(
                "Result");
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView = (WebView) getActivity().findViewById(R.id.webView);
        if (isOnline()) {
            final SharedPreferences sharedPref = getActivity().getSharedPreferences("attendance", Context.MODE_PRIVATE);
            final String credentialsLoginId = sharedPref.getString("CredentialsLogin", null);
            final String credentialsPassword = sharedPref.getString("CredentialsPassword", null);
            if (credentialsLoginId != null && credentialsPassword != null) {
                //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                webView.loadUrl("https://ipuresult.com");
                webView.getSettings().setJavaScriptEnabled(true);

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (getActivity() != null) {
                            webView.loadUrl("javascript:( function() { document.getElementById('Roll_No').setAttribute('value', '" + credentialsLoginId + "');" +
                                    "document.getElementById('submit').click()})()");
                            webView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        super.onReceivedError(view, errorCode, description, failingUrl);
                        view.loadUrl("about:blank");
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        // AlertDialog alertDialog = new AlertDialog.Builder(getActivity());
                        alertDialog.setTitle("Ooops.....");
                        alertDialog.setMessage("Problem in connecting to server - may be your internet connection is too slow ");
                        // alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    }
                });

            } else {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                builder.setMessage("Go to Attendance option first and Fill your information").setTitle("Excited about Result");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AttendanceFragment attendanceFragment = new AttendanceFragment();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, attendanceFragment);
                        fragmentTransaction.commit();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        webView.loadUrl("https://ipuresult.com/");
                        webView.getSettings().setJavaScriptEnabled(true);

                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                webView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                super.onReceivedError(view, errorCode, description, failingUrl);
                                view.loadUrl("about:blank");
                                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                // AlertDialog alertDialog = new AlertDialog.Builder(getActivity());
                                alertDialog.setTitle("Ooops.....");
                                alertDialog.setMessage("Problem in connecting to server - may be your internet connection is too slow ");
                                // alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alertDialog.show();
                            }
                        });
                    }
                });
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Info");
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }

    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(getActivity(), "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

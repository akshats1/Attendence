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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


    public AttendanceFragment() {
        // Required empty public constructor
    }

    WebView mWebView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AllThingsActivity) getActivity()).getSupportActionBar().setTitle(
                "Attendance");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        return inflater.inflate(R.layout.fragment_attendance, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWebView = (WebView) getActivity().findViewById(R.id.web_view);
        if (isOnline()) {

            final SharedPreferences sharedPref = getActivity().getApplicationContext().getSharedPreferences("attendance",Context.MODE_PRIVATE);
            final String credentialsLoginId = sharedPref.getString("CredentialsLogin", null);
            final String credentialsPassword = sharedPref.getString("CredentialsPassword", null);
            if (credentialsLoginId != null && credentialsPassword != null) {
            /* JavaScript must be enabled if you want it to work, obviously */
                mWebView.loadUrl("http://bpit.markattendance.in/");
                mWebView.getSettings().setJavaScriptEnabled(true);

            /* WebViewClient must be set BEFORE calling loadUrl!*/
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        if (getActivity() != null) {
                            //hide loading image
                            //getActivity().findViewById(R.id.progressbar).setVisibility(View.GONE);

                    /* This call inject JavaScript into the page which just finished loading.*/
                            mWebView.loadUrl("javascript:( function() { document.getElementsByClassName('form-control placeholder-no-fix')[0].setAttribute('value', '" + credentialsLoginId + "');" +
                                    "document.getElementsByClassName('form-control placeholder-no-fix')[1].setAttribute('value', '" + credentialsPassword + "');" +
                                    "document.getElementsByClassName('btn blue pull-right')[0].click()})()");
                            //show webview
                            getActivity().findViewById(R.id.web_view).setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
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
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }

                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        if (view.getUrl().equalsIgnoreCase("http://bpit.markattendance.in/pwd.php")) {
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.clear();
                            editor.apply();
                            //editor.commit();
                        } else if (view.getUrl().equalsIgnoreCase("http://bpit.markattendance.in/s_current_month.php")) {
                            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        }
                        return super.shouldOverrideUrlLoading(view, request);
                    }
                });

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View view = inflater.inflate(R.layout.first_time_settings, null);
                builder.setView(view);
                builder.setPositiveButton("REMEMBER ME", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText e = (EditText) view.findViewById(R.id.editText);
                        EditText e2 = (EditText) view.findViewById(R.id.editText2);
                        final String newName = e.getText().toString();
                        final String pass = e2.getText().toString();
                        mWebView = (WebView) getActivity().findViewById(R.id.web_view);

            /* JavaScript must be enabled if you want it to work, obviously */
                        mWebView.loadUrl("http://bpit.markattendance.in/");
                        mWebView.getSettings().setJavaScriptEnabled(true);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("CredentialsLogin", newName);
                        editor.putString("CredentialsPassword",pass);
                        editor.apply();
                        //editor.commit();

            /* WebViewClient must be set BEFORE calling loadUrl!*/
                        mWebView.setWebViewClient(new WebViewClient() {

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                if (getActivity() != null) {
                                    //hide loading image
                                    //getActivity().findViewById(R.id.progressbar).setVisibility(View.GONE);

                    /* This call inject JavaScript into the page which just finished loading.*/
                                    mWebView.loadUrl("javascript:( function() { document.getElementsByClassName('form-control placeholder-no-fix')[0].setAttribute('value', '" + newName + "');" +
                                            "document.getElementsByClassName('form-control placeholder-no-fix')[1].setAttribute('value', '" + pass + "');" +
                                            "document.getElementsByClassName('btn blue pull-right')[0].click()})()");
                                    //show webview
                                    getActivity().findViewById(R.id.web_view).setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
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
                                super.onReceivedError(view, errorCode, description, failingUrl);
                            }

                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                if (view.getUrl().equalsIgnoreCase("http://bpit.markattendance.in/pwd.php")) {
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.clear();
                                    editor.apply();
                                    //editor.commit();
                                } else if (view.getUrl().equalsIgnoreCase("http://bpit.markattendance.in/s_current_month.php")) {
                                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                }
                                return super.shouldOverrideUrlLoading(view, request);
                            }
                        });
                    }
                })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mWebView = (WebView) getActivity().findViewById(R.id.web_view);

            /* JavaScript must be enabled if you want it to work, obviously */
                                mWebView.loadUrl("http://bpit.markattendance.in/");
                                mWebView.getSettings().setJavaScriptEnabled(true);

            /* WebViewClient must be set BEFORE calling loadUrl!*/
                                mWebView.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public void onPageFinished(WebView view, String url) {

                                        if (getActivity() != null) {
                                            //hide loading image
                                            //getActivity().findViewById(R.id.progressbar).setVisibility(View.GONE);
                                            //show webview
                                            getActivity().findViewById(R.id.web_view).setVisibility(View.VISIBLE);
                                        }
                                    }

                                    @Override
                                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
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
                                        super.onReceivedError(view, errorCode, description, failingUrl);
                                    }

                                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                        if (view.getUrl().equalsIgnoreCase("http://bpit.markattendance.in/pwd.php")) {
                                            SharedPreferences.Editor editor = sharedPref.edit();
                                            editor.clear();
                                            editor.apply();
                                            //editor.commit();
                                        } else if (view.getUrl().equalsIgnoreCase("http://bpit.markattendance.in/s_current_month.php")) {
                                            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                        }
                                        return super.shouldOverrideUrlLoading(view, request);
                                    }
                                });
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            //db.close();

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

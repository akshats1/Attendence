package com.example.akshat.attendence;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //  private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isOnline()) {
            // private boolean isOnline(){


            final SQLiteDatabase db = openOrCreateDatabase("BPIT", MODE_WORLD_WRITEABLE, null);
            db.execSQL("create table if not exists Attendance(loginid varchar(20),password varchar(20))");

            String q = "select * from Attendance ";
            final Cursor c = db.rawQuery(q, null);
            final String loginid, password;
            if (c.moveToFirst()) {
                loginid = c.getString(0);
                password = c.getString(1);
                mWebView = (WebView) findViewById(R.id.activity_main_webview);

            /* JavaScript must be enabled if you want it to work, obviously */
                mWebView.loadUrl("http://bpit.markattendance.in/");
                mWebView.getSettings().setJavaScriptEnabled(true);

            /* WebViewClient must be set BEFORE calling loadUrl!*/
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        //hide loading image
                        findViewById(R.id.progressBar1).setVisibility(View.GONE);
                        //show webview
                        findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
                    /* This call inject JavaScript into the page which just finished loading.*/
                        mWebView.loadUrl("javascript:( function() { document.getElementsByClassName('form-control placeholder-no-fix')[0].setAttribute('value', '" + loginid + "');" +
                                "document.getElementsByClassName('form-control placeholder-no-fix')[1].setAttribute('value', '" + password + "')})()");
                    }

                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        view.loadUrl("about:blank");
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        // AlertDialog alertDialog = new AlertDialog.Builder(getActivity());
                        alertDialog.setTitle("Ooops.....");
                        alertDialog.setMessage("Problem in connecting to server - may be your internet connection is too slow ");
                        // alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        alertDialog.show();
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }
                });

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.first_time_settings, null);
                builder.setView(view);
                builder.setPositiveButton("REMEMBER ME", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText e = (EditText) view.findViewById(R.id.editText);
                        EditText e2 = (EditText) view.findViewById(R.id.editText2);
                        final String newName = e.getText().toString();
                        final String pass = e2.getText().toString();
                        db.execSQL("insert into Attendance values('" + newName + "','" + pass + "')");
                        mWebView = (WebView) findViewById(R.id.activity_main_webview);

            /* JavaScript must be enabled if you want it to work, obviously */
                        mWebView.loadUrl("http://bpit.markattendance.in/");
                        mWebView.getSettings().setJavaScriptEnabled(true);

            /* WebViewClient must be set BEFORE calling loadUrl!*/
                        mWebView.setWebViewClient(new WebViewClient() {

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                //hide loading image
                                findViewById(R.id.progressBar1).setVisibility(View.GONE);
                                //show webview
                                findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
                    /* This call inject JavaScript into the page which just finished loading.*/
                                mWebView.loadUrl("javascript:( function() { document.getElementsByClassName('form-control placeholder-no-fix')[0].setAttribute('value', '" + newName + "');" +
                                        "document.getElementsByClassName('form-control placeholder-no-fix')[1].setAttribute('value', '" + pass + "')})()");
                            }

                            @Override
                            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                view.loadUrl("about:blank");
                                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                // AlertDialog alertDialog = new AlertDialog.Builder(getActivity());
                                alertDialog.setTitle("Ooops.....");
                                alertDialog.setMessage("Problem in connecting to server - may be your internet connection is too slow ");
                                // alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                alertDialog.show();
                                super.onReceivedError(view, errorCode, description, failingUrl);
                            }
                        });
                    }
                })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mWebView = (WebView) findViewById(R.id.activity_main_webview);

            /* JavaScript must be enabled if you want it to work, obviously */
                                mWebView.loadUrl("http://bpit.markattendance.in/");
                                mWebView.getSettings().setJavaScriptEnabled(true);

            /* WebViewClient must be set BEFORE calling loadUrl!*/
                                mWebView.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        //hide loading image
                                        findViewById(R.id.progressBar1).setVisibility(View.GONE);
                                        //show webview
                                        findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                        view.loadUrl("about:blank");
                                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                        // AlertDialog alertDialog = new AlertDialog.Builder(getActivity());
                                        alertDialog.setTitle("Ooops.....");
                                        alertDialog.setMessage("Problem in connecting to server - may be your internet connection is too slow ");
                                        // alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        });
                                        alertDialog.show();
                                        super.onReceivedError(view, errorCode, description, failingUrl);
                                    }
                                });
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            //db.close();

        } else {

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            // AlertDialog alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Info");
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
            // alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });

            alertDialog.show();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private ShareActionProvider mShareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /** Inflating the current activity's menu with res/menu/items.xml */
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /** Getting the actionprovider associated with the menu item whose id is share */
        mShareActionProvider = (ShareActionProvider) menu.findItem(R.id.share).getActionProvider();

        /** Setting a share intent */
        mShareActionProvider.setShareIntent(getDefaultShareIntent());

        return super.onCreateOptionsMenu(menu);

    }

    /**
     * Returns a share intent
     */
    private Intent getDefaultShareIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        return intent;


    }


    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.akshat.attendence/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.akshat.attendence/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}




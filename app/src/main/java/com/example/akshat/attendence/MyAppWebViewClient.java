package com.example.akshat.attendence;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.AvoidXfermode;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by akshat on 16-06-2016.
 */
public class MyAppWebViewClient extends WebViewClient

{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        /*String myurl = view.getUrl();
        if (myurl.equalsIgnoreCase("http://bpit.markattendance.in/pwd.php"))
        {
            SQLiteDatabase db = ;
            db.execSQL("create table if not exists Attendance(loginid varchar(20),password varchar(20))");
            db.execSQL("drop table Attendance");
        }*/
        if(Uri.parse(url).getHost().endsWith("bpit.markattendance.in")) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }
}

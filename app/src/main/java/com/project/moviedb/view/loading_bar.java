package com.project.moviedb.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.project.moviedb.R;

public class loading_bar {

    private AlertDialog alert;
    private Activity activity;

    loading_bar(Activity myActivity) {
        activity = myActivity;
    }

    void startLoadingDialog(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_menu, null));
        builder.setCancelable(true);

        alert = builder.create();
        alert.show();
    }

    void dismissDialog(){
        alert.dismiss();
    }


}

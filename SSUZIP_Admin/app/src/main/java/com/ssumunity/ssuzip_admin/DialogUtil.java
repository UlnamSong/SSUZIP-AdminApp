package com.ssumunity.ssuzip_admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ulnamsong on 2016. 11. 17..
 */

public class DialogUtil {

    // resultCode : 1 - OK, 2 - Yes, 3 - No
    public static Activity activity;
    public static int resultCode;

    public static void showDialog(Context mContext, String input_title, String input_content, int type, final int commandType){

        activity = (Activity) mContext;

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        TypefaceUtil.loadTypeface(mContext);

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        TextView title = (TextView) dialogView.findViewById(R.id.tv_dialog_title);
        TextView content = (TextView) dialogView.findViewById(R.id.tv_dialog_content);

        title.setTypeface(TypefaceUtil.typeface_m);
        content.setTypeface(TypefaceUtil.typeface_m);

        title.setText(input_title);
        content.setText(input_content);
        alertDialog.setView(dialogView);

        switch(type) {
            // OK
            case 1:
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton(mContext.getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        resultCode = 1;
                        switch(commandType) {
                            case 1:
                                // LoginActivity & MainActivity
                                break;
                            case 2:
                                // SignupActivity & AuthenticationActivity
                                break;
                            case 3:
                                break;
                        }
                        dialog.cancel(); // Your custom code
                    }
                });
                break;

            // Yes and No
            case 2:
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton(mContext.getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        resultCode = 2;
                        switch(commandType) {
                            case 1:
                                // LoginActivity & MainActivity -> back
                                activity.finish();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                break;
                            case 2:
                                // SignupActivity & AuthenticationActivity
                                break;
                            case 3:
                                // MainActivity
                                activity.startActivity(new Intent(activity, LoginActivity.class));
                                activity.overridePendingTransition(R.anim.fade, R.anim.hold);
                                activity.finish();
                                break;
                        }
                        dialog.cancel(); // Your custom code
                    }
                });

                alertDialog.setNegativeButton(mContext.getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        resultCode = 3;
                        switch(commandType) {
                            case 1:
                                // LoginActivity & MainActivity -> back
                                // Do Nothing
                                break;
                            case 2:
                                // SignupActivity & AuthenticationActivity
                                break;
                        }
                        dialog.cancel(); // Your custom code
                    }
                });
                break;
            // Ok and Cancel
            case 3:
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton(mContext.getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        resultCode = 2;
                        switch(commandType) {
                            case 1:
                                // LoginActivity & MainActivity -> back
                                activity.finish();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                break;
                            case 2:
                                // SignupActivity & AuthenticationActivity
                                break;
                        }
                        dialog.cancel(); // Your custom code
                    }
                });

                alertDialog.setNegativeButton(mContext.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        resultCode = 3;
                        switch(commandType) {
                            case 1:
                                // LoginActivity & MainActivity -> back
                                // Do Nothing
                                break;
                            case 2:
                                // SignupActivity & AuthenticationActivity
                                break;
                        }
                        dialog.cancel(); // Your custom code
                    }
                });
                break;
        }

        alertDialog.show();
    }
}

package com.ssumunity.ssuzip_admin;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Ulnamsong on 2016. 11. 17..
 */

public class TypefaceUtil {
    public static Typeface typeface    = null;
    public static Typeface typeface_m  = null;
    public static Typeface typeface_b  = null;
    public static Typeface typeface_lb = null;

    public static Typeface typeface_number_normal = null;
    public static Typeface typeface_number_bold = null;

    public static void loadTypeface(Context mContext) {
        if(typeface == null) {
            typeface = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.typeface_normal));
        }

        if(typeface_m == null) {
            typeface_m = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.typeface_bold));
        }

        if(typeface_b == null) {
            typeface_b = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.typeface_exbold));
        }

        if(typeface_lb == null) {
            typeface_lb = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.typeface_loginbot));
        }

        if(typeface_number_normal == null) {
            typeface_number_normal = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.typeface_number_normal));
        }

        if(typeface_number_bold == null) {
            typeface_number_bold = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.typeface_number_bold));
        }
    }
}

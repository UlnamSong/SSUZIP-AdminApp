package com.ssumunity.ssuzip_admin.Controller;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;

import java.util.List;

/**
 * Created by Ulnamsong on 2016. 11. 17..
 */

public class MySpinnerAdapter extends ArrayAdapter<String> {

    // Context
    private Context mContext;

    // Custom Font
    private Typeface font = null;

    // (In reality I used a manager which caches the Typeface objects)
    // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

    public MySpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        mContext = context;

        TypefaceUtil.loadTypeface(mContext);
        font = TypefaceUtil.typeface_m;
    }

    // Affects default (closed) state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(font);
        return view;
    }

    // Affects opened state of the spinner
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setTypeface(font);
        return view;
    }
}

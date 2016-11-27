package com.ssumunity.ssuzip_admin.Controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Data.CheckboxItem;
import com.ssumunity.ssuzip_admin.Data.CheckedArray;
import com.ssumunity.ssuzip_admin.R;

/**
 * Created by Ulnamsong on 2016. 11. 23..
 */

public class CheckboxListviewAdapter extends ArrayAdapter<CheckboxItem> {
    private Activity myContext;
    private CheckboxItem[] datas;

    private Typeface typeface      = null;
    private Typeface typeface_b    = null;
    private Typeface typeface_m    = null;

    public CheckboxListviewAdapter(Context context, int textViewResourceId, CheckboxItem[] objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        myContext = (Activity) context;
        datas = objects;
        typeface = Typeface.createFromAsset(myContext.getAssets(), myContext.getString(R.string.typeface_normal));
        typeface_m = Typeface.createFromAsset(myContext.getAssets(), myContext.getString(R.string.typeface_bold));
        typeface_b = Typeface.createFromAsset(myContext.getAssets(), myContext.getString(R.string.typeface_exbold));
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.checkbox_listitem, null);

        final CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.cbContent);
        TextView content = (TextView) rowView.findViewById(R.id.tvContent);
        content.setTypeface(typeface);

        checkbox.setChecked(datas[position].ischecked);
        content.setText(datas[position].name);

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox.isChecked()) {
                    CheckedArray.checkArrayParticipate[position] = true;
                } else {
                    CheckedArray.checkArrayParticipate[position] = false;
                }
            }
        });

        return rowView;
    }
}


package com.ssumunity.ssuzip_admin.Controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Data.EventData;
import com.ssumunity.ssuzip_admin.Data.RankData;
import com.ssumunity.ssuzip_admin.R;

/**
 * Created by Ulnamsong on 2016. 11. 27..
 */

public class RankItemAdapter extends ArrayAdapter<RankData> {
    private Activity myContext;
    private RankData[] datas;

    private Typeface typeface = null;
    private Typeface typeface_b = null;
    private Typeface typeface_m = null;

    public RankItemAdapter(Context context, int textViewResourceId, RankData[] objects) {
        super(context, textViewResourceId, objects);

        myContext = (Activity) context;
        datas = objects;
        typeface = Typeface.createFromAsset(myContext.getAssets(), myContext.getString(R.string.typeface_normal));
        typeface_m = Typeface.createFromAsset(myContext.getAssets(), myContext.getString(R.string.typeface_bold));
        typeface_b = Typeface.createFromAsset(myContext.getAssets(), myContext.getString(R.string.typeface_exbold));
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.rank_item, null);

        //tvRank, tvName, tvMajor
        TextView rankView = (TextView) rowView.findViewById(R.id.tvRank);
        rankView.setTypeface(typeface_m);

        TextView nameView = (TextView) rowView.findViewById(R.id.tvName);
        nameView.setTypeface(typeface);

        TextView majorView = (TextView) rowView.findViewById(R.id.tvMajor);
        majorView.setTypeface(typeface);

        int rank = position + 1;

        switch(rank) {
            case 1:
                rankView.setText(rank + myContext.getString(R.string.event_results_rank_first_token));
                break;
            case 2:
                rankView.setText(rank + myContext.getString(R.string.event_results_rank_second_token));
                break;
            case 3:
                rankView.setText(rank + myContext.getString(R.string.event_results_rank_third_token));
                break;
            default:
                rankView.setText(rank + myContext.getString(R.string.event_results_rank_extra_token));
                break;
        }

        nameView.setText(datas[position].rankName);
        majorView.setText(datas[position].rankMajor);

        return rowView;
    }
}
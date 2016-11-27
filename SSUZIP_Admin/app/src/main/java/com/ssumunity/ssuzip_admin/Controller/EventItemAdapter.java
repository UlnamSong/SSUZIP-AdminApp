package com.ssumunity.ssuzip_admin.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ssumunity.ssuzip_admin.Activity.EventDetailActivity;
import com.ssumunity.ssuzip_admin.Activity.EventListActivity;
import com.ssumunity.ssuzip_admin.Data.EventData;
import com.ssumunity.ssuzip_admin.R;

/**
 * Created by Ulnamsong on 2016. 11. 22..
 */

public class EventItemAdapter extends ArrayAdapter<EventData> {
    private Activity myContext;
    private EventData[] datas;

    private Typeface typeface      = null;
    private Typeface typeface_b    = null;
    private Typeface typeface_m    = null;

    public EventItemAdapter(Context context, int textViewResourceId, EventData[] objects) {
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
        View rowView = inflater.inflate(R.layout.event_list_item, null);

        TextView tvYear = (TextView) rowView.findViewById(R.id.tvYear);
        tvYear.setTypeface(typeface);

        TextView tvMonthDay = (TextView) rowView.findViewById(R.id.tvMonthDay);
        tvMonthDay.setTypeface(typeface_b);

        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        tvTitle.setTypeface(typeface_m);

        TextView tvPerson = (TextView) rowView.findViewById(R.id.tvPerson);
        tvPerson.setTypeface(typeface);

        Button detailButton = (Button) rowView.findViewById(R.id.detail_result_button);
        detailButton.setTypeface(typeface_m);

        tvYear.setText(datas[position].year + myContext.getString(R.string.event_year_text));

        tvMonthDay.setText(datas[position].month + "/" + datas[position].day);
        tvTitle.setText(datas[position].title);
        tvPerson.setText(myContext.getString(R.string.event_inwon_text) + datas[position].totNumber
                + myContext.getString(R.string.event_myung) + " (" + myContext.getString(R.string.event_participate_text)
                + datas[position].curNumber + myContext.getString(R.string.event_myung) + ")");

        switch(datas[position].eventStatus) {
            case "0":
                // Normal
                tvMonthDay.setTextColor(myContext.getResources().getColor(R.color.colorPrimary));
                detailButton.setBackground(myContext.getResources().getDrawable(R.drawable.details_button_bg));
                detailButton.setText(myContext.getString(R.string.event_detail_button));
                detailButton.setTextColor(Color.BLACK);
                break;

            case "1":
                // Almost End
                tvMonthDay.setTextColor(myContext.getResources().getColor(R.color.colorRed));
                detailButton.setBackground(myContext.getResources().getDrawable(R.drawable.details_button_bg));
                detailButton.setText(myContext.getString(R.string.event_detail_button));
                detailButton.setTextColor(Color.BLACK);
                break;

            case "2":
                // End
                tvMonthDay.setTextColor(myContext.getResources().getColor(R.color.colorGrey70));
                detailButton.setBackground(myContext.getResources().getDrawable(R.drawable.results_button_bg));
                detailButton.setText(myContext.getString(R.string.event_result_button));
                detailButton.setTextColor(myContext.getResources().getColor(R.color.colorRed));
                break;

        }
        
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(datas[position].eventStatus) {
                    case "0":
                    case "1":
                        //Toast.makeText(myContext, "Details, Position : " + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(myContext, EventDetailActivity.class);
                        intent.putExtra("title", datas[position].title);
                        intent.putExtra("maxperson", datas[position].totNumber);
                        intent.putExtra("year", datas[position].year);
                        intent.putExtra("month", datas[position].month);
                        intent.putExtra("day", datas[position].day);
                        intent.putExtra("content", datas[position].content);
                        intent.putExtra("curperson", datas[position].curNumber);

                        myContext.startActivity(intent);
                        myContext.overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                    
                    case "2":
                        Toast.makeText(myContext, "Results, Position : " + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return rowView;
    }
}

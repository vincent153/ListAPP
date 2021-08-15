package com.example.listapps;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private Activity mActivity;
    private ArrayList<MainActivity.PInfo> mInfos;
    public CustomAdapter(Context context, Activity activity,ArrayList<MainActivity.PInfo> infos) {
        mContext = context;
        mActivity = activity;
        mInfos = infos;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return mInfos.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return mInfos.get(arg0);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View row;
        row = inflater.inflate(R.layout.row, parent, false);
        MainActivity.PInfo info =(MainActivity.PInfo) mInfos.get(position);

        TextView title;
        ImageView icon;
        Spinner spinner = (Spinner) row.findViewById(R.id.planets_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.mode_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mSpinnerItems);

        spinner.setAdapter(adapter);
        icon = (ImageView) row.findViewById(R.id.imgIcon);
        title = (TextView) row.findViewById(R.id.txtTitle);
        title.setText(info.appname);

        icon.setImageDrawable(info.icon);

        return (row);
    }
}
